package com.istiaksaif.Project02.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.istiaksaif.Project02.Adapter.TabViewPagerAdapter;
import com.istiaksaif.Project02.Fragment.FirstTabFragment;
import com.istiaksaif.Project02.Fragment.SecondTabFragment;
import com.istiaksaif.Project02.Fragment.ThirdTabFragment;
import com.istiaksaif.Project02.R;

import static androidx.fragment.app.FragmentStatePagerAdapter.*;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabViewPagerAdapter.AddFragment(new FirstTabFragment(),"First Tab");
        tabViewPagerAdapter.AddFragment(new SecondTabFragment(),"Second Tab");
        tabViewPagerAdapter.AddFragment(new ThirdTabFragment(),"Third Tab");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(tabviewPager);

        //transformPage between fragments
//        tabviewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                page.setTranslationX(page.getWidth()* -position);
//                if(position <= -1 || position >= 1){
//                    page.setAlpha(0);
//                }else if(position==0){
//                    page.setAlpha(1);
//                }else {
//                    page.setAlpha(1-Math.abs(position));
//                }
//            }
//        });
    }
}