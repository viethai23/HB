package com.example.hb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hb.Adapter.TruyenAdapter;
import com.example.hb.Fragment.LibraryFragment;
import com.example.hb.Fragment.ReadHistoryFragment;
import com.example.hb.Object.Truyen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mNavigationView;
    GridView gdvListTruyen;
    TruyenAdapter adapter;
    ArrayList<Truyen> truyenArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottom_nav);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_congdong:
                        startActivity(new Intent(MainActivity.this, CongDongActivity.class));
                        break;
                    case R.id.action_kesach:
                        break;
                    case R.id.action_khampha:
                        break;
                    case R.id.action_canhan:
                        break;
                    default:
                }
                return true;
            }
        });
        TextView library_ui = findViewById(R.id.action_home_library);
        TextView history_ui = findViewById(R.id.action_history);

        history_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ReadHistoryFragment());
            }
        });

        library_ui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new LibraryFragment());
            }
        });
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main,fragment);
        fragmentTransaction.commit();
    }

    private void init(){
        truyenArrayList = new ArrayList<>();
        truyenArrayList.add(new Truyen("Cẩu Thả Tại Nữ Ma Đầu Bên Người Vụng Trộm Tu Luyện","690","https://static.cdnno.com/poster/bat-dau-nu-ma-dau-phu-ta/300.jpg?1646967756"));
        truyenArrayList.add(new Truyen("Quang Âm Chi Ngoại","707","https://static.cdnno.com/poster/quang-am-chi-ngoai/300.jpg?1655013821"));
        truyenArrayList.add(new Truyen("Mink Đường Phố Số 13","1485","https://static.cdnno.com/poster/mink-duong-pho-so-13/300.jpg?1635124677"));
        truyenArrayList.add(new Truyen("Thần Minh Máy Mô Phỏng","327","https://static.cdnno.com/poster/than-minh-may-mo-phong/300.jpg?1672824059"));
        truyenArrayList.add(new Truyen("Cẩu Thả Tại Nữ Ma Đầu Bên Người Vụng Trộm Tu Luyện","690","https://static.cdnno.com/poster/bat-dau-nu-ma-dau-phu-ta/300.jpg?1646967756"));
        truyenArrayList.add(new Truyen("Quang Âm Chi Ngoại","707","https://static.cdnno.com/poster/quang-am-chi-ngoai/300.jpg?1655013821"));
        truyenArrayList.add(new Truyen("Mink Đường Phố Số 13","1485","https://static.cdnno.com/poster/mink-duong-pho-so-13/300.jpg?1635124677"));
        truyenArrayList.add(new Truyen("Thần Minh Máy Mô Phỏng","327","https://static.cdnno.com/poster/than-minh-may-mo-phong/300.jpg?1672824059"));
        truyenArrayList.add(new Truyen("Cẩu Thả Tại Nữ Ma Đầu Bên Người Vụng Trộm Tu Luyện","690","https://static.cdnno.com/poster/bat-dau-nu-ma-dau-phu-ta/300.jpg?1646967756"));
        truyenArrayList.add(new Truyen("Quang Âm Chi Ngoại","707","https://static.cdnno.com/poster/quang-am-chi-ngoai/300.jpg?1655013821"));
        truyenArrayList.add(new Truyen("Mink Đường Phố Số 13","1485","https://static.cdnno.com/poster/mink-duong-pho-so-13/300.jpg?1635124677"));
        truyenArrayList.add(new Truyen("Thần Minh Máy Mô Phỏng","327","https://static.cdnno.com/poster/than-minh-may-mo-phong/300.jpg?1672824059"));

        adapter = new TruyenAdapter(this,0,truyenArrayList);
    }
    private void anhXa(){
        gdvListTruyen = findViewById(R.id.gdvListTruyen);
    }
    private void setUp(){
        gdvListTruyen.setAdapter(adapter);
    }
    private void setClick(){}
    private void setUpViewPager() {

    }
}