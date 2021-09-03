package com.istiaksaif.uniclubz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istiaksaif.uniclubz.Adaptar.TabViewPagerAdapter;
import com.istiaksaif.uniclubz.Fragment.BloodReqFragment;
import com.istiaksaif.uniclubz.Fragment.ClubHomeFragment;
import com.istiaksaif.uniclubz.Fragment.EventCreateFragment;
import com.istiaksaif.uniclubz.Fragment.MembersFragment;
import com.istiaksaif.uniclubz.Fragment.clubProfileFragment;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.Utils.ImageGetHelper;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ClubActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager tabviewPager;
    private Toolbar toolbar;
    private TextView clubName,editImage;
    private ImageView clubImage;
    private Uri imageUri;
    private ImageGetHelper getImageFunction;

    private ProgressDialog progressDialog,pro;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private String clubPhoto;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Intent intent;
    private String clubId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        intent = getIntent();
        clubId = intent.getStringExtra("clubId");

        getImageFunction = new ImageGetHelper(null,ClubActivity.this);
        progressDialog = new ProgressDialog(this);

        toolbar = findViewById(R.id.clubtoolbar);
        clubImage = findViewById(R.id.clubimage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().hide();

        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabviewPager = (ViewPager)findViewById(R.id.tabviewpager);
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabViewPagerAdapter.AddFragment(new ClubHomeFragment(),null);
        tabViewPagerAdapter.AddFragment(new EventCreateFragment(),null);
        tabViewPagerAdapter.AddFragment(new BloodReqFragment(),null);
        tabViewPagerAdapter.AddFragment(new clubProfileFragment(),"Profile");
        tabViewPagerAdapter.AddFragment(new MembersFragment(),"Members");
        tabviewPager.setAdapter(tabViewPagerAdapter);
        tabviewPager.setCurrentItem(3);
        tabLayout.setupWithViewPager(tabviewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_addevent);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_blood_drop);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(1));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 1f;
        layout.setLayoutParams(layoutParams);
        LinearLayout layout1 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(2));
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layout1.getLayoutParams();
        layoutParams1.weight = 1f;
        layout1.setLayoutParams(layoutParams1);
        LinearLayout layout2 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layout2.getLayoutParams();
        layoutParams2.weight = 1f;
        layout2.setLayoutParams(layoutParams2);
        LinearLayout layout3 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(3));
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layout3.getLayoutParams();
        layoutParams3.weight = 1.7f;
        LinearLayout layout4 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(4));
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layout4.getLayoutParams();
        layoutParams4.weight = 1.7f;
        

        //clubName
        clubName = findViewById(R.id.clubname);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ClubInfo");
        Query query = databaseReference.orderByChild("clubId").equalTo(clubId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    String clubname = ""+dataSnapshot.child("clubName").getValue();
                    String clubimage = ""+dataSnapshot.child("clubImage").getValue();

                    clubName.setText(clubname);
                    try {
                        Picasso.get().load(clubimage).resize(320,320).into(clubImage);
                    }catch (Exception e){
                        Picasso.get().load(R.drawable.dropdown).into(clubImage);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ClubActivity.this,"Some Thing Wrong", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //clubImage
        storageReference = FirebaseStorage.getInstance().getReference();
        editImage = findViewById(R.id.imageedit);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clubPhoto = "clubImage";
                getImageFunction.pickFromGallery();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getImageFunction.IMAGE_PICK_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            uploadProfilePhoto(imageUri);
            clubImage.setImageURI(imageUri);
        }
    }
    private void uploadProfilePhoto(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] fileInBytes = baos.toByteArray();

        String filePathName = clubPhoto+"_"+clubId;
        StorageReference storageReference1 = storageReference.child(filePathName);

        pro = new ProgressDialog(ClubActivity.this);
        pro.show();
        pro.setContentView(R.layout.progress_dialog);
        pro.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        storageReference1.putBytes(fileInBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downloadUri = uriTask.getResult();
                if(uriTask.isSuccessful()){
                    HashMap<String, Object> results = new HashMap<>();
                    results.put(clubPhoto,downloadUri.toString());

                    databaseReference.child(clubId).updateChildren(results)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressDialog.dismiss();
                                    pro.dismiss();
                                    Toast.makeText(ClubActivity.this,"Image Update", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ClubActivity.this,"Error Update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(ClubActivity.this,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ClubActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setToolbar(Toolbar toolbar){
        toolbar = findViewById(R.id.clubtoolbar);
        setSupportActionBar(toolbar);
    }
    public void setText(TextView clubName,TextView editImage){
        int titleBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            titleBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        clubName = findViewById(R.id.clubname);
        clubName.setVisibility(View.INVISIBLE);
        clubName.setMaxHeight(titleBarHeight);
        editImage = findViewById(R.id.imageedit);
        editImage.setVisibility(View.GONE);
    }
    public void setimg(ImageView clubImage){
        clubImage = findViewById(R.id.clubimage);
        clubImage.setVisibility(View.GONE);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();

        }return true;
    }

}