package com.example.hb.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hb.Activities.ChapActivity;
import com.example.hb.Adapter.WikidichAdapter;
import com.example.hb.Api.ApiJsoupListWikidich;
import com.example.hb.Interfaces.LayTruyenCV;
import com.example.hb.Object.TruyenKhamPha;
import com.example.hb.R;

import java.util.ArrayList;

public class WikidichFragment extends Fragment implements LayTruyenCV {

    View view;
    GridView gdvKhamPha;
    WikidichAdapter adapter;
    ArrayList khamPhaTruyenArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wikidich, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiJsoupListWikidich(this).execute();
    }

    private void init(){
        khamPhaTruyenArrayList = new ArrayList<>();
        adapter = new WikidichAdapter(getActivity(),0,khamPhaTruyenArrayList);
    }
    private void anhXa(){

        gdvKhamPha = view.findViewById(R.id.gdvWikidich);
    }
    private void setUp(){

        gdvKhamPha.setAdapter(adapter);
    }
    private void setClick(){
        gdvKhamPha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TruyenKhamPha truyenKhamPha = (TruyenKhamPha) khamPhaTruyenArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyenKhamPha);
                Intent intent = new Intent(getActivity(), ChapActivity.class);
                intent.putExtra("data",b);
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
        khamPhaTruyenArrayList = data;
        adapter = new WikidichAdapter(getActivity(),0,khamPhaTruyenArrayList);
        gdvKhamPha.setAdapter(adapter);
    }

    @Override
    public void biLoiCV() {
        Toast.makeText(getActivity(),"Lỗi Kết Nối",Toast.LENGTH_SHORT).show();
    }
}