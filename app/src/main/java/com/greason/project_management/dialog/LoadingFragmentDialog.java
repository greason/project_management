package com.greason.project_management.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greason.project_management.R;
import com.greason.project_management.utils.DensityUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Greason on 16/8/20.
 */
public class LoadingFragmentDialog extends DialogFragment {

    @InjectView(R.id.load_layout)
    View load_layout;
    @InjectView(R.id.load_content)
    TextView load_content;

    String content;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Fragment_dialog_style);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loading_fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, view);
        load_layout.getLayoutParams().width = (int) (DensityUtil.getMetricsWidth(getContext()) * 0.8);

    }
}
