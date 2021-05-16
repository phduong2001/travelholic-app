package com.example.travelholic.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelholic.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Notification> notifications;


    public NotificationRecyclerViewAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.notifications = notifications;
    }

    @NonNull
    @NotNull
    @Override
    public NotificationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notification_block, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String url = "http://10.0.2.2/travelholic-app/server/" + notifications.get(position).getAvatar();
        Picasso.get().load(url).into(holder.ivAvatar);
        if (notifications.get(position).getType().equals("apply")) {
            String content = notifications.get(position).getCreator() + " want to join " +
                    notifications.get(position).getTourName();
            holder.tvContent.setText(content);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvContent;
        private Button btnAccept;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_notif_avatar);
            tvContent = itemView.findViewById(R.id.tv_notif_content);
            btnAccept = itemView.findViewById(R.id.btn_accept);
        }
    }
}