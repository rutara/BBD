package com.example.zemelapis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("register.php")
    Call<User> performRegistration(@Query("name") String name, @Query("email") String email, @Query("password") String password);

    @Headers("Content-Type: application/json")
    @GET("login.php")
    Call<User>performUserLogin(@Query("email") String email, @Query("password") String password);

    @Headers("Content-Type: application/json")
    @GET("write.php")
    Call<Place>performWrite(@Query("place_type") String place_type, @Query("place_comments") String place_comments, @Query("place_loc") String place_loc);

    @Headers("Content-Type: application/json")
    @GET("read.php")
    Call<Place>performRead(@Query("place_type") String place_type, @Query("place_loc") String place_loc);
}
