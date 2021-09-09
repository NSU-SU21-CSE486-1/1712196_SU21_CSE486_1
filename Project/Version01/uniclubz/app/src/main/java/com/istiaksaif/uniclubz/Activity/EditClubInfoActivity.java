package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.R;

import java.util.HashMap;

public class EditClubInfoActivity extends AppCompatActivity {

    private TextInputEditText clubName,clubDes,clubUniName,email,phone,website;
    private Button submit;

    private String clubId;
    private Toolbar toolBar;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_info_edit);
        intent = getIntent();
        clubId = intent.getStringExtra("clubId");

        progressDialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("ClubInfo");


        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clubUniName = findViewById(R.id.clubuniname);
        clubName = findViewById(R.id.clubname);
        clubDes = findViewById(R.id.clubdes);
        email = findViewById(R.id.club_email);
        phone = findViewById(R.id.clubphone);
        website = findViewById(R.id.clubweb);
        submit = findViewById(R.id.next_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info();
            }
        });
    }
    private void Info() {
        String ClubName = clubName.getText().toString();
        String ClubUNIName = clubUniName.getText().toString();
        String ClubDESCRIPTION = clubDes.getText().toString();
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();
        String Website = website.getText().toString();

        if (TextUtils.isEmpty(ClubName)){
            Toast.makeText(EditClubInfoActivity.this, "please enter Club Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(ClubUNIName)){
            Toast.makeText(EditClubInfoActivity.this, "please enter ClubUniName", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(ClubDESCRIPTION)){
            Toast.makeText(EditClubInfoActivity.this, "please enter Club Description", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(Email)){
            Toast.makeText(EditClubInfoActivity.this, "please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        HashMap<String, Object> result = new HashMap<>();
        result.put("clubName", ClubName);
        result.put("clubDes", ClubDESCRIPTION);
        result.put("clubEmail", Email);
        result.put("clubUniName", ClubUNIName);
        result.put("clubPhone", Phone);
        result.put("clubWeb", Website);


        databaseReference.child(clubId).updateChildren(result)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(EditClubInfoActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}