package android.example.clubinformant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MembersFragment extends Fragment {

    RecyclerView customRecyclerView;
    DatabaseReference clubReference;
    DatabaseReference statusReference;
    DatabaseReference membersReference;
    ArrayList<Student> students;
    MembersCardAdapter membersCardAdapter;
    String club;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        initWidgets(view);
        customRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        customRecyclerView.setAdapter(membersCardAdapter);
        getStudentInfo();
        //Setting up the recyclerView
        return view;
    }

    private void initWidgets(View view) {
        customRecyclerView = view.findViewById(R.id.rv_members_recycler_view);
        clubReference = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/clubChoice");
        students = new ArrayList<>();
        membersCardAdapter = new MembersCardAdapter(getContext(), students);
        statusReference = FirebaseDatabase.getInstance().getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/status");
        membersReference = FirebaseDatabase.getInstance().getReference("Users/");
    }

    //This method is used to display the members by specific club.
    public void getStudentInfo() {
        statusReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("Student")) {
                    //Knowing what club
                    clubReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            club = snapshot.getValue().toString();
                            switch (club) {
                                case "AZTECH CLUB":
                                    DatabaseReference aztechReference = FirebaseDatabase.getInstance().getReference("Clubs/AZTECH CLUB");
                                    aztechReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Student studentInfo;
                                            for (DataSnapshot name : snapshot.getChildren()) {
                                                studentInfo = name.getValue(Student.class);
                                                students.add(studentInfo);
                                            }
                                            membersCardAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                                case "MAHARLIKA CLUB":
                                    DatabaseReference maharlikaReference = FirebaseDatabase.getInstance().getReference("Clubs/MAHARLIKA CLUB");
                                    maharlikaReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Student studentInfo;
                                            for (DataSnapshot name : snapshot.getChildren()) {
                                                studentInfo = name.getValue(Student.class);
                                                students.add(studentInfo);
                                            }
                                            membersCardAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                                case "PANDAYWIKA CLUB":
                                    DatabaseReference pandayWikaReference = FirebaseDatabase.getInstance().getReference("Clubs/PANDAYWIKA CLUB");
                                    pandayWikaReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Student studentInfo;
                                            for (DataSnapshot name : snapshot.getChildren()) {
                                                studentInfo = name.getValue(Student.class);
                                                students.add(studentInfo);
                                            }
                                            membersCardAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                                case "TEATRO MILENYO CLUB":
                                    DatabaseReference teatroMilenyoReference = FirebaseDatabase.getInstance().getReference("Clubs/TEATRO MILENYO CLUB");
                                    teatroMilenyoReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Student studentInfo;
                                            for (DataSnapshot name : snapshot.getChildren()) {
                                                studentInfo = name.getValue(Student.class);
                                                students.add(studentInfo);
                                            }
                                            membersCardAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                                case "WE MATTER CLUB":
                                    DatabaseReference weMatterReference = FirebaseDatabase.getInstance().getReference("Clubs/WE MATTER CLUB");
                                    weMatterReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Student studentInfo;
                                            for (DataSnapshot name : snapshot.getChildren()) {
                                                studentInfo = name.getValue(Student.class);
                                                students.add(studentInfo);
                                            }
                                            membersCardAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    membersReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Student studentInfo;
                            for (DataSnapshot name : snapshot.getChildren()) {
                                if (name.child("status").getValue().toString().equals("Student")) {
                                    studentInfo = name.getValue(Student.class);
                                    students.add(studentInfo);
                                } else {

                                }
                                membersCardAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}