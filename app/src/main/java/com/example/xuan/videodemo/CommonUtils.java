package com.example.xuan.videodemo;

import android.content.Context;

/**
 * Author : xuan.
 * Date : 2018/5/8.
 * Description :the description of this file
 */

public class CommonUtils {
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        if (context == null || context.getResources() == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
