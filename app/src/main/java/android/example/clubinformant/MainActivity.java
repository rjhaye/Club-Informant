package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialButton teacherSignUpBtn;
    private MaterialButton studentSignUpBtn;
    private MaterialButton signInBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
        } else {
            initWidgets();
            //Setting onCLickListeners
            teacherSignUpBtn.setOnClickListener(this);
            studentSignUpBtn.setOnClickListener(this);
            signInBtn.setOnClickListener(this);
        }



    }

    public void initWidgets() {
        teacherSignUpBtn = findViewById(R.id.btn_register_as_a_teacher);
        studentSignUpBtn = findViewById(R.id.btn_register_as_a_student);
        signInBtn = findViewById(R.id.btn_go_to_sign_in);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_as_a_teacher:
                Intent teacherRegistrationForm = new Intent(MainActivity.this, TeacherRegistrationForm.class);
                startActivity(teacherRegistrationForm);
                break;
            case R.id.btn_register_as_a_student:
                Intent studentRegistrationForm = new Intent(MainActivity.this, StudentRegistrationForm.class);
                startActivity(studentRegistrationForm);
                break;
            case R.id.btn_go_to_sign_in:
                Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signIn);
                break;
            default:
                break;
        }

    }

}