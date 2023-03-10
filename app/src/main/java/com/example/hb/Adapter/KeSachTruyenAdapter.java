package com.example.hb.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.hb.Object.TruyenKeSach;
import com.example.hb.R;

import java.util.ArrayList;
import java.util.List;

public class KeSachTruyenAdapter extends ArrayAdapter<TruyenKeSach> {

    private Context ct;
    private ArrayList<TruyenKeSach> arr;

    public KeSachTruyenAdapter(@NonNull FragmentActivity context, int resource, @NonNull List<TruyenKeSach> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(R.layout.item_truyen_ke_sach,null);
        }
        if(arr.size()>0){
            TruyenKeSach truyen = this.arr.get(position);
            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyen);
            TextView tenTenChap = convertView.findViewById(R.id.txvtenChap);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnhTruyen);

            tenTenTruyen.setText(truyen.getTenTruyen());
            tenTenChap.setText(truyen.getTenChap());
            Glide.with(this.ct).load(truyen.getLinkAnh()).into(imgAnhTruyen);
        }
        return convertView;
    }
}
