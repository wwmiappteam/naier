/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.util.DialogUtil;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:36:17
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierMoreActivity extends NaierBaseActivity {

    private NaierApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_more_layout);
        initTopBaseViews(getString(R.string.more_title), false, false, false,
                null);
        application = (NaierApplication) getApplication();
    }

    @Override
    protected void onResume() {
        if (application.getCustom() == null) {
            findViewById(R.id.more_rly_login).setVisibility(View.VISIBLE);
            findViewById(R.id.more_rly_personal).setVisibility(View.GONE);
        } else {
            findViewById(R.id.more_rly_login).setVisibility(View.GONE);
            findViewById(R.id.more_rly_personal).setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    public void clickItem(View v) {
        switch (v.getId()) {
        case R.id.more_rly_login:
            startActivity(new Intent(NaierMoreActivity.this,
                    LoginActivity.class));
            break;
        case R.id.more_rly_personal:
            startActivity(new Intent(NaierMoreActivity.this,
                    PersonalActivity.class));
            break;
        case R.id.more_rly_register:
            startActivity(new Intent(NaierMoreActivity.this,
                    RegisterActivity.class));
            break;
        case R.id.more_rly_aboutus:
            startActivity(new Intent(NaierMoreActivity.this,
                    AboutusActivity.class));
            break;
        case R.id.more_rly_callcenter:
            if (application.getCompany() == null) {
                simpleToast("电话数据正在获取中，请稍后再试");
            } else {
                DialogUtil.createMakePhonecallDialog(this, application
                        .getCompany().getAdvise_tel());
            }
            break;
        case R.id.more_rly_feedback:
            startActivity(new Intent(NaierMoreActivity.this,
                    FeedbackActivity.class));
            break;
        case R.id.more_rly_copyright:
            new AlertDialog.Builder(this).setTitle("版权说明")
                    .setMessage("Copyright © 2013-2015 \n成都奈儿家政服务有限公司\n版权所有")
                    .setPositiveButton("确定", null).show();
            break;
        default:
            break;
        }

    }

}
