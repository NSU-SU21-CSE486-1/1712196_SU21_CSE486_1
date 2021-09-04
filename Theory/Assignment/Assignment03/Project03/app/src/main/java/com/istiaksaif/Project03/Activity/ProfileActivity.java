package com.istiaksaif.Project03.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.istiaksaif.Project03.Adapter.TabViewPagerAdapter;
import com.istiaksaif.Project03.Fragment.FirstTabFragment;
import com.istiaksaif.Project03.Fragment.SecondTabFragment;
import com.istiaksaif.Project03.Fragment.ThirdTabFragment;
import com.istiaksaif.Project03.R;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;
    public static TextView submitButton;
    private ImageView swtichbtn;

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

        submitButton = findViewById(R.id.submitbtn);
        swtichbtn = findViewById(R.id.switchbtn);
        swtichbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, FinalActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void saveData() {
//
//        DatabaseClass db  = DatabaseClass.getDbInstance(this.getApplicationContext());
//
//        User model = new User();
//        model.getName();
//        model.getNID();
//        model.getBloodGroup();
//        model.getDOB();
//        db.userDao().insertUser(model);
//
//        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();
//    }
}