package com.istiaksaif.Project01.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project01.R;

public class FinalActivity extends AppCompatActivity {

    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup;
    private TextView uniName,studentID,department,level,phone,email;
    private Toolbar toolbar;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        b = getIntent().getExtras();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        fullName = findViewById(R.id.name);
        dateOfBirth = findViewById(R.id.dateofbirth);
        nid = findViewById(R.id.nid);
        bloodGroup = findViewById(R.id.bloodgroup);
        uniName = findViewById(R.id.uniname);
        studentID = findViewById(R.id.id);
        department = findViewById(R.id.dep);
        level = findViewById(R.id.level);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        if (b!=null){
            String result = b.getString("name");
            fullName.setText(result);
            String result1 = b.getString("dateofbirth");
            dateOfBirth.setText(result1);
            String result3 = b.getString("bloodgroup");
            bloodGroup.setText(result3);
            String result2 = b.getString("nid");
            nid.setText(result2);
            String result4 = b.getString("uniname");
            uniName.setText(result4);
            String result5 = b.getString("department");
            department.setText(result5);
            String result6 = b.getString("id");
            studentID.setText(result6);
            String result7 = b.getString("studentLevel");
            level.setText(result7);
            String result8 = b.getString("email");
            studentID.setText(result8);
            String result9 = b.getString("phone");
            level.setText(result9);
        }
    }
}