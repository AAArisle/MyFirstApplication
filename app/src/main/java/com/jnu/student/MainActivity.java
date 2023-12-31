package com.jnu.student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), getLifecycle()));

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("图书");
                            break;
                        case 1:
                            tab.setText("新闻");
                            break;
                        case 2:
                            tab.setText("地图");
                            break;
                        case 3:
                            tab.setText("时钟");
                            break;
                        case 4:
                            tab.setText("游戏");
                            break;
                    }
                }).attach();

    }
}