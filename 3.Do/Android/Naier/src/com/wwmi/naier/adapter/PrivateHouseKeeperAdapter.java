/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.adapter
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-29</br>
 */
package com.wwmi.naier.adapter;


import java.util.List;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.KeepersType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * PrivateHouseKeeperAdapter summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-11-29 下午4:16:37</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class PrivateHouseKeeperAdapter extends BaseAdapter {

	private List<KeepersType> listKeeperType;

	private LayoutInflater inflater;

	public PrivateHouseKeeperAdapter(Context context, List<KeepersType> listKeeperType) {

		this.listKeeperType = listKeeperType;
		inflater = LayoutInflater.from(context);
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {

		return listKeeperType.size();
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {

		return listKeeperType.get(position);
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {

		return position;
	}

	/**
	 * Description :
	 * 
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = inflater.inflate(R.layout.private_housekeeper_item, null);
			vh.tvName = (TextView) convertView.findViewById(R.id.tv_private_housekeeper_item_name);
			vh.tvContent = (TextView) convertView.findViewById(R.id.tv_private_housekeeper_item_content);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		KeepersType keepersType = listKeeperType.get(position);
		vh.tvName.setText(keepersType.getTypeName());
		vh.tvContent.setText(keepersType.getTypeDescription());
		return convertView;
	}

	class ViewHolder {

		TextView tvName;

		TextView tvContent;
	}
}
