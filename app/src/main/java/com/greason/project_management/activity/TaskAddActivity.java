package com.greason.project_management.activity;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.greason.project_management.Constants;
import com.greason.project_management.R;
import com.greason.project_management.activity.base.AbstractAppCompatActivity;
import com.greason.project_management.database.table.TaskTable;
import com.greason.project_management.database.task.TaskDbTask;
import com.greason.project_management.events.TaskEvent;
import com.greason.project_management.utils.TextUtil;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by Greason on 17/4/27.
 */

public class TaskAddActivity extends AbstractAppCompatActivity {


    @InjectView(R.id.task_name)
    EditText task_name;

    @InjectView(R.id.task_description)
    EditText task_description;

    long projectId;

    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            projectId = intent.getLongExtra(Constants.INTENT_ID, 0);
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_task_add);
    }

    @Override
    protected void initializeData() {

    }

    @Override
    protected void initializeViews() {
        getTopTitle().setText(getString(R.string.task_add_title));

        getTopAction().setVisibility(View.VISIBLE);
        getTopAction().setText(getString(R.string.action_confirm));
        getTopAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isReady()) {
                    return;
                }
                TaskTable taskTable = new TaskTable(projectId, task_name.getText().toString(),
                        task_description.getText().toString());
                TaskDbTask.addOrUpdateTable(taskTable);
                finish();

                //refresh the list
                EventBus.getDefault().post(new TaskEvent(TaskEvent.TYPE_REFRESH));
            }
        });

        task_name.setCursorVisible(false);
        task_description.setCursorVisible(false);

        task_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                task_name.setCursorVisible(true);
                return false;
            }
        });
        task_description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                task_description.setCursorVisible(true);
                return false;
            }
        });
    }

    @Override
    protected void initialRequest() {

    }

    private boolean isReady() {
        boolean flag = false;
        if (TextUtil.isValidate(task_name.getText().toString())) {
            flag = true;
        }

        return flag;
    }
}

