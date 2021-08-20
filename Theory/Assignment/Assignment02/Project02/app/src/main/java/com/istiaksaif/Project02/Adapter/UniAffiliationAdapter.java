package com.istiaksaif.Project02.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.istiaksaif.Project02.Activity.FinalActivity;
import com.istiaksaif.Project02.Fragment.FirstTabFragment;
import com.istiaksaif.Project02.R;
import com.istiaksaif.Project02.Utils.FinalUniAffiliation;
import com.istiaksaif.Project02.Utils.ItemList;
import com.istiaksaif.Project02.Utils.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static com.istiaksaif.Project02.Activity.ProfileActivity.Contact_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.Uni_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.User_Key;
import static com.istiaksaif.Project02.Activity.ProfileActivity.fileContent;
import static com.istiaksaif.Project02.Activity.ProfileActivity.fileContent1;
import static com.istiaksaif.Project02.Activity.ProfileActivity.fileContent2;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filePath;
import static com.istiaksaif.Project02.Activity.ProfileActivity.filename;
import static com.istiaksaif.Project02.Activity.ProfileActivity.submitButton;
import static com.istiaksaif.Project02.Adapter.ItemAdapter.Email;
import static com.istiaksaif.Project02.Adapter.ItemAdapter.Phone;
import static com.istiaksaif.Project02.Utils.optionUniName.optionUniName;

public class UniAffiliationAdapter extends RecyclerView.Adapter<UniAffiliationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ItemList> mdata;

    public UniAffiliationAdapter(Context context, ArrayList<ItemList> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public UniAffiliationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.uniafffiliationcard,parent,false);
        UniAffiliationAdapter.ViewHolder viewHolder = new UniAffiliationAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UniAffiliationAdapter.ViewHolder holder, int position) {
        holder.itemopen.setText(mdata.get(position).getlOpen());
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uniname = holder.uniName.getText().toString();
                String studentid = holder.studentID.getText().toString();
                String dep = holder.department.getText().toString();
                String level = holder.level.getText().toString();

                String FullName = FirstTabFragment.fullName.getText().toString();
                String DateOfBirth = FirstTabFragment.dateOfBirth.getText().toString();
                String NID = FirstTabFragment.nid.getText().toString();
                String BloodGroup = FirstTabFragment.bloodGroup.getText().toString();

                ItemList itemList = new ItemList();
                String phone = itemList.getPhone();
                String email = itemList.getEmail();


                    fileContent ="Name : " +FullName+"\n"+"DOB: " +DateOfBirth+"\n"
                            +"NID : " +NID+"\n"+"Blood Group : " +BloodGroup;
                    if(!fileContent.equals("")){
                        File myFile = new File(context.getExternalFilesDir(filePath),filename);
                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(myFile);
                            fileOutputStream.write(fileContent.getBytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    fileContent1 ="University Name : " +uniname+"\n"+"StudentId: " +studentid+"\n"
                            +"Department : " +dep+"\n"+"Level : " +level;
                    if(!fileContent1.equals("")) {
                        File myFile = new File(context.getExternalFilesDir(filePath), "uniInfo.txt");
                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(myFile);
                            fileOutputStream.write(fileContent1.getBytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    fileContent2 ="Phone Number : " +Phone+"\n"+"Email : " +Email;
                    if(!fileContent2.equals("")) {
                        File myFile = new File(context.getExternalFilesDir(filePath), "contactInfo.txt");
                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(myFile);
                            fileOutputStream.write(fileContent2.getBytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                Intent intent = new Intent(context, FinalActivity.class);
                intent.putExtra(User_Key,new User(FullName,DateOfBirth,NID,BloodGroup));
                intent.putExtra(Uni_Key,new FinalUniAffiliation(uniname,studentid,dep,level,null));
                intent.putExtra(Contact_Key, new ItemList(phone,email,null));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemopen;
        public TextInputEditText studentID;
        public MaterialAutoCompleteTextView uniName,department,level;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemopen = itemView.findViewById(R.id.layoutopen);
            studentID = itemView.findViewById(R.id.studentId);
            uniName = itemView.findViewById(R.id.uniName);
            department = itemView.findViewById(R.id.department);
            level = itemView.findViewById(R.id.level);
            TextInputLayout textInputLayoutUniName = itemView.findViewById(R.id.uninamelayout);
            ArrayAdapter<String> arrayAdapterUni = new ArrayAdapter<>(context,R.layout.usertype_item,optionUniName);
            ((MaterialAutoCompleteTextView) textInputLayoutUniName.getEditText()).setAdapter(arrayAdapterUni);

            TextInputLayout textInputLayout = itemView.findViewById(R.id.departmentdropdown);
            String []option = {"CSE","BBA","Economics","Bio Chemistry","Architecture","Pharmacy","EEE","Math","Physics"};
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,R.layout.usertype_item,option);
            ((MaterialAutoCompleteTextView) textInputLayout.getEditText()).setAdapter(arrayAdapter);

            TextInputLayout textInputLayout1 = itemView.findViewById(R.id.dropdown);
            String []option1 = {"UnderGraduate","MS","PhD","Post-Doc"};
            ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(context,R.layout.usertype_item,option1);
            ((MaterialAutoCompleteTextView) textInputLayout1.getEditText()).setAdapter(arrayAdapter1);

        }
    }
}
