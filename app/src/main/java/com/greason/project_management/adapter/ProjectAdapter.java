package com.greason.project_management.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.greason.project_management.IntentUtils;
import com.greason.project_management.R;
import com.greason.project_management.adapter.base.RecyclerBaseAdapter;
import com.greason.project_management.adapter.holder.ABRecyclerViewHolder;
import com.greason.project_management.bean.ProjectBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Greason on 17/4/27.
 */

public class ProjectAdapter extends RecyclerBaseAdapter<ProjectBean> {

    public ProjectAdapter(Activity mActivity, List<ProjectBean> list) {
        super(mActivity, list);
    }

    @Override
    public ABRecyclerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.adapter_project, parent, false));
    }

    @Override
    public void onBindViewHolder(ABRecyclerViewHolder holder, int position) {
        holder.onBindViewHolder(position);
    }

    class ViewHolder extends ABRecyclerViewHolder {

        @InjectView(R.id.pro_name)
        TextView pro_name;

        @InjectView(R.id.pro_description)
        TextView pro_description;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

        @Override
        public void onBindViewHolder(int position) {
            final ProjectBean bean = getList().get(position);

            pro_name.setText(bean.name);
            pro_description.setText(bean.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.toTaskActivity(getActivity(), bean);
                }
            });

        }
    }

}
