package com.example.hb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hb.R;
import com.example.hb.databinding.ActivityDangNhapBinding;

public class DangNhapActivity extends AppCompatActivity {

    private ActivityDangNhapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}