package com.poomer555gmail.findchord;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poomer555gmail.findchord.DB.DB;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ChordActivity extends AppCompatActivity {
    private DB mChordHelper;
    private SQLiteDatabase mDB;
    private SQLiteDatabase mDB2;
   private String name ,LV ,ADD,FAV,PIC;
   private File mFile;
    private Context mConText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        ImageView mPic = findViewById(R.id.ChordPic);
        TextView mChordName = findViewById(R.id.ShowName);
        TextView mChordLV = findViewById(R.id.ShowLV);
        Button mFav = findViewById(R.id.FavButt);
        Button mDeleteButt = findViewById(R.id.DeleteButt);

        Intent intent =getIntent();
        final int ID = intent.getIntExtra("Position",0);

        mChordHelper = new DB(this);
        mDB = mChordHelper.getWritableDatabase();



   // mDB2 = mChordHelper.getWritableDatabase();

      Cursor cursor = mDB.rawQuery("SELECT * FROM "+DB.TABLE_NAME+" WHERE " + DB.COL_ID+" = "+ ID  ,null);

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(DB.COL_Name));
            LV = cursor.getString(cursor.getColumnIndex(DB.COL_Level));
            ADD = cursor.getString(cursor.getColumnIndex(DB.COL_UserAdd));
            FAV = cursor.getString(cursor.getColumnIndex(DB.COL_Favor));
            PIC = cursor.getString(cursor.getColumnIndex(DB.COL_PIC));
        }


        mChordName.setText(name);

        if(LV.equals("1")){
            mChordLV.setTextColor(Color.parseColor("#00FF24"));
            mChordLV.setText("ง่าย");
        }
        else if(LV.equals("2")){
            mChordLV.setTextColor(Color.parseColor("#FFF319"));
            mChordLV.setText("ปานกลาง");
        }
        else {
            mChordLV.setTextColor(Color.parseColor("#FF1B19"));
            mChordLV.setText("ยาก");
        }

        try
        {
            // get input stream
            InputStream ims = getAssets().open(PIC);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mPic.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();

            File picfile = new File(getApplicationContext().getFilesDir(),PIC);
            Drawable drawable = Drawable.createFromPath(picfile.getAbsolutePath());
            mPic.setImageDrawable(drawable);

        }




        if(ADD.equals("0")){
            mDeleteButt.setVisibility(View.GONE);
        }

        mFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FAV.equals("1")){
                    ContentValues CV = new ContentValues();
                    CV.put(mChordHelper.COL_Favor,"0");
                    mDB.update(
                            mChordHelper.TABLE_NAME,
                            CV ,
                           mChordHelper.COL_ID+" = "+ID,
                            null
                            );
                    Toast.makeText(ChordActivity.this,"ยกเลิกFavorite",Toast.LENGTH_LONG).show();
                }
                if(FAV.equals("0")){
                    ContentValues CV = new ContentValues();
                    CV.put(mChordHelper.COL_Favor,"1");
                    mDB.update(
                            mChordHelper.TABLE_NAME,
                            CV ,
                            mChordHelper.COL_ID+" = "+ID,
                            null
                    );
                    Toast.makeText(ChordActivity.this,"Favoriteเรียบร้อย",Toast.LENGTH_LONG).show();
                }
            }
        });


        mDeleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDB.delete(mChordHelper.TABLE_NAME,
                        mChordHelper.COL_ID+" = "+ID,
                        null

                        );
                Intent intent = new Intent(ChordActivity.this,MainActivity.class);
                setResult(RESULT_OK);
                startActivity(intent);


            }
        });











    }
}
