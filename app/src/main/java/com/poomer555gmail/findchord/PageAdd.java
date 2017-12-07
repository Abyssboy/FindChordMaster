package com.poomer555gmail.findchord;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.poomer555gmail.findchord.Adapter.ChordAdapter;
import com.poomer555gmail.findchord.DB.DB;
import com.poomer555gmail.findchord.Model.ChordItem;

import java.util.ArrayList;

/**
 * Created by poome on 12/3/2017.
 */

public class PageAdd extends AppCompatActivity {


    private DB mHelper;
    private SQLiteDatabase mDB;

    private ArrayList<ChordItem> mChordItem = new ArrayList<>();
    private ChordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabadd);

        mHelper = new DB(this);
        mDB = mHelper.getReadableDatabase();

        LoadDataFromDB();

        mAdapter = new ChordAdapter(
                this,
                R.layout.listitem,
                mChordItem
        );

        ListView LV = findViewById(R.id.AddAllChordItem);
        LV.setAdapter(mAdapter);

        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChordItem chordItem = mChordItem.get(i);
                int ID = chordItem.ID;


                Intent intent = new Intent(PageAdd.this, ChordActivity.class);

                intent.putExtra("Position", ID);
                startActivityForResult(intent, 2);


            }
        });

        FloatingActionButton Fab = findViewById(R.id.fab);

        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageAdd.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });


    }


    private void LoadDataFromDB() {


        Cursor cursor = mDB.rawQuery("SELECT * FROM " + DB.TABLE_NAME + " WHERE " + DB.COL_UserAdd + " = '1'", null);
        mChordItem.clear();

        while (cursor.moveToNext()) {
            int ID = cursor.getInt(cursor.getColumnIndex(DB.COL_ID));
            String Name = cursor.getString(cursor.getColumnIndex(DB.COL_Name));
            String Pic = cursor.getString(cursor.getColumnIndex(DB.COL_PIC));
            String Favor = cursor.getString(cursor.getColumnIndex(DB.COL_Favor));
            String Level = cursor.getString(cursor.getColumnIndex(DB.COL_Level));
            String Add = cursor.getString(cursor.getColumnIndex(DB.COL_UserAdd));

            ChordItem Item = new ChordItem(ID, Name, Pic, Favor, Level, Add);
            mChordItem.add(Item);


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (requestCode == RESULT_OK) {
                LoadDataFromDB();
                mAdapter.notifyDataSetChanged();
            }
        }


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                LoadDataFromDB();
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
