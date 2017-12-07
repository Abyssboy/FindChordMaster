package com.poomer555gmail.findchord.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.poomer555gmail.findchord.Model.ChordItem;
import com.poomer555gmail.findchord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poome on 11/28/2017.
 */

public class ChordAdapter extends ArrayAdapter<ChordItem> {
    private Context mContext;
    private  int mLayoutResID;
    private ArrayList<ChordItem> mChordItem;

    public ChordAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ChordItem> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mLayoutResID = resource;
        this.mChordItem = objects;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View ItemLayout = inflater.inflate(mLayoutResID,null);


        ChordItem Item = mChordItem.get(position);

        TextView ChordName = ItemLayout.findViewById(R.id.Name);
        TextView ChordDetail = ItemLayout.findViewById(R.id.Detail);
        TextView ChordLV = ItemLayout.findViewById(R.id.lv);

        ChordName.setText(Item.Name);
        ChordDetail.setText("คอร์ด "+Item.Name);

        if(Item.Level.equals("1")){
            ChordLV.setTextColor(Color.parseColor("#00FF24"));
            ChordLV.setText("ระดับความยาก:ง่าย");
        }
        else if(Item.Level.equals("2")){
            ChordLV.setTextColor(Color.parseColor("#FFF319"));
            ChordLV.setText("ระดับความยาก:ปานกลาง");
        }
        else {
            ChordLV.setTextColor(Color.parseColor("#FF1B19"));
            ChordLV.setText("ระดับความยาก:ยาก");
        }



        return ItemLayout;




    }
}
