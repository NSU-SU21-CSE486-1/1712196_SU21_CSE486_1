package com.istiaksaif.uniclubz.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.R;

public class clubProfileFragment extends Fragment {

    private Toolbar toolbar;
    private TextView clubName;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        clubName.setVisibility(View.VISIBLE);
        clubName.setMaxHeight(80);
    }
    @Override
    public void onStop() {
        super.onStop();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName);
    }
}