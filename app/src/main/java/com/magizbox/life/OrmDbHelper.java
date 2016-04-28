package com.magizbox.life;

/**
 * Created by LanAnh on 27/04/2016.
 */

import android.content.Context;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.SQLException;

public class OrmDbHelper extends SQLiteAssetHelper {

    protected AndroidConnectionSource mConnectionSource = new AndroidConnectionSource(this);

    // name of the database file for your application
    public static final String DATABASE_NAME = "actions.db";

    // any time you make changes to your database objects, increase the database version
    public static final int DATABASE_VERSION = 1;


    // the DAO object we use to access the tables
    private Dao<Action, Integer> mActionDao = null;


    public OrmDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        mActionDao = null;
    }

    /**
     *
     * @return Dao we need to access the Actions table
     *
     * @throws java.sql.SQLException
     */
    public Dao<Action, Integer> getActionDao() throws SQLException {
        if (mActionDao == null) {
            mActionDao = getDao(Action.class);
        }
        return mActionDao;
    }

    /**
     * Lifted off of https://github.com/j256/ormlite-examples/blob/master/android/HelloAndroidNoHelper/src/com/example/hellonohelper/DatabaseHelper.java
     *
     *
     * @param clazz
     * @param <D>
     * @param <T>
     * @return
     * @throws java.sql.SQLException
     */
    private <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        // lookup the dao, possibly invoking the cached database config
        Dao<T, ?> dao = DaoManager.lookupDao(mConnectionSource, clazz);
        if (dao == null) {
            // try to use our new reflection magic
            DatabaseTableConfig<T> tableConfig = DatabaseTableConfigUtil.fromClass(mConnectionSource, clazz);
            if (tableConfig == null) {
                /**
                 * TODO: we have to do this to get to see if they are using the deprecated annotations like
                 * {@link DatabaseFieldSimple}.
                 */
                dao = (Dao<T, ?>) DaoManager.createDao(mConnectionSource, clazz);
            } else {
                dao = (Dao<T, ?>) DaoManager.createDao(mConnectionSource, tableConfig);
            }
        }

        @SuppressWarnings("unchecked")
        D castDao = (D) dao;
        return castDao;
    }}
