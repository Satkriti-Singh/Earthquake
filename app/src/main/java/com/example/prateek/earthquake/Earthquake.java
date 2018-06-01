package com.example.prateek.earthquake;

public class Earthquake {
    private String mplace;
    private Double mmag;
    private String mdate;
    private  String murl;

    private long mmillisecond;

    public Earthquake(String place, Double mag, long millisecond,String url)
    {
        mplace=place;
        mmag=mag;
        mmillisecond=millisecond;
        murl=url;
    }

    public String getMplace() {
        return mplace;
    }

    public long getMmillisecond() {
        return mmillisecond;
    }

    public Double getMmag() {
        return mmag;
    }
    public  String getMurl(){
        return murl;

    }

}
