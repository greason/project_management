package com.greason.project_management.database.task;

import com.greason.project_management.activity.App;
import com.greason.project_management.database.DbHelper;
import com.greason.project_management.database.DbUtil;
import com.lidroid.xutils.DbUtils;

/**
 * 基础数据库处理
 * <p>
 * Created by Greason on 16/7/20.
 */
public class BaseDbTask {

    protected static DbUtils dbUtils;

    static {
        dbUtils = DbUtil.getDB(App.getContext(), DbHelper.AppDbName);
    }

    public static void dropDb() {
        DbUtil.dropDb(dbUtils);
    }
}
