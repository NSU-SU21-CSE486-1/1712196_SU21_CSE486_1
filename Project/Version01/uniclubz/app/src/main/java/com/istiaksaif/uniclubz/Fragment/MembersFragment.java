package com.istiaksaif.uniclubz.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Activity.RequestActivity;
import com.istiaksaif.uniclubz.Adaptar.MembersAdapter;
import com.istiaksaif.uniclubz.Model.MemberItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

public class MembersFragment extends Fragment {
    private Toolbar toolbar;
    private TextView clubName,editImage,invite,request;
    private ImageView clubImg;
    private RecyclerView adminRecyclerView,memberRecyclerView;
    private MembersAdapter memberListAdapter,adminListAdapter;
    private ArrayList<MemberItem> memberItemArrayList,adminItemArrayList;

    private DatabaseReference DatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Intent intent;
    private String clubId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = getActivity().getIntent();
        clubId = intent.getStringExtra("clubId");

        DatabaseRef = FirebaseDatabase.getInstance().getReference();
        memberItemArrayList = new ArrayList<>();
        adminItemArrayList = new ArrayList<>();

        memberRecyclerView = view.findViewById(R.id.clubmembersrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memberRecyclerView.setLayoutManager(layoutManager);
        memberRecyclerView.setHasFixedSize(true);
        GetData();

        adminRecyclerView = view.findViewById(R.id.clubadminrecycle);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        adminRecyclerView.setLayoutManager(layoutManager1);
        adminRecyclerView.setHasFixedSize(true);
        GetDataForAdmin();

        request = view.findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RequestActivity.class);
                intent.putExtra("clubId",clubId);
                startActivity(intent);
            }
        });
        Query query = DatabaseRef.child("ClubInfo").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        for (DataSnapshot snapshot1 : dataSnapshot.child("membersList").getChildren()) {
                            if (snapshot1.child("status").getValue().toString().equals("pending")) {
                                String req = Long.toString(dataSnapshot.child("membersList").getChildrenCount());
                                request.setText("request " + req);
                                if (Integer.parseInt(req) == 0) {
                                    request.setTextColor(getResources().getColor(R.color.dark_blue));
                                } else {
                                    request.setTextColor(getResources().getColor(R.color.pink));
                                }
                            }
                        }
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void GetData() {
        Query query = DatabaseRef.child("ClubInfo").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memberItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        for (DataSnapshot snapshot1:snapshot.child("membersList").getChildren()){
                            MemberItem memberitem = new MemberItem();
                            if(snapshot1.child("status").getValue().toString().equals("confirm")) {
                                String userid = snapshot1.child("userId").getValue().toString();
                                memberitem.setUserId(userid);
                                memberitem.setStatus(snapshot1.child("status").getValue().toString());
                                Query query1 = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("userId").equalTo(userid);
                                query1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                            memberitem.setName(dataSnapshot1.child("name").getValue().toString());
                                            memberitem.setImage(dataSnapshot1.child("imageUrl").getValue().toString());
                                            for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("UniAffiliation").getChildren()) {
                                                memberitem.setUniname(dataSnapshot2.child("UniName").getValue().toString());
                                                memberitem.setDepartment(dataSnapshot2.child("department").getValue().toString());

                                            }
                                            memberItemArrayList.add(memberitem);
                                        }
                                        memberListAdapter = new MembersAdapter(getContext(), memberItemArrayList);
                                        memberRecyclerView.setAdapter(memberListAdapter);
                                        memberListAdapter.notifyDataSetChanged();
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

    private void GetDataForAdmin() {
        Query query = DatabaseRef.child("ClubInfo").orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminItemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MemberItem memberitem = new MemberItem();
                    try {
                        String userid = snapshot.child("admin").getValue().toString();
                        memberitem.setUserId(userid);
                        Query query1 = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("userId").equalTo(userid);
                        query1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                adminItemArrayList.clear();
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    memberitem.setName(dataSnapshot1.child("name").getValue().toString());
                                    memberitem.setImage(dataSnapshot1.child("imageUrl").getValue().toString());
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("UniAffiliation").getChildren()) {
                                        memberitem.setUniname(dataSnapshot2.child("UniName").getValue().toString());
                                        memberitem.setDepartment(dataSnapshot2.child("department").getValue().toString());

                                    }
                                    adminItemArrayList.add(memberitem);
                                }
                                adminListAdapter = new MembersAdapter(getContext(), adminItemArrayList);
                                adminRecyclerView.setAdapter(adminListAdapter);
                                adminListAdapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName,editImage);
        ((ClubActivity)getActivity()).setimg(clubImg);
    }
}