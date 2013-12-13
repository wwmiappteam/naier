/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.common.Constants;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-21 下午04:08:27
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierCoreBusinessDetailActivity extends NaierBaseActivity
        implements OnClickListener {
    private TextView txtTitle;
    private TextView txtPrice;
    private WebView webView;

    private LinearLayout lltLayout;
    private NaierApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_core_business_detail_layout);
        initViews();
    }

    /**
     * Description:初始化控件
     * 
     */
    private void initViews() {
        LinearLayout.LayoutParams llt = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        application = (NaierApplication) getApplication();
        initTopBaseViews("核心业务", true, true, false, R.drawable.call_telephone);
        addMenuClickListener(this);
        findViewById(R.id.imgbtn_top_menu).setOnClickListener(this);
        lltLayout = (LinearLayout) findViewById(R.id.llt_core_business_detail);
        txtPrice = (TextView) findViewById(R.id.txt_core_business_detail_price);
        txtTitle = (TextView) findViewById(R.id.txt_core_business_detail_title);
        txtTitle.setText(getIntent().getStringExtra("title"));
        txtPrice.setText("价格：￥" + getIntent().getStringExtra("price"));
        webView = new WebView(NaierCoreBusinessDetailActivity.this);
        webView.setLayoutParams(llt);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setOnTouchListener(Constants.touchListenerFroWebview);
        webView.loadDataWithBaseURL(null,
                getIntent().getStringExtra("introduce"), "text/html", "utf-8",
                null);
        lltLayout.addView(webView);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        case R.id.imgbtn_top_menu:
            if (application.getCompany() == null) {
                simpleToast("电话数据正在获取中，请稍后再试");
            } else {
                Uri call = Uri.parse("tel:"
                        + application.getCompany().getAdvise_tel());
                Intent intentAftermarket = new Intent(Intent.ACTION_CALL, call);
                startActivity(intentAftermarket);
            }

            break;

        default:
            break;
        }
    }
}
