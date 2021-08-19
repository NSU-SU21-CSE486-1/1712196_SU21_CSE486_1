package com.istiaksaif.Project02.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FinalActivity extends AppCompatActivity {

    private TextView uniName,studentID,department,level,phone,email,personalInfo;
    private Toolbar toolbar;
    private Bundle b;

    private String filename = "";
    private  String filePath = "";
    private String fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        b = getIntent().getExtras();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        uniName = findViewById(R.id.uniname);
        studentID = findViewById(R.id.id);
        department = findViewById(R.id.dep);
        level = findViewById(R.id.level);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        personalInfo = findViewById(R.id.personalinfo);

        filename = "userinfo.txt";
        filePath = "project02Dir";
        FileReader fileReader = null;
        File myexFile = new File(getExternalFilesDir(filePath),filename);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileReader = new FileReader(myexFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while(line !=null){
                stringBuilder.append(line).append('\n');
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            String fileContents = stringBuilder.toString();
            personalInfo.setText(fileContents);
        }
    }
}