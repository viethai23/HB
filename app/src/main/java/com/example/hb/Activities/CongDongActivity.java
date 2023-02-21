package com.example.hb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hb.R;

public class CongDongActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_dong);

        imgBack = findViewById(R.id.imageBack);
        txtSignIn = findViewById(R.id.txtSignIn);

        imgBack.setOnClickListener(v -> onBackPressed());

        txtSignIn.setOnClickListener(v -> openDialog(R.layout.activity_dang_nhap));

    }

    private void openDialog(int layout) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        if(layout == R.layout.activity_dang_nhap) {
            TextView txtCreate = dialog.findViewById(R.id.textCreatNewAccount);
            txtCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    openDialog(R.layout.activity_dang_ky);
                }
            });
        }
        else {
            TextView txtSignIn = dialog.findViewById(R.id.textSignIn);
            txtSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    openDialog(R.layout.activity_dang_nhap);
                }
            });
        }

        dialog.show();
    }
}