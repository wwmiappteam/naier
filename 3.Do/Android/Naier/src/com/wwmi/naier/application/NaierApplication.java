/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.application
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-14</br>
 */
package com.wwmi.naier.application;


import android.app.Activity;
import android.app.Application;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.baidu.mapapi.BMapManager;
import com.wwmi.naier.bean.Company;
import com.wwmi.naier.bean.Custom;
import com.wwmi.naier.bean.JsonKeepersType;
import com.wwmi.naier.common.Constants;


/**
 * NaierApplication summary:
 * 
 * @author ZhongSen</br> Description: TODO Create Time: 2013-11-14
 * 上午10:29:42</br> </br>History：</br> Editor **** Time **** Mantis No
 * **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class NaierApplication extends Application {

	public static final boolean IS_PRINT_LOG = true;

	public static final String INTENT_KEEPER_ID = "intent_keeper_id";

	public static final String INTENT_NAME = "intent_keeper_name";

	public static final String INTENT_STR_1 = "intent_str_1";

	public static final String INTENT_STR_2 = "intent_str_2";

	public static final String INTENT_URL = "intent_keeper_url";

	public static final String START_TIME = "startTime";

	public static final String STOP_TIME = "stopTime";

	public static final String DATE_TIME = "datetime";

	public static final int WHAT_SUCCESS = 0xFF;

	public static final int WHAT_FAIL = 0x00;

	public static final int WHAT_SUCCESS_2 = 0xFE;

	public static final int WHAT_SUCCESS_3 = 0xFD;

	public static JsonKeepersType mJsonKeepersType=null;

	// 静态页面中区域图片应该的layoutparams:宽与屏幕同宽，宽高比例3：2
	private LayoutParams mainPageImageShow;

	// 静态页面中区域图片应该的layoutparams:宽与屏幕同宽，宽高比例3：2
	private LinearLayout.LayoutParams mainLltLayoutParams;

	// 静态页面中区域图片应该的layoutparams:宽与屏幕同宽，宽高比例3：2
	private LinearLayout.LayoutParams detailLltLayoutParams;

	private int viewPagerIndex;

	public int getViewPagerIndex() {

		return viewPagerIndex;
	}

	public void setViewPagerIndex(int viewPagerIndex) {

		this.viewPagerIndex = viewPagerIndex;
	}

	/**
	 * 屏幕宽度
	 */
	private int screenWidth;

	/**
	 * 屏幕高度
	 */
	private int screenHeight;

	/**
	 * 网络是否可用
	 */
	private boolean networkAvailable;

	private Custom custom;

	private Company company;

	/**
	 * 百度地图BmapManager
	 */
	private BMapManager mapManager;

	public Custom getCustom() {

		return custom;
	}

	public void setCustom(Custom custom) {

		this.custom = custom;
	}

	public Company getCompany() {

		if (company != null) {
			company =
					new Company(company.getAbout(), company.getAddress(), company.getAdvise_tel(),
							company.getComplain_tel(), company.getEmail(), company.getKeeper_tel(),
							company.getPicturesList(), company.getQq(), company.getSecretary_tel());
		}
		return company;
	}

	public void setCompany(Company company) {

		this.company = company;
	}

	public int getScreenWidth() {

		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {

		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {

		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {

		this.screenHeight = screenHeight;
	}

	public boolean isNetworkAvailable() {

		return networkAvailable;
	}

	public void setNetworkAvailable(boolean networkAvailable) {

		this.networkAvailable = networkAvailable;
	}

	/**
	 * @return viewpager 大小比例
	 */
	public RelativeLayout.LayoutParams getLpllyShape() {

		if (mainPageImageShow == null) {
			mainPageImageShow = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, screenWidth * 3 / 4);
		}
		return mainPageImageShow;
	}

	/**
	 * @return viewpager 大小比例
	 */
	public LinearLayout.LayoutParams getLplltShape() {

		if (mainLltLayoutParams == null) {
			mainLltLayoutParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, screenWidth * 3 / 4);
		}
		return mainLltLayoutParams;
	}

	/**
	 * @return viewpager 大小比例
	 */
	public LinearLayout.LayoutParams getDetailImageShape() {

		if (detailLltLayoutParams == null) {
			detailLltLayoutParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, screenWidth * 3 / 5);
		}
		return detailLltLayoutParams;
	}

	/**
	 * Description:必须在setcontentView之前初始化MapManager
	 * 
	 */
	public void initBmapManager(Activity activity) {

		if (mapManager == null) {
			// 初始化mBmapManager
			mapManager = new BMapManager(activity);
			mapManager.init(Constants.MAP_KEY, null);
		}
	}

}
