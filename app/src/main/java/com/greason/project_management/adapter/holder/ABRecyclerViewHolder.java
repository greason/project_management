package com.greason.project_management.adapter.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * 基类
 * <p>
 * Created by Greason on 16/6/7.
 */
public abstract class ABRecyclerViewHolder extends ViewHolder {

    public ABRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(final int position);
}