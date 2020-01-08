package com.android.prioritycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME="CONTACTS.DB";
    public static final int DATABASE_VERSION=1;
    public static String CREATE_QUERY="CREATE TABLE "+DataProvider.NewContactInfo.TABLE_NAME+"("+ DataProvider.NewContactInfo.USER_NAME+" TEXT,"+DataProvider.NewContactInfo.USER_EMAIL+" TEXT,"+DataProvider.NewContactInfo.USER_GENDER+" TEXT,"+DataProvider.NewContactInfo.USER_PHONE+" TEXT );";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Toast.makeText(context,"Database Created",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    db.execSQL(CREATE_QUERY);
        Log.e("DB","One Row Inserted");
    //Toast.makeText(context,"Table Created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(String name,String email,String gender,String phone,SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put(DataProvider.NewContactInfo.USER_NAME,name);
        values.put(DataProvider.NewContactInfo.USER_EMAIL,email);
        values.put(DataProvider.NewContactInfo.USER_GENDER,gender);
        values.put(DataProvider.NewContactInfo.USER_PHONE,phone);
        db.insert(DataProvider.NewContactInfo.TABLE_NAME,null,values);
        Log.e("DB","One Row Inserted");
    }

    public Cursor getContacs(SQLiteDatabase db)
    {
        Cursor cursor;
        String []selection = {DataProvider.NewContactInfo.USER_NAME, DataProvider.NewContactInfo.USER_EMAIL, DataProvider.NewContactInfo.USER_GENDER, DataProvider.NewContactInfo.USER_PHONE};
        cursor = db.query(DataProvider.NewContactInfo.TABLE_NAME,selection,null,null,null,null,null);
        return cursor;
    }

    public Cursor searchContact(String name,SQLiteDatabase db)
    {
        String []selection = {DataProvider.NewContactInfo.USER_EMAIL, DataProvider.NewContactInfo.USER_PHONE};
        String where  = DataProvider.NewContactInfo.USER_NAME+ " Like ?";
        String []selection_args = {name};
        Cursor cursor = db.query(DataProvider.NewContactInfo.TABLE_NAME,selection,
                where,selection_args,null,null,null);
        return cursor;
    }

    public void deleteContacts(String name,SQLiteDatabase db)
    {
        String where  = DataProvider.NewContactInfo.USER_NAME+ " Like ?";
        String []selection_args = {name};
        db.delete(DataProvider.NewContactInfo.TABLE_NAME,where,selection_args);
    }


}
