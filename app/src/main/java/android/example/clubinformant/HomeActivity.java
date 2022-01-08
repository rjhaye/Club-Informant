package android.example.clubinformant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initWidgets();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MembersFragment()).commit();
        navigate();
    }

    public void initWidgets() {
        chipNavigationBar = findViewById(R.id.bottom_nav_bar);
        chipNavigationBar.setItemSelected(R.id.menu_home, true);
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
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}