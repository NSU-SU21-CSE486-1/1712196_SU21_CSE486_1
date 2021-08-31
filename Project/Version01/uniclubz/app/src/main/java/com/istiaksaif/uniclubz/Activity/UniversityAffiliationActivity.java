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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.istiaksaif.uniclubz.Adaptar.EditUniAffiliationAdapter;
import com.istiaksaif.uniclubz.Adaptar.UniAffiliationRetriveAdapter;
import com.istiaksaif.uniclubz.Model.EditUniAffiliationItem;
import com.istiaksaif.uniclubz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static com.istiaksaif.uniclubz.Utils.optionUniName.optionUniName;

public class UniversityAffiliationActivity extends AppCompatActivity {

    public static Button nextButton;
    private Toolbar toolBar;

    public static FloatingActionButton fab;
    private RecyclerView recyclerView;
    private EditUniAffiliationAdapter editUniAffiliationAdapter;
    private ArrayList<EditUniAffiliationItem> itemList;

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

        fab = (FloatingActionButton) findViewById(R.id.floatingButtonAdd);
        fab.bringToFront();

        recyclerView = findViewById(R.id.uniaffiliationrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        EditUniAffiliationItem ListItem = new EditUniAffiliationItem();
        ListItem.setlOpen("increase");
        itemList.add(ListItem);
        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    try {
                        for (DataSnapshot snapshot1:dataSnapshot.child("UniAffiliation").getChildren()){
                            EditUniAffiliationItem ListItem = new EditUniAffiliationItem();
                            ListItem.setUniName(snapshot1.child("UniName").getValue().toString());
                            ListItem.setId(snapshot1.child("studentId").getValue().toString());
                            ListItem.setDepartment(snapshot1.child("department").getValue().toString());
                            ListItem.setLevel(snapshot1.child("level").getValue().toString());
                            itemList.add(ListItem);
                        }

                    }catch (Exception e) {
                    }
                }
                editUniAffiliationAdapter = new EditUniAffiliationAdapter(UniversityAffiliationActivity.this, itemList);
                recyclerView.setAdapter(editUniAffiliationAdapter);
                editUniAffiliationAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UniversityAffiliationActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUniAffiliationItem ListItem = new EditUniAffiliationItem();
                ListItem.setlOpen("increase");
                itemList.add(ListItem);
                editUniAffiliationAdapter.notifyDataSetChanged();
            }
        });

        editUniAffiliationAdapter = new EditUniAffiliationAdapter(this,itemList);
        recyclerView.setAdapter(editUniAffiliationAdapter);
        editUniAffiliationAdapter.notifyDataSetChanged();

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(uid).child("UniAffiliation").removeValue();
                for (int i = 0; i <  editUniAffiliationAdapter.mdata.size(); i++) {
                    String unique = databaseReference.child(uid).push().getKey();
                    String uniName = editUniAffiliationAdapter.mdata.get(i).getUniName();
                    String STUDENTID = editUniAffiliationAdapter.mdata.get(i).getId();
                    String Department = editUniAffiliationAdapter.mdata.get(i).getDepartment();
                    String StudentLevel = editUniAffiliationAdapter.mdata.get(i).getLevel();
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("UniName", uniName);
                    result.put("studentId", STUDENTID);
                    result.put("department", Department);
                    result.put("level", StudentLevel);
                    databaseReference.child(uid).child("UniAffiliation").child(unique).updateChildren(result);
                }
            }
        });
    }
}