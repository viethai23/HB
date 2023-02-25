package com.example.hb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb.Api.ApiJsoupContentChap;
import com.example.hb.Fragment.NovelFullFragment;
import com.example.hb.Interfaces.LayTruyenVe;
import com.example.hb.Object.ChapTruyen;
import com.example.hb.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class ReadingChapActivity extends AppCompatActivity implements Serializable, LayTruyenVe {
    TextView tenChap,contentChap;
    ImageView btnNextChap,btnPrevChap;
    ChapTruyen chapTruyen;
    FloatingActionButton btnBack;
    int chapPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_chap);

        init();
        anhXa();
        setClick();
        new ApiJsoupContentChap(this,chapTruyen.getLinkChap()).execute();
    }
    private void newStart(){
        Bundle b = new Bundle();
        b.putSerializable("chap post",chapPost);
        Intent intent = new Intent(ReadingChapActivity.this,ReadingChapActivity.class);
        intent.putExtra("data chap",b);
        startActivity(intent);
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data chap");
        chapPost = (int) b.getSerializable("chap post");
        chapTruyen = ChapActivity.arrChap.get(chapPost);
    }
    private void anhXa() {
        tenChap = findViewById(R.id.readTenChap);
        contentChap = findViewById(R.id.readContent);
        btnNextChap = findViewById(R.id.btnNextChapter);
        btnPrevChap = findViewById(R.id.btnPrevChapter);
        btnBack = findViewById(R.id.backContent);
    }
    private void setClick(){
        btnPrevChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chapPost==0){
                    Toast.makeText(ReadingChapActivity.this, "No Previous Chapter", Toast.LENGTH_SHORT).show();
                }else{
                    chapPost-=1;
                    newStart();
                }
            }
        });
        btnNextChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chapPost==ChapActivity.arrChap.size()-1){
                    Toast.makeText(ReadingChapActivity.this, "No Next Chapter", Toast.LENGTH_SHORT).show();
                }else{
                    chapPost+=1;
                    newStart();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingChapActivity.this,ChapActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("truyen novelfull", NovelFullFragment.truyenKhamPha);
                intent.putExtra("data novelfull",b);
                startActivity(intent);
            }
        });
    }
    @Override
    public void batDau() {
    }

    @Override
    public void ketThuc(String data) {
        String content = data;
        Log.d("chap: ",chapTruyen.getTenChap()+"\n"+content);
        tenChap.setText(chapTruyen.getTenChap());
        contentChap.setText(content);
        contentChap.setMovementMethod(new ScrollingMovementMethod());
        setClick();
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Không tải được chương", Toast.LENGTH_SHORT).show();
    }
}