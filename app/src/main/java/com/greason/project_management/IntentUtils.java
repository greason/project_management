package com.greason.project_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.greason.project_management.activity.TaskActivity;
import com.greason.project_management.activity.TaskAddActivity;
import com.greason.project_management.bean.ProjectBean;

/**
 * Created by Greason on 17/4/27.
 */

public class IntentUtils {

    public static void toTaskActivity(Activity activity , ProjectBean activityBean){
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, TaskActivity.class);
        intent.putExtra(Constants.INTENT_PARABLE , (Parcelable) activityBean);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_away_from_right_in, R.anim.activity_static);
    }

    public static void toTaskAddActivity(Activity activity , long id){
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, TaskAddActivity.class);
        intent.putExtra(Constants.INTENT_ID , id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_away_from_right_in, R.anim.activity_static);
    }

}
