package com.leslie.mtoast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;

import static com.leslie.mtoast.InnerToast.LENGTH_NON;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @Desc：
 * @Author：Leslie
 * @Date： 2021-07-23 17:24
 * @Email： mr_feeling_heart@yeah.net
 */
public class MToast {
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    @Retention(SOURCE)
    @IntDef(value = {LENGTH_NON, LENGTH_SHORT, LENGTH_LONG})
    @interface ToastDuration {}


    /**
     * 配置全局Config
     * @param context
     * @return
     */
    public static Config configGlobal(@NonNull Context context){
        return InnerToast.getInstance().config(context);
    }

    /**
     * 获取单个Config
     * @return
     */
    public static Config config(){
        return new Config(InnerToast.config.context);
    }


    /**
     * 显示 - 使用全局布局
     * @param msg
     */
    public static void show(@NonNull CharSequence msg){
        InnerToast.getInstance().show(msg);
    }

    /**
     * 显示 - 使用全局布局
     * @param msg
     * @param duration
     */
    public static void show(@NonNull CharSequence msg, @ToastDuration int duration){
        InnerToast.getInstance().show(msg, duration);
    }

    /**
     * 显示 - 使用全局布局
     * @param msg
     * @param iconDrawable
     */
    public static void show(@NonNull CharSequence msg, Drawable iconDrawable){
        InnerToast.getInstance().show(msg, iconDrawable);
    }

    /**
     * 显示 - 使用全局布局样式
     * @param msg
     * @param config
     */
    public static void show(@NonNull CharSequence msg, @NonNull Config config){
        InnerToast.getInstance().show(msg, config);
    }

    /**
     * 显示 - 使用自定义布局
     * @param view
     */
    public static void show(@NonNull View view){
        InnerToast.getInstance().show(view);
    }

    /**
     * 显示 - 使用自定义布局，带Config，自定义Config(只使用到gravity、duration和margin)
     * @param view
     * @param config
     */
    public static void show(@NonNull View view, Config config){
        InnerToast.getInstance().show(view, config);
    }


    public static class Config {
        Context context;
        Drawable iconDrawable;                // 文字左侧ICON
        @DrawableRes
        int iconResId = -1;                   // 文字左侧ICON resId - 优先级高
        int textSize = 16;                    // 文字大小 默认16sp
        Typeface typeface = Typeface.DEFAULT; // 字体样式
        @ColorInt
        int textColor;                        // 背景颜色
        int iconMargin;                       // ICON右侧和文字之间距离 - 单位dp
        @DrawableRes
        int backgroundRes = R.drawable.toast_bg;// 背景图资源 - 优先级高
        Drawable backgroundDrawable;           // 背景图
        int paddingTop;                        // 四个padding的单位都是dp
        int paddingLeft;
        int paddingRight;
        int paddingBottom;
        int duration = LENGTH_SHORT;          // 显示时长
        int gravity;
        int xOffset;
        int yOffset;
        float horizontalMargin;
        float verticalMargin;


        int dip2px(float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        public Config(Context context) {
            this.context = context;
            iconMargin(5);
            paddingTop(5);
            paddingLeft(15);
            paddingRight(15);
            paddingBottom(5);
            gravity(Gravity.CENTER, 0, 0);
            textColor(Color.WHITE);
        }

        public Config iconDrawable(Drawable iconDrawable) {
            this.iconDrawable = iconDrawable;
            return this;
        }

        public Config iconResId(int iconResId) {
            this.iconResId = iconResId;
            return this;
        }

        public Config textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Config textColor(@ColorInt int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Config typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        public Config iconMargin(int iconMargin) {
            this.iconMargin = dip2px(iconMargin);
            return this;
        }

        public Config backgroundRes(int backgroundRes) {
            this.backgroundRes = backgroundRes;
            return this;
        }

        public Config backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Config duration(@MToast.ToastDuration int duration) {
            this.duration = duration;
            return this;
        }

        public Config paddingLeft(int paddingLeft) {
            this.paddingLeft = dip2px(paddingLeft);
            return this;
        }

        public Config paddingRight(int paddingRight) {
            this.paddingRight = dip2px(paddingRight);
            return this;
        }

        public Config paddingBottom(int paddingBottom) {
            this.paddingBottom = dip2px(paddingBottom);
            return this;
        }

        public Config paddingTop(int paddingTop) {
            this.paddingTop = dip2px(paddingTop);
            return this;
        }

        public Config gravity(int gravity, int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.gravity = gravity;
            return this;
        }

        public Config horizontalMargin(float horizontalMargin) {
            this.horizontalMargin = horizontalMargin;
            return this;
        }

        public Config verticalMargin(float verticalMargin) {
            this.verticalMargin = verticalMargin;
            return this;
        }
    }
}
