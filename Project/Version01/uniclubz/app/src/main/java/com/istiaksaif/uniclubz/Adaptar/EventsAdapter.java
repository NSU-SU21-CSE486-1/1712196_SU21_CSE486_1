package com.istiaksaif.uniclubz.Adaptar;

import android.content.Context;
import android.content.Intent;
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
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<EventItem> mdata;

    public EventsAdapter(Context context, ArrayList<EventItem> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.eventcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ClubId = mdata.get(position).getEventId();
//        String date =mdata.get(position).getDate();
//        String d = null;
//        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
//        try {
//            Date date1 = input.parse(date);
//            d = output.format(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        holder.eventname.setText(mdata.get(position).getEventName());
        holder.eventDate.setText(mdata.get(position).getDate()+", "+" at "+mdata.get(position).getTime());
        holder.participation.setText("Participant");
        if(mdata.get(position).getStatus().equals("")){

        }else if(mdata.get(position).getStatus().equals("conform")){
            holder.joinButton.setVisibility(View.GONE);
        }

        Glide.with(context).load(mdata.get(position).getImage()).placeholder(R.drawable.dropdown).into(holder.eventImage);

    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView eventImage;
        TextView eventname,joinButton,eventDate,participation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage = (ImageView) itemView.findViewById(R.id.eventimage);
            eventname = (TextView) itemView.findViewById(R.id.eventname);
            eventDate = (TextView) itemView.findViewById(R.id.eventdate);
            participation = (TextView) itemView.findViewById(R.id.eventparticipant);

            joinButton = (TextView) itemView.findViewById(R.id.joineventbutton);
        }
    }
}
