package com.example.gauravsingh.myapplication.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.activity.MainActivity;
import com.example.gauravsingh.myapplication.adapter.SelectUserAdapter;
import com.example.gauravsingh.myapplication.database.CallSummaryContract;
import com.example.gauravsingh.myapplication.database.CallSummaryDBHelper;
import com.example.gauravsingh.myapplication.databinding.FragmentKeypadBinding;
import com.example.gauravsingh.myapplication.model.SelectUser;
import com.example.gauravsingh.myapplication.utility.AppConstants;
import com.example.gauravsingh.myapplication.utility.FragmentController;
import com.example.gauravsingh.myapplication.utility.SharedPrefsUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KeypadFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private FragmentKeypadBinding binding;
    private CallSummaryDBHelper helper;
    private StringBuilder dialNumber;
    private final int CALL_PHONE_PERMISSION_REQ_CODE = 11;
    private String dialNumberString;
    CallSummaryDBHelper dbHelper;
    Cursor userDetail;
    private boolean isClickedToCall = false;
    ContentResolver resolver;
    private boolean isAccessSet;

    public KeypadFragment() {
        // Required empty public constructor
    }
    public static KeypadFragment newInstance(String param1, String param2) {
        KeypadFragment fragment = new KeypadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_keypad, container, false);
        View view = binding.getRoot();
        setClickListeners();

        setViews();

        return view;
    }

    private void setClickListeners(){
        binding.layoutKeypad.imgClearNumber.setOnClickListener(this);
        binding.layoutKeypad.txtKeyOne.setOnClickListener(this);
        binding.layoutKeypad.txtKeyTwo.setOnClickListener(this);
        binding.layoutKeypad.txtKeyThree.setOnClickListener(this);
        binding.layoutKeypad.txtKeyFour.setOnClickListener(this);
        binding.layoutKeypad.txtKeyFive.setOnClickListener(this);
        binding.layoutKeypad.txtKeySix.setOnClickListener(this);
        binding.layoutKeypad.txtKeySeven.setOnClickListener(this);
        binding.layoutKeypad.txtKeyEight.setOnClickListener(this);
        binding.layoutKeypad.txtKeyNine.setOnClickListener(this);
        binding.layoutKeypad.txtKeyZero.setOnClickListener(this);
        binding.layoutKeypad.txtKeyStar.setOnClickListener(this);
        binding.layoutKeypad.txtKeyHash.setOnClickListener(this);
        binding.layoutKeypad.imgCallBtn.setOnClickListener(this);
        binding.layoutKeypad.imgClearNumber.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialNumber = new StringBuilder("");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                return false;
            }
        });
    }
    private void setViews(){
        dialNumberString = "";
        isAccessSet = false;
        isClickedToCall = false;
        dialNumber = new StringBuilder("");
        dialNumber.append(((MainActivity)getActivity()).dialNumber);
        binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
        resolver = getActivity().getContentResolver();
        dbHelper = new CallSummaryDBHelper(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgClearNumber:
                if (dialNumber.toString().equals("")){
                    return;
                }
                int lastCharIndex = dialNumber.length();
                dialNumber.deleteCharAt(lastCharIndex - 1);
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyOne:
                dialNumber.append("1");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyTwo:
                dialNumber.append("2");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyThree:
                dialNumber.append("3");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyFour:
                dialNumber.append("4");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyFive:
                dialNumber.append("5");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeySix:
                dialNumber.append("6");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeySeven:
                dialNumber.append("7");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyEight:
                dialNumber.append("8");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyNine:
                dialNumber.append("9");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyZero:
                dialNumber.append("0");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyStar:
                dialNumber.append("*");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;
            case R.id.txtKeyHash:
                dialNumber.append("#");
                binding.layoutKeypad.txtDialNumber.setText(dialNumber.toString());
                break;

            case R.id.imgCallBtn:
                if (dialNumber.toString().isEmpty()){
                    Toast.makeText(getActivity(), getResources().getString(R.string.empty_number_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isAccessSet = SharedPrefsUtils.getSharedPrefBoolean(getActivity(), AppConstants.preferences.PREF_NAME,
                        AppConstants.preferences.ACCESS_SETTING, false);
                if (isAccessSet){
                    Toast.makeText(getActivity(), "Call via access number.", Toast.LENGTH_SHORT).show();
                    dialNumberString = getResources().getString(R.string.access_number_value)+","+dialNumber.toString();
                }else{
                    dialNumberString = dialNumber.toString();
                }
                doDataBaseEntry();
                isClickedToCall = true;
                checkPermissionForPhoneCall();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isClickedToCall){
            checkPermissionForPhoneCall();
            isClickedToCall = false;
        }
    }

    private void doDataBaseEntry(){
        String formatedDialedNumber;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            formatedDialedNumber = PhoneNumberUtils.formatNumber(dialNumber.toString(), "IN");
        } else{
            formatedDialedNumber = PhoneNumberUtils.formatNumber(dialNumber.toString());
            // do something for phones running an SDK before lollipop
        }
        userDetail = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?",
                new String[]{dialNumber.toString()},
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " ASC");

        String contactName = "No name";
        String contactNumber = dialNumber.toString();
        String contactImage = "";
        Date dateTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String callTime = sdf.format(dateTime);
        String callDuration = "";
        String callStatus = "";
        String value[] = {contactName, contactNumber, contactImage, callTime, callDuration, callStatus};
        DataBaseEntry entryTask = new DataBaseEntry();
        entryTask.execute(value);

    }

    private void checkPermissionForPhoneCall() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE_PERMISSION_REQ_CODE);
        } else{
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialNumberString)));

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case CALL_PHONE_PERMISSION_REQ_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try{
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialNumberString)));
                    }catch (SecurityException e){

                    }

                } else {
                    Toast.makeText(getActivity(), R.string.call_permission,Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }
    class DataBaseEntry extends AsyncTask<String, Void, Long>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
        @Override
        protected Long doInBackground(String... params) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long newRowId = 0;
            if (userDetail != null) {
                Log.e("count", "" + userDetail.getCount());
                if (userDetail.getCount() == 0) {
                    ContentValues values = new ContentValues();
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME, params[0]);
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NUMBER, params[1]);
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_IMAGE, params[2]);
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_TIME, params[3]);
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_DURATION, params[4]);
                    values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_STATUS, params[5]);
                    newRowId = db.insert(CallSummaryContract.CallEntry.TABLE_NAME, null, values);
                }else{
                    ContentValues values = new ContentValues();
                    while (userDetail.moveToNext()) {
                        Bitmap bit_thumb = null;
                        String id = userDetail.getString(userDetail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                        String name = userDetail.getString(userDetail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String phoneNumber = userDetail.getString(userDetail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String EmailAddr = userDetail.getString(userDetail.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA2));
                        String image_thumb = userDetail.getString(userDetail.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));

                        // Create a new map of values, where column names are the keys

                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME, name);
                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NUMBER, phoneNumber);
                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_IMAGE, image_thumb);
                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_TIME, params[3]);
                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_DURATION, params[4]);
                        values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_STATUS, params[5]);

                    }
                    newRowId = db.insert(CallSummaryContract.CallEntry.TABLE_NAME, null, values);
                }
            } else {
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME, params[0]);
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NUMBER, params[1]);
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_IMAGE, params[2]);
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_TIME, params[3]);
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_DURATION, params[4]);
                values.put(CallSummaryContract.CallEntry.COLUMN_NAME_CALL_STATUS, params[5]);
                newRowId = db.insert(CallSummaryContract.CallEntry.TABLE_NAME, null, values);
                Log.e("Cursor close 1", "----------------");
            }
            return newRowId;
        }
        @Override
        protected void onPostExecute(Long rowId) {
            super.onPostExecute(rowId);
            //Toast.makeText(getActivity(),"row inserted"+ rowId, Toast.LENGTH_SHORT).show();


        }
    }
    @Override
    public void onStop() {
        dbHelper.close();
        super.onStop();
    }
}
