package com.example.hb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        //new ApiJsoupContentTruyenWikidich((LayGioiThieuTruyen) this, truyenKhamPha.getDetailURL()).execute();
        new ApiJsoupContentTruyenNovelFull(this,truyenKhamPha.getDetailURL()).execute();
    }

    private void init() {
        Bundle b = getIntent().getBundleExtra("data");
        truyenKhamPha = (TruyenKhamPha) b.getSerializable("truyen");

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
        listChap.setAdapter(adapter);
    }

    private void setClick() {
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
        chapTenTruyen.setText(truyen.getTenTruyen());
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

