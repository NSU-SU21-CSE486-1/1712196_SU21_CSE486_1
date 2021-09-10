package com.istiaksaif.Project03.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.istiaksaif.Project03.R;
import com.istiaksaif.Project03.Utils.Contact;
import com.istiaksaif.Project03.Utils.UniAff;
import com.istiaksaif.Project03.Utils.User;
import com.istiaksaif.Project03.roomdb.DatabaseClass;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private TextView name,nid,blood,dob;
    private RecyclerView uniaffrecyclerview,contactrecyclerview;
    private FinalUniAffiliationAdapter finalUniAffiliationAdapter;
    private FinalContactAdapter finalContactAdapter;

    private Context context;
    private List<User> userList;
    private List<UniAff> uniAffList;
    private List<Contact> contactList;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    public UserListAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getName());
        holder.nid.setText(userList.get(position).getNID());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseClass db = DatabaseClass.getDbInstance(context.getApplicationContext());
                userList =db.userDao().getAllUsers();

                dialogBuilder = new AlertDialog.Builder(context);
                final View contactPopupView = LayoutInflater.from(context).inflate(R.layout.popup,null);

                dialogBuilder.setView(contactPopupView);
                dialog = dialogBuilder.create();
                dialog.show();

                name = contactPopupView.findViewById(R.id.name);
                dob = contactPopupView.findViewById(R.id.dob);
                nid = contactPopupView.findViewById(R.id.nid);
                blood = contactPopupView.findViewById(R.id.blood);

                name.setText(userList.get(position).getName());
                dob.setText(userList.get(position).getDOB());
                nid.setText(userList.get(position).getNID());
                blood.setText(userList.get(position).getBloodGroup());
                uniaffrecyclerview = contactPopupView.findViewById(R.id.uniaffiliationcardrecycle);
                uniaffrecyclerview.setLayoutManager(new LinearLayoutManager(context));

                try {
                    uniAffList =db.userDao().findForUser(userList.get(position).getUserId());
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                    uniaffrecyclerview.addItemDecoration(dividerItemDecoration);
                    finalUniAffiliationAdapter = new FinalUniAffiliationAdapter(context);
                    uniaffrecyclerview.setAdapter(finalUniAffiliationAdapter);
                    finalUniAffiliationAdapter.setUserList(uniAffList);

                    contactrecyclerview = contactPopupView.findViewById(R.id.contactrecyclerView);
                    contactrecyclerview.setLayoutManager(new LinearLayoutManager(context));
                }catch (Exception e){
                    Toast.makeText(context,"Data not Exits",Toast.LENGTH_LONG).show();
                }

                try {
                    contactList =db.userDao().getUserContacts(userList.get(position).getUserId());
                    DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                    contactrecyclerview.addItemDecoration(dividerItemDecoration1);
                    finalContactAdapter = new FinalContactAdapter(context);
                    contactrecyclerview.setAdapter(finalContactAdapter);
                    finalContactAdapter.setUserList(contactList);
                }catch (Exception e){
                    Toast.makeText(context,"Data not Exits",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name, nid;
        private CardView linearLayout;

        public MyViewHolder(View view) {
            super(view);
            nid = itemView.findViewById(R.id.nid);
            name = itemView.findViewById(R.id.name);
            linearLayout = itemView.findViewById(R.id.layout);
        }
    }
}
