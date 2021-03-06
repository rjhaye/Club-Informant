package android.example.clubinformant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherRegistrationForm extends AppCompatActivity implements View.OnClickListener {
    private EditText fname;
    private EditText lname;
    private EditText eMail;
    private EditText password;
    private EditText registrationKey;
    private FirebaseAuth mAuth;
    private MaterialButton signUpBtn;
    private MaterialButton signInBtn;
    private CircleImageView profilePicture;
    private FirebaseStorage storage;
    private Uri imageUri;
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                profilePicture.setImageURI(result);
                imageUri = result;
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration_form);
        initWidgets();
        signUpBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
        profilePicture.setOnClickListener(this);

    }

    public void initWidgets() {
        fname = findViewById(R.id.et_teacher_fname_sign_up);
        lname = findViewById(R.id.et_teacher_lname_sign_up);
        eMail = findViewById(R.id.et_teacher_email_sign_up);
        password = findViewById(R.id.et_teacher_password_sign_up);
        registrationKey = findViewById(R.id.et_teacher_registration_key_sign_up);
        mAuth = FirebaseAuth.getInstance();
        signUpBtn = findViewById(R.id.btn_teacher_sign_up);
        signInBtn = findViewById(R.id.btn_teacher_sign_in);
        profilePicture = findViewById(R.id.iv_teacher_select_image);
        storage = FirebaseStorage.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_teacher_select_image:
                mGetContent.launch("image/*");
                break;
            case R.id.btn_teacher_sign_up:
                if (fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || eMail.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        registrationKey.getText().toString().isEmpty()) {
                    Toast.makeText(TeacherRegistrationForm.this, "Empty field detected.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(eMail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(TeacherRegistrationForm.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            saveInfo();
                            uploadImage();
                            Intent intent = new Intent(TeacherRegistrationForm.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                break;
            case R.id.btn_teacher_sign_in:
                Intent signInActivity = new Intent(TeacherRegistrationForm.this, SignInActivity.class);
                startActivity(signInActivity);
                break;
            default:
                break;
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference storageReference = storage.getReference("images/teachers/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            storageReference.putFile(imageUri);
        }
    }

    public void saveInfo() {
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        Teacher newUser;
        if (imageUri != null) {
            newUser = new Teacher(fname.getText().toString(), lname.getText().toString(), eMail.getText().toString(), "Teacher",
                    FirebaseAuth.getInstance().getCurrentUser().getUid(), "images/teachers/");
        } else {
            newUser = new Teacher(fname.getText().toString(), lname.getText().toString(), eMail.getText().toString(), "Teacher",
                    FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        userReference.setValue(newUser);
    }
}