package edu.ewubd.mybloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    private static final String dbname = "bloodbank.db";


    public Database(Context context) {
        super(context, dbname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_query = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT,user_id VARCHAR(50) UNIQUE,name VARCHAR(100),email VARCHAR(255) UNIQUE,phone VARCHAR(100),divition VARCHAR(100),address VARCHAR(200),bloodGroup VARCHAR(100),lastDonation VARCHAR(100),password VARCHAR(100))";
        db.execSQL(user_query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean user_insert(String user_id,String name,String email, String phone,String divition,String address,String bloodGroup,String lastDonation, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_id",user_id);
        values.put("name",name);
        values.put("address",address);
        values.put("phone", phone);
        values.put("divition", divition);
        values.put("bloodGroup", bloodGroup);
        values.put("lastDonation", lastDonation);
        values.put("email",email);
        values.put("password",password);
        //Boolean user = check_user(user_id);
        long r = db.insert("users",null,values);
        if(r==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean check_user(String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where user_id=?",new String[] {user_id});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean check_email(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email=?",new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }




    public Boolean check_login(String user_id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where user_id = ? and password = ?",new String[]{user_id,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    public Cursor searchResult(String bloodGroup, String divition){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where bloodGroup = ? and divition = ?",new String[]{bloodGroup,divition});
        return cursor;

    }
    public Cursor userInfo(String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where user_id=?",new String[] {user_id});
        return cursor;

    }

    public boolean update_info(String user_id,String name,String email, String phone,String divition,String address,String bloodGroup,String lastDonation, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put("name", name);
        dataToInsert.put("email", email);
        dataToInsert.put("phone", phone);
        dataToInsert.put("divition", divition);
        dataToInsert.put("address", address);
        dataToInsert.put("bloodGroup", bloodGroup);
        dataToInsert.put("lastDonation", lastDonation);
        dataToInsert.put("password", password);
        String where = "user_id" + "=" + user_id;
        try {
            db.update("users", dataToInsert, where, null);
            return true;
        } catch (Exception e) {
            String error = e.getMessage().toString();
            return false;
        }

    }

    public boolean delete_profile(String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataToInsert = new ContentValues();

        String where = "user_id" + "=" + user_id;
        try {
            db.delete("users", where, null);
            return true;
        } catch (Exception e) {
            String error = e.getMessage().toString();
            return false;
        }

    }















}
