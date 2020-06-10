package com.afifar.user.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userProfile extends AppCompatActivity {
     TextView name,email,phone,password;
     Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name=(TextView)findViewById(R.id.nameT);
        email=(TextView)findViewById(R.id.emailT);
        password=(TextView)findViewById(R.id.passwordT);
        phone=(TextView)findViewById(R.id.phoneT);
        signout=(Button)findViewById(R.id.signout);
        getSupportActionBar().hide();

        name.setText(getIntent().getExtras().getString("NAME"));
        email.setText(getIntent().getExtras().getString("EMAIL"));
        password.setText(getIntent().getExtras().getString("PASSWORD"));
        phone.setText(getIntent().getExtras().getString("PHONE"));

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
