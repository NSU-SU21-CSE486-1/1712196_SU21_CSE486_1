package com.istiaksaif.Project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Activity.ProfileActivity;
import com.istiaksaif.Project02.Adapter.FinalUniAffiliationAdapter;
import com.istiaksaif.Project02.Adapter.ItemAdapter;
import com.istiaksaif.Project02.Utils.FinalUniAffiliation;
import com.istiaksaif.Project02.Utils.ItemList;
import com.istiaksaif.Project02.Utils.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.istiaksaif.Project02.Activity.ProfileActivity.Contact_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filePath;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filename;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
//    if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().hasExtra(ProfileActivity.User_Key) && getIntent().hasExtra(ProfileActivity.Uni_Key) && getIntent().hasExtra(ProfileActivity.Contact_Key)){
//        User user = (User)getIntent().getSerializableExtra(ProfileActivity.User_Key);
//        FinalUniAffiliation ListItem = (FinalUniAffiliation)getIntent().getSerializableExtra(ProfileActivity.Uni_Key);
//        ItemList itemList = (ItemList) getIntent().getSerializableExtra(Contact_Key);
//        fileContent ="Name : " +user.getName()+"\n"+"DOB: " +user.getDOB()+"\n"
//                +"NID : " +user.getNID()+"\n"+"Blood Group : " +user.getBloodGroup();
//        if(!fileContent.equals("")){
//            File myFile = new File(getExternalFilesDir(filePath),filename);
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = new FileOutputStream(myFile);
//                fileOutputStream.write(fileContent.getBytes());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        name.setText(user.getName());
//        dob.setText(user.getDOB());
//        nid.setText(user.getNID());
//        blood.setText(user.getBloodGroup());
//        fileContent1 ="University Name : " +ListItem.getUniName()+"\n"+"StudentId: " +ListItem.getId()+"\n"
//                +"Department : " +ListItem.getDepartment()+"\n"+"Level : " +ListItem.getLevel();
//        if(!fileContent1.equals("")) {
//            File myFile = new File(getExternalFilesDir(filePath), "uniInfo.txt");
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = new FileOutputStream(myFile);
//                fileOutputStream.write(fileContent1.getBytes());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ListItem.setUniName(ListItem.getUniName());
//        ListItem.setId(ListItem.getId());
//        ListItem.setDepartment(ListItem.getDepartment());
//        ListItem.setLevel(ListItem.getLevel());
//        uniAffiliationArrayList.add(ListItem);
//
//        fileContent2 ="Phone Number : " +ListItem.getUniName()+"\n"+"Email : " +ListItem.getId();
//        if(!fileContent2.equals("")) {
//            File myFile = new File(getExternalFilesDir(filePath), "contactInfo.txt");
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = new FileOutputStream(myFile);
//                fileOutputStream.write(fileContent2.getBytes());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        itemList.setPhone(itemList.getPhone());
//        itemList.setEmail(itemList.getEmail());
//        itemArrayList.add(itemList);
//    }
//    else if(getIntent().getExtras()==null){
}