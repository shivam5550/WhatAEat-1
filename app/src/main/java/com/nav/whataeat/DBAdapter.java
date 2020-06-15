package com.nav.whataeat;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
//    VARIABLES
    private static final String databaseName = "whataeat";
    private static final int databaseVersion = 4;

//    DATABASE VARIABLES
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

//    CLASS DBAdapter
    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

//    DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                // Create tables
                db.execSQL("CREATE TABLE IF NOT EXISTS food " +
                            " (food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            " food_name VARCHAR," +
                            " food_serving_size DOUBLE," +
                            " food_user_id INT," +
                            " food_category VARCHAR," +
                            "food_note VARCHAR);");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS food ");
            onCreate(db);

            String TAG = "Tag";
            Log.w(TAG, "Upgrading database from version "+ oldVersion+" to "
                    + newVersion + ", which will destroy all old data.");
        }
    } // end of databaseHelper

    // Open Database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Close Database
    public void close() {
        DBHelper.close();
    }

    // Inserting Data
    public void insert(String table, String fields, String values){
         db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
    }

    // Count
    public int count(String table) {
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
}
