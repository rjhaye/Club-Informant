package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherRegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration_form);
    }

    public void initWidgets() {

    }

    public void signUpBtn(View view) {

    }

    public void goToSignInBtn(View view) {
        Intent signInActivity = new Intent(TeacherRegistrationForm.this, SignInActivity.class);
        startActivity(signInActivity);
    }
}