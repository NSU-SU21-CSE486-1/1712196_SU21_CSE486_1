package com.istiaksaif.Project01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project01.Activity.UniversityAffiliationActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup;
    private Button nextButton;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.name);
        dateOfBirth = findViewById(R.id.dateofbirth);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int nYear = calendar.get(Calendar.YEAR);
                int nMonth = calendar.get(Calendar.MONTH);
                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        nid = findViewById(R.id.nid);
        bloodGroup = findViewById(R.id.bloodgroup);
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info();
            }
        });
    }

    private void Info() {
        String FullName = fullName.getText().toString();
        String DateOfBirth = dateOfBirth.getText().toString();
        String NID = nid.getText().toString();
        String BloodGroup = bloodGroup.getText().toString();

        if (TextUtils.isEmpty(FullName)){
            Toast.makeText(MainActivity.this, "please enter your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(DateOfBirth)){
            Toast.makeText(MainActivity.this, "please enter DateOfBirth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(NID)){
            Toast.makeText(MainActivity.this, "please enter NID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (NID.length()<13){
            Toast.makeText(MainActivity.this, "Nid number minimum 13 and max 18 numbers", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(BloodGroup)){
            Toast.makeText(MainActivity.this, "please enter bloodGroup", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, UniversityAffiliationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",FullName);
        bundle.putString("dateofbirth",DateOfBirth);
        bundle.putString("nid",NID);
        bundle.putString("bloodgroup",BloodGroup);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            month = month+1;
            date = dayOfMonth+"/"+month+"/"+year;
            dateOfBirth.setText(date);
        }
    };
}