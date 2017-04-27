package com.greason.project_management.database.task;

import com.greason.project_management.database.table.TaskTable;
import com.greason.project_management.utils.TextUtil;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

import java.util.List;


/**
 * Created by Greason on 16/7/20.
 */
public class TaskDbTask extends BaseDbTask {

    private static final String TAG = TaskDbTask.class.getSimpleName();

    public static void addOrUpdateTable(TaskTable table) {
        if (TextUtil.isValidate(table)) {
            try {
                dbUtils.saveOrUpdate(table);
            } catch (Exception ignored) {
            }
        }
    }

    public static void addOrUpdateAllTable(List<TaskTable> tableList) {
        if (TextUtil.isValidate(tableList)) {
            try {
                dbUtils.saveOrUpdateAll(tableList);
            } catch (Exception ignored) {
            }
        }
    }

    public static List<TaskTable> getAllTable() {
        try {
            return dbUtils.findAll(Selector.from(TaskTable.class));
        } catch (Exception ignored) {
        }

        return null;
    }


    public static List<TaskTable> getAllTable(long projectId) {
        try {
            return dbUtils.findAll(Selector.from(TaskTable.class).where(TaskTable.tagNameStr, "=", projectId));
        } catch (Exception ignored) {
        }

        return null;
    }

    public static boolean findFirst(int idGoods) {
        try {
            TaskTable table = dbUtils.findFirst(Selector.from(TaskTable.class).where(TaskTable.tagNameStr, "=", idGoods));
            return table != null;
        } catch (Exception ignored) {
        }

        return false;
    }

    public static TaskTable findFirstTable(int idGoods) {
        try {
            TaskTable table = dbUtils.findFirst(Selector.from(TaskTable.class).where(TaskTable.tagNameStr, "=", idGoods));
            return table;
        } catch (Exception ignored) {
        }

        return null;
    }

    public static void deleteTable(int idGoods) {
        try {
            dbUtils.delete(TaskTable.class, WhereBuilder.b(TaskTable.tagNameStr, "=", idGoods));
        } catch (Exception ignored) {
        }
    }

    public static void deleteAllTable() {
        try {
            dbUtils.deleteAll(TaskTable.class);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
