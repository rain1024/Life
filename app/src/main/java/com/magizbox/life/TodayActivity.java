package com.magizbox.life;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TodayActivity extends AppCompatActivity {

    public void switchMain() {
        try {
            Intent move = new Intent(this, ActionActivity.class);
            startActivity(move);
        } catch(Exception e){}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                        .setAction("Action", null).show();
                switchMain();
            }
        });

        String[] facts = {"LISTENING", "ACTING", "INSPIRING", "HOPING", "BELIEVING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.fact_item, facts);
        ListView list = (ListView) findViewById(R.id.factListView);
        list.setAdapter(adapter);
    }
}
