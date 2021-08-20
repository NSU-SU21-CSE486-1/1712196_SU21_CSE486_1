package com.istiaksaif.Project02.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Adapter.FinalUniAffiliationAdapter;
import com.istiaksaif.Project02.Adapter.ItemAdapter;
import com.istiaksaif.Project02.Adapter.UniAffiliationAdapter;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.FinalUniAffiliation;
import com.istiaksaif.Project02.Utils.ItemList;
import com.istiaksaif.Project02.Utils.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static com.istiaksaif.Project02.Activity.ProfileActivity.Contact_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.fileContent;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filePath;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filename;

public class FinalActivity extends AppCompatActivity {

    private TextView uniName,studentID,department,level,phone,email,name,nid,blood,dob,namelist,nidlist;
    private Toolbar toolbar;

    private String fileContent,fileContent1,fileContent2;
    private RecyclerView uniRecycler,contactRecycler;
    private FinalUniAffiliationAdapter finalUniAffiliationAdapter;
    private ArrayList<FinalUniAffiliation> uniAffiliationArrayList;
    private ItemAdapter itemAdapter;
    private ArrayList<ItemList> itemArrayList;

    private LinearLayout linearLayout;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");

        nidlist = findViewById(R.id.nid);
        namelist = findViewById(R.id.name);
        FileReader fileReader = null;
        File myexFile = new File(getExternalFilesDir(filePath), filename);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            fileReader = new FileReader(myexFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < 0; i++)
                bufferedReader.readLine();
                line = bufferedReader.readLine();
                namelist.setText(line);
            for (int i = 0; i < 2; i++)
                bufferedReader.readLine();
            line = bufferedReader.readLine();
            nidlist.setText(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        linearLayout = findViewById(R.id.layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createpopupDiaglog();
            }
        });

    }

    public void createpopupDiaglog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        name = contactPopupView.findViewById(R.id.name);
        dob = contactPopupView.findViewById(R.id.dob);
        nid = contactPopupView.findViewById(R.id.nid);
        blood = contactPopupView.findViewById(R.id.blood);
        uniName = contactPopupView.findViewById(R.id.uniinfo);
        phone = contactPopupView.findViewById(R.id.contactinfo);

//        uniRecycler = findViewById(R.id.uniaffiliationcardrecycle);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        uniRecycler.setLayoutManager(layoutManager);
//        uniRecycler.setHasFixedSize(true);
//        uniAffiliationArrayList = new ArrayList<>();
//
//        contactRecycler = findViewById(R.id.contactrecyclerView);
//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
//        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
//        contactRecycler.setLayoutManager(layoutManager1);
//        contactRecycler.setHasFixedSize(true);
//        itemArrayList = new ArrayList<>();


            FileReader fileReader = null;
            File myexFile = new File(getExternalFilesDir(filePath), filename);
            StringBuilder stringBuilder = new StringBuilder();
            try {
                fileReader = new FileReader(myexFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = bufferedReader.readLine();
                    String fileContents = stringBuilder.toString();

                    name.setText(fileContents);
                    dob.setVisibility(View.GONE);
                    nid.setVisibility(View.GONE);
                    blood.setVisibility(View.GONE);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            FinalUniAffiliation ListItem = new FinalUniAffiliation();
            FileReader fileReader1 = null;
            File myexFile1 = new File(getExternalFilesDir(filePath), "uniInfo.txt");
            StringBuilder stringBuilder1 = new StringBuilder();
            try {
                fileReader1 = new FileReader(myexFile1);
                BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
                String line1 = bufferedReader1.readLine();
                while (line1 != null) {
                    stringBuilder1.append(line1).append('\n');
                    line1 = bufferedReader1.readLine();
                    String fileContents = stringBuilder1.toString();

                    uniName.setText(fileContents);
//                    ListItem.setUniName(fileContents);
//                    ListItem.setLevel("");
//                    uniAffiliationArrayList.add(ListItem);
                }
                bufferedReader1.close();
                fileReader1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            ItemList itemList = new ItemList();
            FileReader fileReader2 = null;
            File myexFile2 = new File(getExternalFilesDir(filePath), "contactInfo.txt");
            StringBuilder stringBuilder2 = new StringBuilder();
            try {
                fileReader2 = new FileReader(myexFile2);
                BufferedReader bufferedReader = new BufferedReader(fileReader2);
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuilder2.append(line).append('\n');
                    line = bufferedReader.readLine();
                    String fileContents = stringBuilder2.toString();

                    phone.setText(fileContents);
//                    itemList.setPhone(fileContents);
//                    itemArrayList.add(itemList);
                }
                bufferedReader.close();
                fileReader2.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        finalUniAffiliationAdapter = new FinalUniAffiliationAdapter(this,uniAffiliationArrayList);
//        uniRecycler.setAdapter(finalUniAffiliationAdapter);
//        finalUniAffiliationAdapter.notifyDataSetChanged();
//
//        itemAdapter = new ItemAdapter(this,itemArrayList);
//        contactRecycler.setAdapter(itemAdapter);
//        itemAdapter.notifyDataSetChanged();


    }
}