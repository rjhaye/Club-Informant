package android.example.clubinformant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentRegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_form);
    }

    public void initWidgets() {

    }

    public void signUpBtn(View view) {

    }

    public void goToSignInBtn(View view) {
        Intent signInActivity = new Intent(StudentRegistrationForm.this, SignInActivity.class);
        startActivity(signInActivity);
    }
}