package com.example.travelholic.adapter;

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
import com.example.travelholic.helper.CircleTransform;
import com.example.travelholic.model.Notification;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationRecyclerViewAdapter extends RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    private final LayoutInflater inflater;
    private final List<Notification> notifications;

    public interface OnItemClickListener {
        void onAcceptClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NotificationRecyclerViewAdapter(Context context, List<Notification> notifications) {
        this.inflater = LayoutInflater.from(context);
        this.notifications = notifications;
    }

    @NonNull
    @NotNull
    @Override
    public NotificationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notification_block, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String url = "http://10.0.2.2/travelholic-app/server/" + notifications.get(position).getAvatar();
        Picasso.get().load(url).transform(new CircleTransform()).into(holder.ivAvatar);
        String content = notifications.get(position).getCreatorName() + " want to join " +
                notifications.get(position).getTourName();
        holder.tvContent.setText(content);
        if (notifications.get(position).getStatus().equals("joined")) {
            holder.btnAccept.setText(holder.itemView.getContext().getString(R.string.accepted));
            holder.btnAccept.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final TextView tvContent;
        private final Button btnAccept;

        public ViewHolder(@NonNull @NotNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_notif_avatar);
            tvContent = itemView.findViewById(R.id.tv_notif_content);
            btnAccept = itemView.findViewById(R.id.btn_accept);

            btnAccept.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION)
                        listener.onAcceptClick(position);
                }
            });
        }
    }
}
