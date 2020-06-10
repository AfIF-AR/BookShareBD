package com.afifar.user.demo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName="BookShareBD.db";
    private static final String Table1="information";
    private  static final String Table2="sharingTable";
    private static final String Table3="donatingTable";
    private static final String Table4="Helpdesk";

    public DatabaseHelper(Context context) {
        super(context,DatabaseName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table1+"(NAME TEXT,EMAIL TEXT,PASSWORD TEXT,PHONE INTEGER)");
        db.execSQL("CREATE TABLE "+Table2+"(EMAIL TEXT,PHONE INTEGER,BOOKNAME TEXT,AUTHORNAME TEXT)");
        db.execSQL("CREATE TABLE "+Table3+"(EMAIL TEXT,PHONE INTEGER,BOOKNAME TEXT,AUTHORNAME TEXT)");
        db.execSQL("CREATE TABLE "+Table4+"(EMAIL TEXT,HELP TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table1);
        db.execSQL("DROP TABLE IF EXISTS "+Table2);
        db.execSQL("DROP TABLE IF EXISTS "+Table3);
        db.execSQL("DROP TABLE IF EXISTS "+Table4);
        onCreate(db);
    }
    public boolean insertData(String name, String email, String pass, int phone){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",pass);
        contentValues.put("PHONE",phone);
        long res=db.insert(Table1,null,contentValues);

        if(res== -1)
            return false;

        else
            return true;


    }
    public boolean insertDataShareTable(String email, int phone, String BookName, String AuthorName){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("EMAIL",email);
        contentValues.put("PHONE",phone);
        contentValues.put("BOOKNAME",BookName);
        contentValues.put("AUTHORNAME",AuthorName);
        long res=db.insert(Table2,null,contentValues);

        if(res== -1)
            return false;

        else
            return true;


    }
    public boolean insertDataDonateTable(String email, int phone, String BookName, String AuthorName){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("EMAIL",email);
        contentValues.put("PHONE",phone);
        contentValues.put("BOOKNAME",BookName);
        contentValues.put("AUTHORNAME",AuthorName);
        long res=db.insert(Table3,null,contentValues);

        if(res== -1)
            return false;

        else
            return true;


    }
    public boolean insertHelp(String email, String help){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("HELP",help);
        contentValues.put("EMAIL",email);
        long res=db.insert(Table4,null,contentValues);

        if(res== -1)
            return false;

        else
            return true;


    }

    public Cursor getData(String email, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+Table1+" WHERE EMAIL='"+email+"' AND PASSWORD='"+pass+"'";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }
    public Cursor getShareTableData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table2, null);
        return res;


    }
    public Cursor getDonateTableData(String bookName, String email){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+Table2+" WHERE EMAIL='"+email+"' AND BOOKNAME='"+bookName+"'";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }
}
