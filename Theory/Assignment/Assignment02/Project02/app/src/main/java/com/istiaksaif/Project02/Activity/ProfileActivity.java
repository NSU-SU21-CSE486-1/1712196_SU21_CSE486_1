package com.istiaksaif.Project02.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Adapter.TabViewPagerAdapter;
import com.istiaksaif.Project02.Fragment.FirstTabFragment;
import com.istiaksaif.Project02.Fragment.SecondTabFragment;
import com.istiaksaif.Project02.Fragment.ThirdTabFragment;
import com.istiaksaif.Project02.MainActivity;
import com.istiaksaif.Project02.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Permission;

import static androidx.fragment.app.FragmentStatePagerAdapter.*;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;
    private String filename = "";
    private String filePath = "";
    private String fileContent;
//    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup;
    private TextView textView,submitButton,swtichbtn;
    private String fullName,dateOfBirth,nid,bloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        checkPermission();

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabViewPagerAdapter.AddFragment(new FirstTabFragment(),"First Tab");
        tabViewPagerAdapter.AddFragment(new SecondTabFragment(),"Second Tab");
        tabViewPagerAdapter.AddFragment(new ThirdTabFragment(),"Third Tab");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(tabviewPager);

//        fullName = findViewById(R.id.name);

        submitButton = findViewById(R.id.submitbtn);
        swtichbtn = findViewById(R.id.switchbtn);
        swtichbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,FinalActivity.class);
                startActivity(intent);
            }
        });
        //data save file
        filename = "userinfo.txt";
        filePath = "project02Dir";
        if(!isExternalStorageRW()){
            submitButton.setEnabled(false);
        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName= FirstTabFragment.fullName.getText().toString().trim();
                dateOfBirth= FirstTabFragment.dateOfBirth.getText().toString().trim();
                nid= FirstTabFragment.nid.getText().toString().trim();
                bloodGroup= FirstTabFragment.bloodGroup.getText().toString().trim();
                fileContent ="Name : " +fullName+"\n"+"DOB: " +dateOfBirth+"\n"
                +"NID : " +nid+"\n"+"Blood Group : " +bloodGroup;
//                File file = new File(getExternalFilesDir(filePath),filename);
//                if(!file.exists()){
//                    try {
//                        file.createNewFile();
//                        FileWriter fileWriter = new FileWriter(file,true);
//                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                        for(String string : fileContent){
//                            fileContent += string+System.getProperty(System.lineSeparator());
//                            bufferedWriter.write(fileContent);
//                            bufferedWriter.close();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }

                if(!fileContent.equals("")){
                    File myFile = new File(getExternalFilesDir(filePath),filename);
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(myFile);
                        fileOutputStream.write(fileContent.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(ProfileActivity.this,"done",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ProfileActivity.this,FinalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isExternalStorageRW() {
        String storage = Environment.getExternalStorageState();
        if (storage.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }
    private void checkPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(ProfileActivity.this,"Granted",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(ProfileActivity.this,"Denied",Toast.LENGTH_LONG).show();
            }
        }
    }
}