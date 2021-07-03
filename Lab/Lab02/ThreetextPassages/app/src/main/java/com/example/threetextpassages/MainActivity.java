package com.example.threetextpassages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.threetextpassages.Activity.SecondActivity;

public class MainActivity extends AppCompatActivity {

    private Button textOne, textTwo, textThree;
    public static final String choice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textOne = findViewById(R.id.passage1);
        textTwo = findViewById(R.id.passage2);
        textThree = findViewById(R.id.passage3);

        textOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(choice,"one");
                startActivity(intent);
            }
        });

        textTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(choice,"two");
                startActivity(intent);
            }
        });

        textThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(choice,"three");
                startActivity(intent);
            }
        });
    }
}