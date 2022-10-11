package com.example.yuvarajan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("restaurentDb", Context.MODE_PRIVATE,null);

        lst = findViewById(R.id.lst);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int quantity = c.getColumnIndex("quantity");
        int contact = c.getColumnIndex("contact");

        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst.setAdapter(arrayAdapter);


        final  ArrayList<student> stud = new ArrayList<student>();

        if(c.moveToFirst())
        {
            do{
                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                int dob = 0;
                stu.dob = c.getString(dob);


                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(name) + " \t "  + c.getString(quantity) + " \t "  + c.getString(contact) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst.invalidateViews();



        }



    }
}