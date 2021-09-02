package com.istiaksaif.Project03.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project03.R;

public class SecondActivity extends AppCompatActivity {

    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup,phone,email;
    private TextView uniName,studentID,department,level;
    private Button nextButton;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        b = getIntent().getExtras();

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
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info();
            }
        });

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
        }
    }
    private void Info() {
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();

        if (TextUtils.isEmpty(Email)){
            Toast.makeText(SecondActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(Phone)){
            Toast.makeText(SecondActivity.this, "please enter phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(SecondActivity.this, FinalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email",Email);
        bundle.putString("phone",Phone);
        intent.putExtras(bundle);
        intent.putExtras(b);
        startActivity(intent);
    }
}