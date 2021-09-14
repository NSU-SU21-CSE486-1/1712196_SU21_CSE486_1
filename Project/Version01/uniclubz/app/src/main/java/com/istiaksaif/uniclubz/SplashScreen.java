package com.istiaksaif.uniclubz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Activity.LogInActivity;
import com.istiaksaif.uniclubz.Activity.UserClubHomeActivity;
import com.istiaksaif.uniclubz.Activity.UserHomeActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

    }
    private void checkUserInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        Query query = databaseReference.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("nid").getValue(String.class).equals("")) {
                    new Handler().postDelayed(new Runnable(){
                        public void run(){
                            Intent intent = new Intent(SplashScreen.this, LogInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },SPLASH_TIME_OUT);
                }
                else{
                    new Handler().postDelayed(new Runnable(){
                        public void run(){
                            Intent intent = new Intent(SplashScreen.this, UserHomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },SPLASH_TIME_OUT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            checkUserInfo();
        }else{
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    Intent intent = new Intent(SplashScreen.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
    }
}