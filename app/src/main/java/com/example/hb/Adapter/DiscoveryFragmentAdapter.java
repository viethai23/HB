package com.example.hb.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hb.Fragment.HotNovelFragment;
import com.example.hb.Fragment.LastestReleaseFragment;

public class DiscoveryFragmentAdapter extends FragmentStateAdapter {
    public DiscoveryFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==1){
            return new HotNovelFragment();
        }
        return new LastestReleaseFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
