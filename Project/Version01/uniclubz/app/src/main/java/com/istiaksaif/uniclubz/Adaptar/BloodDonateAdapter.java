package com.istiaksaif.uniclubz.Adaptar;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Model.BloodDonateItem;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;
import java.util.HashMap;


public class BloodDonateAdapter extends RecyclerView.Adapter<BloodDonateAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BloodDonateItem> mdata;

    public BloodDonateAdapter(Context context, ArrayList<BloodDonateItem> mdata) {
        this.context = context;
        this.mdata = mdata;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blooddonatecard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Bag.setText(mdata.get(position).getBag()+" Bags "+mdata.get(position).getBloodgroup()+" Blood needed");
        holder.Hospital.setText(mdata.get(position).getHosname()+", "+" at "+mdata.get(position).getHosaddress());
        holder.DateTime.setText(mdata.get(position).getDate()+", "+mdata.get(position).getTime());
        holder.number.setText(" "+mdata.get(position).getContactphone());
    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView Bag,Hospital,DateTime,number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Bag = (TextView) itemView.findViewById(R.id.bagtxt);
            Hospital = (TextView) itemView.findViewById(R.id.hospitalloc);
            DateTime = (TextView) itemView.findViewById(R.id.dateandtime);
            number = (TextView) itemView.findViewById(R.id.number);
            layout = (LinearLayout) itemView.findViewById(R.id.bloodlayout);
            WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = window.getDefaultDisplay();
            int displayWidth = display.getWidth();
            layout.getLayoutParams().width = (displayWidth/10)*8;
        }
    }
}
