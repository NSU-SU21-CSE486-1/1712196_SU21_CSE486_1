package com.example.threetextpassages.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.threetextpassages.R;

import static com.example.threetextpassages.MainActivity.choice;

public class SecondActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView passage;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();
        String Choice = intent.getStringExtra(choice);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        passage = findViewById(R.id.passage);

        switch (Choice){
            case "one":
                passage.setText(R.string.one);
                getSupportActionBar().setTitle(R.string.oneTitle);
                break;
            case "two":
                passage.setText(R.string.two);
                getSupportActionBar().setTitle(R.string.twoTitle);
                break;
            case "three":
                passage.setText(R.string.three);
                getSupportActionBar().setTitle(R.string.threeTitle);
                break;
        }
    }
}