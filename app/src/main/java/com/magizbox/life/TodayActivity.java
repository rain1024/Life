package com.magizbox.life;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodayActivity extends AppCompatActivity {

    private List<String> facts;
    OrmDbHelper mOrmDbHelper;
    private OrmDbHelper getHelper() {
        if (mOrmDbHelper == null) {
            mOrmDbHelper = new OrmDbHelper(this);
        }
        return mOrmDbHelper;
    }

    private void getFacts() {
        facts = new ArrayList<String>();
        mOrmDbHelper = getHelper();
        try {
            Dao<Action, Integer> actionDao = mOrmDbHelper.getActionDao();
            List<Action> actions = actionDao.queryBuilder().limit(5).orderByRaw("RANDOM()").query();
            for(Action action: actions){
                facts.add(action.good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

        getFacts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.fact_item, facts);
        ListView list = (ListView) findViewById(R.id.factListView);
        list.setAdapter(adapter);
    }
}
