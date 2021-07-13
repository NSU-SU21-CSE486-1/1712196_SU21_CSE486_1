package com.istiaksaif.fragmentex1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Open,Close;
    private View fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Open = findViewById(R.id.openbutton);
        Close = findViewById(R.id.closebutton);
        fragment = findViewById(R.id.fragment);
        fragment.setVisibility(View.GONE);

        Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open.setVisibility(View.GONE);
                Close.setVisibility(View.VISIBLE);
                fragment.setVisibility(View.VISIBLE);
            }
        });

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open.setVisibility(View.VISIBLE);
                Close.setVisibility(View.GONE);
                fragment.setVisibility(View.GONE);
            }
        });
    }
}