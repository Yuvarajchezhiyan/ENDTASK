package com.example.yuvarajan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,id,dob;
    Button register1,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        dob=findViewById(R.id.dob);

        register1=findViewById(R.id.register1);
        view=findViewById(R.id.view);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });


    }


    public void insert()
    {
        try

        {
            String uname=name.getText().toString();
            String uid=id.getText().toString();
            String udo=dob.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("studentDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,sid VARCHAR,dob VARCHAR)");

            String sql = "insert into records(name,sid,dob)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,uname);
            statement.bindString(2,uid);
            statement.bindString(3,udo);
            statement.execute();
            Toast.makeText(this,"Profile Created",Toast.LENGTH_LONG).show();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
        }
    }




}