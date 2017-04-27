package com.greason.project_management.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.greason.project_management.Constants;
import com.greason.project_management.IntentUtils;
import com.greason.project_management.R;
import com.greason.project_management.activity.base.AbstractAppCompatActivity;
import com.greason.project_management.adapter.TaskAdapter;
import com.greason.project_management.adapter.base.DividerItemDecoration;
import com.greason.project_management.bean.ProjectBean;
import com.greason.project_management.bean.TaskBean;
import com.greason.project_management.database.table.TaskTable;
import com.greason.project_management.database.task.TaskDbTask;
import com.greason.project_management.events.TaskEvent;
import com.greason.project_management.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Greason on 17/4/27.
 */

public class TaskActivity extends AbstractAppCompatActivity {


    @InjectView(R.id.task_rv)
    RecyclerView task_rv;

    TaskAdapter mTaskAdapter;
    ProjectBean mProjectBean;
    List<TaskBean> datas;

    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            mProjectBean = intent.getParcelableExtra(Constants.INTENT_PARABLE);
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_task);
    }

    @Override
    protected void initializeData() {
        if (mProjectBean != null && mProjectBean.mTaskBeanList != null) {
            datas = mProjectBean.mTaskBeanList;
        } else {
            datas = new ArrayList<>();
        }
    }

    @Override
    protected void initializeViews() {
        com.greason.project_management.activity.App.RegisterEventBus(this);

        getTopTitle().setText(getString(R.string.task_title));

        getTopAction().setVisibility(View.VISIBLE);
        getTopAction().setText(getString(R.string.task_add));
        getTopAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.toTaskAddActivity(getActivity(), mProjectBean.id);
            }
        });

        mTaskAdapter = new TaskAdapter(getActivity(), datas);

        task_rv.setAdapter(mTaskAdapter);
        task_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        task_rv.setHasFixedSize(true);
        task_rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initialRequest() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        com.greason.project_management.activity.App.UnregisterEventBus(this);
    }

    public void onEvent(TaskEvent event) {
        if (event.type == TaskEvent.TYPE_REFRESH) {
            datas.clear();

            List<TaskTable> list = TaskDbTask.getAllTable(mProjectBean.id);

            if (TextUtil.isValidate(list)) {

                //change the data for test
                for (int i = 0; i < list.size(); i++) {
                    TaskTable taskTable = list.get(i);
                    datas.add(new TaskBean(taskTable.id, taskTable.name, taskTable.description));
                }
                mTaskAdapter.notifyDataSetChanged();
            }
        }
    }
}
