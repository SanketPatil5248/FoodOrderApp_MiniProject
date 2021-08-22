package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION =2;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders "+
                        "(id integer primary key autoincrement,"+ //id automatically gets incrementd.
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "quantity int,"+
                        "description text,"+
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders"); //drops table orders if exists
        onCreate(sqLiteDatabase); //again for upgrade we create table using this function.
    }

    public boolean insertOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity) // data insertion function.
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
        id = 0
        name =1
        phone = 2
        price = 3
        image =4
        desc = 5
        foodname = 6
        quantity = 7
         */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        values.put("quantity",quantity);
        long id = database.insert("orders", null, values);
        if(id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, foodname, image, price from orders", null );      //Query function
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);

            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {            //regarding update code

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from orders where id = "+id, null );      //Query function

        if(cursor != null)
            cursor.moveToFirst();

       // cursor.close();
       // database.close();
        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity, int id) // data insertion function.
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
        id = 0
        name =1
        phone = 2
        price = 3
        image =4
        desc = 5
        foodname = 6
        quantity = 7
         */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodName",foodName);
        values.put("quantity",quantity);
        long row = database.update("orders", values, "id="+id, null);
        if(row <= 0) {
            return false;
        } else {
            return true;
        }
    }
    public int deletedOrder(String id) {            //deletion Code
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id, null);
    }
}
