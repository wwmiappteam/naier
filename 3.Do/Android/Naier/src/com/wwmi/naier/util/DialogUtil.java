/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午03:57:30
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class DialogUtil {

    /**
     * Description:创建一个Toast.LENGTH_SHORT toast
     * 
     * @param context
     * @param msg
     */
    public static void createShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Description:创建一个Toast.LENGTH_SHORT toast
     * 
     * @param context
     * @param msg
     */
    public static void createLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


}
