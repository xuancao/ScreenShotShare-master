package com.xuancao.screenshotshare.share;

import android.graphics.Bitmap;

/**
 * Desc:分享数据
 */
public class ShareInfoEntity {
    /**
     * 分享的名称
     */
    private String title="";

    /**
     * 分享的内容
     */
    private String content="";

    /**
     * 分享的图片url或者本地的路径
     */
    private String imgUrl="";

    /**
     * 分享的地址
     */
    private String url="";

    private Bitmap bitmap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
