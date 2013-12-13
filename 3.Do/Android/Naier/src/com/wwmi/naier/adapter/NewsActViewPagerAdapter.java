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
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wwmi.naier.activity.NaierNewsActDetailActivity;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonActive.Active;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-27 下午05:26:15
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NewsActViewPagerAdapter extends PagerAdapter {
    private Activity activity;
    private List<ImageView> views;
    private LayoutInflater inflater;
    private ArrayList<Active> actives;
    private NaierApplication application;


    public NewsActViewPagerAdapter(Activity activity,
            List<ImageView> viewsHomepageShowImg, ArrayList<Active> actives) {
        this.activity = activity;
        this.views = viewsHomepageShowImg;
        this.inflater = LayoutInflater.from(activity);
        this.actives = actives;
        application = (NaierApplication) activity.getApplication();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));
        views.get(position).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,
                        NaierNewsActDetailActivity.class);
                intent.putExtra("title",
                        actives.get(application.getViewPagerIndex())
                                .getActiveTitle());
                intent.putExtra("start",
                        actives.get(application.getViewPagerIndex())
                                .getActiveStart());
                intent.putExtra("end",
                        actives.get(application.getViewPagerIndex())
                                .getActiveEnd());
                intent.putExtra("tel",
                        actives.get(application.getViewPagerIndex())
                                .getActiveTel());
                intent.putExtra("content",
                        actives.get(application.getViewPagerIndex())
                                .getActiveDescription());
                activity.startActivity(intent);
            }
        });
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
