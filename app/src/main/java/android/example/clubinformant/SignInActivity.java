package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText password;
    private TextView forgotPasswordBtn;
    private MaterialButton signInBtn;
    private MaterialButton signUpBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initWidgets();
        forgotPasswordBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    public void initWidgets() {
        email = findViewById(R.id.et_email_sign_in);
        password = findViewById(R.id.et_password_sign_in);
        signInBtn = findViewById(R.id.btn_sign_in);
        forgotPasswordBtn = findViewById(R.id.tv_forgot_password);
        signUpBtn = findViewById(R.id.btn_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Empty field detected.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Logged in successful!", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(homeIntent);
                        } else {
                            Toast.makeText(SignInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.tv_forgot_password:
                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_up:
                Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(mainActivity);
                break;
            default:
                break;
        }
    }
}