package com.example.hb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.hb.Activities.MainActivity;
import com.example.hb.R;
import com.example.hb.databinding.ActivityDangNhapBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.regex.Pattern;

public class DangNhapActivity extends AppCompatActivity {

    private ActivityDangNhapBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        setListeners();

    }

    private void setListeners() {
        binding.textCreatNewAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), DangKyActivity.class)));
        binding.buttonDangNhap.setOnClickListener(v -> {
            dangNhap();
        });
    }

    private void showToasts(String messeger) {
        Toast.makeText(getApplicationContext(), messeger, Toast.LENGTH_SHORT).show();
    }


    private void loading(Boolean isLoading) {
        if(isLoading) {
            binding.buttonDangNhap.setVisibility(View.INVISIBLE);
            binding.progessBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progessBar.setVisibility(View.INVISIBLE);
            binding.progessBar.setVisibility(View.VISIBLE);
        }
    }

    private void dangNhap() {
        loading(true);
        final String email = binding.inputEmail.getText().toString();
        final String password = binding.inputPassword.getText().toString();
        if(email.isEmpty() || password.isEmpty()) {
            showToasts("Vui lòng nhập đầy đủ thông tin");
            loading(false);
        }
        else
            kiemTraDangNhap(email, password);
        
    }

    private void updateUI() {
        CongDongActivity.setSuccesLogin(true);
        startActivity(new Intent(getApplicationContext(), CongDongActivity.class));
        finish();
    }

    private void kiemTraDangNhap(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            showToasts("Đăng nhập thành công");
                            updateUI();
                        }
                        else {
                            showToasts(task.getException().getMessage());
                            loading(false);
                        }
                    }
                });
    }


}