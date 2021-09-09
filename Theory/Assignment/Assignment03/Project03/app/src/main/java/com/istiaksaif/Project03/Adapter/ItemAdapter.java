package com.istiaksaif.Project03.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.Contact;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<Contact> mdata;

    public ItemAdapter(Context context, ArrayList<Contact> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contactcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(mdata.get(position).getEmail());
        holder.phone.setText(mdata.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextInputEditText phone,email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);

            phone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setPhone(phone.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setPhone(phone.getText().toString());
                }
            });
            email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mdata.get(getAdapterPosition()).setEmail(email.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    mdata.get(getAdapterPosition()).setEmail(email.getText().toString());
                }
            });
        }
    }
}
