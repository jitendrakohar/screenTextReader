package com.example.textreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    String tablename;
    public DBHelper(Context context, String tablename) {
        super(context, "Userdata.db", null, 1);
        this.context=context;
        this.tablename=tablename;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tablename+"(Data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tablename);
    }

    public boolean insertData(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        long result=db.insert("whatsapp",null,contentValues);
        if(result==-1) {
            Log.e("TESTREQ", "insertData: failed" );
            return  false;
        }
        else
        {
            Log.e("TESTREQ", "insertData: successful" );

            return true;
        }
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tablename,new String[]{"data"},null,null,null,null,null);
        return cursor;
    }

}
