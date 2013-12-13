/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-12-2</br>
 */
package com.wwmi.naier.activity;


import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.KeeperAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonKeepersType;
import com.wwmi.naier.bean.KeepersType;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;


/**
 * KeeperStarActivity summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-2 下午2:11:42</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class KeeperStarActivity extends NaierBaseActivity implements OnItemClickListener, OnClickListener {

	private GridView gv;

	private TextView tvDesc;

	private String title;

	private KeepersType mKeepersType;

	private Handler handler;

	private KeeperAdapter adapter;

	/**
	 * 生活咨询：type_code_1
	 * 形象设计：type_code_2
	 * 居家环境：type_code_3
	 * 营养健康：type_code_4
	 * 住家明星：type_code_5
	 * 装修咨询：type_code_6
	 */
	private String typeCode;

	/**
	 * Description :
	 * 
	 * 
	 * @see com.wwmi.naier.activity.NaierBaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.keeper_star);
		title = getIntent().getStringExtra(NaierApplication.INTENT_STR_1);
		// 类型标识
		typeCode = getIntent().getStringExtra(NaierApplication.INTENT_STR_2);
		initTopBaseViews(title, true, false, false, null);
		initView();
		initHandler();
		loadData();
	}

	private void loadData() {

		startProgressDialog(Constants.LOADING);
		new Thread(new Runnable() {
			@Override
			public void run() {
				JsonKeepersType jsonKeepersType = NaierApplication.mJsonKeepersType;
				if (jsonKeepersType == null) {
					jsonKeepersType = HTTPTool.getKeepersType();
				}
				Message message = handler.obtainMessage();
				if (jsonKeepersType != null) {
					String msg = jsonKeepersType.getMsg();
					if (!TextUtils.isEmpty(msg)) {
						message.what = NaierApplication.WHAT_FAIL;
						message.obj = msg;
					} else {
						message.what = NaierApplication.WHAT_SUCCESS;
						message.obj = jsonKeepersType;
					}
				} else {
					message.what = NaierApplication.WHAT_FAIL;
					message.obj = "获取数据失败";
				}
				handler.sendMessage(message);
			}
		}).start();

	}

	/**
	 * Summary : </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param jsonKeepersType </br>
	 */
	private void setGvAdapter(JsonKeepersType jsonKeepersType) {

		List<KeepersType> list = jsonKeepersType.getData();
		for (KeepersType keepersType : list) {
			if (keepersType.getTypeCode().equals(typeCode)) {
				mKeepersType = keepersType;
				break;
			}
		}
		if (mKeepersType != null) {
			tvDesc.setText(mKeepersType.getTypeDescription());
			adapter = new KeeperAdapter(KeeperStarActivity.this, mKeepersType.getKeepers(), gv);
			gv.setAdapter(adapter);
		}

	}

	private void initView() {

		tvDesc = (TextView) findViewById(R.id.tv_keeper_star_desc);
		gv = (GridView) findViewById(R.id.gv_keeper_star);
		gv.setColumnWidth(getWindowManager().getDefaultDisplay().getWidth() / 4);
		gv.setNumColumns(3);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv.setHorizontalSpacing(0);
		gv.setVerticalSpacing(0);
		gv.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gv.setDrawSelectorOnTop(true);
		gv.setPadding(0, 0, 0, 0);
		gv.setOnItemClickListener(this);
		addBackClickListener(this);
	}

	private void initHandler() {
	
		handler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				stopProgressDialog();
				switch (msg.what) {
					case NaierApplication.WHAT_SUCCESS:
						JsonKeepersType jsonKeepersType = (JsonKeepersType) msg.obj;
						NaierApplication.mJsonKeepersType = jsonKeepersType;
						setGvAdapter(jsonKeepersType);

						break;
					case NaierApplication.WHAT_FAIL:
						simpleToast((String) msg.obj);
						break;
					default:
						break;
				}
				return false;
			}
		});
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 * android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		String keeperId = mKeepersType.getKeepers().get(position).getKeeperID();
		Intent intent = new Intent(this, HouseKeeperDetailActivity.class);
		intent.putExtra(NaierApplication.INTENT_KEEPER_ID, keeperId);
		startActivity(intent);
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
			case R.id.imgbtn_top_back:
				onBackPressed();
				break;

			default:
				break;
		}
	}
}
