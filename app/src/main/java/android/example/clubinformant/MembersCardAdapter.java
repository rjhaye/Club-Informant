package android.example.clubinformant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersCardAdapter extends RecyclerView.Adapter<MembersCardAdapter.ViewHolder> {
    Context context;
    ArrayList<Student> students;

    public MembersCardAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_members, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student member = students.get(position);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users/" + member.getUid());
        holder.fullName.setText(member.getLastName() + ", " + member.getFirstName());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("imageUrl").exists()) {
                    String imageUrl = snapshot.child("imageUrl").getValue().toString();
                    StorageReference imageUrlRef = FirebaseStorage.getInstance("gs://sti-club-informant.appspot.com").getReference(imageUrl);
                    GlideApp.with(context)
                            .load(imageUrlRef)
                            .placeholder(R.drawable.select_image)
                            .apply(RequestOptions.skipMemoryCacheOf(true))
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                            .into(holder.profilePicture);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;
        private CircleImageView profilePicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.tv_full_name);
            profilePicture = itemView.findViewById(R.id.iv_members_profile_picture);
        }
    }
}
