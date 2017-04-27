package com.greason.project_management.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.greason.project_management.R;
import com.greason.project_management.dialog.LoadingFragmentDialog;
import com.greason.project_management.utils.TouchEffectUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by Greason on 17/4/27.
 */
public abstract class AbstractAppCompatActivity extends AppCompatActivity {

    protected String TAG = AbstractAppCompatActivity.class.getSimpleName();

    @Optional
    @InjectView(R.id.top_title_ll)
    View top_title_ll;
    @Optional
    @InjectView(R.id.back_iv)
    ImageView back_iv;
    @Optional
    @InjectView(R.id.top_back_title)
    TextView top_back_title;
    @Optional
    @InjectView(R.id.top_back_action)
    TextView top_back_action;
    @Optional
    @InjectView(R.id.top_line)
    View top_line;

    protected LoadingFragmentDialog loadingDialog;

    protected boolean isNeedRequested = false;
    protected boolean isRequesting = false;

    protected Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            handlerMsg(msg);
            return false;
        }
    });

    protected void handlerMsg(Message msg) {

    }

    protected void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingFragmentDialog();
        }
        loadingDialog.show(getSupportFragmentManager(), "loading");
    }

    protected void hideLoadingDialog() {
        if (loadingDialog != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dismiss();
                }
            });
        }
    }

    public void setNeedRequested(boolean needRequested) {
        isNeedRequested = needRequested;
    }

    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }


    public AbstractAppCompatActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView();
        ButterKnife.inject(this);
        initializeData();
        initializeViews();
        initialRequest();

        TAG = getActivity().getClass().getSimpleName();

        if (back_iv != null) {
            back_iv.setOnTouchListener(TouchEffectUtil.TouchFocusChange(false));
        }
        if (top_back_action != null) {
            top_back_action.setOnTouchListener(TouchEffectUtil.TouchFocusChange(false));
        }


    }

    protected abstract void getIntentData();

    protected abstract void setContentView();

    protected abstract void initializeData();

    protected abstract void initializeViews();

    protected abstract void initialRequest();

    @Override
    protected void onResume() {
        super.onResume();

        if (isNeedRequested && !isRequesting) {
            isNeedRequested = false;
            initialRequest();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Optional
    @OnClick(R.id.back_iv)
    public void back_ivClick(View view) {
        finishAction();
    }

    @Optional
    @OnClick(R.id.top_back_action)
    public void top_actionClick(View view) {
        topAction();
    }

    protected void topAction() {
    }

    @Override
    public void onBackPressed() {
        finishAction();
    }

    protected void finishAction() {
        finish();
        overridePendingTransition(R.anim.activity_static, R.anim.push_away_from_right_out);
    }

    protected TextView getTopTitle() {
        return top_back_title;
    }

    public TextView getTopAction() {
        return top_back_action;
    }

    public ImageView getBackIv() {
        return back_iv;
    }

    public View getTopll() {
        return top_title_ll;
    }

    public View getTopLine() {
        return top_line;
    }

    public void onHide() {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected Activity getActivity() {
        return this;
    }

    private int minVelocity = 2000;
    private final int AINMATOR_DURATION = 300;
    private boolean openStatus = true;

    public boolean isOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
    }

}
