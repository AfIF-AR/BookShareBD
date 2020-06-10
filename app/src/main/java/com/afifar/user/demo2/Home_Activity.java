package com.afifar.user.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   Button donate, event, member;
   DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    db=new DatabaseHelper(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        donate=(Button)findViewById(R.id.btnDonate);
        event = (Button) findViewById(R.id.btnEvents);
        member = (Button) findViewById(R.id.btnBeMember);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        final AlertDialog.Builder ad=new AlertDialog.Builder(Home_Activity.this);
        final View adview=getLayoutInflater().inflate(R.layout.alertdialogue2,null);
        TextView tv=(TextView) adview.findViewById(R.id.textViewbookdetails);
        final EditText bookname= (EditText) adview.findViewById(R.id.bookname);
        final EditText authorname=(EditText) adview.findViewById(R.id.authorname);
        final Button donate2=(Button)adview.findViewById(R.id.donate);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                donate2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!bookname.getText().toString().isEmpty() && !authorname.getText().toString().isEmpty()){
                            db.insertDataDonateTable(getIntent().getExtras().getString("EMAIL"),Integer.parseInt(getIntent().getExtras().
                                    getString("PHONE")),bookname.getText().toString(),authorname.getText().toString());
                            Toast.makeText(getApplicationContext(),"Book stored in Library",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please fill every field",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                ad.setView(adview);
                AlertDialog adv=ad.create();
                adv.show();
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Activity.this, EventsActivity.class);
                startActivity(i);

            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home_Activity.this, BeAvolunteer.class);
                startActivity(i);

            }
        });


   /*     shareabook.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent i=new Intent(Home_Activity.this,userProfile.class);
            i.putExtra("NAME",getIntent().getExtras().getString("NAME"));
            i.putExtra("EMAIL",getIntent().getExtras().getString("EMAIL"));
            i.putExtra("PASSWORD",getIntent().getExtras().getString("PASSWORD"));
            i.putExtra("PHONE",getIntent().getExtras().getString("PHONE"));
            startActivity(i);
        } else if (id == R.id.nav_about) {
            Intent i=new Intent(Home_Activity.this,aboutActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_help) {
            Intent i=new Intent(Home_Activity.this,help.class);
            i.putExtra("EMAIL",getIntent().getExtras().getString("EMAIL"));
            startActivity(i);

        }  else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShareOnclick(View view) {
        Intent i=new Intent(Home_Activity.this,ShareActivity.class);
        i.putExtra("PHONE",getIntent().getExtras().getString("PHONE"));
        i.putExtra("EMAIL",getIntent().getExtras().getString("EMAIL"));
        //i.putExtra("NAME",getIntent().getExtras().getString("NAME"));
        startActivity(i);
    }

    public void logout(MenuItem item) {
        Intent i=new Intent(Home_Activity.this,MainActivity.class);
        startActivity(i);
    }



}
