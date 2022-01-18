package android.example.clubinformant;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomToast extends AppCompatActivity {

    public void createToast(Context context, String message, int duration) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_view, findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.tv_custom_toast_text);
        text.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

}
