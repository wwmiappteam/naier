/*
 * Copyright © 2009-2013 Chengdu Tianfu Software Park Co., Ltd.</br>
 * Company: Chengdu Tianfu Software Park Co., Ltd.</br>
 * Project: SevenPlusPlus
 * Package: com.toscm.sevenplusplus.util
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-2-2</br>
 */
package com.wwmi.naier.util;


import com.wwmi.naier.application.NaierApplication;

import android.util.Log;


/**
 * 
 * Logger summary:
 * Log类
 * 
 * @author zhongsen</br>
 * Description:
 * Create Time: 2013-2-2 下午5:28:27</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class Logger {

	/***
	 * 
	 * Summary : Send an INFO log message. </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public static void i(String tag, String msg) {

		if (NaierApplication.IS_PRINT_LOG) {
			Log.i(tag, msg);
		}
	}

	/***
	 * 
	 * Summary : Send a DEBUG log message. </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public static void d(String tag, String msg) {

		if (NaierApplication.IS_PRINT_LOG) {
			Log.d(tag, msg);
		}
	}

	/***
	 * 
	 * Summary : Send a WARN log message. </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public static void w(String tag, String msg) {

		if (NaierApplication.IS_PRINT_LOG) {
			Log.w(tag, msg);
		}
	}

	/***
	 * 
	 * Summary : Send an ERROR log message. </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public static void e(String tag, String msg) {

		if (NaierApplication.IS_PRINT_LOG) {
			Log.e(tag, msg);
		}
	}

	/***
	 * 
	 * Summary : Send a VERBOSE log message. </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public static void v(String tag, String msg) {

		if (NaierApplication.IS_PRINT_LOG) {
			Log.v(tag, msg);
		}
	}
}
