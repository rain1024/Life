package com.magizbox.life;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by LanAnh on 13/05/2016.
 */
public class FactAdapter extends ArrayAdapter<String> {
    public FactAdapter(Context context, int resource) {
super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
