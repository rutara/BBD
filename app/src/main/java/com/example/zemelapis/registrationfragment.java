package com.example.zemelapis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrationfragment extends Fragment {

private EditText Name,uemail,UserPassword;
private Button BnRegister;
private TextView textViewResult;
    public registrationfragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_registrationfragment, container,false);
        Name = view.findViewById(R.id.txt_name);
        uemail = view.findViewById(R.id.txt_email);
        UserPassword = view.findViewById(R.id.txt_password);
        BnRegister = view.findViewById(R.id.bn_register);
        textViewResult = view.findViewById(R.id.test);
        BnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
          performRegistration();
            }
        });
    return view;
    }
    public void performRegistration(){
        String name = Name.getText().toString();
        String email = uemail.getText().toString();
        String password = UserPassword.getText().toString();
        Call<User> call = login.apiInterface.performRegistration(name,email,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    login.prefConfig.displayToast("Registracija pavykus");
                }
                else if(response.body().getResponse().equals("exist")) {
                    login.prefConfig.displayToast("Toks naudotojas jau egzistuoja");
                }
                else if(response.body().getResponse().equals("error")) {
                    login.prefConfig.displayToast("Kažkas nepavyko :(");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                login.prefConfig.displayToast("Kažkas nepavyko");
            }
        });
        Name.setText("");
        UserPassword.setText("");
        uemail.setText("");
    }
}
