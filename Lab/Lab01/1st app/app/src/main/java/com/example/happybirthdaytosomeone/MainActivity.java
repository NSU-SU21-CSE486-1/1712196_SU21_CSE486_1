package com.example.happybirthdaytosomeone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    int num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            num1 = 0;
            num2 = 62 / num1;
            System.out.println(num2);
            println("Hello World");
        }catch (Exception e) {
            Log.e("MainActivity", "Happy BirthDay to Erin");
        }
    }
}