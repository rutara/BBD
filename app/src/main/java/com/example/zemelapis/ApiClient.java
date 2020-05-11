package com.example.zemelapis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://192.168.1.144/maps/";
    public static Retrofit retrofit = null;




   public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

public static Retrofit getApiClient()
{

    if(retrofit==null){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    return retrofit;

}
}
