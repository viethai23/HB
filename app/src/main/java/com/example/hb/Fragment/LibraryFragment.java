package com.example.hb.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hb.Adapter.KeSachTruyenAdapter;
import com.example.hb.Api.ApiLayTruyen;
import com.example.hb.Interfaces.LayTruyenVe;
import com.example.hb.Object.TruyenKeSach;
import com.example.hb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LibraryFragment extends Fragment implements LayTruyenVe {

    View view;
    GridView gdvListTruyen;
    KeSachTruyenAdapter adapter;
    ArrayList truyenArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        truyenArrayList = new ArrayList<>();
        adapter = new KeSachTruyenAdapter(getActivity(),0,truyenArrayList);
    }
    private void anhXa(){

        gdvListTruyen = view.findViewById(R.id.gdvListTruyen);
    }
    private void setUp(){

        gdvListTruyen.setAdapter(adapter);
    }
    private void setClick(){}

    @Override
    public void batDau() {
        Toast.makeText(getActivity(),"Đang Lấy Dữ Liệu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenArrayList.clear();
            JSONObject temp = new JSONObject(data);
            JSONArray arr = new JSONArray(temp.getString("record"));
            for(int i=0;i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenArrayList.add(new TruyenKeSach(o));
            }
            adapter = new KeSachTruyenAdapter(getActivity(),0,truyenArrayList);
            gdvListTruyen.setAdapter(adapter);
        }catch (JSONException e){
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(getActivity(),"Lỗi Kết Nối",Toast.LENGTH_SHORT).show();
    }
}