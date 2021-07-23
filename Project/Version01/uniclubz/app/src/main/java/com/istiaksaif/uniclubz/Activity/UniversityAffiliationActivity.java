package com.istiaksaif.uniclubz.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.R;

import java.util.HashMap;

public class UniversityAffiliationActivity extends AppCompatActivity {

    private TextInputEditText studentID;
    private MaterialAutoCompleteTextView uniName,autoCompleteTextView,autoCompleteTextView1;
    private Button nextButton;
    private Toolbar toolBar;

    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_affiliation);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        studentID = findViewById(R.id.studentId);
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info();
            }
        });

        uniName = findViewById(R.id.uniName);
        TextInputLayout textInputLayoutUniName = findViewById(R.id.uninamelayout);
        String []optionUniName = {"North South University","East West University","Dhaka University"
                ,"BRAC University","AIUB","IUB","Shahjalal University of Science and Technology",
                "Rajshahi University of Engineering & Technology","RU","Deffordial University"};
        ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(this,R.layout.usertype_item,optionUniName);
        ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);

        autoCompleteTextView = findViewById(R.id.department);
        TextInputLayout textInputLayout = findViewById(R.id.departmentdropdown);
        String []option = {"CSE","BBA","Economics","Bio Chemistry","Architecture","Pharmacy","EEE","Math","Physics"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.usertype_item,option);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);


        autoCompleteTextView1 = findViewById(R.id.level);
        TextInputLayout textInputLayout1 = findViewById(R.id.dropdown);
        String []option1 = {"UnderGraduate","MS","PhD","Post-Doc"};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this,R.layout.usertype_item,option1);
        ((MaterialAutoCompleteTextView) textInputLayout1.getEditText()).setAdapter(arrayAdapter1);

        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String uniname = ""+dataSnapshot.child("UniName").getValue();
                    String dep = ""+dataSnapshot.child("department").getValue();
                    String level = ""+dataSnapshot.child("level").getValue();
                    String receivenstudentid = ""+dataSnapshot.child("studentId").getValue();

                    uniName.setText(uniname);
                    autoCompleteTextView.setText(dep);
                    autoCompleteTextView1.setText(level);
                    studentID.setText(receivenstudentid);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UniversityAffiliationActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void Info() {
        String UNINAME = uniName.getText().toString();
        String STUDENTID = studentID.getText().toString();
        String Department = autoCompleteTextView.getText().toString();
        String StudentLevel = autoCompleteTextView1.getText().toString();

        if (TextUtils.isEmpty(UNINAME)){
            Toast.makeText(UniversityAffiliationActivity.this, "please enter your University Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(STUDENTID)){
            Toast.makeText(UniversityAffiliationActivity.this, "please enter Your ID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(Department)){
            Toast.makeText(UniversityAffiliationActivity.this, "please enter Department", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(StudentLevel)){
            Toast.makeText(UniversityAffiliationActivity.this, "please enter your study Level", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        HashMap<String, Object> result = new HashMap<>();
        result.put("UniName", UNINAME);
        result.put("studentId", STUDENTID);
        result.put("department", Department);
        result.put("level", StudentLevel);


        databaseReference.child(uid).updateChildren(result)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(UniversityAffiliationActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}