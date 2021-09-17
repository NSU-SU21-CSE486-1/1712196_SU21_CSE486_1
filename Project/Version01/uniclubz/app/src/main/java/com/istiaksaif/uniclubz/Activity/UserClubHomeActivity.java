package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.istiaksaif.uniclubz.Adaptar.BloodDonateAdapter;
import com.istiaksaif.uniclubz.Adaptar.EventsAdapter;
import com.istiaksaif.uniclubz.Model.BloodDonateItem;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.Utils.ImageGetHelper;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UserClubHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView clubName,clubDes,clubPrivacy,member,joinButton;
    private ImageView clubImage;

    private DatabaseReference databaseReference,eventsDatabaseRef,bloodDonateDatabase;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Intent intent;
    private String clubId;

    private RecyclerView eventsRecyclerView,bloodDonateRecyclerView;
    private EventsAdapter eventsAdapter;
    private ArrayList<EventItem> eventList;
    private BloodDonateAdapter bloodDonateAdapter;
    private ArrayList<BloodDonateItem> bloodDonateItemArrayList;
    private TextView event,blood;

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

        //clubName
        clubName = findViewById(R.id.clubname);
        clubDes = findViewById(R.id.clubdescription);
        clubPrivacy = findViewById(R.id.clubprivacy);
        member = findViewById(R.id.clubmembers);
        joinButton = findViewById(R.id.joinbutton);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        Query query = databaseReference.orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    try {
                        String clubname = dataSnapshot.child("clubName").getValue().toString();
                        String clubimage = dataSnapshot.child("clubImage").getValue().toString();
                        clubDes.setText(dataSnapshot.child("clubDes").getValue().toString());
                        clubPrivacy.setText(dataSnapshot.child("clubPrivacy").getValue()+" group ");
                        member.setText(".  "+Long.toString(dataSnapshot.child("membersList").getChildrenCount())+" members ");
                        clubName.setText(clubname);
                        if (dataSnapshot.child("membersList").child(uid).exists()) {
                            String status = dataSnapshot.child("membersList").child(uid).child("status").getValue().toString();
                            if(status.equals("pending")){
                                joinButton.setText("pending");
                            }else if(status.equals("confirm")){
                                joinButton.setVisibility(View.GONE);
                            }
                        } else {
                            joinButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    HashMap<String, Object> result = new HashMap<>();
                                    result.put("userId", uid);
                                    result.put("status", "pending");
                                    databaseReference.child(clubId).child("membersList").child(uid).updateChildren(result);
                                    joinButton.setText("pending");
                                }
                            });
                        }
                        try {
                            Picasso.get().load(clubimage).resize(320,320).into(clubImage);
                        }catch (Exception e){
                            Picasso.get().load(R.drawable.dropdown).into(clubImage);
                        }
                    }catch (Exception e){

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserClubHomeActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bloodDonateDatabase = FirebaseDatabase.getInstance().getReference();
        bloodDonateItemArrayList = new ArrayList<>();

        bloodDonateRecyclerView = findViewById(R.id.blooddonate);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        bloodDonateRecyclerView.setLayoutManager(layoutManager1);
        bloodDonateRecyclerView.setHasFixedSize(true);
        GetData();

        event = findViewById(R.id.clevent);
        blood = findViewById(R.id.bloodtitle);
        eventsDatabaseRef = FirebaseDatabase.getInstance().getReference();
        eventList = new ArrayList<>();

        eventsRecyclerView = findViewById(R.id.clubevents);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventsRecyclerView.setLayoutManager(layoutManager);
        eventsRecyclerView.setHasFixedSize(true);
        GetDataFromFirebase();
    }
    private void GetData() {
        Query query = bloodDonateDatabase.child("BloodReqList").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        BloodDonateItem bloodDonateItem = new BloodDonateItem();
                        bloodDonateItem.setBag(snapshot.child("bag").getValue().toString());
                        bloodDonateItem.setBloodgroup(snapshot.child("bloodGroup").getValue().toString());
                        bloodDonateItem.setContactphone(snapshot.child("contactPhone").getValue().toString());
                        bloodDonateItem.setTime(snapshot.child("time").getValue().toString());
                        bloodDonateItem.setHosname(snapshot.child("hosName").getValue().toString());
                        bloodDonateItem.setHosaddress(snapshot.child("hosAddress").getValue().toString());
                        String date = snapshot.child("date").getValue().toString();
                        String d = null;
                        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
                        try {
                            Date date1 = input.parse(date);
                            d = output.format(date1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        bloodDonateItem.setDate(d);
                        bloodDonateItemArrayList.add(bloodDonateItem);

                    } catch (Exception e) {

                    }
                }
                bloodDonateAdapter = new BloodDonateAdapter(UserClubHomeActivity.this, bloodDonateItemArrayList);
                bloodDonateRecyclerView.setAdapter(bloodDonateAdapter);
                bloodDonateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void GetDataFromFirebase() {
        Query query = eventsDatabaseRef.child("EventList").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        EventItem eventItem = new EventItem();
                        eventItem.setEventName(snapshot.child("EventName").getValue().toString());
                        eventItem.setImage(snapshot.child("image").getValue().toString());
                        eventItem.setEventId(snapshot.child("eventId").getValue().toString());
                        eventItem.setTime(snapshot.child("startTime").getValue().toString());
                        String date = snapshot.child("StartDate").getValue().toString();
                        eventItem.setParticipant(Long.toString(snapshot.child("Participant").getChildrenCount()));
                        String d = null;
                        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
                        try {
                            Date date1 = input.parse(date);
                            d = output.format(date1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        eventItem.setDate(d);
                        if (snapshot.child("Participant").child(uid).exists()) {
                            eventItem.setStatus(snapshot.child("Participant").child(uid).child("status").getValue().toString());
                        } else {
                            eventItem.setStatus("");
                        }

                        eventList.add(eventItem);

                    } catch (Exception e) {

                    }
                }
                eventsAdapter = new EventsAdapter(UserClubHomeActivity.this, eventList);
                eventsRecyclerView.setAdapter(eventsAdapter);
                eventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ClearAll(){
        if (eventList !=null){
            eventList.clear();
            if (eventsAdapter!=null){
                eventsAdapter.notifyDataSetChanged();
            }
        }
        eventList = new ArrayList<>();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();

        }return true;
    }
}