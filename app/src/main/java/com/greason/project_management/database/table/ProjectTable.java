package com.greason.project_management.database.table;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Greason on 16/7/20.
 */
@Table(name = "project")
public class ProjectTable {

    public static final String tagNameStr = "id";


    @Column(column = "id")
    public long id;
    public String name;
    public String description;

    public ProjectTable(){

    }

    public ProjectTable(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
