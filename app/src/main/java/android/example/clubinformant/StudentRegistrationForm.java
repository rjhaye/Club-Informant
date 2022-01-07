package android.example.clubinformant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentRegistrationForm extends AppCompatActivity {
    private EditText studentId;
    private EditText fname;
    private EditText lname;
    private String club;
    private Spinner clubSpinner;
    private EditText eMail;
    private EditText password;
    private EditText registrationKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_form);
        initWidgets();
        implementSpinner();

    }

    public void initWidgets() {
        fname = findViewById(R.id.et_student_fname_sign_up);
        lname = findViewById(R.id.et_student_lname_sign_up);
        clubSpinner = findViewById(R.id.spinner_club_choices);
        eMail = findViewById(R.id.et_student_email_sign_up);
        password = findViewById(R.id.et_student_password_sign_up);
        registrationKey = findViewById(R.id.et_student_registration_key_sign_up);
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

    public void signUpBtn(View view) {

    }

    public void goToSignInBtn(View view) {
        Intent signInActivity = new Intent(StudentRegistrationForm.this, SignInActivity.class);
        startActivity(signInActivity);
    }
}