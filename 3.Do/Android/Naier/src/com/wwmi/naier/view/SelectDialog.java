package com.wwmi.naier.view;

/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */

import android.app.Dialog;
import android.content.Context;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-20 上午11:36:24
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class SelectDialog extends Dialog {

    public SelectDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SelectDialog(Context context, int layout, int style) {
        super(context, style);
        SelectDialog.this.setContentView(layout);
    }
}
