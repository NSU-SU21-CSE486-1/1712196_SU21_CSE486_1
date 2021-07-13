package com.istiaksaif.fragmentcommunication.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.istiaksaif.fragmentcommunication.Activity.SongDetailsActivity;
import com.istiaksaif.fragmentcommunication.MainActivity;
import com.istiaksaif.fragmentcommunication.R;

import static com.istiaksaif.fragmentcommunication.MainActivity.choice;

public class SampleFragment extends Fragment {

    private TextView passage,title;

    private Intent intent;
    public String Choice;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        intent = getActivity().getIntent();
        Choice = intent.getStringExtra(choice);

        title = view.findViewById(R.id.title);
        passage = view.findViewById(R.id.passage);

        switch (Choice){
            case "one":
                title.setText(R.string.title1);
                passage.setText(R.string.article1);
                break;
            case "two":
                title.setText(R.string.title2);
                passage.setText(R.string.article2);
                break;
            case "three":
                passage.setText(R.string.article3);
                title.setText(R.string.title3);
                break;
            case "four":
                passage.setText(R.string.article4);
                title.setText(R.string.title4);
                break;
            case "five":
                passage.setText(R.string.article5);
                title.setText(R.string.title5);
                break;
            case "six":
                passage.setText(R.string.article6);
                title.setText(R.string.title6);
                break;
            case "seven":
                passage.setText(R.string.article7);
                title.setText(R.string.title7);
                break;
            default:
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        return view;
    }
}