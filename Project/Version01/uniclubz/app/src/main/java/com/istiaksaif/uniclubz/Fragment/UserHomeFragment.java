package com.istiaksaif.uniclubz.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.istiaksaif.uniclubz.Adaptar.EventsAdapter;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserHomeFragment extends Fragment {

    private RecyclerView eventsRecyclerView;
    private EventsAdapter eventsAdapter;
    private ArrayList<EventItem> eventList;
    private DatabaseReference eventsDatabaseRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventsDatabaseRef = FirebaseDatabase.getInstance().getReference();
        eventList = new ArrayList<>();

        eventsRecyclerView = view.findViewById(R.id.eventRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventsRecyclerView.setLayoutManager(layoutManager);
        eventsRecyclerView.setHasFixedSize(true);
        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {
        Query query = eventsDatabaseRef.child("EventList");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        EventItem eventItem = new EventItem();
                        if (!snapshot.child("Participant").exists()){
                            eventItem.setEventName(snapshot.child("EventName").getValue().toString());
                            eventItem.setImage(snapshot.child("image").getValue().toString());
                            eventItem.setEventId(snapshot.child("eventId").getValue().toString());
                            eventItem.setTime(snapshot.child("startTime").getValue().toString());
                            String date = snapshot.child("StartDate").getValue().toString();
                            String d = null;
                            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
                            try {
                                Date date1 = input.parse(date);
                                d = output.format(date1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            eventItem.setDate(d);
                            eventItem.setStatus("");

                            eventList.add(eventItem);
                        }else if(snapshot.child("Participant").exists()) {
                            eventItem.setEventName(snapshot.child("EventName").getValue().toString());
                            eventItem.setImage(snapshot.child("image").getValue().toString());
                            eventItem.setEventId(snapshot.child("eventId").getValue().toString());
                            eventItem.setTime(snapshot.child("startTime").getValue().toString());
                            String date = snapshot.child("StartDate").getValue().toString();
                            String d = null;
                            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat output = new SimpleDateFormat("dd MMMM yyyy");
                            try {
                                Date date1 = input.parse(date);
                                d = output.format(date1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            eventItem.setDate(d);
                            if (snapshot.child("Participant").child(uid).exists()) {
                                eventItem.setStatus(snapshot.child("Participant").child(uid).child("status").getValue().toString());
                            } else {
                                eventItem.setStatus("");
                            }

                            eventList.add(eventItem);
                        }

                    } catch (Exception e) {

                    }
                }
                eventsAdapter = new EventsAdapter(getContext(), eventList);
                eventsRecyclerView.setAdapter(eventsAdapter);
                eventsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if (eventList !=null){
            eventList.clear();
            if (eventsAdapter!=null){
                eventsAdapter.notifyDataSetChanged();
            }
        }
        eventList = new ArrayList<>();
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        return view;
    }
}