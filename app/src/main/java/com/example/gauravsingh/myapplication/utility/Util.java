package com.example.gauravsingh.myapplication.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaurav.singh on 7/3/2018.
 */

public class Util {

    public static String timeAgoFormate(String timeString){
        String agoString = "";
        try
        {


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
            //Date past = format.parse("2016.02.05 AD at 23:59:30");
            Date past = format.parse(timeString);
            Date now = new Date();
            long seconds= TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
            long minutes=TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
            long hours=TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
            long days=TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());
//
//          System.out.println(TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime()) + " milliseconds ago");
//          System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
//          System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
//          System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

            if(seconds<60)
            {
                agoString = seconds+" seconds ago";
                System.out.println(seconds+" seconds ago");
            }
            else if(minutes<60)
            {
                agoString = minutes+" minutes ago";
                System.out.println(minutes+" minutes ago");
            }
            else if(hours<24)
            {
                agoString = hours+" hours ago";
                System.out.println(hours+" hours ago");
            }
            else
            {
                agoString = days+" days ago";
                System.out.println(days+" days ago");
            }
        }
        catch (Exception j){
            j.printStackTrace();
        }

        return agoString;

    }
}
