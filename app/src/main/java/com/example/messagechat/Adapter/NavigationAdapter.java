package com.example.messagechat.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.messagechat.Fragment.AccountFragment;
import com.example.messagechat.Fragment.HomeFragment;
import com.example.messagechat.Fragment.MessageFragment;

public class NavigationAdapter extends FragmentStateAdapter {
    public NavigationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MessageFragment();
            default:
                return new AccountFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
