/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.os.Bundle;
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
 * @time 2013-11-21 下午05:33:39
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierServiceDetailActivity extends NaierBaseActivity {
    private TextView txtTitle;
    private WebView wbvContent;
    private LinearLayout lltServiceDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_service_detail_layout);
        initViews();
    }

    /**
     * Description:初始化控件
     * 
     */
    private void initViews() {
        initTopBaseViews("服务细则", true, false, false, null);
        txtTitle = (TextView) findViewById(R.id.txt_service_detail_title);

        initWebview();
        txtTitle.setText(getIntent().getStringExtra("title"));
    }

    /**
     * Description:初始化webview
     * 
     */
    private void initWebview() {
        LinearLayout.LayoutParams llt = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llt.setMargins(0, 0, 0, 0);
        lltServiceDetail = (LinearLayout) findViewById(R.id.llt_service_detail);
        wbvContent = new WebView(NaierServiceDetailActivity.this);
        wbvContent.setOnTouchListener(Constants.touchListenerFroWebview);
        wbvContent.setLayoutParams(llt);
        wbvContent.setHorizontalScrollBarEnabled(false);
        wbvContent.setVerticalScrollBarEnabled(false);
        wbvContent.loadDataWithBaseURL(null,
                getIntent().getStringExtra("content"), "text/html", "utf-8",
                null);
        lltServiceDetail.addView(wbvContent);

    }

}
