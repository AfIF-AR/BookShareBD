package com.afifar.user.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class help extends AppCompatActivity {

    Button send;
    EditText et;
    TextView tv;
    String mail="";
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        send=(Button) findViewById(R.id.send);
        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Help");
        databaseHelper=new DatabaseHelper(this);
        mail=getIntent().getExtras().getString("EMAIL");
        ///tv.setText();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mail=getIntent().getExtras().getString("EMAIL");
                databaseHelper.insertHelp(mail,et.getText().toString());
                Toast.makeText(help.this, "Sent successfully", Toast.LENGTH_SHORT).show();
                et.setText("");
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
