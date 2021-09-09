package com.istiaksaif.Project03.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.istiaksaif.Project03.Activity.FinalActivity;
import com.istiaksaif.Project03.Adapter.ItemAdapter;
import com.istiaksaif.Project03.Adapter.UniAffiliationAdapter;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.Utils.User;
import com.istiaksaif.Project03.roomdb.DatabaseClass;

import java.util.ArrayList;

import static com.istiaksaif.Project03.Activity.ProfileActivity.submitButton;

public class SecondTabFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private UniAffiliationAdapter uniAffiliationAdapter;
    private ArrayList<UniAff> itemList;
    private ItemAdapter itemAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingButtonAdd);
        fab.bringToFront();

        recyclerView = view.findViewById(R.id.uniaffiliationrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        UniAff ListItem = new UniAff();
        itemList.add(ListItem);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniAff ListItem = new UniAff();
                itemList.add(ListItem);
                uniAffiliationAdapter.notifyDataSetChanged();
            }
        });

        uniAffiliationAdapter = new UniAffiliationAdapter(getActivity(),itemList);
        recyclerView.setAdapter(uniAffiliationAdapter);
        uniAffiliationAdapter.notifyDataSetChanged();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseClass db  = DatabaseClass.getDbInstance(getActivity().getApplicationContext());
                User model = new User();
                UniAff uniAff = new UniAff();
                Contact modelcon = new Contact();
                String FullName = FirstTabFragment.fullName.getText().toString();
                String DateOfBirth = FirstTabFragment.dateOfBirth.getText().toString();
                String NID = FirstTabFragment.nid.getText().toString();
                String BloodGroup = FirstTabFragment.bloodGroup.getText().toString();
                model.setName(FullName);
                model.setNID(NID);
                model.setUserId(Integer.parseInt(NID));
                model.setDOB(DateOfBirth);
                model.setBloodGroup(BloodGroup);
                for (int i = 0; i <  uniAffiliationAdapter.mdata.size(); i++) {
                    String uniName = uniAffiliationAdapter.mdata.get(i).getUniName();
                    String STUDENTID = uniAffiliationAdapter.mdata.get(i).getId();
                    String Department = uniAffiliationAdapter.mdata.get(i).getDepartment();
                    String StudentLevel = uniAffiliationAdapter.mdata.get(i).getLevel();


                    uniAff.setUniName(uniName);
                    uniAff.setId(STUDENTID);
                    uniAff.setDepartment(Department);
                    uniAff.setLevel(StudentLevel);
                    uniAff.setUserId(Integer.parseInt(NID));
                    db.userDao().insertUniAff(uniAff);
                }
                for (int i = 0; i <  itemAdapter.mdata.size(); i++) {
                    String email = itemAdapter.mdata.get(i).getEmail();
                    String phone = itemAdapter.mdata.get(i).getPhone();

                    modelcon.setEmail(email);
                    modelcon.setPhone(phone);
                    modelcon.setUserId(Integer.parseInt(NID));
                    db.userDao().insertContact(modelcon);
                }
                db.userDao().insertUser(model);
                Intent intent = new Intent(getActivity(), FinalActivity.class);
                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), "Data Successfully Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);
        return view;
    }
}