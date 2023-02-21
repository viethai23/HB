package com.example.hb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hb.Fragment.LibraryFragment;
import com.example.hb.Fragment.ReadHistoryFragment;

public class BookshelfFragmentAdapter extends FragmentStateAdapter {
    public BookshelfFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BookshelfFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BookshelfFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==1){
            return new ReadHistoryFragment();
        }
        return new LibraryFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
