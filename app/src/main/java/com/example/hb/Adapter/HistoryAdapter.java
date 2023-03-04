package com.example.hb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hb.Activities.ChapActivity;
import com.example.hb.Interfaces.ItemClickListener;
import com.example.hb.Object.TruyenLichSu;
import com.example.hb.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<TruyenLichSu> arr;
    private LayoutInflater inflater;

    public HistoryAdapter(Context context, ArrayList<TruyenLichSu> arr) {
        this.arr = arr;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_truyen_lich_su,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TruyenLichSu thisTruyen = arr.get(position);
        Glide.with(this.inflater.getContext()).load(thisTruyen.getTruyen().getLinkAnh()).into(holder.imgTruyen);
        holder.txvTenTruyen.setText(thisTruyen.getTenTruyen());
        holder.txvTenChap.setText(thisTruyen.getChapTruyen().get(thisTruyen.getCurrentChap()).getTenChap());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    //Intent intent = new Intent(this, ChapActivity.class);
                }else {

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView txvTenTruyen,txvTenChap;
        ImageView imgTruyen;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTenChap=itemView.findViewById(R.id.txvtenChapHistory);
            txvTenTruyen=itemView.findViewById(R.id.txvTenTruyenHistory);
            imgTruyen = itemView.findViewById(R.id.imgHistory);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getBindingAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getBindingAdapterPosition(),true);
            return true;
        }
    }
}
