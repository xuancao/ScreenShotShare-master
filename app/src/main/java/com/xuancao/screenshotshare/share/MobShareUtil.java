package com.xuancao.screenshotshare.share;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.xuancao.screenshotshare.R;
import com.xuancao.screenshotshare.Utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Desc:分享工具类
 */
public class MobShareUtil {

    /**
     * 分享到微信好友
     */
    public static void share2WeChat(Context context, ShareInfoEntity entity) {
        ShareSDK.initSDK(context);
        Platform.ShareParams sp = new Platform.ShareParams();
        if (TextUtils.isEmpty(entity.getTitle())) {
            sp.setTitle(context.getString(R.string.app_name));
        } else {
            sp.setTitle(entity.getTitle());
        }
        if (TextUtils.isEmpty(entity.getContent())) {
            sp.setText(context.getString(R.string.app_name));
        } else {
            sp.setText(entity.getContent());
        }
//        if (!TextUtils.isEmpty(entity.getImgUrl())) {
//            sp.setImageUrl(entity.getImgUrl());
//        } else {
//            sp.setImageUrl(Constant.logoUrl);
//        }
        if (entity.getBitmap()!=null){
            sp.setImageData(entity.getBitmap());
            sp.setShareType(Platform.SHARE_IMAGE);
        }
        if (!TextUtils.isEmpty(entity.getUrl())){
            sp.setUrl(entity.getUrl());
            sp.setShareType(Platform.SHARE_WEBPAGE);
        }

        Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
        weChat.setPlatformActionListener(new OnShareResultListener());
        weChat.share(sp);
    }

    /**
     * 分享到微信朋友圈
     */
    public static void share2Moments(Context context, ShareInfoEntity entity) {
        ShareSDK.initSDK(context);
        Platform.ShareParams sp = new Platform.ShareParams();
        // wechatMoments.setTitle("不容错过");
        if (TextUtils.isEmpty(entity.getTitle())) {
            sp.setTitle(context.getString(R.string.app_name));
        } else {
            sp.setTitle(entity.getTitle());
        }
        if (TextUtils.isEmpty(entity.getContent())) {
            sp.setText(context.getString(R.string.app_name));
        } else {
            sp.setText(entity.getContent());
        }
//        if (!TextUtils.isEmpty(entity.getImgUrl())) {
//            sp.setImageUrl(entity.getImgUrl());
//        } else {
//            sp.setImageUrl(Constant.logoUrl);
//        }
        if (entity.getBitmap()!=null){
            sp.setImageData(entity.getBitmap());
            sp.setShareType(Platform.SHARE_IMAGE);
        }
        if (!TextUtils.isEmpty(entity.getUrl())){
            sp.setUrl(entity.getUrl());
            sp.setShareType(Platform.SHARE_WEBPAGE);
        }
        Platform weixin = ShareSDK.getPlatform(WechatMoments.NAME);
        weixin.setPlatformActionListener(new OnShareResultListener());
        weixin.share(sp);
    }

    static class OnShareResultListener implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            ToastUtil.showShort("分享成功");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            ToastUtil.showShort("分享失败");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            ToastUtil.showShort("取消分享");
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;

    }
}
