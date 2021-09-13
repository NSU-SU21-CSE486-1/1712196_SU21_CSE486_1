package com.istiaksaif.uniclubz.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Activity.ChatActivity;
import com.istiaksaif.uniclubz.Model.EventItem;
import com.istiaksaif.uniclubz.Model.MemberItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.istiaksaif.uniclubz.Fragment.ProfileFragment.uniDep;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MemberItem> mdata;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();

    public MembersAdapter(Context context, ArrayList<MemberItem> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.memberitemcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = mdata.get(position).getUserId();
        holder.membername.setText(mdata.get(position).getName());
        holder.memberUni.setText("Studies "+mdata.get(position).getDepartment()+" at "+mdata.get(position).getUniname());
        Glide.with(context).load(mdata.get(position).getImage()).placeholder(R.drawable.dropdown).into(holder.memberImage);
        if(id.equals(uid)){
            holder.chat.setVisibility(View.GONE);
        }else {
            holder.chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    intent.putExtra("userId",id);
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView memberImage,chat;
        TextView membername,memberUni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memberImage = (ImageView) itemView.findViewById(R.id.memberimage);
            membername = (TextView) itemView.findViewById(R.id.membername);
            memberUni = (TextView) itemView.findViewById(R.id.currentuni);
            chat = (ImageView) itemView.findViewById(R.id.chat);
        }
    }
}
