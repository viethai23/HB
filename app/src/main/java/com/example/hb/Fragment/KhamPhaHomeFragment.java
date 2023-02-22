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

import com.example.hb.Adapter.KhamPhaTruyenAdapter;
import com.example.hb.Api.ApiJSOUP;
import com.example.hb.Interfaces.LayTruyenCV;
import com.example.hb.R;

import java.util.ArrayList;

public class KhamPhaHomeFragment extends Fragment implements LayTruyenCV {

    View view;
    GridView gdvKhamPha;
    KhamPhaTruyenAdapter adapter;
    ArrayList khamPhaTruyenArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kham_pha_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiJSOUP(this).execute();
    }

    private void init(){
        khamPhaTruyenArrayList = new ArrayList<>();
        adapter = new KhamPhaTruyenAdapter(getActivity(),0,khamPhaTruyenArrayList);
    }
    private void anhXa(){

        gdvKhamPha = view.findViewById(R.id.gdvKhamPha);
    }
    private void setUp(){

        gdvKhamPha.setAdapter(adapter);
    }
    private void setClick(){}

    @Override
    public void batDauCV() {
        Toast.makeText(getActivity(),"Đang Lấy Dữ Liệu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThucCV(ArrayList data) {
        khamPhaTruyenArrayList.clear();
        khamPhaTruyenArrayList = data;
        adapter = new KhamPhaTruyenAdapter(getActivity(),0,khamPhaTruyenArrayList);
        gdvKhamPha.setAdapter(adapter);
    }

    @Override
    public void biLoiCV() {
        Toast.makeText(getActivity(),"Lỗi Kết Nối",Toast.LENGTH_SHORT).show();
    }
}