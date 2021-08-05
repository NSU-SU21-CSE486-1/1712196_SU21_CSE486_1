package com.istiaksaif.uniclubz.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Adaptar.TimeAdapter;
import com.istiaksaif.uniclubz.Model.TimeModel;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.Utils.CalendarHelper;
import com.istiaksaif.uniclubz.Utils.ImageGetHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EventCreateFragment extends Fragment {
    private Toolbar toolbar;
    private TextView clubName;

    private MaterialAutoCompleteTextView privacy;
    private EditText eventName,eventDescription,eventPlace,endDate,startDate;
    private TextView startTimeStore,endTimeStore;
    private Button eventCreateButton;
    private DatabaseReference databaseReference,database;
    private StorageReference storageReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Button donateButton;
    private String DonateID,date;
    private Uri image;
    private ImageView eventImage;

    private RecyclerView timeRecyclerView,timeRecyclerView1;
    private TimeAdapter timeAdapter,timeAdapter1;
    private List<TimeModel> timeModelList;
    private ImageGetHelper getImageFunction;
    private ProgressDialog pro;

//    public static final int START_DATE = 0;
//    public static final int END_DATE = 1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getImageFunction = new ImageGetHelper(this);
        pro = new ProgressDialog(getContext());

        eventName = view.findViewById(R.id.eventname);
        eventDescription = view.findViewById(R.id.eventdescription);
        eventPlace = view.findViewById(R.id.eventplace);
        privacy = view.findViewById(R.id.privacyinput);
        TextInputLayout textInputLayout = view.findViewById(R.id.dropdown);
        String []option = {"Public","Private(Only Club Members)","Specific University"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.usertype_item,option);
        ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);
        startDate = view.findViewById(R.id.startdate);
        endDate = view.findViewById(R.id.enddate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int nYear = calendar.get(Calendar.YEAR);
                int nMonth = calendar.get(Calendar.MONTH);
                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

                startDate.setText(date);
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Calendar calendar = Calendar.getInstance();
//                int nYear = calendar.get(Calendar.YEAR);
//                int nMonth = calendar.get(Calendar.MONTH);
//                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

//                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
//                endDate.setText(CalendarHelper.getDateFromDatePicker(datePickerDialog));
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
            }
        });
        eventCreateButton = view.findViewById(R.id.eventcreatebutton);
        eventCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        startTimeStore = view.findViewById(R.id.starttimestore);
        endTimeStore = view.findViewById(R.id.endtimestore);

        timeRecyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeRecyclerView.setLayoutManager(layoutManager);
        timeRecyclerView1 = view.findViewById(R.id.endrecyclerview);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        timeRecyclerView1.setLayoutManager(layoutManager1);

        timeModelList = new ArrayList<TimeModel>();

        timeModelList.add(new TimeModel("20:00"));
        timeModelList.add(new TimeModel("20:30"));
        timeModelList.add(new TimeModel("21:00"));
        timeModelList.add(new TimeModel("21:30"));
        timeModelList.add(new TimeModel("22:00"));
        timeModelList.add(new TimeModel("22:30"));
        timeModelList.add(new TimeModel("23:00"));
        timeModelList.add(new TimeModel("23:30"));
        timeModelList.add(new TimeModel("00:00"));
        timeModelList.add(new TimeModel("00:30"));
        timeModelList.add(new TimeModel("01:00"));
        timeModelList.add(new TimeModel("01:30"));
        timeModelList.add(new TimeModel("02:00"));
        timeModelList.add(new TimeModel("02:30"));
        timeModelList.add(new TimeModel("03:00"));
        timeModelList.add(new TimeModel("03:30"));
        timeModelList.add(new TimeModel("04:00"));
        timeModelList.add(new TimeModel("04:30"));
        timeModelList.add(new TimeModel("05:00"));
        timeModelList.add(new TimeModel("05:30"));
        timeModelList.add(new TimeModel("06:00"));
        timeModelList.add(new TimeModel("06:30"));
        timeModelList.add(new TimeModel("07:00"));
        timeModelList.add(new TimeModel("07:30"));
        timeModelList.add(new TimeModel("08:00"));
        timeModelList.add(new TimeModel("08:30"));
        timeModelList.add(new TimeModel("09:00"));
        timeModelList.add(new TimeModel("09:30"));
        timeModelList.add(new TimeModel("10:00"));
        timeModelList.add(new TimeModel("10:30"));
        timeModelList.add(new TimeModel("11:00"));
        timeModelList.add(new TimeModel("11:30"));
        timeModelList.add(new TimeModel("12:00"));
        timeModelList.add(new TimeModel("12:30"));
        timeModelList.add(new TimeModel("13:00"));
        timeModelList.add(new TimeModel("13:30"));
        timeModelList.add(new TimeModel("14:00"));
        timeModelList.add(new TimeModel("14:30"));
        timeModelList.add(new TimeModel("15:00"));
        timeModelList.add(new TimeModel("15:30"));
        timeModelList.add(new TimeModel("16:00"));
        timeModelList.add(new TimeModel("16:30"));
        timeModelList.add(new TimeModel("17:00"));
        timeModelList.add(new TimeModel("17:30"));
        timeModelList.add(new TimeModel("18:00"));
        timeModelList.add(new TimeModel("18:30"));
        timeModelList.add(new TimeModel("19:00"));
        timeModelList.add(new TimeModel("19:30"));

        timeAdapter = new TimeAdapter(getContext(),timeModelList);
        timeRecyclerView.setAdapter(timeAdapter);
        timeAdapter.notifyDataSetChanged();
        timeAdapter1 = new TimeAdapter(getContext(),timeModelList);
        timeRecyclerView1.setAdapter(timeAdapter1);
        timeAdapter1.notifyDataSetChanged();
        timeAdapter.setOnItemClickListner(new TimeAdapter.onItemClickListner() {
            @Override
            public void onClick(String str) {
                startTimeStore.setText(str);
            }
        });
        timeAdapter1.setOnItemClickListner(new TimeAdapter.onItemClickListner() {
            @Override
            public void onClick(String str) {
                endTimeStore.setText(str);
            }
        });

        //addImage
        eventImage = view.findViewById(R.id.eventimage);
        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFunction.pickFromGallery();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == getImageFunction.IMAGE_PICK_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            image = data.getData();
            eventImage.setImageURI(image);
            eventImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_create, container, false);
        return view;
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
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName);
    }
}