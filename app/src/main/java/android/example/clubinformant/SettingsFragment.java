package android.example.clubinformant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    FirebaseAuth user;
    Bundle bundle;
    private TextView userName;
    private MaterialCardView basicInfoBtn;
    private MaterialCardView contactInfoBtn;
    private MaterialCardView aboutAppBtn;
    private MaterialCardView logOutBtn;
    private MaterialCardView changeCredentialsBtn;
    private CircleImageView profilePicture;
    private StorageReference storageReference;
    private Uri imageUri;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                profilePicture.setImageURI(result);
                imageUri = result;
            }
            MaterialAlertDialogBuilder confirmPicUpload = new MaterialAlertDialogBuilder(getContext());
            confirmPicUpload.setMessage("Do you want to upload this image?")
                    .setPositiveButton("Yes", (dialog, which) -> uploadInfo())
                    .setNegativeButton("No", (dialog, which) -> {

                    }).show();
        }
    });

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
        profilePicture.setOnClickListener(this);
        //Check if the full name is fetch from database
        if (bundle.getString("fullName") == null) {
            userName.setText("Welcome, user!");
        } else {
            userName.setText(bundle.getString("fullName"));
        }
        checkStatus();
        return view;
    }

    private void checkStatus() {
        DatabaseReference statusReference = FirebaseDatabase.getInstance().getReference("Users/" + user.getCurrentUser().getUid());
        statusReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    storageReference = FirebaseStorage.getInstance("gs://sti-club-informant.appspot.com").getReference(snapshot.child("imageUrl").getValue().toString());

                    GlideApp.with(getContext())
                            .load(storageReference)
                            .placeholder(R.drawable.select_image)
                            .into(profilePicture);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void initWidgets(View view) {
        userName = view.findViewById(R.id.tv_user_name);
        basicInfoBtn = view.findViewById(R.id.card_basic_info);
        contactInfoBtn = view.findViewById(R.id.card_contact_info);
        aboutAppBtn = view.findViewById(R.id.card_about_app);
        logOutBtn = view.findViewById(R.id.card_log_out);
        changeCredentialsBtn = view.findViewById(R.id.card_change_credentials);
        bundle = this.getArguments();
        user = FirebaseAuth.getInstance();
        profilePicture = view.findViewById(R.id.iv_profile_picture);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile_picture:
                Log.d("TAG", "onClick: YES");
                mGetContent.launch("image/*");
                break;
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

    private void uploadInfo() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/" + user.getCurrentUser().getUid());
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("status").getValue().equals("Student")) {
                    StorageReference storageReference = firebaseStorage.getReference("images/students/" + user.getCurrentUser().getUid());
                    storageReference.putFile(imageUri);
                } else {
                    StorageReference storageReference = firebaseStorage.getReference("images/teachers/" + user.getCurrentUser().getUid());
                    storageReference.putFile(imageUri);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}