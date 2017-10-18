package com.xuancao.screenshotshare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xuancao.screenshotshare.Utils.ScreenShot;
import com.xuancao.screenshotshare.Utils.ToastUtil;
import com.xuancao.screenshotshare.app.Constant;
import com.xuancao.screenshotshare.share.MobShareUtil;
import com.xuancao.screenshotshare.share.OnDialogItemClickListener;
import com.xuancao.screenshotshare.share.ShareInfoEntity;
import com.xuancao.screenshotshare.share.TeamCardShareDialog;

public class MainActivity extends AppCompatActivity {

    private Button share;

    private RelativeLayout rl_screen_shot_area;

    private int time = 0;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share = (Button) findViewById(R.id.share);
        rl_screen_shot_area = (RelativeLayout) findViewById(R.id.rl_screen_shot_area);

        ViewTreeObserver observer = rl_screen_shot_area.getViewTreeObserver();
        observer .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(time == 0){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ScreenShot screenShot = new ScreenShot(MainActivity.this);
                            bitmap = screenShot.shoot();
                            time++;
                        }
                    }).start();
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoShare();
            }
        });
    }

    private void gotoShare(){

        ShareInfoEntity entity = new ShareInfoEntity();
        entity.setImgUrl(Environment.getExternalStorageDirectory() +"/screenshot.png");
//        entity.setTitle("dd");
//        entity.setUrl("www.baidu.com");
//        entity.setContent("dsgf");
        entity.setBitmap(bitmap);

        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        showShareDialog(entity);
    }

    private void showShareDialog(final ShareInfoEntity entity){
        TeamCardShareDialog dialog = new TeamCardShareDialog.Builder(this, new OnDialogItemClickListener() {
            @Override
            public void onItemClickListener(int index) {
                switch (index){
                    case Constant.TEAM_SHARE_BY_WEICHATMOMENTS:
                        if (entity != null) {
                            if(MobShareUtil.isWeixinAvilible(MainActivity.this)) {
                                MobShareUtil.share2Moments(MainActivity.this, entity);
                            }else{
                                ToastUtil.showShort("手机未安装微信");
                            }
                        }
                        break;
                    case Constant.TEAM_SHARE_BY_WEICHAT:
                        if (entity != null) {
                            if(MobShareUtil.isWeixinAvilible(MainActivity.this)){
                                MobShareUtil.share2WeChat(MainActivity.this, entity);
                            }else{
                                ToastUtil.showShort("手机未安装微信");
                            }
                        }
                        break;
                }
            }
        }).create();
        dialog.show();
    }
}
