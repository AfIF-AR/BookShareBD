package com.afifar.user.demo2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    String checkmail="name@gmail.com",checkpass="X12345678";
    String Name,Email,Password;
    Button signup;
    Button login;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        signup=(Button)findViewById(R.id.btn2);
        login=(Button)findViewById(R.id.btn);
        email=(EditText)findViewById(R.id.et2);
        password=(EditText)findViewById(R.id.editText5);
        databaseHelper=new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
                boolean connectivity=networkInfo!=null && networkInfo.isConnectedOrConnecting();
                Intent i;
                if(connectivity==true) {
                    Cursor cursor = databaseHelper.getData(email.getText().toString(), password.getText().toString());

                    while (cursor.moveToNext()) {

                        i = new Intent(MainActivity.this, Home_Activity.class);
                        i.putExtra("NAME", cursor.getString(0));
                        i.putExtra("EMAIL",cursor.getString(1));
                        i.putExtra("PASSWORD",cursor.getString(2));
                        i.putExtra("PHONE",cursor.getString(3));
                        checkmail = cursor.getString(1);
                        checkpass = cursor.getString(2);
                        startActivity(i);

                    }
                    if (email.getText().toString().equals(checkmail) && password.getText().toString().equals(checkpass)) {
                        Toast.makeText(getApplicationContext(), "Password and email match", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Password and email does not match", Toast.LENGTH_SHORT).show();
                    }
                    email.setText("");
                    password.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
    }
}
