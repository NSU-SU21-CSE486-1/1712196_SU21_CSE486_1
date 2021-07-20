package com.istiaksaif.uniclubz.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Adaptar.TabViewPagerAdapter;
import com.istiaksaif.uniclubz.Fragment.ClubsFragment;
import com.istiaksaif.uniclubz.Fragment.NotificationFragment;
import com.istiaksaif.uniclubz.Fragment.ProfileFragment;
import com.istiaksaif.uniclubz.Fragment.UserHomeFragment;
import com.istiaksaif.uniclubz.R;

/**
 * Created by Istiak Saif on 14/07/21.
 */
public class UserHomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private long backPressedTime;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        int displayWidth = getWindowManager().getDefaultDisplay().getHeight();

        toolbar = findViewById(R.id.toolbar);
//        toolbar.setPadding(0,0,0,0);
//        toolbar.setContentInsetsAbsolute(0,0);
//        toolbar.setContentInsetsRelative(0,0);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        tabViewPagerAdapter.AddFragment(new ProfileFragment(),"Profile");
        tabViewPagerAdapter.AddFragment(new NotificationFragment(),null);
        tabViewPagerAdapter.AddFragment(new ClubsFragment(),null);
        tabViewPagerAdapter.AddFragment(new UserHomeFragment(),null);
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(3);
        tabLayout.setupWithViewPager(tabviewPager);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(3));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 1f;
        layout.setLayoutParams(layoutParams);
        LinearLayout layout1 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(2));
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layout1.getLayoutParams();
        layoutParams1.weight = 1f;
        layout1.setLayoutParams(layoutParams1);
        LinearLayout layout2 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(1));
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layout2.getLayoutParams();
        layoutParams2.weight = 1f;
        layout2.setLayoutParams(layoutParams2);
        LinearLayout layout3 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layout3.getLayoutParams();
        layoutParams3.weight = 1.7f;
        layout3.setLayoutParams(layoutParams3);
        tabLayout.getTabAt(3).setIcon(R.drawable.home);
        tabLayout.getTabAt(2).setIcon(R.drawable.club_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.notification);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawernavview);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.menu);

        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
        }return true;
    }
    public void onBackPressed(){
        if(backPressedTime + 2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(),"Press Back Again to Exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}