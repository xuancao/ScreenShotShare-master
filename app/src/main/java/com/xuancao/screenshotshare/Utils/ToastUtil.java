package com.xuancao.screenshotshare.Utils;

import android.content.Context;
import android.widget.Toast;

import com.xuancao.screenshotshare.app.Myapp;

/**
 * Desc:吐司工具类
 */
public class ToastUtil {

    private static Context context = Myapp.getInstance();

    public static void showShort(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}
