    package com.poomer555gmail.findchord;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.poomer555gmail.findchord.DB.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

    public class AddActivity extends AppCompatActivity {

        private static final String TAG = AddActivity.class.getName();
        private Spinner mSpinner;
        private EditText mName;
        private ImageView mImageView;
        private Button mButt;

        private File mSelectedPicFile;

        String fLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

mSpinner = findViewById(R.id.AddLvSpinner);
mName = findViewById(R.id.AddNameText);
mImageView = findViewById(R.id.Addview);
mButt = findViewById(R.id.AddButt);



        String[] LV = getResources().getStringArray(R.array.ALV);
        ArrayAdapter<String> adapterLV = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,LV);
        mSpinner.setAdapter(adapterLV);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                       fLV = String.valueOf(1);
                        break;
                    case 1:
                        fLV = String.valueOf(2);
                        break;
                    case 2:
                        fLV = String.valueOf(3);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(
                        AddActivity.this,
                        "ถ่ายรูปหรือเลือกรูปภาพที่ต้องการ",
                        0
                );
            }
        });

        mButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedPicFile == null){
                    Toast.makeText(getApplicationContext(),"ยังไม่ได้เลือกรูปภาพ",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                File PrivateDir=   getApplicationContext().getFilesDir();
                File DstFile = new File(PrivateDir,mSelectedPicFile.getName());

                try{
                    copyFile(mSelectedPicFile,DstFile);
                }catch (IOException e){
                    e.printStackTrace();
                    Log.e(TAG,"Error Copy File");

                    return;
                }
                SaveDataToDB();
                setResult(RESULT_OK);

                finish();



            }
        });

    }

        private void SaveDataToDB() {
            String Name = mName.getText().toString();
            String LV = fLV ;
            String Add = String.valueOf(1);
            String Favor = String.valueOf(0);
            String Image = mSelectedPicFile.getName();

            ContentValues CV =  new ContentValues();
            CV.put(DB.COL_Name,Name);
            CV.put(DB.COL_Level,LV);
            CV.put(DB.COL_PIC,Image);
            CV.put(DB.COL_UserAdd,Add);
            CV.put(DB.COL_Favor,Favor);

            DB mHelper = new DB(this);
            SQLiteDatabase SQL =mHelper.getWritableDatabase();

           SQL.insert(DB.TABLE_NAME,null,CV);

        }

        public static void copyFile(File src, File dst) throws IOException {
            FileInputStream inputStream = new FileInputStream(src);
            FileOutputStream outputStream = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    //Some error handling
                    Log.e("error",e.getMessage());
                }

                @Override
                public void onImagesPicked(List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                    //Handle the images
                    mSelectedPicFile = imagesFiles.get(0);
                    Drawable drawable = Drawable.createFromPath(mSelectedPicFile.getAbsolutePath());
                    mImageView.setImageDrawable(drawable);

                    Log.i(TAG,mSelectedPicFile.getAbsolutePath());
                }
            });
        }

    }
