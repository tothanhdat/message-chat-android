package com.example.messagechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.example.messagechat.Adapter.NavigationAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);



        ViewPager2 viewPager2 = findViewById(R.id.view_paper2);
        viewPager2.setAdapter(new NavigationAdapter(this));
        TabLayout navigation = findViewById(R.id.navigation);


        navigation.addTab(navigation.newTab().setIcon(R.drawable.home));
        navigation.addTab(navigation.newTab().setIcon(R.drawable.chat));
        navigation.addTab(navigation.newTab().setIcon(R.drawable.user));

        navigation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }
}