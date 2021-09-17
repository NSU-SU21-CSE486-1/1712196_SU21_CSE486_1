package com.istiaksaif.uniclubz.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Activity.SearchActivity;
import com.istiaksaif.uniclubz.Adaptar.ClubSugListAdapter;
import com.istiaksaif.uniclubz.Adaptar.ClubListAdapter;
import com.istiaksaif.uniclubz.Adaptar.JoinedClubListAdapter;
import com.istiaksaif.uniclubz.Adaptar.MembersAdapter;
import com.istiaksaif.uniclubz.Model.ClubJoinedListItem;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.Model.ClubSugListItem;
import com.istiaksaif.uniclubz.Model.MemberItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

public class ClubsFragment extends Fragment {

    private RecyclerView adminClubRecyclerView,joinClubRecyclerView,clubRecyclerView;
    private ClubListAdapter clubListAdapter;
    private ClubSugListAdapter ClubSugListAdapter;
    private JoinedClubListAdapter joinedClubListAdapter;
    private ArrayList<ClubListItem> clubItemArrayList;
    private ArrayList<ClubSugListItem> clubSugListItemArrayList;
    private ArrayList<ClubJoinedListItem> clubJoinedListItemArrayList;
    private DatabaseReference clubItemDatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private ImageView searchImg;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clubItemDatabaseRef = FirebaseDatabase.getInstance().getReference();
        clubItemArrayList = new ArrayList<>();

        adminClubRecyclerView = view.findViewById(R.id.ownClubRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        adminClubRecyclerView.setLayoutManager(layoutManager);
        adminClubRecyclerView.setHasFixedSize(true);
        GetDataFromFirebase();

        joinClubRecyclerView = view.findViewById(R.id.addedClubRecycler);
        joinClubRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        joinClubRecyclerView.setHasFixedSize(true);

        GetDataJoinedClub();
        clubJoinedListItemArrayList = new ArrayList<>();

        clubRecyclerView = view.findViewById(R.id.sugClubRecycler);
        clubRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        clubRecyclerView.setHasFixedSize(true);

        clubSugListItemArrayList = new ArrayList<>();
        GetData();
        clubSugListItemArrayList.clear();
        ClearAll();

        searchImg = view.findViewById(R.id.searchimg);
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetDataJoinedClub() {
        Query query = clubItemDatabaseRef.child("ClubInfo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clubJoinedListItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        if(!snapshot.child("admin").getValue().toString().equals(uid)) {
                            if (snapshot.child("membersList").child(uid).exists()) {
                                ClubJoinedListItem clubJoinedListItem = new ClubJoinedListItem();
                                clubJoinedListItem.setClubName(snapshot.child("clubName").getValue().toString());
                                clubJoinedListItem.setImage(snapshot.child("clubImage").getValue().toString());
                                clubJoinedListItem.setClubId(snapshot.child("clubId").getValue().toString());
                                clubJoinedListItem.setAdmin(snapshot.child("admin").getValue().toString());
                                if (snapshot.child("membersList").child(uid).exists()) {
                                    clubJoinedListItem.setStatus(snapshot.child("membersList").child(uid).child("status").getValue().toString());
                                } else {
                                    clubJoinedListItem.setStatus("");
                                }
                                clubJoinedListItemArrayList.add(clubJoinedListItem);
                            }
                        }
                    } catch (Exception e) {

                    }
                }
                joinedClubListAdapter = new JoinedClubListAdapter(getContext(), clubJoinedListItemArrayList);
                joinClubRecyclerView.setAdapter(joinedClubListAdapter);
                joinedClubListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                ClubSugListAdapter = new ClubSugListAdapter(getContext(), clubSugListItemArrayList);
                clubRecyclerView.setAdapter(ClubSugListAdapter);
                ClubSugListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void GetDataFromFirebase() {
        Query query = clubItemDatabaseRef.child("ClubInfo").orderByChild("admin").equalTo(uid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        ClubListItem clubListItem = new ClubListItem();
                        clubListItem.setClubName(snapshot.child("clubName").getValue().toString());
                        clubListItem.setImage(snapshot.child("clubImage").getValue().toString());
                        clubListItem.setClubId(snapshot.child("clubId").getValue().toString());
                        clubItemArrayList.add(clubListItem);

                    } catch (Exception e) {

                    }
                }
                clubListAdapter = new ClubListAdapter(getContext(), clubItemArrayList);
                adminClubRecyclerView.setAdapter(clubListAdapter);
                clubListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if (clubItemArrayList !=null){
            clubItemArrayList.clear();
            if (clubListAdapter!=null){
                clubListAdapter.notifyDataSetChanged();
            }
        }
        clubItemArrayList = new ArrayList<>();
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        return view;
    }
}