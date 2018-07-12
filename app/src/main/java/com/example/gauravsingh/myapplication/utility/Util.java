package com.example.gauravsingh.myapplication.utility;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public static void getStyledString(){
        SpannableString styledString
                = new SpannableString("Large\n\n"     // index 0 - 5
                + "Bold\n\n"          // index 7 - 11
                + "Underlined\n\n"    // index 13 - 23
                + "Italic\n\n"        // index 25 - 31
                + "Strikethrough\n\n" // index 33 - 46
                + "Colored\n\n"       // index 48 - 55
                + "Highlighted\n\n"   // index 57 - 68
                + "K Superscript\n\n" // "Superscript" index 72 - 83
                + "K Subscript\n\n"   // "Subscript" index 87 - 96
                + "Url\n\n"           //  index 98 - 101
                + "Clickable\n\n");   // index 103 - 112

        // make the text twice as large
        styledString.setSpan(new RelativeSizeSpan(2f), 0, 5, 0);

        // make text bold
        styledString.setSpan(new StyleSpan(Typeface.BOLD), 7, 11, 0);

        // underline text
        styledString.setSpan(new UnderlineSpan(), 13, 23, 0);

        // make text italic
        styledString.setSpan(new StyleSpan(Typeface.ITALIC), 25, 31, 0);

        styledString.setSpan(new StrikethroughSpan(), 33, 46, 0);

        // change text color
        styledString.setSpan(new ForegroundColorSpan(Color.GREEN), 48, 55, 0);

        // highlight text
        styledString.setSpan(new BackgroundColorSpan(Color.CYAN), 57, 68, 0);

        // superscript
        styledString.setSpan(new SuperscriptSpan(), 72, 83, 0);
        // make the superscript text smaller
        styledString.setSpan(new RelativeSizeSpan(0.5f), 72, 83, 0);

        // subscript
        styledString.setSpan(new SubscriptSpan(), 87, 96, 0);
        // make the subscript text smaller
        styledString.setSpan(new RelativeSizeSpan(0.5f), 87, 96, 0);

        // url
        styledString.setSpan(new URLSpan("http://www.google.com"), 98, 101, 0);

        // clickable text
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                // We display a Toast. You could do anything you want here.
               // Toast.makeText(SpanExample.this, "Clicked", Toast.LENGTH_SHORT).show();

            }
        };

        styledString.setSpan(clickableSpan, 103, 112, 0);


       /* textView.setMovementMethod(LinkMovementMethod.getInstance());


        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.WHITE);

        textView.setText(styledString);

        setContentView(textView);*/


    }
}
