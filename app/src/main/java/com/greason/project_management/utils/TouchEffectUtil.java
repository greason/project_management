package com.greason.project_management.utils;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Greason on 16/6/11.
 */
public class TouchEffectUtil {


    public static View.OnTouchListener TouchFocusChange() {
        return TouchFocusChange(true, 1.0f, 0.3f);
    }

    public static View.OnTouchListener TouchFocusChange(final boolean isDelay) {
        return TouchFocusChange(isDelay, 1.0f, 0.3f);
    }

    public static View.OnTouchListener TouchFocusChange(final boolean isDelay, final float normalAlpha, final float pressAlpha) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha(pressAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (isDelay) {
                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            view.setAlpha(normalAlpha);
                                        }
                                    },
                                    300
                            );
                        } else {
                            view.setAlpha(normalAlpha);
                        }
                        break;
                }
                return false;
            }
        };
    }

}
