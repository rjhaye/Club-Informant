package android.example.clubinformant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void initWidgets() {

    }

    public void signInBtn(View view) {

    }

    public void goToSignUpBtn(View view) {
        Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }
}