package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initWidgets() {

    }

    public void teacherRegisterBtn(View view) {
        Intent teacherRegistrationForm = new Intent(MainActivity.this, TeacherRegistrationForm.class);
        startActivity(teacherRegistrationForm);
    }

    public void studentRegisterBtn(View view) {
        Intent studentRegistrationForm = new Intent(MainActivity.this, StudentRegistrationForm.class);
        startActivity(studentRegistrationForm);
    }

    public void signInBtn(View view) {
        Intent signIn = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(signIn);
    }
}