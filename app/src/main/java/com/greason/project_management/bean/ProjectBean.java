package com.greason.project_management.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Greason on 17/4/27.
 */

public class ProjectBean implements Parcelable {

    public long id;
    public String name;
    public String description;
    public List<TaskBean> mTaskBeanList;

    public ProjectBean() {

    }

    public ProjectBean(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public ProjectBean(long id, String name, String description, List<TaskBean> mTaskBeanList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mTaskBeanList = mTaskBeanList;
    }

    protected ProjectBean(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        mTaskBeanList = in.createTypedArrayList(TaskBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeTypedList(mTaskBeanList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProjectBean> CREATOR = new Creator<ProjectBean>() {
        @Override
        public ProjectBean createFromParcel(Parcel in) {
            return new ProjectBean(in);
        }

        @Override
        public ProjectBean[] newArray(int size) {
            return new ProjectBean[size];
        }
    };

    public void setTaskBeanList(List<TaskBean> taskBeanList) {
        mTaskBeanList = taskBeanList;
    }

}
