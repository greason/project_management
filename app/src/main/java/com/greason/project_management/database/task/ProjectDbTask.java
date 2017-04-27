package com.greason.project_management.database.task;

import com.greason.project_management.database.table.ProjectTable;
import com.greason.project_management.utils.TextUtil;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

import java.util.List;

/**
 * Created by Greason on 16/7/20.
 */
public class ProjectDbTask extends BaseDbTask {

    private static final String TAG = ProjectDbTask.class.getSimpleName();

    public static void addOrUpdateTable(ProjectTable table) {
        if (TextUtil.isValidate(table)) {
            try {
                dbUtils.saveOrUpdate(table);
            } catch (Exception ignored) {
            }
        }
    }

    public static void addOrUpdateAllTable(List<ProjectTable> tableList) {
        if (TextUtil.isValidate(tableList)) {
            try {
                dbUtils.saveOrUpdateAll(tableList);
            } catch (Exception ignored) {
            }
        }
    }

    public static List<ProjectTable> getAllTable() {
        try {
            return dbUtils.findAll(Selector.from(ProjectTable.class));
        } catch (Exception ignored) {
        }

        return null;
    }

    public static boolean findFirst(int idGoods) {
        try {
            ProjectTable table = dbUtils.findFirst(Selector.from(ProjectTable.class).where(ProjectTable.tagNameStr, "=", idGoods));
            return table != null;
        } catch (Exception ignored) {
        }

        return false;
    }

    public static void deleteTable(int idGoods) {
        try {
            dbUtils.delete(ProjectTable.class, WhereBuilder.b(ProjectTable.tagNameStr, "=", idGoods));
        } catch (Exception ignored) {
        }
    }

    public static void deleteAllTable() {
        try {
            dbUtils.deleteAll(ProjectTable.class);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
