package android.example.clubinformant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentRegistrationForm extends AppCompatActivity implements View.OnClickListener {
    FirebaseStorage storage;
    Uri imageUri;
    private CircleImageView profilePicture;
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                profilePicture.setImageURI(result);
                imageUri = result;
            }
        }
    });
    private EditText studentId;
    private EditText fname;
    private EditText lname;
    private String club;
    private Spinner clubSpinner;
    private EditText eMail;
    private EditText password;
    private EditText registrationKey;
    private FirebaseAuth mAuth;
    private MaterialButton signUpBtn;
    private MaterialButton signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_form);
        initWidgets();
        implementSpinner();
        signUpBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
        profilePicture.setOnClickListener(this);

    }

    public void initWidgets() {
        studentId = findViewById(R.id.et_student_id_sign_up);
        fname = findViewById(R.id.et_student_fname_sign_up);
        lname = findViewById(R.id.et_student_lname_sign_up);
        clubSpinner = findViewById(R.id.spinner_club_choices);
        eMail = findViewById(R.id.et_student_email_sign_up);
        password = findViewById(R.id.et_student_password_sign_up);
        registrationKey = findViewById(R.id.et_student_registration_key_sign_up);
        mAuth = FirebaseAuth.getInstance();
        signUpBtn = findViewById(R.id.btn_student_sign_up);
        signInBtn = findViewById(R.id.btn_student_sign_in);
        profilePicture = findViewById(R.id.iv_student_select_image);
        storage = FirebaseStorage.getInstance();
    }

    public void implementSpinner() {
        ArrayList<String> clubs = new ArrayList<>();
        clubs.add("AZTECH CLUB");
        clubs.add("MAHARLIKA CLUB");
        clubs.add("PANDAYWIKA CLUB");
        clubs.add("TEATRO MILENYO CLUB");
        clubs.add("WE MATTER CLUB");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(StudentRegistrationForm.this,
                R.layout.custom_simple_spinner_item, clubs);
        adapter.setDropDownViewResource(R.layout.custom_simple_spinner_dropdown_item);
        clubSpinner.setAdapter(adapter);
        clubSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                club = clubs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_student_select_image:
                mGetContent.launch("image/*");
                break;
            case R.id.btn_student_sign_up:
                DatabaseReference clubsNumberReference = FirebaseDatabase.getInstance().getReference("Clubs/" + club + "/");
                if (studentId.getText().toString().isEmpty() || fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || eMail.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty() || registrationKey.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Empty field detected.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //This will check the number of clubs. Toast will be displayed if it's full.
                clubsNumberReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() >= 180) {
                            Toast.makeText(StudentRegistrationForm.this, "This club is already full.", Toast.LENGTH_SHORT).show();
                        } else {
                            mAuth.createUserWithEmailAndPassword(eMail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        saveInfo();
                                        uploadImage();
                                        Toast.makeText(StudentRegistrationForm.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(StudentRegistrationForm.this, HomeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(StudentRegistrationForm.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });//End of checking
                break;
            case R.id.btn_student_sign_in:
                Intent signInActivity = new Intent(StudentRegistrationForm.this, SignInActivity.class);
                startActivity(signInActivity);
                break;
            default:
                break;
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference storageReference = storage.getReference("images/students/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            storageReference.putFile(imageUri);
        }
    }

    public void saveInfo() {
        //Saving info in Clubs directory
        DatabaseReference referenceByClub = FirebaseDatabase.getInstance().getReference("Clubs/" + club + "/" + (lname.getText().toString() + ", " +
                fname.getText().toString()));
        //Saving info in Users directory
        DatabaseReference referenceByUser = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/");
        //Adding the data
        Student newUser;
        if (imageUri != null) {
            newUser = new Student(studentId.getText().toString(), fname.getText().toString(), lname.getText().toString(), club, eMail.getText().toString(),
                    FirebaseAuth.getInstance().getCurrentUser().getUid(), "Student", "images/students/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            referenceByClub.setValue(newUser);
        } else {
            newUser = new Student(studentId.getText().toString(), fname.getText().toString(), lname.getText().toString(), club, eMail.getText().toString(),
                    FirebaseAuth.getInstance().getCurrentUser().getUid(), "Student");
            referenceByClub.setValue(newUser).addOnFailureListener(e -> Toast.makeText(StudentRegistrationForm.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
            //TODO: Kailangan ba ng addOnFailureLister?
        }
        referenceByUser.setValue(newUser);
    }
}