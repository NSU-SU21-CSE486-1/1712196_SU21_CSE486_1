package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.istiaksaif.uniclubz.Adaptar.TabViewPagerAdapter;
import com.istiaksaif.uniclubz.Fragment.BloodReqFragment;
import com.istiaksaif.uniclubz.Fragment.ClubsFragment;
import com.istiaksaif.uniclubz.Fragment.EventCreateFragment;
import com.istiaksaif.uniclubz.Fragment.MembersFragment;
import com.istiaksaif.uniclubz.Fragment.NotificationFragment;
import com.istiaksaif.uniclubz.Fragment.ProfileFragment;
import com.istiaksaif.uniclubz.Fragment.UserHomeFragment;
import com.istiaksaif.uniclubz.Fragment.clubProfileFragment;
import com.istiaksaif.uniclubz.R;
/**
 * Created by Istiak Saif on 27/07/21.
 */
public class ClubActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().hide();

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabViewPagerAdapter.AddFragment(new UserHomeFragment(),null);
        tabViewPagerAdapter.AddFragment(new EventCreateFragment(),null);
        tabViewPagerAdapter.AddFragment(new BloodReqFragment(),null);
        tabViewPagerAdapter.AddFragment(new clubProfileFragment(),"Profile");
        tabViewPagerAdapter.AddFragment(new MembersFragment(),"Members");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(3);
        tabLayout.setupWithViewPager(tabviewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_addevent);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_drop_of_blood);

        tabviewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setTranslationX(page.getWidth()* -position);
                if(position <= -1 || position >= 1){
                    page.setAlpha(0);
                }else if(position==0){
                    page.setAlpha(1);
                }else {
                    page.setAlpha(1-Math.abs(position));
                }
            }
        });
    }
    public void setToolbar(Toolbar toolbar){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}