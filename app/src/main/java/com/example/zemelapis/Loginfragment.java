package com.example.zemelapis;

import android.app.Activity;
import android.content.Context;
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

public class Loginfragment extends Fragment {
    private TextView RegText;
    private EditText email, password;
    private Button BnLogin;
    OnLoginFormActivityListener loginFormActivityListener;

    public interface OnLoginFormActivityListener
    {
        public void performRegister();
        public void performLogin(String name);
    }


    public Loginfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_loginfragment, container,false);
        RegText = view.findViewById(R.id.register_txt);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.pass);
        BnLogin = view.findViewById(R.id.login);
        BnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 performLogin();
            }
        });
      RegText.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){
          loginFormActivityListener.performRegister();
          }
        });
      return view;
    };
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener)
activity;
    }

    private void performLogin(){
        String usermail = email.getText().toString();
        String pass = password.getText().toString();
        Call<User> call = login.apiInterface.performUserLogin(usermail,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    login.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }
                 else if(response.body().getResponse().equals("failed")){
                    login.prefConfig.displayToast("failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                login.prefConfig.displayToast("nepaejo prisijungt");
            }
        });

        email.setText("");
        password.setText("");
    }

}
