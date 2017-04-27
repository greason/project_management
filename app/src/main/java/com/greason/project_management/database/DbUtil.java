package com.greason.project_management.database;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * database 工具类
 * <p/>
 * Created by Greason on 16/7/20.
 */
public class DbUtil {

    private static final String TAG = "DbUtil";

    public static boolean DBConfigDebug = false;
    public static boolean DBConfigAllowTransaction = true;

    public static DbUtils getDB(Context context, String dbName) {
        DbUtils db = DbUtils.create(context, dbName);
        db.configAllowTransaction(DBConfigAllowTransaction);
        db.configDebug(DBConfigDebug);

        return db;
    }

    public static void dropDb(DbUtils dbUtils) {
        try {
            dbUtils.dropDb();
        } catch (DbException e) {
            
        }
    }
}
