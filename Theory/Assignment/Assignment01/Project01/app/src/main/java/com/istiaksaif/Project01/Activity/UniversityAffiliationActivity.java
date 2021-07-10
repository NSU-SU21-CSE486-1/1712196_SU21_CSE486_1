package com.istiaksaif.Project01.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project01.R;

public class UniversityAffiliationActivity extends AppCompatActivity {

    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup,studentID;
    private MaterialAutoCompleteTextView uniName,autoCompleteTextView,autoCompleteTextView1;
    private Button nextButton;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_affiliation);

        b = getIntent().getExtras();

        fullName = findViewById(R.id.name);
        dateOfBirth = findViewById(R.id.dateofbirth);
        nid = findViewById(R.id.nid);
        bloodGroup = findViewById(R.id.bloodgroup);
        studentID = findViewById(R.id.studentId);
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info();
            }
        });

        if (b!=null){
            String result = b.getString("name");
            fullName.setText(result);
            String result1 = b.getString("dateofbirth");
            dateOfBirth.setText(result1);
            String result3 = b.getString("bloodgroup");
            bloodGroup.setText(result3);
            String result2 = b.getString("nid");
            nid.setText(result2);
        }

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

        Intent intent = new Intent(UniversityAffiliationActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uniname",UNINAME);
        bundle.putString("id",STUDENTID);
        bundle.putString("department",Department);
        bundle.putString("studentLevel",StudentLevel);
        intent.putExtras(bundle);
        intent.putExtras(b);
        startActivity(intent);
    }
}