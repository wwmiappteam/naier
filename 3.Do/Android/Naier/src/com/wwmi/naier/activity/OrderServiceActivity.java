/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-25</br>
 */
package com.wwmi.naier.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Custom.Order;
import com.wwmi.naier.bean.JsonMsg;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.AsyncImageLoader;


/**
 * OrderServiceActivity summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-11-25 上午10:23:41</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class OrderServiceActivity extends NaierBaseActivity implements OnClickListener {

	private EditText edtRequire;

	private TextView tvStart;

	private TextView tvStop;

	private TextView tvName;

	private ImageView ivHead;

	private TextView tvService;

	private Button btnSubmit;

	private Handler handler;

	private String startTime = null;

	private String stopTime = null;

	private NaierApplication application;

	private String keeperId;

	private String keeperName;

	private String url;

	private String service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_service);
		initTopBaseViews("预约服务", true, false, false, null);
		application = (NaierApplication) getApplication();
		initData();
		initView();
		initHandler();
	}

	private void initData() {

		keeperId = getIntent().getStringExtra(NaierApplication.INTENT_KEEPER_ID);
		keeperName = getIntent().getStringExtra(NaierApplication.INTENT_NAME);
		url = getIntent().getStringExtra(NaierApplication.INTENT_URL);
		service = getIntent().getStringExtra(NaierApplication.INTENT_STR_1);
	}

	private void initHandler() {

		handler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				stopProgressDialog();
				switch (msg.what) {
					case NaierApplication.WHAT_SUCCESS:
						Order order = new Order(stopTime, keeperId, keeperName, url, service, "", startTime);
						application.getCustom().getOrderList().add(order);
						simpleToast("预约成功");
						finish();
						break;
					case NaierApplication.WHAT_FAIL:
						String error = (String) msg.obj;
						if (TextUtils.isEmpty(error)) {
							error = "预约失败";
						}
						simpleToast(error);

						break;

					default:
						break;
				}
				return false;
			}
		});
	}

	private void initView() {

		edtRequire = (EditText) findViewById(R.id.edt_order_service_other);
		tvStart = (TextView) findViewById(R.id.tv_order_service_start);
		tvStop = (TextView) findViewById(R.id.tv_order_service_stop);
		tvService = (TextView) findViewById(R.id.tv_order_service_name);
		btnSubmit = (Button) findViewById(R.id.btn_order_service_submit);
		tvName = (TextView) findViewById(R.id.tv_order_service_keeper_name);
		ivHead = (ImageView) findViewById(R.id.iv_order_service_keeper);

		tvName.setText(keeperName);
		tvService.setText(service);
		AsyncImageLoader asyncImageLoader = new AsyncImageLoader(this);
		asyncImageLoader.setView(ivHead);
		asyncImageLoader.execute(url);

		tvStart.setOnClickListener(this);
		tvStop.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		addBackClickListener(this);

	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		Intent intent;
		switch (v.getId()) {
			case R.id.tv_order_service_start:
				intent = new Intent(this, PickerActivity.class);
				intent.putExtra(NaierApplication.STOP_TIME, stopTime);
				startActivityForResult(intent, 1);
				break;
			case R.id.tv_order_service_stop:
				intent = new Intent(this, PickerActivity.class);
				intent.putExtra(NaierApplication.START_TIME, startTime);
				startActivityForResult(intent, 2);
				break;
			case R.id.btn_order_service_submit:
				if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(stopTime)) {
					simpleToast("请选择开始时间和结束时间");
				} else {
					submit();
				}
				break;
			case R.id.imgbtn_top_back:
				onBackPressed();
				break;
			default:
				break;
		}

	}

	private void submit() {
		startProgressDialog(Constants.LOADING);
		new Thread(new Runnable() {

			@Override
			public void run() {

				JsonMsg jsonMsg =
						HTTPTool.keeperOrderAdd(application.getCustom().getCustomID(), keeperId, startTime, stopTime,
								edtRequire.getText().toString().trim());
				Message message = handler.obtainMessage();
				if (jsonMsg != null) {
					String msg = jsonMsg.getMsg();
					if (!TextUtils.isEmpty(msg)) {
						message.what = NaierApplication.WHAT_FAIL;
						message.obj = msg;
					} else {
						message.what = NaierApplication.WHAT_SUCCESS;
					}
				} else {
					message.what = NaierApplication.WHAT_FAIL;
				}
				handler.sendMessage(message);
			}
		}).start();
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// 开始
		if (requestCode == 1 && resultCode == 101) {
			String datetime = data.getStringExtra(NaierApplication.DATE_TIME);
			startTime = datetime;
			tvStart.setText(startTime);
		} else
		// 结束
		if (requestCode == 2 && resultCode == 101) {
			String datetime = data.getStringExtra(NaierApplication.DATE_TIME);
			stopTime = datetime;
			tvStop.setText(stopTime);
		}

	}
}
