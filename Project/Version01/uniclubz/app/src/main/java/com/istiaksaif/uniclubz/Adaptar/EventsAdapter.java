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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Activity.ClubActivity;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<EventItem> mdata;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

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
        String EventId = mdata.get(position).getEventId();

        holder.eventname.setText(mdata.get(position).getEventName());
        holder.eventDate.setText(mdata.get(position).getDate()+", "+" at "+mdata.get(position).getTime());
        holder.participation.setText("Participant");
        if(mdata.get(position).getStatus().equals("")){
            holder.joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("userId", uid);
                    result.put("status", "Interested");
                    databaseReference.child("EventList").child(EventId).child("Participant").child(uid).updateChildren(result);
                    holder.joinButton.setText("Interested");
                }
            });

        }else if(mdata.get(position).getStatus().equals("Interested")){
            holder.joinButton.setText("Interested");
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
