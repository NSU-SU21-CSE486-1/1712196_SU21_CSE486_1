package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.Utils.ImageGetHelper;
import com.squareup.picasso.Picasso;

public class UserClubHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView clubName;
    private ImageView clubImage;

    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Intent intent;
    private String clubId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_club_home);
        intent = getIntent();
        clubId = intent.getStringExtra("clubId");

        toolbar = findViewById(R.id.userclubtoolbar);
        clubImage = findViewById(R.id.clubimage);
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
//        getSupportActionBar().hide();

        //clubName
        clubName = findViewById(R.id.clubname);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        Query query = databaseReference.orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String clubname = ""+dataSnapshot.child("clubName").getValue();
                    String clubimage = ""+dataSnapshot.child("clubImage").getValue();

                    clubName.setText(clubname);
                    try {
                        Picasso.get().load(clubimage).resize(320,320).into(clubImage);
                    }catch (Exception e){
                        Picasso.get().load(R.drawable.dropdown).into(clubImage);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserClubHomeActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();

        }return true;
    }
}