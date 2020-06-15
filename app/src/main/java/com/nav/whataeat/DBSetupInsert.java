package com.nav.whataeat;

import android.content.Context;

public class DBSetupInsert {

    // Variables
    private final Context context;

    // Public Class
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }

    // Setup to Insert food into food table
    public void setupInsertToFood(String values) {
        DBAdapter db = new DBAdapter(context);
        db.open();
        db.insert("food",
                "food_id, food_name, food_serving_size, food_user_id, food_category", values);
        db.close();
    }

    // Insert All Food into food database
    public void insertAllFood() {
        setupInsertToFood("NULL, 'Boiled Egg', '66.0', NULL, 'Breakfast'");
        setupInsertToFood("NULL, 'Rajma Chawal', '236.0', NULL, 'Lunch'");
        setupInsertToFood("NULL, 'Chicken', '136.0', NULL, 'Dinner'");
    }

}