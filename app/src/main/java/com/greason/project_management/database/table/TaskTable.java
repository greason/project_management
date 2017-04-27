package com.greason.project_management.database.table;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Greason on 16/8/23.
 */
@Table(name = "task")
public class TaskTable {

    public static final String tagNameStr = "projectId";

    public long id;

    @Column(column = "projectId")
    public long projectId;
    public String name;
    public String description;

    public TaskTable(){

    }

    public TaskTable(long projectId, String name, String description) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
    }

}
