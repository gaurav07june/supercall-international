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
import com.example.gauravsingh.myapplication.model.SelectUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class SelectUserAdapter extends BaseAdapter{

    public List<SelectUser> _data;
    private ArrayList<SelectUser> arraylist;
    Context _c;
    ViewHolder v;
    ImageView image;


    public SelectUserAdapter(List<SelectUser> selectUsers, Context context) {
        _data = selectUsers;
        _c = context;
        this.arraylist = new ArrayList<SelectUser>();
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
            view = li.inflate(R.layout.contact_item_view, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }

        v = new ViewHolder();

        v.title = (TextView) view.findViewById(R.id.name);
        v.phone = (TextView) view.findViewById(R.id.no);
        v.imageView = (ImageView) view.findViewById(R.id.pic);
        v.separotor = (View) view.findViewById(R.id.separator);
        if (i == (_data.size() - 1)){
            v.separotor.setVisibility(View.GONE);
        }else{
            v.separotor.setVisibility(View.VISIBLE);
        }

        final SelectUser data = (SelectUser) _data.get(i);
        v.title.setText(data.getName());
        //v.check.setChecked(data.getCheckedBox());
        v.phone.setText(data.getPhone());

        // Set image if exists
        try {

            if (data.getThumb() != null) {
                v.imageView.setImageBitmap(data.getThumb());
            } else {
                v.imageView.setImageResource(R.drawable.super_call_man);
            }
            // Seting round image
            /*Bitmap bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.contact_icon); // Load default image
            roundedImage = new RoundImage(bm);
            v.imageView.setImageDrawable(image);*/
        } catch (OutOfMemoryError e) {
            // Add default picture
            v.imageView.setImageDrawable(this._c.getDrawable(R.drawable.super_call_man));
            e.printStackTrace();
        }

        Log.e("Image Thumb", "--------------" + data.getThumb());

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

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _data.clear();
        if (charText.length() == 0) {
            _data.addAll(arraylist);
        } else {
            for (SelectUser wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    _data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    static class ViewHolder {
        ImageView imageView;
        TextView title, phone;
        CheckBox check;
        View separotor;
    }
}
