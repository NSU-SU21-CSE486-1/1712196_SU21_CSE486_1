package com.istiaksaif.Project03.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.Utils.User;
import com.istiaksaif.Project03.roomdb.DatabaseClass;

import java.util.List;

public class FinalUniAffiliationAdapter extends RecyclerView.Adapter<FinalUniAffiliationAdapter.ViewHolder> {
    private Context context;
    private List<UniAff> mdata;

    public FinalUniAffiliationAdapter(Context context) {
        this.context = context;
    }
    public void setUserList(List<UniAff> mdata) {
        this.mdata = mdata;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FinalUniAffiliationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.finaluniafiiliationcard,parent,false);
        FinalUniAffiliationAdapter.ViewHolder viewHolder = new FinalUniAffiliationAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FinalUniAffiliationAdapter.ViewHolder holder, int position) {
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

        public TextView studentID,uniName,department,level;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentID = itemView.findViewById(R.id.id);
            uniName = itemView.findViewById(R.id.uniname);
            department = itemView.findViewById(R.id.dep);
            level = itemView.findViewById(R.id.level);
        }
    }
}
