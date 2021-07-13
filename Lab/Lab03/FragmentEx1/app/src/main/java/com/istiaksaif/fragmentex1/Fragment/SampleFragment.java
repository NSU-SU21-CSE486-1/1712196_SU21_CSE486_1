package com.istiaksaif.fragmentex1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.istiaksaif.fragmentex1.R;

public class SampleFragment extends Fragment {

    public static final int Yes = 0;
    public static final int No = 1;
    private RadioGroup radioGroup;
    private RatingBar ratingBar;
    private TextView textView;

    public SampleFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
         radioGroup = view.findViewById(R.id.radio_group);
         ratingBar = view.findViewById(R.id.ratingBar);

         radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 View radioButton = radioGroup.findViewById(i);
                 int index = radioGroup.indexOfChild(radioButton);
                 textView = view.findViewById(R.id.fragment_header);
                 switch (index) {
                     case Yes:
                         textView.setText(R.string.yes_message);
                         break;
                     case No:
                         textView.setText(R.string.no_message);
                         break;
                     default:
                         break;
                 }
             }
         });

        ratingBar.setOnRatingBarChangeListener
                (new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float rating, boolean fromUser) {
                        String myRating = (getString(R.string.my_rating) +
                                String.valueOf(ratingBar.getRating()));
                        Toast.makeText(getContext(), myRating, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        return view;
    }
}