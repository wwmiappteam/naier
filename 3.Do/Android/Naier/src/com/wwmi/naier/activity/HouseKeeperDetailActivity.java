/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-19</br>
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
import android.widget.ImageView;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonKeepersDetail;
import com.wwmi.naier.bean.KeepersDetail;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.AsyncImageLoader;
import com.wwmi.naier.view.LevelImageView;


/**
 * HouseKeeperDetailActivity summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-11-19 下午5:36:22</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class HouseKeeperDetailActivity extends NaierBaseActivity implements OnClickListener {

    private NaierApplication application;

	private String houseKeeperId;

	private TextView tvName;

	private TextView tvSex;

	private TextView tvAge;

	private TextView tvLevelCareer;

	private TextView tvLevelManner;

	private TextView tvLevelHardworking;

	private TextView tvLevelCareful;

	private TextView tvExperience;

	private ImageView ivLevel;

	private ImageView ivHead;

	private LevelImageView livCareer;

	private LevelImageView livManner;

	private LevelImageView livHardworing;

	private LevelImageView livCareful;

	private TextView tvSpeciality;

	private TextView tvBrief;

	private Button btnOrder;

	private Handler handler;

	private String keeperName;

	private String url;

	private String typeName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.housekeeper_detail);
		application = (NaierApplication) getApplication();
		initTopBaseViews("服务人员", true, false, false, null);
		houseKeeperId = getIntent().getStringExtra(NaierApplication.INTENT_KEEPER_ID);
		initHandler();
		initView();
		loadData();

	}

	private void loadData() {

		startProgressDialog(Constants.LOADING);
		new Thread(new Runnable() {

			@Override
			public void run() {

				Message message = handler.obtainMessage();
				JsonKeepersDetail jsonKeepersDetail = HTTPTool.getKeepersDetail(houseKeeperId);
				if(jsonKeepersDetail!=null){
					String msg = jsonKeepersDetail.getMsg();
					if (TextUtils.isEmpty(msg)) {
						message.what = NaierApplication.WHAT_SUCCESS;
						message.obj = jsonKeepersDetail.getData();
					} else {
						message.what = NaierApplication.WHAT_FAIL;
						message.obj = msg;
					}
				}else{
					message.what = NaierApplication.WHAT_FAIL;
					message.obj = "获取数据失败";
				}
				
				handler.sendMessage(message);
			}
		}).start();
	}

	private void initHandler() {

		handler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {

				stopProgressDialog();
				switch (msg.what) {
					case NaierApplication.WHAT_SUCCESS:
						KeepersDetail detail = (KeepersDetail) msg.obj;
						if (detail != null) {
							typeName = detail.getTypeName();
							tvAge.setText(detail.getKeeper_age());
							tvBrief.setText(detail.getKeeper_introduce());
							tvExperience.setText(detail.getKeeper_experience());
							keeperName = detail.getKeeper_name();
							tvName.setText(keeperName);
							tvSex.setText(detail.getKeeper_gender());
							tvSpeciality.setText(detail.getKeeper_special());
							Integer integer = Integer.valueOf(detail.getKeeper_level());
							ivLevel.setImageResource(R.drawable.housekeeper_level_bg_1 + integer - 1);
							String career = detail.getKeeper_professional();
							String careful = detail.getKeeper_attentive();
							String hardworking = detail.getKeeper_hardworking();
							String manner = detail.getKeeper_attitude();
							livCareer.setLevel(Integer.valueOf(career));
							livCareful.setLevel(Integer.valueOf(careful));
							livHardworing.setLevel(Integer.valueOf(hardworking));
							livManner.setLevel(Integer.valueOf(manner));
							tvLevelCareer.setText(career);
							tvLevelHardworking.setText(hardworking);
							tvLevelCareful.setText(careful);
							tvLevelManner.setText(manner);
							url = detail.getKeeper_photo();
							AsyncImageLoader loader = new AsyncImageLoader(HouseKeeperDetailActivity.this);
							loader.setView(ivHead);
							loader.execute(url);
						}
						break;
					case NaierApplication.WHAT_FAIL:
						String error = (String) msg.obj;
						if (TextUtils.isEmpty(error)) {
							error = "查询失败";
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

		tvName = (TextView) findViewById(R.id.tv_housekeep_detail_name);
		tvAge = (TextView) findViewById(R.id.tv_housekeep_detail_age);
		tvSex = (TextView) findViewById(R.id.tv_housekeep_detail_sex);
		tvSpeciality = (TextView) findViewById(R.id.tv_housekeeper_detail_speciality);
		tvBrief = (TextView) findViewById(R.id.tv_housekeeper_detail_brief);
		tvExperience = (TextView) findViewById(R.id.tv_housekeep_detail_experience);
		btnOrder = (Button) findViewById(R.id.btn_housekeeper_detail_order);
		ivLevel = (ImageView) findViewById(R.id.iv_housekeeper_detail_level);
		ivHead = (ImageView) findViewById(R.id.iv_housekeeper_detail_head);
		livCareer = (LevelImageView) findViewById(R.id.liv_housekeeper_detail_career);
		livCareful = (LevelImageView) findViewById(R.id.liv_housekeeper_detail_careful);
		livHardworing = (LevelImageView) findViewById(R.id.liv_housekeeper_detail_hardworking);
		livManner = (LevelImageView) findViewById(R.id.liv_housekeeper_detail_manner);
		tvLevelCareer = (TextView) findViewById(R.id.tv_housekeeper_detail_level_career);
		tvLevelHardworking = (TextView) findViewById(R.id.tv_housekeeper_detail_level_hardworking);
		tvLevelCareful = (TextView) findViewById(R.id.tv_housekeeper_detail_level_careful);
		tvLevelManner = (TextView) findViewById(R.id.tv_housekeeper_detail_level_manner);

		btnOrder.setOnClickListener(this);
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

		switch (v.getId()) {
			case R.id.btn_housekeeper_detail_order:
			    if (application.getCustom() == null) {
		            startActivity(new Intent(this, LoginActivity.class));
		        } else {
		            Intent intent = new Intent(this, OrderServiceActivity.class);
	                intent.putExtra(NaierApplication.INTENT_KEEPER_ID, houseKeeperId);
	                intent.putExtra(NaierApplication.INTENT_NAME, keeperName);
	                intent.putExtra(NaierApplication.INTENT_URL, url);
	                intent.putExtra(NaierApplication.INTENT_STR_1, typeName);
	                startActivity(intent);
		        }
				break;
			case R.id.imgbtn_top_back:
				onBackPressed();
				break;

			default:
				break;
		}
	}
}
