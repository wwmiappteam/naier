/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.wwmi.naier.R;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午03:46:30
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierTabActivity extends TabActivity {

    private TabHost tabHost;
    private long exitTime = 0;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_tab_layout);
        init();
    }

    /**
     * Description:初始化
     * 
     */
    private void init() {
        tabHost = getTabHost();
        tabHost.addTab(getTabSpec("1", NaierHomePageActivity.class,
                R.layout.tab_bottom_homepage));
        tabHost.addTab(getTabSpec("2", NaierNewsActActivity.class,
                R.layout.tab_bottom_newsact));
        tabHost.addTab(getTabSpec("3", NaierLocaActivity.class,
                R.layout.tab_bottom_loca));
        tabHost.addTab(getTabSpec("4", NaierComplaintActivity.class,
                R.layout.tab_bottom_complaint));
        tabHost.addTab(getTabSpec("5", NaierMoreActivity.class,
                R.layout.tab_bottom_more));
    }


    private TabSpec getTabSpec(String key, Class<?> whichclass, int tab) {
        TabSpec tabSpec = tabHost.newTabSpec(key);
        Intent intent = new Intent(this, whichclass);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(tab, null);
        tabSpec.setIndicator(view);
        tabSpec.setContent(intent);
        return tabSpec;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.exit_prompt), Toast.LENGTH_SHORT)
                        .show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return false;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }


}
