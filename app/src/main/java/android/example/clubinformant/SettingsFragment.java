package android.example.clubinformant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private TextView userName;
    private CardView basicInfoBtn;
    private CardView contactInfoBtn;
    private CardView aboutAppBtn;
    private CardView logOutBtn;
    private CardView changeCredentialsBtn;
    FirebaseAuth user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initWidgets(view);
        //Setting onClickListeners
        basicInfoBtn.setOnClickListener(this);
        contactInfoBtn.setOnClickListener(this);
        aboutAppBtn.setOnClickListener(this);
        logOutBtn.setOnClickListener(this);
        changeCredentialsBtn.setOnClickListener(this);
        user = FirebaseAuth.getInstance();
        return view;
    }

    public void initWidgets(View view) {
        userName = view.findViewById(R.id.tv_user_name);
        basicInfoBtn = view.findViewById(R.id.card_basic_info);
        contactInfoBtn = view.findViewById(R.id.card_contact_info);
        aboutAppBtn = view.findViewById(R.id.card_about_app);
        logOutBtn = view.findViewById(R.id.card_log_out);
        changeCredentialsBtn = view.findViewById(R.id.card_change_credentials);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_basic_info:
                break;
            case R.id.card_contact_info:
                break;
            case R.id.card_about_app:
                break;
            case R.id.card_log_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to logout?").setPositiveButton("Yes, logout", (dialog, which) -> {
                    user.signOut();
                    ((Activity) getContext()).finish();
                    Intent mainIntent = new Intent(getContext(), MainActivity.class);
                    startActivity(mainIntent);
                }).setNegativeButton("No", (dialog, which) -> {
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.card_change_credentials:
                break;
            default:
                break;
        }
    }
}