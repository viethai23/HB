package com.example.hb.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hb.Adapter.HistoryAdapter;
import com.example.hb.Object.TruyenLichSu;
import com.example.hb.R;

import java.util.ArrayList;
import java.util.Collections;


public class ReadHistoryFragment extends Fragment {

   View view;
   HistoryAdapter adapter;
   public static ArrayList<TruyenLichSu> listTruyen = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_read_history, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.lstHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryAdapter(getContext(),listTruyen);
        recyclerView.setAdapter(adapter);
    }

}