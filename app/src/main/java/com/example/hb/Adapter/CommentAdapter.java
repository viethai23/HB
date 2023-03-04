package com.example.hb.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hb.Model.Comment;
import com.example.hb.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context mContext;
    private List<Comment> mData;

    public CommentAdapter(Context mContext, List<Comment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.item_container_received_comment, parent, false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if(mData.get(position).getUimg() != null)
            Glide.with(mContext).load(mData.get(position).getUimg()).into(holder.img_user);
        else
            holder.img_user.setImageResource(R.drawable.anime_avt);
        holder.u_name.setText(mData.get(position).getUname());
        holder.u_comment.setText(mData.get(position).getContent());
        holder.u_date.setText(timestampToString((Long) mData.get(position).getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        ImageView img_user;
        TextView u_name, u_comment, u_date;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            img_user = itemView.findViewById(R.id.imageProfile);
            u_name = itemView.findViewById(R.id.textName);
            u_comment = itemView.findViewById(R.id.textComment);
            u_date = itemView.findViewById(R.id.textDateTime);
        }
    }

    private String timestampToString(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String date = sdf.format(calendar.getTime());
        return date;
    }
}
