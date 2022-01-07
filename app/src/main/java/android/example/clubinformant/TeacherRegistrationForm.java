package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherRegistrationForm extends AppCompatActivity {
    private EditText fname;
    private EditText lname;
    private EditText eMail;
    private EditText password;
    private EditText registrationKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration_form);
        initWidgets();
    }

    public void initWidgets() {
        fname = findViewById(R.id.et_teacher_fname_sign_up);
        lname = findViewById(R.id.et_teacher_lname_sign_up);
        eMail = findViewById(R.id.et_teacher_email_sign_up);
        password = findViewById(R.id.et_teacher_password_sign_up);
        registrationKey = findViewById(R.id.et_teacher_registration_key_sign_up);
    }

    public void signUpBtn(View view) {

    }

    public void goToSignInBtn(View view) {
        Intent signInActivity = new Intent(TeacherRegistrationForm.this, SignInActivity.class);
        startActivity(signInActivity);
    }
}