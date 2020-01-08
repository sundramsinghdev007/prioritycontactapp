package com.android.prioritycontactapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
EditText et_search;
TextView tv_semail,tv_sphone;
Button delbtn;
DBHelper dbHelper;
SQLiteDatabase db;
String sname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        delbtn=(Button)findViewById(R.id.delbtn);
        et_search=(EditText)findViewById(R.id.et_search);
        tv_semail=(TextView)findViewById(R.id.tv_semail);
        tv_sphone=(TextView)findViewById(R.id.tv_sphone);
        dbHelper=new DBHelper(this);
    }

    public void searchname(View view)
    {
        sname=et_search.getText().toString();
        db=dbHelper.getReadableDatabase();
        Cursor cursor=dbHelper.searchContact(sname,db);
        if(cursor.moveToNext())
        {
            String EMAIL = cursor.getString(0);
            String PHONE = cursor.getString(1);
            tv_semail.setText(EMAIL);
            tv_sphone.setText(PHONE);
            delbtn.setVisibility(View.VISIBLE);
        }
        else
            {
                tv_semail.setText("Name Not Available");
                tv_sphone.setText("Phone Not Available");
                et_search.setText("");
            }

    }

    public void delete(View view)
    {
        db=dbHelper.getReadableDatabase();
        dbHelper.deleteContacts(sname,db);
        tv_semail.setText("");
        tv_sphone.setText("");

        Toast.makeText(this,"Data Deleted",Toast.LENGTH_SHORT).show();
    }
}
