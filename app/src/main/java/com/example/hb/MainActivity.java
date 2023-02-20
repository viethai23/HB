package com.example.hb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

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
    ArrayList truyenArrayList;
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
        init(); anhXa(); setUp(); setClick();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main,fragment);
        fragmentTransaction.commit();
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