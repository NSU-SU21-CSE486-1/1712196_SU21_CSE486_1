package com.istiaksaif.Project03.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.istiaksaif.Project03.Adapter.UserListAdapter;
import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.roomdb.DatabaseClass;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.User;

import java.util.List;

public class FinalActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private RecyclerView recyclerview;
    private UserListAdapter userListAdapter;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerview = findViewById(R.id.recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(dividerItemDecoration);
        userListAdapter = new UserListAdapter(this);
        recyclerview.setAdapter(userListAdapter);

        loadUserList();

    }

    private void loadUserList() {
        DatabaseClass db = DatabaseClass.getDbInstance(this.getApplicationContext());
        userList =db.userDao().getAllUsers();
        userListAdapter.setUserList(userList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}