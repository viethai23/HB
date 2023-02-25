package com.example.hb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hb.Adapter.ChapTruyenItemAdapter;
import com.example.hb.Api.ApiJsoupContentTruyenNovelFull;
import com.example.hb.Api.ApiJsoupContentTruyenWikidich;
import com.example.hb.Interfaces.LayGioiThieuTruyen;
import com.example.hb.Object.ChapTruyen;
import com.example.hb.Object.TruyenKhamPha;
import com.example.hb.Object.TruyenKhamPhaTruyen;
import com.example.hb.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements Serializable, LayGioiThieuTruyen {
    TruyenKhamPha truyenKhamPha;
    TextView chapTenTruyen;
    TextView chapTenTacGia;
    ImageView imgChap;
    TextView chapTrangThai;
    TextView chapTheLoai;
    TextView chapGioiThieu;
    ListView listChap;
    public static ArrayList<ChapTruyen> arrChap;
    ChapTruyenItemAdapter adapter;
    TruyenKhamPha truyenNovelfull,truyenWiki;
    String linkTruyenWiki, linkTruyenNovelfull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        if(linkTruyenWiki!=null){
            new ApiJsoupContentTruyenWikidich(this, linkTruyenWiki).execute();
        }
        if(linkTruyenNovelfull!=null){
            new ApiJsoupContentTruyenNovelFull(this, linkTruyenNovelfull).execute();
        }
    }

    private void init() {

//        Bundle b = getIntent().getBundleExtra("data wiki");
//        truyenWiki = (TruyenKhamPha) b.getSerializable("truyen wiki");
//        linkTruyenWiki = (String) truyenWiki.getDetailURL();
        Bundle c = getIntent().getBundleExtra("data novelfull");
        truyenNovelfull = (TruyenKhamPha) c.getSerializable("truyen novelfull");
        linkTruyenNovelfull = (String) truyenNovelfull.getDetailURL();

        arrChap = new ArrayList<>();
        adapter = new ChapTruyenItemAdapter(this, 0, arrChap);
    }

    private void anhXa() {
        chapTenTruyen = findViewById(R.id.chapTenTruyen);
        chapTenTacGia = findViewById(R.id.chapTenTacGia);
        chapTheLoai = findViewById(R.id.chapTheLoai);
        chapTrangThai = findViewById(R.id.chapTrangThai);
        chapGioiThieu = findViewById(R.id.chapGioiThieu);
        imgChap = findViewById(R.id.imgChap);
        listChap = findViewById(R.id.listChap);
    }

    private void setUp() {
        chapTenTruyen.setText(truyenNovelfull.getTenTruyen());
        listChap.setAdapter(adapter);
    }

    private void setClick() {
        listChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle b = new Bundle();
                b.putSerializable("chap post",position);
                Intent intent = new Intent(ChapActivity.this,ReadingChapActivity.class);
                intent.putExtra("data chap",b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Đang Lấy Dữ Liệu", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void ketThuc(TruyenKhamPhaTruyen data,ArrayList data2) {
        TruyenKhamPhaTruyen truyen = data;
        chapTenTacGia.setText(truyen.getTenTacGia());
        chapTrangThai.setText(truyen.getTrangThai());
        chapTheLoai.setText(truyen.getTheLoai());
        chapGioiThieu.setText(truyen.getGioiThieu());
        Glide.with(this).load(truyen.getLinkAnh()).into(imgChap);
        arrChap.clear();
        arrChap = data2;
        adapter = new ChapTruyenItemAdapter(this,0,arrChap);
        listChap.setAdapter(adapter);
    }

    @Override
    public void biLoiTruyen() {
        Toast.makeText(this, "Lỗi Kết Nối Truyen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void biLoiChap() {
        Toast.makeText(this, "Lỗi Kết Nối Chap", Toast.LENGTH_SHORT).show();
    }

}

