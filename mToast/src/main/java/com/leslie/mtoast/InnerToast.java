package com.leslie.mtoast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.leslie.mtoast.MToast.Config;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * @Desc：
 * @Author：Leslie
 * @Date： 2021-07-23 17:25
 * @Email： mr_feeling_heart@yeah.net
 */
class InnerToast {
    static final int LENGTH_NON = -1;
    static InnerToast instance;
    static MToast.Config config;

    public static InnerToast getInstance() {
        if (null == instance) {
            synchronized (InnerToast.class) {
                if (null == instance) {
                    instance = new InnerToast();
                }
            }
        }
        return instance;
    }

    Config config(Context context) {
        if (null == config) {
            config = new Config(context);
        }
        return config;
    }

    /**
     * 正常显示，仅显示文字 - 全局
     *
     * @param msg
     */
    void show(CharSequence msg) {
        getToast(config.context, null, msg, null, LENGTH_NON, config).show();
    }

    /**
     * 正常显示，仅显示文字 - 全局
     *
     * @param msg
     */
    void show(CharSequence msg, @MToast.ToastDuration int duration) {
        getToast(config.context, null, msg, null, duration, config).show();
    }

    /**
     * 正常显示，仅显示文字 - 全局
     *
     * @param msg
     */
    void show(CharSequence msg, Drawable iconDrawable) {
        getToast(config.context, null, msg, iconDrawable, LENGTH_NON, config).show();
    }

    /**
     * 显示 - 单个配置config
     * @param msg
     * @param config
     */
    void show(@NonNull CharSequence msg, @NonNull Config config){
        getToast(config.context, null, msg, null, LENGTH_NON, config).show();
    }

    /**
     * 自定义view - 单个Toast
     * @param view
     */
    void show(@NonNull View view){
        getToast(config.context, view, null, null, LENGTH_NON, config).show();
    }

    /**
     * 自定义view - 单个Toast
     * @param view
     */
    void show(@NonNull View view, Config config){
        getToast(config.context, view, null, null, LENGTH_NON, config).show();
    }

    private Toast getToast(Context context,
                           View customView,
                           CharSequence msg,
                           Drawable iconDrawable,
                           @MToast.ToastDuration int duration,
                           Config config) {
        if (null == context){
            throw new AssertionError("请先调用MToast.configGlobal()做配置初始化工作！！！");
        }
        Toast toast = Toast.makeText(context, msg, LENGTH_SHORT);
        if (null == customView && null != config) {
            // 使用全局样式
            View view = LayoutInflater.from(context).inflate(R.layout.mtoast, null);

            // 背景
            if (config.backgroundRes > -1) view.setBackgroundResource(config.backgroundRes);
            else if (null != config.backgroundDrawable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) view.setBackground(config.backgroundDrawable);
                else view.setBackgroundDrawable(config.backgroundDrawable);
            }

            ImageView imageView = view.findViewById(R.id.toast_icon);
            TextView textView = view.findViewById(R.id.toast_text);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.textSize);
            textView.setTextColor(config.textColor);
            textView.setTypeface(config.typeface);
            textView.setText(msg);

            // icon
            imageView.setVisibility(isShowIcon(config) ? View.VISIBLE : View.GONE);
            imageView.setPadding(0, 0, config.iconMargin, 0);
            if (null != iconDrawable) {
                imageView.setImageDrawable(iconDrawable);
            } else {
                if (config.iconResId > -1) imageView.setImageResource(config.iconResId);
                else if (null != config.iconDrawable) imageView.setImageDrawable(config.iconDrawable);
            }

            // 显示时长
            if (duration > 0) toast.setDuration(duration);
            else toast.setDuration(config.duration);

            view.setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom);
            toast.setView(view);
            toast.setMargin(config.horizontalMargin, config.verticalMargin);
            toast.setGravity(config.gravity, config.xOffset, config.yOffset);
        }else {
            if (null != config) {
                toast.setGravity(config.gravity, config.xOffset, config.yOffset);
                toast.setDuration(config.duration);
                toast.setMargin(config.horizontalMargin, config.verticalMargin);
            }
            toast.setView(customView);
        }
        return toast;
    }

    private boolean isShowIcon(@NonNull Config config) {
        return config.iconResId > -1 || null != config.iconDrawable;
    }





}
