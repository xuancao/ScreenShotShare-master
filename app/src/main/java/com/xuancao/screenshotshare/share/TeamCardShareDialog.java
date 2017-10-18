package com.xuancao.screenshotshare.share;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuancao.screenshotshare.R;
import com.xuancao.screenshotshare.app.Constant;

public class TeamCardShareDialog extends Dialog {

    public TeamCardShareDialog(Context context) {
        super(context);
    }

    public TeamCardShareDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private OnDialogItemClickListener listener;
        public TeamCardShareDialog dialog;
        private LayoutInflater inflater;

        private TextView tv_share_weichatmoents;
        private TextView tv_share_weichat;
        private TextView tv_share_cancel;

        public Builder(Context context,  OnDialogItemClickListener listener) {
            this.context = context;
            this.listener = listener;
        }

        public TeamCardShareDialog create() {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_team_card_share, null);
//            dialogView.setBackgroundColor(context.getResources().getColor(R.color.bg_white));
            tv_share_weichatmoents = (TextView) dialogView.findViewById(R.id.tv_share_weichatmoents);
            tv_share_weichat = (TextView) dialogView.findViewById(R.id.tv_share_weichat);
            tv_share_cancel = (TextView) dialogView.findViewById(R.id.tv_share_cancel);

            // 设置dialog样式
            dialog = new TeamCardShareDialog(context, R.style.custom_dialog);
            // 设置dialog整体布局
            dialog.addContentView(dialogView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                    .WRAP_CONTENT));
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
            // 设置监听
            setListener();
            return dialog;
        }

        private View getContentView(View mView, boolean hasMargin) {
            if (mView.getLayoutParams() == null) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,88);
                mView.setLayoutParams(params);
            }
            return mView;
        }

        private void setListener() {
            tv_share_weichatmoents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClickListener(Constant.TEAM_SHARE_BY_WEICHATMOMENTS);
                    }
                    dialog.dismiss();
                }
            });

            tv_share_weichat.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClickListener(Constant.TEAM_SHARE_BY_WEICHAT);
                    }
                    dialog.dismiss();
                }
            });

            tv_share_cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }
}
