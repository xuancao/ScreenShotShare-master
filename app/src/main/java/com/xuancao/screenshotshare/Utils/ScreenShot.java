package com.xuancao.screenshotshare.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScreenShot {
    private Context mContext;
    private OnShotFinishedListener listener;

    public ScreenShot(Context context) {
        mContext = context;
    }

    // 获取指定Activity的截屏，保存到png文件
    private Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        // 去掉标题栏
//         Bitmap bitmap = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap bitmap = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bitmap;
    }

    // 保存到sdcard
    private void savePic(Activity activity, Bitmap b, String strFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFileName);

            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
//                Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onFinished();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暴露接口
    public interface OnShotFinishedListener {
        void onFinished();
    }

    // 让外界传递接口进来
    public void setOnShotFinishedListener(OnShotFinishedListener listener) {
        this.listener = listener;
    }

    // 程序入口
    public Bitmap shoot() {
        Bitmap bitmap = takeScreenShot((Activity) mContext);
        savePic((Activity) mContext, bitmap, Environment.getExternalStorageDirectory() +"/screenshot.png");
        return bitmap;
    }
}
