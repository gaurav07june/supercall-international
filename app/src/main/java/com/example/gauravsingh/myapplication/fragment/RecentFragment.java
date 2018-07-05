package com.example.gauravsingh.myapplication.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gauravsingh.myapplication.R;
import com.example.gauravsingh.myapplication.activity.MainActivity;
import com.example.gauravsingh.myapplication.adapter.CallHistoryAdapter;
import com.example.gauravsingh.myapplication.database.CallSummaryContract;
import com.example.gauravsingh.myapplication.database.CallSummaryDBHelper;
import com.example.gauravsingh.myapplication.databinding.FragmentRecentBinding;
import com.example.gauravsingh.myapplication.model.CallHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecentFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentRecentBinding binding;
    CallSummaryDBHelper dbHelper;
    ArrayList<CallHistory> callHistoryArrayList;
    CallHistoryAdapter adapter;
    ContentResolver resolver;

    public RecentFragment() {
    }

    public static RecentFragment newInstance(String param1, String param2) {
        RecentFragment fragment = new RecentFragment();
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recent, container, false);
        View view = binding.getRoot();
        //((MainActivity)getActivity()).setToolbar(getResources().getString(R.string.recent));
        setView();
        return view;
    }

    private void setView(){
        dbHelper = new CallSummaryDBHelper(getContext());
        resolver = getActivity().getContentResolver();
        binding.txtClearAll.setOnClickListener(this);
        DateBaseOperation dbOperation = new DateBaseOperation();
        dbOperation.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtClearAll:
                performDelete();

                break;
        }
    }

    public void performDelete(){
        DeleteDBOperation deleteOperation = new DeleteDBOperation();
        deleteOperation.execute();
    }



    class DateBaseOperation extends AsyncTask<Void,  Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {BaseColumns._ID,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NUMBER,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_IMAGE,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CALL_TIME,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CALL_DURATION,
                    CallSummaryContract.CallEntry.COLUMN_NAME_CALL_STATUS
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME + " = ?";
            String[] selectionArgs = { "" };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME + " DESC";

            Cursor cursor = db.query(
                    CallSummaryContract.CallEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause (selection)
                    null,          // The values for the WHERE clause (selectionArgs)
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            callHistoryArrayList = new ArrayList<>();

            while(cursor.moveToNext()) {
                CallHistory callHistory = new CallHistory();
                long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(CallSummaryContract.CallEntry._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_NUMBER));
                String image = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CONTACT_IMAGE));
                String callTime = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CALL_TIME));
                String callDuration = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CALL_DURATION));
                String callStatus = cursor.getString(cursor.getColumnIndexOrThrow
                        (CallSummaryContract.CallEntry.COLUMN_NAME_CALL_STATUS));
                Bitmap bit_thumb = null;
                try {
                    if (image != null) {
                        bit_thumb = MediaStore.Images.Media.getBitmap(resolver, Uri.parse(image));
                    } else {
                        Log.e("No Image Thumb", "--------------");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                callHistory.setName(name);
                callHistory.setNumber(number);
                callHistory.setImage(bit_thumb);
                callHistory.setCalltime(callTime);
                callHistory.setCallduration(callDuration);
                callHistory.setCallstatus(callStatus);
                callHistoryArrayList.add(callHistory);
            }
            cursor.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            StringBuilder demo = new StringBuilder("");
            for (CallHistory callHistory : callHistoryArrayList){
                demo.append(callHistory.getNumber());
            }
            adapter = new CallHistoryAdapter(callHistoryArrayList, getActivity());
            binding.listCallHistory.setAdapter(adapter);
        }
    }

    class DeleteDBOperation extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("delete from "+ CallSummaryContract.CallEntry.TABLE_NAME);
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.clearAll();
        }
    }

    @Override
    public void onStop() {
        dbHelper.close();
        super.onStop();
    }
}
