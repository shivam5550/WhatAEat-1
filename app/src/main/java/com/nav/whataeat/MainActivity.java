package com.nav.whataeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // STETHO
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        DBAdapter db = new DBAdapter(this);
        db.open();

        // Count rows in food
        int numberOfRows = db.count("food");

        if(numberOfRows < 1){
            // Run Setup
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
        }

//        Toast.makeText(this, "There are " + numberOfRows + " rows in the table", Toast.LENGTH_LONG).show();

        db.close();

//        Toast.makeText(this, "Database Works, food created!", Toast.LENGTH_SHORT).show();
    }
}