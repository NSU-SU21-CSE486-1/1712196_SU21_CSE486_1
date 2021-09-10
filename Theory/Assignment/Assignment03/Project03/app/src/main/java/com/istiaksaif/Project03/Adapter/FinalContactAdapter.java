package com.istiaksaif.Project03.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.R;

import java.util.List;

public class FinalContactAdapter extends RecyclerView.Adapter<FinalContactAdapter.ViewHolder> {
    private Context context;
    private List<Contact> mdata;

    public FinalContactAdapter(Context context){
        this.context = context;
    }

    public void setUserList(List<Contact> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.finalcontactcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.email.setText(mdata.get(position).getEmail());
        holder.phone.setText(mdata.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView email, phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}