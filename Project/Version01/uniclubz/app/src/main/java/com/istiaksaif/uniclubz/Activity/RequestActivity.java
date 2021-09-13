package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Adaptar.MembersAdapter;
import com.istiaksaif.uniclubz.Model.MemberItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private RecyclerView reqRecyclerView;
    private MembersAdapter reqListAdapter;
    private ArrayList<MemberItem> reqItemArrayList;

    private DatabaseReference DatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Intent intent;
    private String clubId;
    private TextView clubname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        intent = getIntent();
        clubId = intent.getStringExtra("clubId");

        clubname = findViewById(R.id.clubname);
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

        DatabaseRef = FirebaseDatabase.getInstance().getReference();
        reqItemArrayList = new ArrayList<>();

        reqRecyclerView = findViewById(R.id.membersreqrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reqRecyclerView.setLayoutManager(layoutManager);
        reqRecyclerView.setHasFixedSize(true);
        GetData();
    }
    private void GetData() {
        Query query = DatabaseRef.child("ClubInfo").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reqItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MemberItem memberitem = new MemberItem();
                    clubname.setText(snapshot.child("clubName").getValue().toString());
                    try {
                        for (DataSnapshot snapshot1:snapshot.child("membersList").getChildren()){
                            if(snapshot1.child("status").getValue().toString().equals("pending")) {
                                String userid = snapshot1.child("userId").getValue().toString();
                                memberitem.setUserId(userid);
                                Query query1 = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("userId").equalTo(userid);
                                query1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        reqItemArrayList.clear();
                                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                            memberitem.setName(dataSnapshot1.child("name").getValue().toString());
                                            memberitem.setImage(dataSnapshot1.child("imageUrl").getValue().toString());
                                            for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("UniAffiliation").getChildren()) {
                                                memberitem.setUniname(dataSnapshot2.child("UniName").getValue().toString());
                                                memberitem.setDepartment(dataSnapshot2.child("department").getValue().toString());

                                            }
                                            reqItemArrayList.add(memberitem);
                                        }
                                        reqListAdapter = new MembersAdapter(RequestActivity.this, reqItemArrayList);
                                        reqRecyclerView.setAdapter(reqListAdapter);
                                        reqListAdapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}