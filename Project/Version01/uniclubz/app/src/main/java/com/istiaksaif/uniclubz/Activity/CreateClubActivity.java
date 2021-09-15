package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

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
import com.google.firebase.storage.FirebaseStorage;
import com.istiaksaif.uniclubz.R;

import java.util.HashMap;

import static com.istiaksaif.uniclubz.Utils.optionUniName.optionUniName;

public class CreateClubActivity extends AppCompatActivity {

    private TextInputEditText clubName,clubEmail,invite;
    private MaterialAutoCompleteTextView clubUniName,privacy;
    private Button nextButton;
    private Toolbar toolBar;

    private DatabaseReference databaseReference,databaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private ProgressDialog progressDialog;
    private String ClubId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_club);

        progressDialog = new ProgressDialog(this);

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

        clubName = findViewById(R.id.clubname);
        clubEmail = findViewById(R.id.clubeamil);
        invite = findViewById(R.id.invite);
        nextButton = findViewById(R.id.create_club_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info();
            }
        });

        clubUniName = findViewById(R.id.clubuniName);
        TextInputLayout textInputLayoutUniName = findViewById(R.id.clubuninamelayout);
        ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(this,R.layout.usertype_item,optionUniName);
        ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);

        privacy = findViewById(R.id.clubprivacy);
        TextInputLayout textInputLayout = findViewById(R.id.privacydropdrown);
        String []option = {"public","private","University Student"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.usertype_item,option);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String uniname = ""+dataSnapshot.child("UniName").getValue();

                    clubUniName.setText(uniname);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CreateClubActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void Info() {
        String CLUBNAME = clubName.getText().toString();
        String CLUBEMAIL = clubEmail.getText().toString();
        String CLUBUNINAME = clubUniName.getText().toString();
        String PRIVACY = privacy.getText().toString();
        String INVITE = invite.getText().toString();

        if (TextUtils.isEmpty(CLUBNAME)){
            Toast.makeText(CreateClubActivity.this, "please enter your Club Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(CLUBEMAIL)){
            Toast.makeText(CreateClubActivity.this, "please enter Your Club Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(CLUBUNINAME)){
            Toast.makeText(CreateClubActivity.this, "please enter Club University Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(PRIVACY)){
            Toast.makeText(CreateClubActivity.this, "please enter your club Privacy", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        ClubId = databaseReference.child(uid).push().getKey();

        HashMap<String, Object> result = new HashMap<>();
        result.put("clubName", CLUBNAME);
        result.put("clubEmail", CLUBEMAIL);
        result.put("clubUniName", CLUBUNINAME);
        result.put("clubPrivacy", PRIVACY);
        result.put("clubDes", "");
        result.put("clubPhone", "");
        result.put("clubWeb", "");
        result.put("admin",uid);
        result.put("clubId",ClubId);
        result.put("clubImage","https://firebasestorage.googleapis.com/v0/b/uniclubz.appspot.com/o/1628247632703.android.app.ContextImpl%24ApplicationContentResolver%40225a7c6c?alt=media&token=1c08d730-93ea-494e-97c2-8c2975619ba8");

        databaseRef.child(ClubId).updateChildren(result)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(CreateClubActivity.this,ClubActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(CreateClubActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}