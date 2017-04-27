package com.greason.project_management.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Greason on 17/4/27.
 */

public class TaskBean implements Parcelable {

    public long id;
    public long projectId;
    public String name;
    public String description;

    public TaskBean() {

    }

    public TaskBean(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected TaskBean(Parcel in) {
        id = in.readLong();
        projectId = in.readLong();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(projectId);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TaskBean> CREATOR = new Creator<TaskBean>() {
        @Override
        public TaskBean createFromParcel(Parcel in) {
            return new TaskBean(in);
        }

        @Override
        public TaskBean[] newArray(int size) {
            return new TaskBean[size];
        }
    };
}
