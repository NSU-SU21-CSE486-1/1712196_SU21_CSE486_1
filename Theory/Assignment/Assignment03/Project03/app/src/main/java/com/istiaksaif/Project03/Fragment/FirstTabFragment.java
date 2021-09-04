package com.istiaksaif.Project03.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.User;
import com.istiaksaif.Project03.roomdb.DatabaseClass;

import java.util.Calendar;

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
        String FullName = fullName.getText().toString().trim();
        String DateOfBirth = dateOfBirth.getText().toString().trim();
        String NID = nid.getText().toString().trim();
        String BloodGroup = bloodGroup.getText().toString().trim();

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

        DatabaseClass db  = DatabaseClass.getDbInstance(getActivity().getApplicationContext());

        User model = new User();
        model.setName(FullName);
        model.setDOB(DateOfBirth);
        model.setNID(NID);
        model.setBloodGroup(BloodGroup);
        db.userDao().insertUser(model);

        fullName.setText("");
        dateOfBirth.setText("");
        nid.setText("");
        bloodGroup.setText("");
        Toast.makeText(getActivity(), "Data Successfully Saved", Toast.LENGTH_SHORT).show();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_tab, container, false);
        return view;
    }
}