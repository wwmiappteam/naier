/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.util
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-12-1</br>
 */
package com.wwmi.naier.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.TextUtils;


/**
 * DateUtil summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-1 下午6:14:33</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class DateUtil {

	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";

	public static final String FORMAT_DATE_DAY = "d";

	/**
	 * 
	 * Summary : </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param dateTime yyyy-MM-dd HH:mm
	 * @return </br>
	 */
	public static Date getDateTimeFromString(String dateTime) {

		Date date = null;
		if (!TextUtils.isEmpty(dateTime)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
				date = sdf.parse(dateTime);
			} catch (Exception e) {

			}
		}
		return date;

	}

	public static int getDay(Date date) {

		int day = 1;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DAY);
			day = Integer.valueOf(sdf.format(date));
		}
		return day;

	}
}
