package com.istiaksaif.uniclubz.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.istiaksaif.uniclubz.Activity.RegistationActivity;
import com.istiaksaif.uniclubz.R;
import com.istiaksaif.uniclubz.viewmodel.AuthViewModel;

public class SignUpFragment extends Fragment {

    private TextInputEditText fullName,email,nid,password,passwordRepeat;
    private Button registrationButton;
    private TextView signInText;
    private AuthViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    navController.navigate(R.id.action_signUpFragment_to_signInFragment);
                }
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullName = view.findViewById(R.id.name);
        email = view.findViewById(R.id.eamil);
        nid = view.findViewById(R.id.nid);
        password = view.findViewById(R.id.pass);
        passwordRepeat = view.findViewById(R.id.passretype);
        registrationButton = view.findViewById(R.id.reg_button);

        navController = Navigation.findNavController(view);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = fullName.getText().toString();
                String Email = email.getText().toString();
                String NID = nid.getText().toString();
                String Password = password.getText().toString();
                String Password_re = passwordRepeat.getText().toString();
                if (TextUtils.isEmpty(Password_re)){
                    Toast.makeText(getActivity(), "please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!Password.equals(Password_re)){
                    passwordRepeat.setError("password not match");
                    return;
                }
                else if (Password.length()<8){
                    Toast.makeText(getActivity(), "password week & password length at least 8 character", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!isValidEmail(Email)){
                    email.setError("Invalid email");
                    return;
                }

                if (!FullName.isEmpty() && !Email.isEmpty() && !NID.isEmpty() && !Password.isEmpty() && !Password_re.isEmpty()){
                    viewModel.register(Email, Password);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }
    private Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}