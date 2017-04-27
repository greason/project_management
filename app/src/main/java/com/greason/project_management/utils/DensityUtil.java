package com.greason.project_management.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.greason.project_management.activity.App;


/**
 * Created by Greason on 16/6/11.
 */
public class DensityUtil {

    public static int changeAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /* 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /* 将px值转换为sp值，保证文字大小不变 */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; // scaleDensity是运行时确定的，是会跟这用户设置的偏好字体大小变化的
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) ((spValue - 0.5f) * fontScale);
    }

    /* 获取手机的通知栏的高度 */
    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height",
                        "dimen", "android"));
    }

    public static double getLayoutScale() {
        return getMetricsHeight(App.getContext()) / App.getContext().getResources().getDisplayMetrics().density / 568.00;
    }

    /* 获取手机屏幕的宽度 */
    public static int getMetricsWidth(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /* 获取手机屏幕的高度 */
    public static int getMetricsHeight(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
