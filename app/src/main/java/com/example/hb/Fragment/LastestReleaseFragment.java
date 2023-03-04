package com.example.hb.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hb.Activities.ChapActivity;
import com.example.hb.Adapter.NovelfullAdapter;
import com.example.hb.Adapter.WikidichAdapter;
import com.example.hb.Api.ApiJsoupListWikidich;
import com.example.hb.Api.ApiListNovelFullLastestRelease;
import com.example.hb.Interfaces.LayTruyenCV;
import com.example.hb.Object.TruyenKhamPha;
import com.example.hb.R;

import java.util.ArrayList;

public class LastestReleaseFragment extends Fragment implements LayTruyenCV {

    View view;
    GridView gdvLatestRelease;
    NovelfullAdapter adapter;
    ArrayList khamPhaTruyenArrayList;
    public static TruyenKhamPha truyenKhamPha;
    String url ="https://novelfull.com/latest-release-novel";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lastest_release, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiListNovelFullLastestRelease(this,url).execute();
    }

    private void init(){
        khamPhaTruyenArrayList = new ArrayList<>();
        adapter = new NovelfullAdapter(getActivity(),0,khamPhaTruyenArrayList);
    }
    private void anhXa(){

        gdvLatestRelease = view.findViewById(R.id.gdvLastestRelease);
    }
    private void setUp(){

        gdvLatestRelease.setAdapter(adapter);
    }
    private void setClick(){
        gdvLatestRelease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                truyenKhamPha = (TruyenKhamPha) khamPhaTruyenArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen novelfull",truyenKhamPha);
                Intent intent = new Intent(getActivity(), ChapActivity.class);
                intent.putExtra("data novelfull",b);
                startActivity(intent);
            }
        });
    }


    @Override
    public void batDauCV() {
        Toast.makeText(getActivity(),"Đang Lấy Dữ Liệu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThucCV(ArrayList data) {
        khamPhaTruyenArrayList.clear();
        khamPhaTruyenArrayList.addAll(data);
        adapter = new NovelfullAdapter(getActivity(),0,khamPhaTruyenArrayList);
        gdvLatestRelease.setAdapter(adapter);
    }

    @Override
    public void biLoiCV() {
        Toast.makeText(getActivity(),"Lỗi Kết Nối",Toast.LENGTH_SHORT).show();
    }
}