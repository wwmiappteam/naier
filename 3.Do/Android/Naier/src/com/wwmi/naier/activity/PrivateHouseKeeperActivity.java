/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-21</br>
 */
package com.wwmi.naier.activity;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.PrivateHouseKeeperAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonKeepersType;
import com.wwmi.naier.bean.JsonScrollKeepers;
import com.wwmi.naier.bean.JsonScrollKeepers.ScrollKeepers;
import com.wwmi.naier.bean.KeepersType;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.AsyncImageLoader;
import com.wwmi.naier.util.DialogUtil;


/**
 * PrivateHouseKeeperActivity summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-11-21 下午3:28:02</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class PrivateHouseKeeperActivity extends NaierBaseActivity implements OnClickListener, OnItemClickListener {

	public static final int MARGIN = 10;

	private LinearLayout lltImgs;

	private ListView lv;

	private PrivateHouseKeeperAdapter adapter;

	private Handler handler;

	private List<KeepersType> listKeeperType;

	private List<ScrollKeepers> listScrollKeepers;

	private HorizontalScrollView hsv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.private_housekeeper);
		initTopBaseViews("私人管家", true, true, false, R.drawable.private_housekeeper_phone);
		initView();
		initHandler();
		loadDataType();
	}

	private void initHandler() {

		handler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {

				stopProgressDialog();
				switch (msg.what) {
					case NaierApplication.WHAT_SUCCESS:
						JsonKeepersType keepersType = (JsonKeepersType) msg.obj;
						NaierApplication.mJsonKeepersType = keepersType;
						listKeeperType = new ArrayList<KeepersType>(keepersType.getData());
						KeepersType delType = null;
						for (KeepersType temp : listKeeperType) {
                            if (Constants.TYPE_CODE_5.equals(temp.getTypeCode())) {
                                delType = temp;
                                break;
                            }
                        }
						listKeeperType.remove(delType);
						adapter = new PrivateHouseKeeperAdapter(PrivateHouseKeeperActivity.this, listKeeperType);
						lv.setAdapter(adapter);
						loadDataImg();
						break;
					case NaierApplication.WHAT_SUCCESS_2:
						listScrollKeepers = (List<ScrollKeepers>) msg.obj;
						if (listScrollKeepers.size() == 0) {
							hsv.setVisibility(View.GONE);
						} else {
							hsv.setVisibility(View.VISIBLE);
							for (int i = 0; i < listScrollKeepers.size(); i++) {
								ScrollKeepers scrollKeepers = listScrollKeepers.get(i);
								ImageView imageView = new ImageView(PrivateHouseKeeperActivity.this);
								LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(107, 107);
								layoutParams.setMargins(MARGIN, 0, MARGIN, 0);
								imageView.setLayoutParams(layoutParams);
								imageView.setBackgroundResource(R.drawable.private_housekeeper_heads_default);
								imageView.setTag(i);
								imageView.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {

										int position = (Integer) v.getTag();
										String id = listScrollKeepers.get(position).getKeeperID();
										Intent intent =
												new Intent(PrivateHouseKeeperActivity.this,
														HouseKeeperDetailActivity.class);
										intent.putExtra(NaierApplication.INTENT_KEEPER_ID, id);
										startActivity(intent);
									}
								});
								AsyncImageLoader asyncImageLoader =
										new AsyncImageLoader(PrivateHouseKeeperActivity.this);
								asyncImageLoader.setView(imageView);
								asyncImageLoader.execute(scrollKeepers.getKeeperPhoto());
								lltImgs.addView(imageView);
							}
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

	private void loadDataType() {

		startProgressDialog(Constants.LOADING);
		new Thread(new Runnable() {

			@Override
			public void run() {

				JsonKeepersType jsonKeepersType = NaierApplication.mJsonKeepersType;
				if (jsonKeepersType == null) {
					jsonKeepersType = HTTPTool.getKeepersType();
				}
				String msg = jsonKeepersType.getMsg();
				Message message = handler.obtainMessage();
				if (TextUtils.isEmpty(msg)) {
					message.what = NaierApplication.WHAT_SUCCESS;
					message.obj = jsonKeepersType;
				} else {
					message.what = NaierApplication.WHAT_FAIL;
					message.obj = msg;
				}
				handler.sendMessage(message);
			}
		}).start();
	}

	private void loadDataImg() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				JsonScrollKeepers jsonScrollKeepers = HTTPTool.getScrollKeepers();
				Message message = handler.obtainMessage();
				if (jsonScrollKeepers != null) {
					String msg = jsonScrollKeepers.getMsg();
					if (TextUtils.isEmpty(msg)) {
						message.what = NaierApplication.WHAT_SUCCESS_2;
						message.obj = jsonScrollKeepers.getData();
					} else {
						message.what = NaierApplication.WHAT_FAIL;
						message.obj = msg;
					}
				} else {
					message.what = NaierApplication.WHAT_FAIL;
					message.obj = "获取数据失败";
				}
				handler.sendMessage(message);
			}
		}).start();
	}

	private void initView() {

		hsv = (HorizontalScrollView) findViewById(R.id.hsv_private_housekeeper);
		hsv.setVisibility(View.GONE);
		lv = (ListView) findViewById(R.id.lv_private_house_keeper);
		lltImgs = (LinearLayout) findViewById(R.id.llt_private_housekeeper_imgs);
		addMenuClickListener(this);
		addBackClickListener(this);
		lv.setOnItemClickListener(this);
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
			case R.id.imgbtn_top_menu:
				DialogUtil.createMakePhonecallDialog(this, ((NaierApplication) getApplication()).getCompany()
						.getSecretary_tel());
				break;
			case R.id.imgbtn_top_back:
				onBackPressed();
			default:
				break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Intent intent = new Intent(this, KeeperStarActivity.class);
		intent.putExtra(NaierApplication.INTENT_STR_1, listKeeperType.get(position).getTypeName());
		intent.putExtra(NaierApplication.INTENT_STR_2, listKeeperType.get(position).getTypeCode());
		startActivity(intent);
	}
}
