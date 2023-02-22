package com.example.hb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hb.Adapter.BookshelfFragmentAdapter;
import com.example.hb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mNavigationView;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    BookshelfFragmentAdapter fragmentAdapter;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.bottom_nav);
        tabLayout = findViewById(R.id.tabBookshelf);
        viewPager2 = findViewById(R.id.bookshelfPager);

        tabLayout.addTab(tabLayout.newTab().setText("Kệ Sách"));
        tabLayout.addTab(tabLayout.newTab().setText("Lịch Sử"));

        fragmentAdapter = new BookshelfFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(fragmentAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_congdong:
                        startActivity(new Intent(MainActivity.this, CongDongActivity.class));
                        break;
                    case R.id.action_kesach:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;
                    case R.id.action_khampha:
                        startActivity(new Intent(MainActivity.this,KhamPhaActivity.class));
                        break;
                    case R.id.action_canhan:
                        break;
                    default:
                }
                return true;
            }
        });

    }




    private void setUpViewPager() {

    }
}