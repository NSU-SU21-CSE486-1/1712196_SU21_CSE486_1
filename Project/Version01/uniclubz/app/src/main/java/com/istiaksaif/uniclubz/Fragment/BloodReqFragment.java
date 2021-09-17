package com.istiaksaif.uniclubz.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Activity.LogInActivity;
import com.istiaksaif.uniclubz.Adaptar.TimeAdapter;
import com.istiaksaif.uniclubz.Model.TimeModel;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.istiaksaif.uniclubz.Utils.TimeListHelper.optionTimeList;

public class BloodReqFragment extends Fragment {
    private Toolbar toolbar;
    private TextView clubName,editImage;
    private ImageView clubImg;
    private int titleBarHeight;

    private MaterialAutoCompleteTextView privacy;
    private EditText contactName,contactPhone,bloodGroup,donateDate,bag,hosName,hosAddress;
    private TextView timeStore;
    private Button bloodReqButton;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private String bloodDonateId,date,clubId;

    private RecyclerView timeRecyclerView,timeRecyclerView1;
    private TimeAdapter timeAdapter,timeAdapter1;
    private List<TimeModel> timeModelList;
    private Intent intent;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = getActivity().getIntent();
        clubId = intent.getStringExtra("clubId");

        contactName = view.findViewById(R.id.name);
        contactPhone = view.findViewById(R.id.phone);
        bloodGroup = view.findViewById(R.id.bloodgroup);
        bag = view.findViewById(R.id.bag);
        donateDate = view.findViewById(R.id.date);
        hosName = view.findViewById(R.id.hosname);
        hosAddress = view.findViewById(R.id.hosloc);
        donateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int nYear = calendar.get(Calendar.YEAR);
                int nMonth = calendar.get(Calendar.MONTH);
                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        bloodReqButton = view.findViewById(R.id.bloodreqbutton);

        databaseReference = FirebaseDatabase.getInstance().getReference("BloodReqList");
        bloodDonateId = databaseReference.child(uid).push().getKey();

        bloodReqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToFirebase();
            }
        });
        timeStore = view.findViewById(R.id.timestore);

        timeRecyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeRecyclerView.setLayoutManager(layoutManager);

        timeModelList = new ArrayList<>();
        for(int i=0;i<=47;i++){
            TimeModel time = new TimeModel(optionTimeList[i]);
            timeModelList.add(time);
        }
        timeAdapter = new TimeAdapter(getContext(),timeModelList);
        timeRecyclerView.setAdapter(timeAdapter);
        timeAdapter.notifyDataSetChanged();

        timeAdapter.setOnItemClickListner(new TimeAdapter.onItemClickListner() {
            @Override
            public void onClick(String str) {
                timeStore.setText(str);
            }
        });
    }

    private void uploadToFirebase() {
        String ContactName = contactName.getText().toString().trim();
        String ContactPhone = contactPhone.getText().toString().trim();
        String BloodGroup = bloodGroup.getText().toString().trim();
        String Bag = bag.getText().toString().trim();
        String Date = donateDate.getText().toString().trim();
        String Time = timeStore.getText().toString().trim();
        String HospitalName = hosName.getText().toString().trim();
        String HospitalAddress = hosAddress.getText().toString().trim();

        HashMap<String, Object> result = new HashMap<>();
        result.put("contactName", ContactName);
        result.put("contactPhone", ContactPhone);
        result.put("bloodGroup", BloodGroup);
        result.put("bag", Bag);
        result.put("time" , Time);
        result.put("date" , Date);
        result.put("hosName" , HospitalName);
        result.put("hosAddress" , HospitalAddress);
        result.put("bloodDonateId", bloodDonateId);
        result.put("clubId", clubId);
        databaseReference.child(bloodDonateId).updateChildren(result);
        intent();
    }

    private void intent() {
        contactName.setText("");
        contactPhone.setText("");
        bloodGroup.setText("");
        bag.setText("");
        donateDate.setText("");
        timeStore.setText("");
        hosName.setText("");
        hosAddress.setText("");
        Toast.makeText(getActivity(),"Donate Request SuccessFully Added",Toast.LENGTH_LONG).show();
    }

    private DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            month = month+1;
            date = dayOfMonth+"/"+month+"/"+year;
            donateDate.setText(date);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_req, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName,editImage);
        ((ClubActivity)getActivity()).setimg(clubImg);
    }
}