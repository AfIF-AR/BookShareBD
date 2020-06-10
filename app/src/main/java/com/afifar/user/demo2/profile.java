package com.afifar.user.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView email;
    Button help,donate1,share;
    String mail="";
    int phone;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email=(TextView)findViewById(R.id.textView);
        help=(Button)findViewById(R.id.button2);
        donate1=(Button)findViewById(R.id.button3);
        share=(Button)findViewById(R.id.button4);
        db=new DatabaseHelper(this);
        email.setText(getIntent().getExtras().getString("EMAIL"));
        mail=getIntent().getExtras().getString("EMAIL");
        final AlertDialog.Builder ad=new AlertDialog.Builder(profile.this);
        final View adview=getLayoutInflater().inflate(R.layout.alertdialogue2,null);
        TextView tv=(TextView) adview.findViewById(R.id.textViewbookdetails);
        final EditText bookname= (EditText) adview.findViewById(R.id.bookname);
        final EditText authorname=(EditText) adview.findViewById(R.id.authorname);
        final Button donate=(Button) adview.findViewById(R.id.donate);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ///Toast.makeText(getApplicationContext(),"gggg",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(profile.this,help.class);
                i.putExtra("EMAIL",mail);
                startActivity(i);

            }
        });
        donate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               donate.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       db.insertDataDonateTable(mail, Integer.parseInt(getIntent().getExtras().getString("PHONE")),bookname.getText().toString(),authorname.getText().toString());
                   }
               });

                ad.setView(adview);
                AlertDialog adv=ad.create();
                adv.show();
            }
        });

       // phone=Integer.parseInt(getIntent().getExtras().getString("PHONE"));
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,ShareActivity.class);
                i.putExtra("EMAIL",mail);
                i.putExtra("PHONE",getIntent().getExtras().getString("PHONE"));
                startActivity(i);
            }
        });
    }
}
