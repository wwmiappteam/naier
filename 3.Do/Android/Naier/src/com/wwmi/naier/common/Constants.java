/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.common;

import java.io.File;

import android.os.Environment;
import android.text.InputFilter;
import android.text.LoginFilter;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:11:46
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class Constants {
    /**
     * character encoding:utf-8
     */
    public static final String ENCODING_CHARACTER = "UTF-8";
    public static final String PAGE_ROWS = "10";
    /**
     * baidu map APPkey
     */
    public static final String MAP_KEY = "tN8mHhhfomoLvAGf0FOWtxCj";
    /**
     * SDCard路径
     */
    public static final String SDCARD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath();

    public static final String APPLICATION_PATH = SDCARD_PATH + File.separator
            + "naier" + File.separator;

    public static final String PAGE_ONE = "page_1";
    public static final String PAGE_TWO = "page_2";
    public static final String PAGE_THREE = "page_3";
    public static final String PAGE_FOUR = "page_4";

    /**
     * 应用图片存放路径
     */
    public static final String SECTOR_PATH_MAINPAGE = APPLICATION_PATH
            + "page_1.jpg";

    /**
     * 私人秘书页面常量
     */
    public static final int TYPE_GET_LIST = 0;

    public static final int TYPE_GET_DETAIL = 1;

    public static final int TYPE_PAGE_LOAD_SUCCESS = 2;
    // 生活咨询固定Code
    public static final String TYPE_CODE_1 = "type_code_1";
    // 形象设计固定Code
    public static final String TYPE_CODE_2 = "type_code_2";
    // 居家环境固定Code
    public static final String TYPE_CODE_3 = "type_code_3";
    // 营养健康固定Code
    public static final String TYPE_CODE_4 = "type_code_4";
    // 住家明星固定Code
    public static final String TYPE_CODE_5 = "type_code_5";
    // 装修咨询固定Code
    public static final String TYPE_CODE_6 = "type_code_6";

    /**
     * 阻断WebView的touch事件
     */
    public static final View.OnTouchListener touchListenerFroWebview = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    };

    public static final InputFilter[] loginFilters = new InputFilter[] {
            new InputFilter.LengthFilter(20),
            new LoginFilter.UsernameFilterGeneric() };

    public static final String LOADING = "拼命加载中，请稍后...";

    public static final String PREFERENCES_NAME = "naier_user_preferences";
}
