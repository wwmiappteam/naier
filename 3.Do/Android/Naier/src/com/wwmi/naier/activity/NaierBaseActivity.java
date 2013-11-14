/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wwmi.naier.R;
import com.wwmi.naier.util.DialogUtil;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午03:44:36
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierBaseActivity extends Activity {

    /**
     * 更新时间
     */
    protected long updateListTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void networkToast() {
        if ((System.currentTimeMillis() - updateListTime) > 2000) {
            updateListTime = System.currentTimeMillis();
            DialogUtil.createShortToast(this,
                    getString(R.string.network_prompt));
        }
    }

    protected void simpleToast(String msg) {
        if ((System.currentTimeMillis() - updateListTime) > 2000) {
            updateListTime = System.currentTimeMillis();
            DialogUtil.createShortToast(this, msg);
        }
    }
}
