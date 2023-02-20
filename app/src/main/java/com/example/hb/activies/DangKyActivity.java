package com.example.hb.activies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hb.R;
import com.example.hb.databinding.ActivityDangKyBinding;

public class DangKyActivity extends AppCompatActivity {

    private ActivityDangKyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}