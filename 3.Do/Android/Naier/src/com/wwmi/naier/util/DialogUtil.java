/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.util;

import com.wwmi.naier.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

    /**
     * Description : 创建一个拨打电话的确认框
     * 
     * @param context
     *            上下文对象
     * @param phoneNumber
     *            电话号码
     * @return void
     * @throws :
     */
    public static void createMakePhonecallDialog(final Context context,
            final String phoneNumber) {
        new AlertDialog.Builder(context).setTitle("确认")
                .setIcon(R.drawable.icon_verify)
                .setMessage("拨打电话:" + phoneNumber + "?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uriAftermarketPhonecall = Uri.parse("tel:"
                                + phoneNumber);
                        Intent intentAftermarket = new Intent(
                                Intent.ACTION_CALL, uriAftermarketPhonecall);
                        context.startActivity(intentAftermarket);
                    }
                }).show();
    }

}
