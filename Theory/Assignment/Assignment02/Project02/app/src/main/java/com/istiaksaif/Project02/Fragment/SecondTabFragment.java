package com.istiaksaif.Project02.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Adapter.UniAffiliationAdapter;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.ItemList;

import java.util.ArrayList;

public class SecondTabFragment extends Fragment {

    public static TextInputEditText studentID;
    public static MaterialAutoCompleteTextView uniName,autoCompleteTextView,autoCompleteTextView1,uniName1,autoCompleteTextView11,autoCompleteTextView111;
    private Button nextButton;
    private Bundle b;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private UniAffiliationAdapter uniAffiliationAdapter;
//    private ItemAdapter uniAffiliationAdapter;
//    private String[] strings = {""};
    private ArrayList<ItemList> itemList;

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

        uniAffiliationAdapter = new UniAffiliationAdapter(getActivity(),itemList);
        recyclerView.setAdapter(uniAffiliationAdapter);
        uniAffiliationAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);
        return view;
    }
}