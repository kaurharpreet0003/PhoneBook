package com.example.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PersonDatabase";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME = "Person";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "firstname";
    private static final String COLUMN_LAST_NAME = "lastname";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONE = "phone";

    public DatabaseHelper(@Nullable Context context) {


        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID +" INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FIRST_NAME + " varchar(200) NOT NULL," +
                COLUMN_LAST_NAME + " varchar(200) NOT NULL," +
                COLUMN_ADDRESS + " varchar(200) NOT NULL," +
                COLUMN_PHONE + " NUMBER NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);

    }

    boolean addPerson(String firstname,String lastname ,String address , String phone){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();



        ContentValues cv = new ContentValues();


        cv.put(COLUMN_FIRST_NAME, firstname);
        cv.put(COLUMN_LAST_NAME,lastname);
        cv.put(COLUMN_ADDRESS,address);
        cv.put(COLUMN_PHONE,phone);


        return   sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;


    }

    Cursor getAllPersons(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }



    boolean deletePerson(int id){
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();


        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID +"=?", new String[]{String.valueOf(id)}) > 0;
    }

}
