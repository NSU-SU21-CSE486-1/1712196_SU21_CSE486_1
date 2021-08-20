package com.istiaksaif.Project02.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Activity.FinalActivity;
import com.istiaksaif.Project02.Adapter.ItemAdapter;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.ItemList;

import java.util.ArrayList;

public class ThirdTabFragment extends Fragment {

    private TextInputEditText fullName,dateOfBirth,nid,bloodGroup,phone,email;
    private TextView uniName,studentID,department,level;
    private Button nextButton;
    private Bundle b;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ItemAdapter uniAffiliationAdapter;
    private ArrayList<ItemList> itemList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fab = (FloatingActionButton) view.findViewById(R.id.floatingButtonAdd);
        fab.bringToFront();

        recyclerView = view.findViewById(R.id.contactrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        ItemList ListItem = new ItemList();
        ListItem.setlOpen("increase");
        itemList.add(ListItem);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList ListItem = new ItemList();
                ListItem.setlOpen("increase");
                itemList.add(ListItem);
                uniAffiliationAdapter.notifyDataSetChanged();
            }
        });

        uniAffiliationAdapter = new ItemAdapter(getActivity(),itemList);
        recyclerView.setAdapter(uniAffiliationAdapter);
        uniAffiliationAdapter.notifyDataSetChanged();

    }

    private void Info() {
        String Email = email.getText().toString();
        String Phone = phone.getText().toString();

        if (TextUtils.isEmpty(Email)){
            Toast.makeText(getActivity(), "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(Phone)){
            Toast.makeText(getActivity(), "please enter phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getActivity(), FinalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email",Email);
        bundle.putString("phone",Phone);
        intent.putExtras(bundle);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_tab, container, false);
        return view;
    }
}