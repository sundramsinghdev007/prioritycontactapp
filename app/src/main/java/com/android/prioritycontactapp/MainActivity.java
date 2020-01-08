package com.android.prioritycontactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void add(View view)
    {
        Intent intent = new Intent(MainActivity.this,AddContact.class);
        startActivity(intent);
    }

    public void show(View view)
    {
        Intent intent = new Intent(MainActivity.this,ShowContac.class);
        startActivity(intent);
    }

    public void search(View view)
    {
        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(intent);
    }
}
