/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.common.Constants;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-27 上午11:30:01
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierNewsActDetailActivity extends NaierBaseActivity {
    /**
     * 
     */
    private TextView txtTitle;
    /**
     * 
     */
    private TextView txtTime;
    /**
     * 
     */
    private TextView txtTel;
    private LinearLayout lltNesact;
    private WebView wbvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_newsact_detail_layout);
        initViews();
        initViewData();
    }

    /**
     * Description:初始化view
     * 
     */
    private void initViews() {
        initTopBaseViews("新闻活动", true, false, false, null);
        lltNesact = (LinearLayout) findViewById(R.id.llt_newsact_deatil);
        txtTitle = (TextView) findViewById(R.id.txt_newsact_deatil_title);
        txtTime = (TextView) findViewById(R.id.txt_newsact_deatil_time);
        txtTel = (TextView) findViewById(R.id.txt_newsact_deatil_tel);
        wbvContent = new WebView(NaierNewsActDetailActivity.this);
    }

    /**
     * Description:初始化views的数据
     * 
     */
    private void initViewData() {
        txtTitle.setText(getIntent().getExtras().getString("title"));
        if (!TextUtils.isEmpty(getIntent().getExtras().getString("start"))
                | !TextUtils.isEmpty(getIntent().getExtras().getString("end"))) {
            txtTime.setText("时间：" + getIntent().getExtras().getString("start")
                    + "-" + getIntent().getExtras().getString("end"));

        }
        if (!TextUtils.isEmpty(getIntent().getExtras().getString("tel"))) {
            txtTel.setText("咨询电话：" + getIntent().getExtras().getString("tel"));
        }

        LinearLayout.LayoutParams llt = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        wbvContent.setLayoutParams(llt);
        wbvContent.setHorizontalScrollBarEnabled(false);
        wbvContent.setVerticalScrollBarEnabled(false);
        wbvContent.setOnTouchListener(Constants.touchListenerFroWebview);
        wbvContent.loadDataWithBaseURL(null,
                getIntent().getStringExtra("content"), "text/html", "utf-8",
                null);
        lltNesact.addView(wbvContent);
    }
}
