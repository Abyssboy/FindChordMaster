package com.poomer555gmail.findchord.Model;

/**
 * Created by poome on 11/28/2017.
 */

public class ChordItem {
    public final int ID;
    public final String Name;
    public final String Pic;
    public final String Favor;
    public final String Level;
    public final String Add;

    public ChordItem(int id, String name, String pic, String favor,String level, String add) {
        ID = id;
        Name = name;
        Pic = pic;
        Favor = favor;
        Level = level ;
        Add = add;

    }

    @Override
    public String toString() {
        return Name;
    }
}
