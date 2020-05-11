package com.example.zemelapis;


public class Place {

    private String place_type;
    private String place_loc;
    private String place_comments;


    public String getLocation(){
        return place_loc;
    }
    public String getType(){

        return place_type;
    }
    public String getComments(){

        return place_comments;
    }

}

