package com.example.travelholic.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelholic.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Tour> tours;

    public TourRecyclerViewAdapter(Context context, List<Tour> tours) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.tours = tours;
    }

    @NonNull
    @NotNull
    @Override
    public TourRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tour_info_block, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TourRecyclerViewAdapter.ViewHolder holder, int position) {
        String url = "http://10.0.2.2/travelholic-app/server/" + tours.get(position).getImage();
        Picasso.get().load(url).into(holder.ivAvatar);
        holder.tvName.setText(tours.get(position).getTourName());
        holder.tvDuring.setText(tours.get(position).getDuring());
        holder.tvStatus.setText(tours.get(position).getStatus());
        holder.tvType.setText(tours.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvDuring;
        private TextView tvStatus;
        private TextView tvType;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_tour_name);
            tvDuring = itemView.findViewById(R.id.tv_during);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }
}