package com.example.hb.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hb.Adapter.TruyenAdapter;
import com.example.hb.Object.Truyen;
import com.example.hb.R;

import java.util.ArrayList;


public class LibraryFragment extends Fragment {

    View view;
    GridView gdvListTruyen;
    TruyenAdapter adapter;
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
        init(); anhXa(); setUp(); setClick();
    }
    private void init(){
        truyenArrayList = new ArrayList<>();
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        truyenArrayList.add(new Truyen("Anh anime dep","100","https://img.thuthuattinhoc.vn/uploads/2019/01/08/anh-anime-boy-dep-nhat_101905549.jpg"));
        adapter = new TruyenAdapter(getActivity(),0,truyenArrayList);
    }
    private void anhXa(){
        gdvListTruyen = view.findViewById(R.id.gdvListTruyen);
    }
    private void setUp(){
        gdvListTruyen.setAdapter(adapter);
    }
    private void setClick(){}
}