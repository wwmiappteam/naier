/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.util.DialogUtil;
import com.wwmi.naier.view.plistview.PListView;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午03:44:36
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierBaseActivity extends Activity {
    /**
     * 标题
     */
    protected TextView tvTopTitle;

    /**
     * 返回按钮
     */
    protected ImageView imgbtnTopBack;

    /**
     * 菜单按钮
     */
    protected ImageView imgbtnTopMenu;

    /**
     * logo
     */
    protected ImageView imgMainPageLogo;
    /**
     * 更新时间
     */
    protected long updateListTime = 0;
    /**
     * 加载进度条
     */
    protected ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Description:初始化顶部按钮
     * 
     * @param title
     * @param showBackbtn
     * @param showMenubtn
     * @param showLogo
     * @param menuIconResId
     */
    protected void initTopBaseViews(String title, boolean showBackbtn,
            boolean showMenubtn, boolean showLogo, Integer menuIconResId) {
        imgMainPageLogo = (ImageView) findViewById(R.id.img_mainpage_logo);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        imgbtnTopBack = (ImageView) findViewById(R.id.imgbtn_top_back);
        imgbtnTopMenu = (ImageView) findViewById(R.id.imgbtn_top_menu);
        setTopTitle(title);
        showBackAndMenu(showBackbtn, showMenubtn, showLogo);
        if (showBackbtn) {
            addBackClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (showMenubtn) {
            setMenuIcon(menuIconResId);
        }
    }

    /**
     * Description:开始dialog
     * 
     */
    protected void startProgressDialog(String message) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.show();
        } else if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * Description:结束dialog
     * 
     */
    protected void stopProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * Description:设置顶部标题
     * 
     * @param title
     */
    protected void setTopTitle(String title) {
        tvTopTitle.setText(title);
    }

    /**
     * Description:设置菜单图标
     * 
     * @param resId
     */
    protected void setMenuIcon(int resId) {
        imgbtnTopMenu.setImageResource(resId);
    }

    protected void setMenuIcon(Drawable drawable) {
        imgbtnTopMenu.setImageDrawable(drawable);
    }

    protected void setMenuIcon(Bitmap bm) {
        imgbtnTopMenu.setImageBitmap(bm);
    }

    /**
     * Description:是否显示返回，菜单按钮
     * 
     * @param showBackbtn
     * @param showMenubtn
     */
    private void showBackAndMenu(boolean showBackbtn, boolean showMenubtn,
            boolean showLogo) {
        imgbtnTopBack.setVisibility(showBackbtn ? View.VISIBLE : View.GONE);
        imgbtnTopMenu.setVisibility(showMenubtn ? View.VISIBLE : View.GONE);
        imgMainPageLogo.setVisibility(showLogo ? View.VISIBLE : View.GONE);
    }

    /**
     * Description: 返回监听
     * 
     * @param listener
     */
    protected void addBackClickListener(View.OnClickListener listener) {
        imgbtnTopBack.setOnClickListener(listener);
    }

    /**
     * Description:菜单按钮监听
     * 
     * @param listener
     */
    protected void addMenuClickListener(View.OnClickListener listener) {
        imgbtnTopMenu.setOnClickListener(listener);
    }

    protected void networkToast() {
        if ((System.currentTimeMillis() - updateListTime) > 2000) {
            updateListTime = System.currentTimeMillis();
            DialogUtil.createShortToast(this,
                    getString(R.string.network_prompt));
        }
    }

    protected void simpleToast(String msg) {
        if ((System.currentTimeMillis() - updateListTime) > 2000) {
            updateListTime = System.currentTimeMillis();
            DialogUtil.createShortToast(this, msg);
        }
    }

    /**
     * Description:更新时间
     * 
     */
    protected void updateListTime(PListView lstInfor) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        lstInfor.setRefreshTime(str);
    }
}
