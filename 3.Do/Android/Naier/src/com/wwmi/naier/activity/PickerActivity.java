/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-12-1</br>
 */
package com.wwmi.naier.activity;


import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.util.DateUtil;


/**
 * PickerActivity summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-1 下午6:01:44</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class PickerActivity extends Activity implements OnClickListener, OnDateChangedListener, OnTimeChangedListener {

	private DatePicker dp;

	private TimePicker tp;

	private Button btn;

	private TextView tv;

	private Date startTime;

	private Date stopTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_service_time_picker);
		startTime = DateUtil.getDateTimeFromString(getIntent().getStringExtra(NaierApplication.START_TIME));
		stopTime = DateUtil.getDateTimeFromString(getIntent().getStringExtra(NaierApplication.STOP_TIME));
		initView();
	}

	private void initView() {

		dp = (DatePicker) findViewById(R.id.dp_order_service_picker);
		tp = (TimePicker) findViewById(R.id.tp_order_service_picker);
		tp.setIs24HourView(true);
		if (startTime == null && stopTime != null) {
			dp.init(stopTime.getYear() + 1900, stopTime.getMonth(), DateUtil.getDay(stopTime), this);
			tp.setCurrentHour(stopTime.getHours());
			tp.setCurrentMinute(stopTime.getMinutes());
		} else if (stopTime == null && startTime != null) {
			dp.init(startTime.getYear() + 1900, startTime.getMonth(), DateUtil.getDay(startTime), this);
			tp.setCurrentHour(startTime.getHours());
			tp.setCurrentMinute(startTime.getMinutes());
		} else if (stopTime == null && startTime == null) {
			Date date = new Date();
			dp.init(date.getYear() + 1900, date.getMonth(), DateUtil.getDay(date), this);
			tp.setCurrentHour(date.getHours());
			tp.setCurrentMinute(date.getMinutes());
		}
		tp.setOnTimeChangedListener(this);

		tv = (TextView) findViewById(R.id.tv_order_service_picker);
		tv.setText(getDateTime());
		btn = (Button) findViewById(R.id.btn_order_service_picker);

		btn.setOnClickListener(this);
	}

	private String getDateTime() {

		return dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth() + " " + tp.getCurrentHour() + ":"
				+ tp.getCurrentMinute();
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.btn_order_service_picker:
				String date = getDateTime();
				Date selectTime = DateUtil.getDateTimeFromString(date);
				if (startTime != null) {
					if (selectTime.compareTo(startTime) < 0) {
						Toast.makeText(this, "预约的开始时间大于了结束时间！", Toast.LENGTH_SHORT).show();
						return;
					}
				} else if (stopTime != null) {
					if (selectTime.compareTo(stopTime) > 0) {
						Toast.makeText(this, "预约的开始时间大于了结束时间！", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				Intent intent = new Intent(this, OrderServiceActivity.class);
				intent.putExtra(NaierApplication.DATE_TIME, date);
				setResult(101, intent);
				finish();
				break;

			default:
				break;
		}

	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

//		tv.setText("时间：" + dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth() + " " + hourOfDay + ":"
//				+ minute);
		tv.setText(getDateTime());
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

//		tv.setText("时间：" + year + "-" + monthOfYear + "-" + dayOfMonth + 1 + " " + tp.getCurrentHour() + ":"
//				+ tp.getCurrentMinute());
		tv.setText(getDateTime());
	}
}
