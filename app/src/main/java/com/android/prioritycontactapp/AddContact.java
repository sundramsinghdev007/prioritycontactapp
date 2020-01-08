package com.android.prioritycontactapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText[] str = new EditText[3];
    int[] ids = {R.id.et_name, R.id.et_email, R.id.et_phone};
    String[] values = new String[str.length];
    RadioButton male, female;
    int i;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String name,email,gender,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        for(i=0;i<str.length;i++)
        {
            str[i]=(EditText)findViewById(ids[i]);
        }
    }

    public void addNew(View view)
    {

        if(male.isChecked()==true ) {
            gender = male.getText().toString();
        }
        if(female.isChecked())
        {
            gender=female.getText().toString();
        }
        for(i=0;i<str.length;i++)
        {
            if(str[i].getText().toString().isEmpty())
            {
                str[i].setError("Empty");
                str[i].requestFocus();
                break;
            }
        }
        if(i==str.length) {
            for (i = 0; i < str.length; i++) {
                values[i] = str[i].getText().toString().trim();
            }

            name=values[0];
            email=values[1];
            phone=values[2];
            //Toast.makeText(this,""+name+email+gender+phone,Toast.LENGTH_SHORT).show();

            dbHelper = new DBHelper(this);
            db=dbHelper.getWritableDatabase();
            dbHelper.addContact(name,email,gender,phone,db);
            Toast.makeText(this,"Contact Save",Toast.LENGTH_SHORT).show();
            db.close();
            Intent intent = new Intent(AddContact.this,MainActivity.class);
            startActivity(intent);
            ActivityCompat.finishAffinity(this);
        }
    }
}
