package com.magizbox.life;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LanAnh on 27/04/2016.
 */
@DatabaseTable(tableName = "actions")
public class Action {
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String bad;

    @DatabaseField
    String good;

    public Action(){

    }


    public Action(String bad, String good) {
        super();
        this.bad = bad;
        this.good = good;
    }

    @Override
    public String toString() {
        return "Action{}";
    }
}
