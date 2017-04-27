package com.greason.project_management.events;

/**
 * Created by Greason on 17/4/27.
 */

public class TaskEvent {

    public static final int TYPE_REFRESH = 0x01;

    public int type;

    public TaskEvent(int type) {
        this.type = type;
    }
}
