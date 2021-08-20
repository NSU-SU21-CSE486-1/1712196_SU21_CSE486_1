package com.istiaksaif.Project02.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project02.Activity.FinalActivity;
import com.istiaksaif.Project02.Activity.ProfileActivity;
import com.istiaksaif.Project02.Activity.UniversityAffiliationActivity;
import com.istiaksaif.Project02.MainActivity;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static com.istiaksaif.Project02.Activity.ProfileActivity.User_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.fileContent;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filePath;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filename;
import static com.istiaksaif.Project02.Activity.ProfileActivity.submitButton;

public class FirstTabFragment extends Fragment {

    public static TextInputEditText fullName,dateOfBirth,nid,bloodGroup;
    private String date;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullName = view.findViewById(R.id.name);
        dateOfBirth = view.findViewById(R.id.dateofbirth);
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int nYear = calendar.get(Calendar.YEAR);
                int nMonth = calendar.get(Calendar.MONTH);
                int nDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datepickerListener, nYear, nMonth, nDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        nid = view.findViewById(R.id.nid);
        bloodGroup = view.findViewById(R.id.bloodgroup);
    }

    public void Info() {
        String FullName = fullName.getText().toString();
        String DateOfBirth = dateOfBirth.getText().toString();
        String NID = nid.getText().toString();
        String BloodGroup = bloodGroup.getText().toString();

        if (TextUtils.isEmpty(FullName)){
            Toast.makeText(getActivity(), "please enter your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(DateOfBirth)){
            Toast.makeText(getActivity(), "please enter DateOfBirth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(NID)){
            Toast.makeText(getActivity(), "please enter NID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (NID.length()<13){
            Toast.makeText(getActivity(), "Nid number minimum 13 and max 18 numbers", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(BloodGroup)){
            Toast.makeText(getActivity(), "please enter bloodGroup", Toast.LENGTH_SHORT).show();
            return;
        }

//        User user = new User(FullName,DateOfBirth,NID,BloodGroup);
//        ArrayList<User> arrayList = new ArrayList<>();
//        arrayList.add(user);
//        Toast.makeText(getActivity(),"done"+user,Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getActivity(), FinalActivity.class);
//        intent.putExtra(User_Key,new User(FullName,DateOfBirth,NID,BloodGroup));
//        startActivity(intent);
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("userInfo.tmp");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(user);
//
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
            dateOfBirth.setText(date);
        }
    };

    private void writeToFile(Context _c, String _filename, String _data) {
        File external = _c.getExternalFilesDir(null);
        File file = new File(external, _filename);

        try {
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(_data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_tab, container, false);
        return view;
    }
}