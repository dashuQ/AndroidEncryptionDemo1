package com.example.androidencryptiondemo.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.androidencryptiondemo.R;


/**
 * Copyright © 2017 回收哥. All rights reserved.
 *
 * @author wujm
 * @Description 加载中对话框
 * @CreateDate 2017-2-10 上午00:00:00
 * @ModifiedBy 修改人中文名或拼音缩写
 * @ModifiedDate 修改日期格式YYYY-MM-DD
 * @WhyModified 改原因描述
 */
public class LoadingDialog extends Dialog {

    private Context context;
    private TextView tvContent;

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_MyDialog_Shape_White_Fillet);
        this.context = context;
        // 加载布局
        setContentView(R.layout.dg_loading);
        // 获取控件
        findViewById();
        // 设置对话框窗体属性
        setProperty(this.context);
    }

    private void findViewById() {
        tvContent = (TextView) findViewById(R.id.tv_content);
    }

    /**
     * 设置对话框内容
     *
     * @param resid
     */
    public void setContent(int resid) {
        tvContent.setText(this.context.getString(resid));
    }

    private void setOnClick() {
    }

    /**
     * 设置对话框窗体属性
     *
     * @param context 上下文
     */
    private void setProperty(Context context) {
//        setCanceledOnTouchOutside(false);// 对话框以外无法取消
        setCancelable(true);
        int h = context.getResources().getDisplayMetrics().heightPixels;
        int w = context.getResources().getDisplayMetrics().widthPixels;
        if (h < w) {
            w = h;
        }
        Window window = getWindow();// 　　　得到对话框的窗口．
        WindowManager.LayoutParams lp = window.getAttributes();
        // 中间
        // lp.x = -vWidth;// 这两句设置了对话框的位置．0为中间
        // lp.y = vheight;// 这两句设置了对话框的位置．0为中间//-(292 - 45)=-247
        // 宽度
        // lp.width = lvW;// 对话框的宽 占屏幕比例的2/3
//        lp.width = w * 2 / 5;// 对话框的宽 占屏幕比例的4/5
        lp.width = lp.WRAP_CONTENT;// 对话框的宽 占屏幕比例的2/3
        // 高
        lp.height = lp.WRAP_CONTENT;// 对话框的高包裹内容
        // 透明度
        // lp.alpha = 0.9f;// 这句设置了对话框的透明度
        // 布局相对位置
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
    }


    /**
     * 设置内容文字颜色
     *
     * @param resid
     */
    public void setContentTextColor(int resid) {
        tvContent.setTextColor(resid);
    }
}
