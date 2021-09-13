package com.istiaksaif.uniclubz.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Activity.EditClubInfoActivity;
import com.istiaksaif.uniclubz.R;

public class clubProfileFragment extends Fragment {

    private Toolbar toolbar;
    private TextView clubName,editImage,clubUniName,clubDescription,clubEmail,clubWeb,clubPhone,editinfo;
    private ImageView clubImg;
    private Intent intent;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = getActivity().getIntent();
        String clubId = intent.getStringExtra("clubId");

        clubUniName = view.findViewById(R.id.clubuniname);
        clubDescription = view.findViewById(R.id.clubdes);
        clubEmail = view.findViewById(R.id.clubemail);
        clubPhone = view.findViewById(R.id.clubphonenum);
        clubWeb = view.findViewById(R.id.clubwebsite);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        Query query = databaseReference.orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    try {
                        clubUniName.setText(""+dataSnapshot.child("clubUniName").getValue());
                        clubDescription.setText("  "+dataSnapshot.child("clubDes").getValue());
                        clubEmail.setText("   "+dataSnapshot.child("clubEmail").getValue());
                        clubPhone.setText("   "+dataSnapshot.child("clubPhone").getValue());
                        clubWeb.setText("   "+dataSnapshot.child("clubWeb").getValue());
                    } catch (Exception e) {

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Some Thing Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        editinfo = view.findViewById(R.id.editinfo);
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditClubInfoActivity.class);
                intent.putExtra("clubId",clubId);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_club_profile, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        clubName = ((ClubActivity)getActivity()).findViewById(R.id.clubname);
        editImage = ((ClubActivity)getActivity()).findViewById(R.id.imageedit);
        clubName.setVisibility(View.VISIBLE);
        editImage.setVisibility(View.VISIBLE);
        clubName.setMaxHeight(80);
        clubImg = ((ClubActivity)getActivity()).findViewById(R.id.clubimage);
        clubImg.setVisibility(View.VISIBLE);

    }
    @Override
    public void onStop() {
        super.onStop();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName,editImage);
        ((ClubActivity)getActivity()).setimg(clubImg);
    }
}