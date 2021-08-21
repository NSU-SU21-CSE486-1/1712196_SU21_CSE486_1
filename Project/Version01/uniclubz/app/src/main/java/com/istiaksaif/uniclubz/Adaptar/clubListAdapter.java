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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istiaksaif.uniclubz.Model.ClubListItem;
import com.istiaksaif.uniclubz.R;

import java.util.ArrayList;


public class clubListAdapter extends RecyclerView.Adapter<clubListAdapter.ViewHolder> {
    private static final String Tag = "RecyclerView";
    private Context context;
    private ArrayList<ClubListItem> mdata;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String ClubId;

    public clubListAdapter(Context context, ArrayList<ClubListItem> mdata) {
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
        ClubId = mdata.get(position).getClubId();

        holder.clubname.setText(mdata.get(position).getClubName());


        Glide.with(context).load(mdata.get(position).getImage()).placeholder(R.drawable.dropdown).into(holder.clubImage);

//        if(mdata.get(position).getStatus().equals("")) {
//            holder.joinButton.setVisibility(View.VISIBLE);
//        }else {
//            holder.joinButton.setVisibility(View.GONE);
//        }
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
        }
    }
}
