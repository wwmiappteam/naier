/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.NewsActViewPagerAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonActive.Active;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.BitmapLoader;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:32:49
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierNewsActActivity extends NaierBaseActivity implements
        OnClickListener, OnPageChangeListener, OnTouchListener {
    /**
     * 
     */
    private ViewPager vpgNewsact;
    /**
     * 
     */
    private NewsActViewPagerAdapter newsActViewPagerAdapter;
    /**
     * 记录当前选中位置的变量
     */
    private int currentIndex;
    /**
     * imageview数组用来装底部的小圆点的imageview
     */
    private ImageView[] dots;
    /**
     * 新闻活动图片
     */
    private ArrayList<Active> activesArrayList;
    /**
     * 
     */
    private NaierApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_newsact_layout);
        initViews();
    }

    /**
     * Description:初始化view
     * 
     */
    private void initViews() {
        initTopBaseViews("新闻活动", false, false, false, null);
        initNewsInfor();
    }

    /**
     * Description:初始化新闻信息
     * 
     */
    private void initNewsInfor() {
        new LoadingNewsActTask().execute(0);
    }

    /**
     * 装载view的list(viewpager里的view)
     */
    private List<ImageView> viewsHomepageShowImg = new ArrayList<ImageView>();
    /**
     * 页面下标
     */
    private int pageIndex;
    /**
     * 下表list用来标识有没有划过此页
     */
    private ArrayList<Integer> indexarrayList = new ArrayList<Integer>();

    /**
     * Description:初始化viewpager
     * 
     */
    private void initViewPager() {
        application = (NaierApplication) getApplication();
        vpgNewsact = (ViewPager) findViewById(R.id.vpg_news_news);
        vpgNewsact.setOnPageChangeListener(this);
        vpgNewsact.setOnClickListener(this);
        initViewpagerViews();
        newsActViewPagerAdapter = new NewsActViewPagerAdapter(this,
                viewsHomepageShowImg, activesArrayList);
        vpgNewsact.setAdapter(newsActViewPagerAdapter);
        initDots();
    }

    /**
     * Description:初始化viewpaer的views
     * 
     */
    private void initViewpagerViews() {
        // 初始化引导图片列表（初始化）
        for (int i = 0; i < activesArrayList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ScaleType.FIT_XY);
            //imageView.setImageResource(R.drawable.img_bg_news);
            viewsHomepageShowImg.add(imageView);
            imageViewsList.add(imageView);
        }
        new DownloadBitmap(imageViewsList.get(0)).execute(0);
    }

    private ArrayList<ImageView> imageViewsList = new ArrayList<ImageView>();

    /**
     * Description:初始化底部的小点
     * 
     */
    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.viewpager_bottom_newsact);
        dots = new ImageView[activesArrayList.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 0, 5, 5);
        for (int i = 0; i < activesArrayList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.pager_point_selector);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ScaleType.FIT_XY);
            ll.addView(imageView);
        }
        // 循环取得小点图片
        for (int i = 0; i < activesArrayList.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
    }

    @Override
    public void onClick(View arg0) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {
        application.setViewPagerIndex(arg0);
        setCurDot(arg0);
        boolean a = true;
        for (int i = 0; i < indexarrayList.size(); i++) {
            if (indexarrayList.get(i) == arg0) {
                a = false;
            }
        }
        if (a) {
            new DownloadBitmap(imageViewsList.get(arg0), arg0).execute(0);
        }

    }

    /**
     * Description:改变底部小点的样式
     * 
     * @param positon
     */
    private void setCurDot(int positon) {
        if (positon < 0 || positon > activesArrayList.size() - 1
                || currentIndex == positon) {
            return;
        }
        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        Intent intent = new Intent(NaierNewsActActivity.this,
                NaierNewsActDetailActivity.class);
        intent.putExtra("title", activesArrayList.get(pageIndex)
                .getActiveTitle());
        intent.putExtra("start", activesArrayList.get(pageIndex)
                .getActiveStart());
        intent.putExtra("end", activesArrayList.get(pageIndex).getActiveEnd());
        intent.putExtra("tel", activesArrayList.get(pageIndex).getActiveTel());
        intent.putExtra("content", activesArrayList.get(pageIndex)
                .getActiveDescription());
        startActivity(intent);
        return true;
    }

    /**
     * @author XiongWeimin 加载新闻信息
     * @Description
     * @revise
     * @time 2013-11-25 下午06:44:36
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingNewsActTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            startProgressDialog(Constants.LOADING);
            dialog.setCancelable(true);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            activesArrayList = (ArrayList<Active>) HTTPTool.getActive("1",
                    "10000").getData();
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            stopProgressDialog();
            initViewPager();
        }

    }

    /**
     * @author XiongWeimin 加载首页图片
     * @Description
     * @revise
     * @time 2013-11-25 下午06:44:47
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class DownloadBitmap extends AsyncTask<Integer, Void, Integer> {

        private ImageView image;
        private Bitmap bitmap;
        private int index;

        public DownloadBitmap(ImageView image, int index) {
            this.image = image;
            this.index = index;
        }

        public DownloadBitmap(ImageView image) {
            this.image = image;
        }

        @Override
        protected void onPreExecute() {
            startProgressDialog(Constants.LOADING);
            dialog.setCancelable(true);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            bitmap = BitmapLoader.downloadBitmap(activesArrayList.get(index)
                    .getActivePoster());
            indexarrayList.add(index);
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (bitmap == null) {
                image.setImageResource(R.drawable.img_bg_news);
            } else {
                image.setImageBitmap(bitmap);
            }
            newsActViewPagerAdapter.notifyDataSetChanged();
            stopProgressDialog();
        }
    }
}
