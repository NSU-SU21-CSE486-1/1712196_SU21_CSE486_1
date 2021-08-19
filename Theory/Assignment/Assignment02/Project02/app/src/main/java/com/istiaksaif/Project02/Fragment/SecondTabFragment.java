package com.istiaksaif.Project02.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project02.Activity.SecondActivity;
import com.istiaksaif.Project02.Activity.UniversityAffiliationActivity;
import com.istiaksaif.Project02.Adapter.ItemAdapter;
import com.istiaksaif.Project02.Adapter.UniAffiliationAdapter;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.ItemList;

import java.util.ArrayList;

import static com.istiaksaif.Project02.Utils.optionUniName.optionUniName;

public class SecondTabFragment extends Fragment {

    private TextInputEditText studentID1,studentID;
    private MaterialAutoCompleteTextView uniName,autoCompleteTextView,autoCompleteTextView1,uniName1,autoCompleteTextView11,autoCompleteTextView111;
    private Button nextButton;
    private Bundle b;
    private FloatingActionButton fab;
    private LinearLayout layout1;
    private RecyclerView recyclerView;
//    private UniAffiliationAdapter uniAffiliationAdapter;
    private ItemAdapter uniAffiliationAdapter;
    private String[] strings = {""};
    private ArrayList<ItemList> itemList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.uniaffiliationrecycle);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        ItemList ListItem = new ItemList();
        ListItem.setlOpen("increase");
        itemList.add(ListItem);
//        studentID = view.findViewById(R.id.studentId);
//
//        uniName = view.findViewById(R.id.uniName);
//        TextInputLayout textInputLayoutUniName = view.findViewById(R.id.uninamelayout);
//        ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(getActivity(),R.layout.usertype_item,optionUniName);
//        ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);
//
//        autoCompleteTextView = view.findViewById(R.id.department);
//        TextInputLayout textInputLayout = view.findViewById(R.id.departmentdropdown);
//        String []option = {"CSE","BBA","Economics","Bio Chemistry","Architecture","Pharmacy","EEE","Math","Physics"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.usertype_item,option);
//        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);
//
//
//        autoCompleteTextView1 = view.findViewById(R.id.level);
//        TextInputLayout textInputLayout1 = view.findViewById(R.id.dropdown);
//        String []option1 = {"UnderGraduate","MS","PhD","Post-Doc"};
//        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getActivity(),R.layout.usertype_item,option1);
//        ((MaterialAutoCompleteTextView) textInputLayout1.getEditText()).setAdapter(arrayAdapter1);
//
//        studentID1 = view.findViewById(R.id.studentId1);
//        uniName1 = view.findViewById(R.id.uniName1);
//        autoCompleteTextView11 = view.findViewById(R.id.department1);
//        autoCompleteTextView111 = view.findViewById(R.id.level1);
//        layout1 = view.findViewById(R.id.l2);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingButtonAdd);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemList ListItem = new ItemList();
                ListItem.setlOpen("increase");
                itemList.add(ListItem);
            }
        });
        uniAffiliationAdapter = new ItemAdapter(getActivity(),itemList);
        recyclerView.setAdapter(uniAffiliationAdapter);
        uniAffiliationAdapter.notifyDataSetChanged();
//        ClearAll();
    }

    private void ClearAll(){
        if (itemList !=null){
            itemList.clear();
            if (uniAffiliationAdapter!=null){
                uniAffiliationAdapter.notifyDataSetChanged();
            }
        }
        itemList = new ArrayList<>();
    }
    private void Info() {
        String UNINAME = uniName.getText().toString();
        String STUDENTID = studentID.getText().toString();
        String Department = autoCompleteTextView.getText().toString();
        String StudentLevel = autoCompleteTextView1.getText().toString();

        if (TextUtils.isEmpty(UNINAME)){
            Toast.makeText(getActivity(), "please enter your University Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(STUDENTID)){
            Toast.makeText(getActivity(), "please enter Your ID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(Department)){
            Toast.makeText(getActivity(), "please enter Department", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(StudentLevel)){
            Toast.makeText(getActivity(), "please enter your study Level", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getActivity(), SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uniname",UNINAME);
        bundle.putString("id",STUDENTID);
        bundle.putString("department",Department);
        bundle.putString("studentLevel",StudentLevel);
        intent.putExtras(bundle);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_tab, container, false);
        return view;
    }
}