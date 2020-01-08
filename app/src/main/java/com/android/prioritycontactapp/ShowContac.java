package com.android.prioritycontactapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowContac extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String,String>> al_contact = new ArrayList<HashMap<String, String>>();
    SimpleAdapter sa;
    DBHelper dbh;
    SQLiteDatabase db;
    Cursor cursor;
    String []from={"name","email","gender","phone"};
    int [] to={R.id.tv_name,R.id.tv_email,R.id.tv_gender,R.id.tv_phone};
    AlertDialog.Builder ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contac);

        dbh=new DBHelper(this);
        db=dbh.getReadableDatabase();
        cursor = dbh.getContacs(db);
        listView=(ListView)findViewById(R.id.add_contact);
        sa=new SimpleAdapter(this,al_contact,R.layout.custom_list,from,to);
        listView.setAdapter(sa);
        while (cursor.moveToNext())
        {
            HashMap<String,String> hm = new HashMap<String,String>();
            hm.put("name",cursor.getString(0));
            hm.put("email",cursor.getString(1));
            hm.put("gender",cursor.getString(2));
            hm.put("phone",cursor.getString(3));
            al_contact.add(hm);
        }

        sa.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String num = ((TextView)view.findViewById(R.id.tv_phone)).getText().toString();
                //Toast.makeText(ShowContac.this,""+num,Toast.LENGTH_LONG).show();
                ab=new AlertDialog.Builder(ShowContac.this);
                ab.setTitle("Call");
                ab.setMessage("Do you want to call : "+num);

                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+num));
                        startActivity(intent);
                    }
                });
                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                ab.show();
            }

        });

    }
}
