package com.istiaksaif.uniclubz.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Activity.LogInActivity;
import com.istiaksaif.uniclubz.R;

public class BloodReqFragment extends Fragment {
    private Toolbar toolbar;
    private TextView clubName;
    private int titleBarHeight;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName);
    }
}