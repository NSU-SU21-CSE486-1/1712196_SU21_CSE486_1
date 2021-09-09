package com.istiaksaif.Project03.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project03.Activity.FinalActivity;
import com.istiaksaif.Project03.Fragment.FirstTabFragment;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.Utils.User;
import com.istiaksaif.Project03.roomdb.DatabaseClass;

import java.util.ArrayList;

import static com.istiaksaif.Project03.Activity.ProfileActivity.submitButton;
import static com.istiaksaif.Project03.Utils.optionUniName.optionUniName;

public class UniAffiliationAdapter extends RecyclerView.Adapter<UniAffiliationAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<UniAff> mdata;

    public UniAffiliationAdapter(Context context, ArrayList<UniAff> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public UniAffiliationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uniafffiliationcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UniAffiliationAdapter.ViewHolder holder, int position) {
        holder.uniName.setText(mdata.get(position).getUniName());
        holder.studentID.setText(mdata.get(position).getId());
        holder.department.setText(mdata.get(position).getDepartment());
        holder.level.setText(mdata.get(position).getLevel());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextInputEditText studentID;
        public MaterialAutoCompleteTextView uniName,department,level;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentID = itemView.findViewById(R.id.studentId);
            uniName = itemView.findViewById(R.id.uniName);
            department = itemView.findViewById(R.id.department);
            level = itemView.findViewById(R.id.level);
            TextInputLayout textInputLayoutUniName = itemView.findViewById(R.id.uninamelayout);
            ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(context,R.layout.usertype_item,optionUniName);
            ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);

            TextInputLayout textInputLayout = itemView.findViewById(R.id.departmentdropdown);
            String []option = {"CSE","BBA","Economics","Bio Chemistry","Architecture","Pharmacy","EEE","Math","Physics"};
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.usertype_item,option);
            ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);

            TextInputLayout textInputLayout1 = itemView.findViewById(R.id.dropdown);
            String []option1 = {"UnderGraduate","MS","PhD","Post-Doc"};
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(context,R.layout.usertype_item,option1);
            ((MaterialAutoCompleteTextView) textInputLayout1.getEditText()).setAdapter(arrayAdapter1);

            uniName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setUniName(uniName.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setUniName(uniName.getText().toString());
                }
            });
            studentID.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setId(studentID.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setId(studentID.getText().toString());
                }
            });

            department.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setDepartment(department.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setDepartment(department.getText().toString());
                }
            });

            level.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setLevel(level.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setLevel(level.getText().toString());
                }
            });
        }
    }
}
