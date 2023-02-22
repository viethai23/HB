package com.example.hb.Activities;
import com.example.hb.Adapter.DiscoveryFragmentAdapter;
import com.example.hb.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.GridView;

public class KhamPhaActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    DiscoveryFragmentAdapter discoveryFragmentAdapter;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kham_pha);

        tabLayout = findViewById(R.id.tabDiscovery);
        viewPager2 = findViewById(R.id.discoveryPager);
        tabLayout.addTab(tabLayout.newTab().setText("Trang chủ"));
        discoveryFragmentAdapter = new DiscoveryFragmentAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(discoveryFragmentAdapter);
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

    }
}