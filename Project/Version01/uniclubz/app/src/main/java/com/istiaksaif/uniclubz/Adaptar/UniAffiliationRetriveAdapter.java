package com.istiaksaif.uniclubz.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaksaif.uniclubz.Model.EditUniAffiliationItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;

import static com.istiaksaif.uniclubz.Fragment.ProfileFragment.uniDep;


public class UniAffiliationRetriveAdapter extends RecyclerView.Adapter<UniAffiliationRetriveAdapter.ViewHolder> {
    private Context context;
    public static ArrayList<EditUniAffiliationItem> mdata;

    public UniAffiliationRetriveAdapter(Context context, ArrayList<EditUniAffiliationItem> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public UniAffiliationRetriveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uniaffiliationshowcard,parent,false);
        UniAffiliationRetriveAdapter.ViewHolder viewHolder = new UniAffiliationRetriveAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UniAffiliationRetriveAdapter.ViewHolder holder, int position) {

        holder.uniName.setText("  University :  "+mdata.get(position).getUniName());
        holder.studentID.setText("  StudentId :  "+mdata.get(position).getId());
        holder.department.setText("  Department :  "+mdata.get(position).getDepartment());
        holder.level.setText("  Level :  "+mdata.get(position).getLevel());
        uniDep.setText("Studies "+mdata.get(position).getDepartment()+" at "+mdata.get(position).getUniName());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView studentID,uniName,department,level;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentID = itemView.findViewById(R.id.prostudentid);
            uniName = itemView.findViewById(R.id.prouniname);
            department = itemView.findViewById(R.id.prodep);
            level = itemView.findViewById(R.id.prolevel);
        }
    }
}
