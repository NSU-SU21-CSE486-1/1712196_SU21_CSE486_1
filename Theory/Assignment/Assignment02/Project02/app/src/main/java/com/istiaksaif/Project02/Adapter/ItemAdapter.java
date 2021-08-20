package com.istiaksaif.Project02.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.istiaksaif.Project02.Activity.FinalActivity;
import com.istiaksaif.Project02.Fragment.FirstTabFragment;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.FinalUniAffiliation;
import com.istiaksaif.Project02.Utils.ItemList;
import com.istiaksaif.Project02.Utils.User;

import java.util.ArrayList;
import java.util.HashMap;

import static com.istiaksaif.Project02.Activity.ProfileActivity.Contact_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.Uni_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.User_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.submitButton;
import static com.istiaksaif.Project02.Utils.optionUniName.optionUniName;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ItemList> mdata;
    public static String Phone,Email;

    public ItemAdapter(Context context, ArrayList<ItemList> mdata) {
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
        holder.itemopen.setText(mdata.get(position).getlOpen());
        Phone = holder.phone.getText().toString();
        Email = holder.email.getText().toString();

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemopen;
        private TextInputEditText phone,email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemopen = itemView.findViewById(R.id.layoutopen);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
        }
    }
}
