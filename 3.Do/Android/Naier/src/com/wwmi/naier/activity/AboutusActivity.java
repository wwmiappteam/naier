package com.wwmi.naier.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Company;
import com.wwmi.naier.common.Constants;

public class AboutusActivity extends NaierBaseActivity {

    private LinearLayout llyForWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        initTopBaseViews(getString(R.string.aboutus_title), true, false, false,
                null);

        NaierApplication application = (NaierApplication) getApplication();
        Company company = application.getCompany();
        if (company == null) {
            startProgressDialog("公司信息加载中...");
            dialog.setCancelable(true);
            return;
        }

        llyForWebview = (LinearLayout) findViewById(R.id.aboutus_lly_des);
        if (getIntent().getBooleanExtra("from_homepage", false)) {
            // 这里要从首页传from_homepage true过来
            llyForWebview.setVisibility(View.GONE);
        } else {
            WebView webview = new WebView(this);
            webview.setOnTouchListener(Constants.touchListenerFroWebview);
            webview.loadDataWithBaseURL(null, company.getAbout(), "text/html",
                    "utf-8", null);
            llyForWebview.addView(webview);
        }

        String contact = getString(R.string.aboutus_contact_txt);
        contact = String.format(contact, company.getAddress(),
                company.getAdvise_tel(), company.getEmail(), company.getQq());
        ((TextView) findViewById(R.id.aboutus_tv_contact)).setText(contact);
    }

    @Override
    protected void onPause() {
        stopProgressDialog();
        super.onPause();
    }

}
