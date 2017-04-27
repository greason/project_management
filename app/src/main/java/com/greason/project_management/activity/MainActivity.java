package com.greason.project_management.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.greason.project_management.R;
import com.greason.project_management.activity.base.AbstractAppCompatActivity;
import com.greason.project_management.adapter.ProjectAdapter;
import com.greason.project_management.adapter.base.DividerItemDecoration;
import com.greason.project_management.bean.ProjectBean;
import com.greason.project_management.bean.TaskBean;
import com.greason.project_management.database.table.ProjectTable;
import com.greason.project_management.database.table.TaskTable;
import com.greason.project_management.database.task.ProjectDbTask;
import com.greason.project_management.database.task.TaskDbTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * Created by Greason on 17/4/27.
 */
public class MainActivity extends AbstractAppCompatActivity {

    @InjectView(R.id.project_rv)
    RecyclerView project_rv;

    ProjectAdapter mProjectAdapter;
    List<ProjectBean> datas;
    int countNum = 10;
    int countTask = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initializeData() {
        datas = new ArrayList<>();

        ProjectDbTask.deleteAllTable();
        TaskDbTask.deleteAllTable();

        for (int i = 0; i < countNum; i++) {
            ProjectBean bean = new ProjectBean(i + 1, "project" + i, "project" + i + " description.");

            ProjectTable projectTable = new ProjectTable(bean.name, bean.description);
            ProjectDbTask.addOrUpdateTable(projectTable);

            List<TaskBean> taskBeanList = new ArrayList<>();
            for (int j = 0; j < countTask; j++) {
                TaskBean taskBean = new TaskBean(j + 1, "task" + j, "task" + j + " description.");
                taskBeanList.add(taskBean);

                TaskTable taskTable = new TaskTable(projectTable.id, taskBean.name, taskBean.description);
                TaskDbTask.addOrUpdateTable(taskTable);
            }
            bean.setTaskBeanList(taskBeanList);
            datas.add(bean);
        }
    }

    @Override
    protected void initializeViews() {
        getTopTitle().setText(getString(R.string.project_title));

        getTopAction().setVisibility(View.VISIBLE);
        getTopAction().setText(getString(R.string.project_add));
        getTopAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mProjectAdapter = new ProjectAdapter(getActivity(), datas);

        project_rv.setAdapter(mProjectAdapter);
        project_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        project_rv.setHasFixedSize(true);
        project_rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    protected void initialRequest() {

    }
}
