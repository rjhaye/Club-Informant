package android.example.clubinformant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    Context context;
    ArrayList<Object> arrAnnounce;
    Button btnAct;
    EditText etrAnn;
    TextView txtTitle;

    AnnouncementAdapter(Context context, ArrayList<Object> arrAnnounce) {
        this.context = context;
        this.arrAnnounce = arrAnnounce;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.announcements_row, parent, false);


        return new ViewHolder(view);


    }

    private void initWidgets(View view) {

        etrAnn = view.findViewById(R.id.etrAnn);
        btnAct = view.findViewById(R.id.btnAct);
        txtTitle = view.findViewById(R.id.AnnouncementText);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        modelForAnnounce model = (modelForAnnounce) arrAnnounce.get(position);
        holder.txtField.setText(model.text);

        holder.Row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setView(R.layout.announcements_add_del_layout);
                dialog.show();
                txtTitle.setText("Update Announcement");
                btnAct.setText("Post");

                etrAnn.setText(((modelForAnnounce) arrAnnounce.get(position)).text);

                btnAct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Btn is clicked");
                        String text = "";

                        if (!etrAnn.getText().toString().isEmpty()) {
                            text = etrAnn.getText().toString();
                        } else {
                            Toast.makeText(context, "Text field is empty!", Toast.LENGTH_SHORT).show();

                        }
                        arrAnnounce.set(position, new modelForAnnounce(text));
                        notifyItemChanged(position);

                    }
                });

            }
        });
        holder.Row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Announcement")
                        .setMessage("Are you sure you want to delete this announcement?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrAnnounce.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAnnounce.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtField;
        LinearLayout Row;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtField = itemView.findViewById(R.id.AnnouncementText);
            Row = itemView.findViewById(R.id.Row);


        }


    }
}