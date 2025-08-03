package com.automationexercices.utils;

public class TimeManager {
    //screenshots - logs - reports
    public static String getTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());
    }
    // unique timestamp for each data
    public static String getSimpleTimestamp() {
        //return a timestamp in numbers only
        return Long.toString(System.currentTimeMillis());
    }

}
