package com.istiaksaif.Project03.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.istiaksaif.Project03.Adapter.ItemAdapter;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.Contact;

import java.util.ArrayList;

public class ThirdTabFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ItemAdapter contactItemAdapter;
    private ArrayList<Contact> itemList;

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
        Contact ListItem = new Contact();
        itemList.add(ListItem);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact ListItem = new Contact();
                itemList.add(ListItem);
                contactItemAdapter.notifyDataSetChanged();
            }
        });

        contactItemAdapter = new ItemAdapter(getActivity(),itemList);
        recyclerView.setAdapter(contactItemAdapter);
        contactItemAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_tab, container, false);
        return view;
    }
}