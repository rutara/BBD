package com.example.zemelapis;

import com.google.gson.annotations.SerializedName;

public class User {
    private String response;
    private  String name;

    public User(String name) {
        this.name = name;

    }


    public String getResponse(){
        return response;
    }
    public String getName(){

        return name;
    }

}
