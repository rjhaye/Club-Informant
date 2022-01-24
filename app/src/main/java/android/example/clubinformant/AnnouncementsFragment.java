package android.example.clubinformant;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class AnnouncementsFragment extends Fragment {


    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnnouncementsFragment.
     */
    // TODO: Rename and change types and number of parameters


    ArrayList<Object> arrAnnounce = new ArrayList<>();
    RecyclerView recView;
    AnnouncementAdapter adapter;
    FloatingActionButton btnOpenDialog;
    Button btnAct;
    EditText etrAnn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);

        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {

        recView = view.findViewById(R.id.recviewAnn);
        btnOpenDialog = view.findViewById(R.id.btnOpenDialog);
        btnAct = view.findViewById(R.id.btnAct);
        etrAnn = view.findViewById(R.id.etrAnn);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setView(R.layout.announcements_add_del_layout);
                dialog.show();




            }
        });
        arrAnnounce.add(new modelForAnnounce("THIS IS A SAMPLE ANNOUNCEMENT"));
        arrAnnounce.add(new modelForAnnounce("SAMPLE ANNOUNCEMENT #2"));

        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AnnouncementAdapter(getContext(), arrAnnounce);
        recView.setAdapter(adapter);
    }


}