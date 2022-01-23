package android.example.clubinformant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initWidgets();
        getUserData();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MembersFragment()).commit();
        navigate();
    }

    public void initWidgets() {
        chipNavigationBar = findViewById(R.id.bottom_nav_bar);
        chipNavigationBar.setItemSelected(R.id.menu_home, true);
        bundle = new Bundle();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }


    private void navigate() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.menu_announcements:
                        fragment = new AnnouncementsFragment();
                        break;
                    case R.id.menu_home:
                        fragment = new MembersFragment();
                        break;
                    case R.id.menu_settings:
                        fragment = new SettingsFragment();
                        break;
                    case R.id.nav_search:
                        fragment = new MembersFragment();
                    default:
                        break;
                }
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }

    private void getUserData() {
        DatabaseReference userNameReference = FirebaseDatabase.getInstance().getReference("Users/" + user.getUid());
        userNameReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bundle.putString("fullName", snapshot.child("lastName").getValue().toString() + ", " + snapshot.child("firstName").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}