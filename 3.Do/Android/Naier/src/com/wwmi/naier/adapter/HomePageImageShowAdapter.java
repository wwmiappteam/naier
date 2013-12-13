/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonActive.Active;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-18 上午10:38:29
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class HomePageImageShowAdapter extends PagerAdapter {

    private Activity activity;
    private List<ImageView> views;
    private LayoutInflater inflater;
    private ArrayList<Active> actives;
    private NaierApplication application;

    public HomePageImageShowAdapter(Activity activity,
            List<ImageView> viewsHomepageShowImg) {
        this.activity = activity;
        this.views = viewsHomepageShowImg;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

}
