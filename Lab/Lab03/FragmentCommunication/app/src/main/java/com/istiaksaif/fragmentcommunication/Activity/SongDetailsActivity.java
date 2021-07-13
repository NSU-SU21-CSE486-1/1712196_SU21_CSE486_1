package com.istiaksaif.fragmentcommunication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.istiaksaif.fragmentcommunication.R;

import static com.istiaksaif.fragmentcommunication.MainActivity.choice;

public class SongDetailsActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private View fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Song Details");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragment = findViewById(R.id.fragment);
        fragment.setVisibility(View.VISIBLE);

    }
}