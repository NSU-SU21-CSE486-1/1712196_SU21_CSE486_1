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
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;


public class ClubListAdapter extends RecyclerView.Adapter<ClubListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ClubListItem> mdata;

    public ClubListAdapter(Context context, ArrayList<ClubListItem> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.clubcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ClubId = mdata.get(position).getClubId();

        holder.clubname.setText(mdata.get(position).getClubName());

        Glide.with(context).load(mdata.get(position).getImage()).placeholder(R.drawable.dropdown).into(holder.clubImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClubActivity.class);
                intent.putExtra("clubId",ClubId);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView clubImage;
        TextView clubname,joinButton;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clubImage = (ImageView) itemView.findViewById(R.id.clubimage);
            clubname = (TextView) itemView.findViewById(R.id.clubname);

            joinButton = (TextView) itemView.findViewById(R.id.joinclubbutton);
            layout = (LinearLayout) itemView.findViewById(R.id.clubcard);
            WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = window.getDefaultDisplay();
            int displayWidth = display.getWidth();
            layout.getLayoutParams().width = (displayWidth/9)*4;
            joinButton.setVisibility(View.GONE);
        }
    }
}
