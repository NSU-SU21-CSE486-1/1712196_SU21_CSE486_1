package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.istiaksaif.uniclubz.Adaptar.TabViewPagerAdapter;
import com.istiaksaif.uniclubz.Fragment.BloodReqFragment;
import com.istiaksaif.uniclubz.Fragment.ClubsFragment;
import com.istiaksaif.uniclubz.Fragment.ClubsHomeFragment;
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

        toolbar = findViewById(R.id.clubtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("");
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
        tabViewPagerAdapter.AddFragment(new ClubsHomeFragment(),null);
        tabViewPagerAdapter.AddFragment(new EventCreateFragment(),null);
        tabViewPagerAdapter.AddFragment(new BloodReqFragment(),null);
        tabViewPagerAdapter.AddFragment(new clubProfileFragment(),"Profile");
        tabViewPagerAdapter.AddFragment(new MembersFragment(),"Members");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(3);
        tabLayout.setupWithViewPager(tabviewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_addevent);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_blood_drop);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(1));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 1f;
        layout.setLayoutParams(layoutParams);
        LinearLayout layout1 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(2));
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layout1.getLayoutParams();
        layoutParams1.weight = 1f;
        layout1.setLayoutParams(layoutParams1);
        LinearLayout layout2 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layout2.getLayoutParams();
        layoutParams2.weight = 1f;
        layout2.setLayoutParams(layoutParams2);
        LinearLayout layout3 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(3));
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layout3.getLayoutParams();
        layoutParams3.weight = 1.7f;
        LinearLayout layout4 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(4));
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layout4.getLayoutParams();
        layoutParams4.weight = 1.7f;

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
        toolbar = findViewById(R.id.clubtoolbar);
        setSupportActionBar(toolbar);
    }
    public void setText(TextView clubName){
        int titleBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            titleBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        clubName = findViewById(R.id.clubname);
        clubName.setVisibility(View.INVISIBLE);
        clubName.setMaxHeight(titleBarHeight);
    }
}