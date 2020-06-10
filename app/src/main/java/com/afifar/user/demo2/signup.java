package com.afifar.user.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText name,email,password,phone;
    Button register;
    DatabaseHelper databaseHelper;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText3);
        register=(Button)findViewById(R.id.btn);
        phone=(EditText)findViewById(R.id.editText4);
        tv=(TextView)findViewById(R.id.textView2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SignUp");
        databaseHelper=new DatabaseHelper(this);
        //Exception exception=new Exception();
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")&& !email.getText().toString().equals("")&&!password.getText().toString().equals("")&&!phone.getText().toString().equals("")) {
                    if (password.getText().toString().length() >= 8) {
                        boolean result = databaseHelper.insertData(name.getText().toString(), email.getText().toString(), password.getText().toString(), Integer.parseInt(phone.getText().toString()));

                        if (result) {
                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                            name.setText("");
                            email.setText("");
                            password.setText("");
                            phone.setText("");

                        } else {
                            Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        tv.setText("password should be atleast 8 character");
                        password.setText("");
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill every content", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
