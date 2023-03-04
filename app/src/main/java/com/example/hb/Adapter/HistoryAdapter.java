package com.example.hb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txvTenTruyen,txvTenChap;
        ImageView imgTruyen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTenChap=itemView.findViewById(R.id.txvtenChapHistory);
            txvTenTruyen=itemView.findViewById(R.id.txvTenTruyenHistory);
            imgTruyen = itemView.findViewById(R.id.imgHistory);
        }
    }
}
