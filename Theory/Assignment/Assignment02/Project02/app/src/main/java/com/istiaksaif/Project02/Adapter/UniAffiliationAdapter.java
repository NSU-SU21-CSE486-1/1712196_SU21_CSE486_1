package com.istiaksaif.Project02.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.model;

import java.util.List;

import static com.istiaksaif.Project02.Utils.optionUniName.optionUniName;

public class UniAffiliationAdapter extends RecyclerView.Adapter<UniAffiliationAdapter.ViewHolder> {
        private String[] mDataset;
        public String[][] data = new String[30][4];
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout mCardView;

            public ViewHolder(LinearLayout v) {
                super(v);
                mCardView = v;
            }
        }

        public UniAffiliationAdapter(String[] myDataset,Context context) {
            mDataset = myDataset;
            this.context = context;
        }

        @Override
        public UniAffiliationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.uniafffiliationcard, parent, false);

            ViewHolder viewHolder = new ViewHolder(linearLayout);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final TextInputEditText txt = (TextInputEditText) holder.mCardView.findViewById(R.id.studentId);
            final MaterialAutoCompleteTextView txt2 = (MaterialAutoCompleteTextView) holder.mCardView.findViewById(R.id.uniName);

            final MaterialAutoCompleteTextView txt3 = (MaterialAutoCompleteTextView) holder.mCardView.findViewById(R.id.department);
            final MaterialAutoCompleteTextView txt4 = (MaterialAutoCompleteTextView) holder.mCardView.findViewById(R.id.level);

            TextInputLayout textInputLayoutUniName = holder.mCardView.findViewById(R.id.uninamelayout);
            ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(context,R.layout.usertype_item,optionUniName);
            ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);

            TextInputLayout textInputLayout = holder.mCardView.findViewById(R.id.departmentdropdown);
            String []option = {"CSE","BBA","Economics","Bio Chemistry","Architecture","Pharmacy","EEE","Math","Physics"};
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.usertype_item,option);
            ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);


            TextInputLayout textInputLayout1 = holder.mCardView.findViewById(R.id.dropdown);
            String []option1 = {"UnderGraduate","MS","PhD","Post-Doc"};
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(context,R.layout.usertype_item,option1);
            ((MaterialAutoCompleteTextView) textInputLayout1.getEditText()).setAdapter(arrayAdapter1);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }

        public String[][] getData() {
            return data;
        }

//        public class ViewHolder extends RecyclerView.ViewHolder{
//
//        private TextInputEditText studentID;
//        private MaterialAutoCompleteTextView uniName,department,level;
//        private LinearLayout layout;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            studentID = itemView.findViewById(R.id.studentId);
//            uniName = itemView.findViewById(R.id.uniName);
//            department = itemView.findViewById(R.id.department);
//            level = itemView.findViewById(R.id.level);
//            layout = itemView.findViewById(R.id.layout);
//
//
            }
//    }
//}
