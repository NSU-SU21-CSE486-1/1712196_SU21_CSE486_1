package com.istiaksaif.Project02.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.ItemList;

import java.util.ArrayList;
import java.util.HashMap;

import static com.istiaksaif.Project02.Utils.optionUniName.optionUniName;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private static final String Tag = "RecyclerView";
    private Context context;
    private ArrayList<ItemList> mdata;

    public ItemAdapter(Context context, ArrayList<ItemList> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uniafffiliationcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemopen.setText(mdata.get(position).getlOpen());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemopen;
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

        }
    }
}
