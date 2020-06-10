package com.afifar.user.demo2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShareActivity extends AppCompatActivity {

    Button pickabook,shareabook;
    String mail="";
    int phone;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        pickabook = (Button) findViewById(R.id.button1);
        shareabook = (Button) findViewById(R.id.button2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Share & pick");
        db = new DatabaseHelper(this);
        shareabook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ShareActivity.this);
                View adview = getLayoutInflater().inflate(R.layout.alertdialogue, null);
                TextView tv = (TextView) adview.findViewById(R.id.textView);
                final EditText bookname = (EditText) adview.findViewById(R.id.editText);
                final EditText authorname = (EditText) adview.findViewById(R.id.editText2);
                Button share = (Button) adview.findViewById(R.id.button);
                //mail=getIntent().getExtras().getString("EMAIL");
                //phone=Integer.parseInt(getIntent().getExtras().getString("PHONE"));
                //Toast.makeText(getApplicationContext(),mail+"\nphone",Toast.LENGTH_LONG).show();
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mail = getIntent().getExtras().getString("EMAIL");
                        phone = Integer.parseInt(getIntent().getExtras().getString("PHONE"));
                        if (!bookname.getText().toString().isEmpty() && !authorname.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Successfully Stored", Toast.LENGTH_LONG).show();
                            db.insertDataShareTable(mail, phone, bookname.getText().toString(), authorname.getText().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Fill every field", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                ad.setView(adview);
                AlertDialog adv = ad.create();
                adv.show();
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


    public void PickAbookOnClick(View view) {

        Cursor res = db.getShareTableData();
        if(res.getCount() == 0){
            showMessage("Error","No data found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Book name: " + res.getString(2) + "\n");
            buffer.append("Auther: " + res.getString(3) + "\n\n");
            //buffer.append("Address: " + res.getString(2) + "\n\n");
        }

        //show all data
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    }


