/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.wwmi.naier.application.NaierApplication;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:06:43
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class CommunicationUtil {

    /**
     * Description:判断wifi网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            return mWiFiNetworkInfo == null ? false : mWiFiNetworkInfo
                    .getType() == ConnectivityManager.TYPE_WIFI ? true : false;
        }
        return false;
    }

    /**
     * Description:判断移动网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            return mMobileNetworkInfo == null ? false : mMobileNetworkInfo
                    .getType() == ConnectivityManager.TYPE_MOBILE ? true
                    : false;
        }
        return false;
    }

    /**
     * Description:更新NaierApplication中的网络状态
     * 
     * @param context
     */
    public static void updateNetworkInfo(Context context) {
        NaierApplication application = (NaierApplication) context
                .getApplicationContext();
        application.setNetworkAvailable(isWifiConnected(context)
                || isMobileConnected(context));
        application = null;
    }

    // /**
    // * 防止出现NetworkOnMainThreadException 异常处理
    // */
    // public static void initStrictMode() {
    // // 判断操作系统是Android版本3.0以上版本
    // if (android.os.Build.VERSION.SDK_INT >= 11) {
    // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
    // .detectDiskReads().detectDiskWrites().detectNetwork()
    // .penaltyLog().build());
    //
    // StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
    // .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
    // .build());
    // }
    // }
    /**
     * Description:获取网络状态
     * 
     * @param context
     */
    public static boolean getNetworkInfo(Context context) {
        return isWifiConnected(context) || isMobileConnected(context);
    }

    /**
     * Description:判断Sd卡是否存在
     * 
     * @return
     */
    public static boolean sdcardExists() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }



}
