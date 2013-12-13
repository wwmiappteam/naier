package com.wwmi.naier.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wwmi.naier.R;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-8-22 上午09:58:20
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class WelcomeActivity extends NaierBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,
                        NaierTabActivity.class);
                startActivity(intent);
                finish();
                // overridePendingTransition(R.anim.push_right_in,
                // R.anim.push_left_out);
            }
        }, 1000);// 1秒
    }
}
