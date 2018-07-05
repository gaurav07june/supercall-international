package com.example.gauravsingh.myapplication.database;

import android.provider.BaseColumns;

/**
 * Created by gaurav.singh on 7/2/2018.
 */

public class CallSummaryContract {

    private CallSummaryContract() {}

    public static class CallEntry implements BaseColumns {
        public static final String TABLE_NAME = "call_entry";
        public static final String COLUMN_NAME_CONTACT_NAME = "contact_name";
        public static final String COLUMN_NAME_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_NAME_CONTACT_IMAGE = "contact_image";
        public static final String COLUMN_NAME_CALL_TIME = "call_time";
        public static final String COLUMN_NAME_CALL_DURATION = "call_duration";
        public static final String COLUMN_NAME_CALL_STATUS = "call_status";

    }
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CallEntry.TABLE_NAME + " (" +
                    CallEntry._ID + " INTEGER PRIMARY KEY," +
                    CallEntry.COLUMN_NAME_CONTACT_NAME + " TEXT," +
                    CallEntry.COLUMN_NAME_CONTACT_NUMBER + " TEXT," +
                    CallEntry.COLUMN_NAME_CONTACT_IMAGE + " TEXT," +
                    CallEntry.COLUMN_NAME_CALL_TIME + " TEXT," +
                    CallEntry.COLUMN_NAME_CALL_DURATION + " TEXT," +
                    CallEntry.COLUMN_NAME_CALL_STATUS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CallEntry.TABLE_NAME;
}
