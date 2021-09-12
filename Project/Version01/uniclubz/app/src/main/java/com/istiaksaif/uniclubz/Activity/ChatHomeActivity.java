package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Adaptar.ChatHomeAdapter;
import com.istiaksaif.uniclubz.Model.Chat;
import com.istiaksaif.uniclubz.Model.ChatsList;
import com.istiaksaif.uniclubz.Model.User;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.istiaksaif.uniclubz.Activity.ChatActivity.getSecretKeySpec;

public class ChatHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolBar;
    private ChatHomeAdapter chatHomeAdapter;
    private List<User> mUserList;
    private List<ChatsList> chatList;
    private String lastmsg,lastmsgtime;

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);

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

        recyclerView = findViewById(R.id.chatrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatHomeActivity.this));

        chatList = new ArrayList<>();
        databaseReference.child("ChatsList").child(firebaseUser.getUid()).orderByChild("updateTime")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        chatList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            ChatsList chat = dataSnapshot.getValue(ChatsList.class);
                            chatList.add(chat);
                        }
                        readChat();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    private void readChat(){
        mUserList = new ArrayList<>();
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUserList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    for(ChatsList cc : chatList){
                        if(user.getUserId() != null && user.getUserId().equals(cc.getId())){
                            mUserList.add(user);
                            break;
                        }
                    }
                    chatHomeAdapter = new ChatHomeAdapter(ChatHomeActivity.this,mUserList,true);
                    recyclerView.setAdapter(chatHomeAdapter);
                    for(int i=0;i<mUserList.size();i++){
                        lastMessage(mUserList.get(i).getUserId());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void lastMessage(final String uId) {
        databaseReference.child("Chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if(chat==null){
                        continue;
                    }
                    if(chat.getSender() == null || chat.getReceiver() == null){
                        continue;
                    }
                    if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender()
                            .equals(uId) || chat.getReceiver().equals(uId) && chat.getSender()
                            .equals(firebaseUser.getUid())){
                        try {
                            lastmsg = decrypt(chat.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        lastmsgtime = chat.getTime();
                    }
                }
                chatHomeAdapter.setLastMessageMap(uId,lastmsg);
                chatHomeAdapter.setLastMessageTimeMap(uId,lastmsgtime);
                chatHomeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String decrypt(String output)throws Exception{
        SecretKeySpec keySpec = getSecretKeySpec();
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE,keySpec);
        byte[] decodedValue = Base64.decode(output,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String dec = new String(decValue);
        return dec;
    }
}