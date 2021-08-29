package com.istiaksaif.uniclubz.Adaptar;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Model.EditUniAffiliationItem;
import com.istiaksaif.uniclubz.R;
import java.util.ArrayList;
import java.util.HashMap;

import static com.istiaksaif.uniclubz.Activity.UniversityAffiliationActivity.fab;
import static com.istiaksaif.uniclubz.Activity.UniversityAffiliationActivity.nextButton;
import static com.istiaksaif.uniclubz.Utils.optionUniName.optionUniName;


public class EditUniAffiliationAdapter extends RecyclerView.Adapter<EditUniAffiliationAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<EditUniAffiliationItem> mdata;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private ProgressDialog progressDialog;

    public EditUniAffiliationAdapter(Context context, ArrayList<EditUniAffiliationItem> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public EditUniAffiliationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uniafffiliationcard,parent,false);
        EditUniAffiliationAdapter.ViewHolder viewHolder = new EditUniAffiliationAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditUniAffiliationAdapter.ViewHolder holder, int position) {
        progressDialog = new ProgressDialog(context);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String unique = databaseReference.child(uid).push().getKey();
        EditUniAffiliationItem item = mdata.get(position);

        holder.itemopen.setText(mdata.get(position).getlOpen());

        holder.uniName.setText(item.getUniName());
        holder.studentID.setText(item.getId());
        holder.department.setText(item.getDepartment());
        holder.level.setText(item.getLevel());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemopen;
        private Button nextButton;
        private TextInputEditText studentID;
        private MaterialAutoCompleteTextView uniName,department,level;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemopen = itemView.findViewById(R.id.layoutopen);
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
