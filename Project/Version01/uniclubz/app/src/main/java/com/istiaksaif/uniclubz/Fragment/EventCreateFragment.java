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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.istiaksaif.uniclubz.Adaptar.TimeAdapter;
import com.istiaksaif.uniclubz.Model.TimeModel;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.Utils.CalendarHelper;
import com.istiaksaif.uniclubz.Utils.ImageGetHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.istiaksaif.uniclubz.Utils.TimeListHelper.optionTimeList;

public class EventCreateFragment extends Fragment {
    private Toolbar toolbar;
    private TextView clubName;
    private ImageView clubImg;

    private MaterialAutoCompleteTextView privacy;
    private EditText eventName,eventDescription,eventPlace,endDate,startDate;
    private TextView startTimeStore,endTimeStore;
    private Button eventCreateButton;
    private DatabaseReference databaseReference,database;
    private StorageReference storageReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private Button donateButton;
    private String EventId,date;
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
        getImageFunction = new ImageGetHelper(this,null);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int nYear = calendar.get(Calendar.YEAR);
                int nMonth = calendar.get(Calendar.MONTH);
                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener1, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        eventCreateButton = view.findViewById(R.id.eventcreatebutton);

        databaseReference = FirebaseDatabase.getInstance().getReference("EventList");
        storageReference = FirebaseStorage.getInstance().getReference();
        EventId = databaseReference.child(uid).push().getKey();

        eventCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToFirebase(image);
                pro.show();
                pro.setContentView(R.layout.progress_dialog);
                pro.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
        for(int i=0;i<=48;i++){
            TimeModel time = new TimeModel(optionTimeList[i]);
            timeModelList.add(time);
        }
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

    private void uploadToFirebase(Uri uri) {
        String EventName = eventName.getText().toString().trim();
        String EventDescription = eventDescription.getText().toString().trim();
        String Location = eventPlace.getText().toString().trim();
        String StartDate = startDate.getText().toString().trim();
        String EndDate = endDate.getText().toString().trim();
        String StartTime = startTimeStore.getText().toString().trim();
        String EndTime = endTimeStore.getText().toString().trim();

        HashMap<String, Object> result = new HashMap<>();
        result.put("EventName", EventName);
        result.put("EventDescription", EventDescription);
        result.put("Location", Location);
        result.put("StartDate", StartDate);
        result.put("EndDate", EndDate);
        result.put("startTime" , StartTime);
        result.put("EndTime",EndTime);
        result.put("eventId", EventId);
        result.put("userId", uid);
        result.put("status", "");
        final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getActivity().getContentResolver());
        databaseReference.child(EventId).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if(uri == null){
                    Uri imguri = Uri.parse("");
                    HashMap<String, Object> resultimg = new HashMap<>();
                    resultimg.put("image", imguri.toString());
                    databaseReference.child(EventId).updateChildren(resultimg).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            intent();
                            Toast.makeText(getActivity(), "Donate Successful", Toast.LENGTH_SHORT).show();
                            pro.dismiss();
                        }
                    });
                }else
                if(uri != null){
                    fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    HashMap<String, Object> resultimg = new HashMap<>();
                                    resultimg.put("image", uri.toString());
                                    databaseReference.child(EventId).updateChildren(resultimg).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            intent();
                                            Toast.makeText(getActivity(), "Donate Successful", Toast.LENGTH_SHORT).show();
                                            pro.dismiss();
                                        }
                                    });
                                }
                            });
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intent() {
        Intent intent = new Intent(getContext(), ClubActivity.class);
        getContext().startActivity(intent);
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
            startDate.setText(date);
        }
    };
    private DatePickerDialog.OnDateSetListener datepickerListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            month = month+1;
            date = dayOfMonth+"/"+month+"/"+year;
            endDate.setText(date);
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        ((ClubActivity)getActivity()).setToolbar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((ClubActivity)getActivity()).setText(clubName);
//        ((ClubActivity)getActivity()).setimg(clubImg);
    }
}