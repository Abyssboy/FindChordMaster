package com.poomer555gmail.findchord.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poome on 11/28/2017.
 */

public class DB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Chord.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "ChordTable";
    public static final String COL_ID ="_id" ;
    public static final String COL_Name = "Name";
    public static final String COL_PIC = "PIC";
    public static String COL_Favor= "Favorite";
    public static final String COL_UserAdd= "UserAdd";
    public static final String COL_Level= "Level";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_Name+ " TEXT, "
            + COL_PIC + " TEXT, "
            + COL_Favor + " TEXT, "
            + COL_Level + " TEXT, "
            + COL_UserAdd + " TEXT) " ;

    public DB (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        insertInitialData(sqLiteDatabase);
    }

   public void insertInitialData(SQLiteDatabase SQL){
       ContentValues cv = new ContentValues();
       cv.put(COL_Name,"C");
       cv.put(COL_PIC,"C.jpg");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");

       SQL.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
       cv.put(COL_Name,"Am");
       cv.put(COL_PIC,"Am.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"A");
       cv.put(COL_PIC,"A.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);
       cv = new ContentValues();

       cv.put(COL_Name,"D");
       cv.put(COL_PIC,"D.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"E");
       cv.put(COL_PIC,"E.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"E7");
       cv.put(COL_PIC,"E7.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"1");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       //ปานกลาง

       cv = new ContentValues();
       cv.put(COL_Name,"Bm");
       cv.put(COL_PIC,"Bm.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"2");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"C7");
       cv.put(COL_PIC,"C7.jpg");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"2");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"F7");
       cv.put(COL_PIC,"F7.gif");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"2");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"Gm");
       cv.put(COL_PIC,"Gm.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"2");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       //ยาก
       cv = new ContentValues();
       cv.put(COL_Name,"Bฺ");
       cv.put(COL_PIC,"B.gif");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"3");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"Cm");
       cv.put(COL_PIC,"Cm.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"3");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"F");
       cv.put(COL_PIC,"F.png");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"3");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);

       cv = new ContentValues();
       cv.put(COL_Name,"Fm");
       cv.put(COL_PIC,"Fm.gif");
       cv.put(COL_Favor,"0");
       cv.put(COL_Level,"3");
       cv.put(COL_UserAdd,"0");
       SQL.insert(TABLE_NAME,null,cv);
   }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(sqLiteDatabase);
    }
}
