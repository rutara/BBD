package com.example.zemelapis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.zemelapis.ApiClient.BASE_URL;

public class login extends AppCompatActivity implements Loginfragment.OnLoginFormActivityListener {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null){
                return;
            }

            if (prefConfig.readLoginStatus()) {
                //  startActivity(new Intent(login.this, MainActivity.class));
                // getSupportFragmentManager().beginTransaction().add()
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Loginfragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Loginfragment()).commit();

            }
        }
    }


    @Override
    public void performRegister() {
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
              new registrationfragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.write(name);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new );
        login.prefConfig.displayToast("galima pereit i main activity");
        startActivity(new Intent(login.this, MainActivity.class));

    }

}
