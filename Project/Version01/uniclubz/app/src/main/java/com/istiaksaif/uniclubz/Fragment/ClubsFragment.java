package com.istiaksaif.uniclubz.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Adaptar.clubJoinListAdapter;
import com.istiaksaif.uniclubz.Adaptar.clubListAdapter;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.Model.ClubSugListItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

public class ClubsFragment extends Fragment {

    private RecyclerView adminClubRecyclerView,joinClubRecyclerView,clubRecyclerView;
    private clubListAdapter clubListAdapter;
    private clubJoinListAdapter clubJoinListAdapter;
    private ArrayList<ClubListItem> clubItemArrayList;
    private ArrayList<ClubSugListItem> clubSugListItemArrayList;
    private DatabaseReference clubItemDatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

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

        clubRecyclerView = view.findViewById(R.id.sugClubRecycler);
        clubRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        clubRecyclerView.setHasFixedSize(true);

        clubSugListItemArrayList = new ArrayList<>();
        GetData();
        clubSugListItemArrayList.clear();
        ClearAll();
    }

    private void GetData() {
        Query query = clubItemDatabaseRef.child("ClubInfo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clubSugListItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        if(!snapshot.child("admin").getValue().toString().equals(uid)){
                            ClubSugListItem clubSugListItem = new ClubSugListItem();
                            clubSugListItem.setClubName(snapshot.child("clubName").getValue().toString());
                            clubSugListItem.setImage(snapshot.child("clubImage").getValue().toString());
                            clubSugListItem.setClubId(snapshot.child("clubId").getValue().toString());
                            clubSugListItem.setAdmin(snapshot.child("admin").getValue().toString());
                            clubSugListItemArrayList.add(clubSugListItem);
                        }
                    } catch (Exception e) {

                    }
                }
                clubJoinListAdapter = new clubJoinListAdapter(getContext(), clubSugListItemArrayList);
                clubRecyclerView.setAdapter(clubJoinListAdapter);
                clubJoinListAdapter.notifyDataSetChanged();
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
                clubListAdapter = new clubListAdapter(getContext(), clubItemArrayList);
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