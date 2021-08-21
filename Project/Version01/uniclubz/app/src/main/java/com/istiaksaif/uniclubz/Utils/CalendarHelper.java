package com.istiaksaif.uniclubz.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.FragmentActivity;

import com.istiaksaif.uniclubz.Fragment.EventCreateFragment;

import java.util.Calendar;

public class CalendarHelper {

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        month = month+1;
        String date = dayOfMonth+"/"+month+"/"+year;
    }
}
