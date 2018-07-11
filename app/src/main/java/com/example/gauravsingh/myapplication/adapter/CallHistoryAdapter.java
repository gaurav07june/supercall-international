package com.example.gauravsingh.myapplication.adapter;

/**
 * Created by gaurav.singh on 7/3/2018.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.model.CallHistory;
import com.example.gauravsingh.myapplication.model.SelectUser;
import com.example.gauravsingh.myapplication.utility.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CallHistoryAdapter extends BaseAdapter{

    public List<CallHistory> _data;
    private ArrayList<CallHistory> arraylist;
    Context _c;
    ViewHolder v;


    public CallHistoryAdapter(List<CallHistory> callHistoryList, Context context) {
        _data = callHistoryList;
        _c = context;
        this.arraylist = new ArrayList<CallHistory>();
        this.arraylist.addAll(_data);
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int i) {
        return _data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.call_history_row, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }

        v = new ViewHolder();
        v.name = (TextView) view.findViewById(R.id.txtUserName);
        v.number = (TextView) view.findViewById(R.id.txtNumber);
        v.status = (TextView) view.findViewById(R.id.txtStatus);
        v.duration = (TextView) view.findViewById(R.id.txtCallDuration);
        v.callTime = (TextView) view.findViewById(R.id.txtCallTime);
        v.separator = (View) view.findViewById(R.id.separator);
        v.imageView = (ImageView) view.findViewById(R.id.imgUser);

        if (i == (_data.size() - 1)){
            v.separator.setVisibility(View.GONE);
        }else{
            v.separator.setVisibility(View.VISIBLE);
        }

        final CallHistory data = (CallHistory) _data.get(i);
       v.name.setText(data.getName());
        v.number.setText(data.getNumber());
        v.status.setText(data.getCallstatus());
        v.duration.setText(data.getCallduration());
        v.callTime.setText(Util.timeAgoFormate(data.getCalltime()));

        //Set image if exists
        try {

            if (data.getImage() != null) {
                v.imageView.setImageBitmap(data.getImage());
            } else {
                v.imageView.setImageResource(R.drawable.super_call_man);
            }

        } catch (OutOfMemoryError e) {

            v.imageView.setImageDrawable(this._c.getDrawable(R.drawable.super_call_man));
            e.printStackTrace();
        }

        Log.e("Image Thumb", "--------------" + data.getImage());

        /*// Set check box listener android
        v.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    data.setCheckedBox(true);
                  } else {
                    data.setCheckedBox(false);
                }
            }
        });*/

        view.setTag(data);
        return view;
    }
    static class ViewHolder {
        ImageView imageView;
        TextView name, number, status, duration, callTime;
        View separator;
    }
    public void clearAll(){
        _data.clear();
        notifyDataSetChanged();
    }
}
