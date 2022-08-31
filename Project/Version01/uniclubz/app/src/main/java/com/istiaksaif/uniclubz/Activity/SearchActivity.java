package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Adaptar.ClubListAdapter;
import com.istiaksaif.uniclubz.Adaptar.ClubSugListAdapter;
import com.istiaksaif.uniclubz.Adaptar.JoinedClubListAdapter;
import com.istiaksaif.uniclubz.Model.ClubJoinedListItem;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.Model.ClubSugListItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ClubSugListAdapter ClubSugListAdapter;
    private ArrayList<ClubSugListItem> clubSugListItemArrayList;
    private Toolbar toolBar;
    private DatabaseReference clubItemDatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(" Search Club");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView = findViewById(R.id.searchview);
        recyclerView = findViewById(R.id.searchClubRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }
    private void GetData() {
        Query query = clubItemDatabaseRef.child("ClubInfo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clubSugListItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        ClubSugListItem clubSugListItem = new ClubSugListItem();
                        if(!snapshot.child("admin").getValue().toString().equals(uid)) {
                            if (!snapshot.child("membersList").exists()){
                                clubSugListItem.setClubName(snapshot.child("clubName").getValue().toString());
                                clubSugListItem.setImage(snapshot.child("clubImage").getValue().toString());
                                clubSugListItem.setClubId(snapshot.child("clubId").getValue().toString());
                                clubSugListItem.setAdmin(snapshot.child("admin").getValue().toString());

                                clubSugListItem.setStatus("");

                                clubSugListItemArrayList.add(clubSugListItem);
                            }else if(snapshot.child("membersList").exists()) {
                                if(!snapshot.child("membersList").child(uid).exists()){
                                    clubSugListItem.setClubName(snapshot.child("clubName").getValue().toString());
                                    clubSugListItem.setImage(snapshot.child("clubImage").getValue().toString());
                                    clubSugListItem.setClubId(snapshot.child("clubId").getValue().toString());
                                    clubSugListItem.setAdmin(snapshot.child("admin").getValue().toString());

                                    clubSugListItem.setStatus("");

                                    clubSugListItemArrayList.add(clubSugListItem);
                                } else if (!snapshot.child("membersList").child(uid).child("status").getValue().toString().equals("confirm")) {
                                    clubSugListItem.setClubName(snapshot.child("clubName").getValue().toString());
                                    clubSugListItem.setImage(snapshot.child("clubImage").getValue().toString());
                                    clubSugListItem.setClubId(snapshot.child("clubId").getValue().toString());
                                    clubSugListItem.setAdmin(snapshot.child("admin").getValue().toString());
                                    if (snapshot.child("membersList").child(uid).exists()) {
                                        clubSugListItem.setStatus(snapshot.child("membersList").child(uid).child("status").getValue().toString());
                                    } else {
                                        clubSugListItem.setStatus("");
                                    }
                                    clubSugListItemArrayList.add(clubSugListItem);
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                }
                ClubSugListAdapter = new ClubSugListAdapter(SearchActivity.this, clubSugListItemArrayList);
                recyclerView.setAdapter(ClubSugListAdapter);
                ClubSugListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        clubItemDatabaseRef = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        if(searchView !=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<ClubSugListItem> searchLists = new ArrayList<>();
        for(ClubSugListItem obj:clubSugListItemArrayList){
            if(obj.getClubName().toLowerCase().contains(str.toLowerCase())){
                searchLists.add(obj);
            }
        }
        ClubSugListAdapter = new ClubSugListAdapter(SearchActivity.this,searchLists);
        recyclerView.setAdapter(ClubSugListAdapter);
    }
}