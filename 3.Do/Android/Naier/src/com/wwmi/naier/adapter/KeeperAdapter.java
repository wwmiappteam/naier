/*
 * 0 * Copyright © 2009-2013 Chengdu Tianfu Software Park Co., Ltd.</br>
 * Company: Chengdu Tianfu Software Park Co., Ltd.</br>
 * Project: testSelectSeat
 * Package: com.zs.demo.testselectseat
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-6-4</br>
 */
package com.wwmi.naier.adapter;


import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.bean.KeepersType.TypeKeepers;
import com.wwmi.naier.util.AsyncImageLoader2;
import com.wwmi.naier.util.AsyncImageLoader2.ImageCallback;


/**
 * 
 * KeeperAdapter summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-2 下午3:25:02</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class KeeperAdapter extends BaseAdapter {

	private Context context;

	private LayoutInflater inflater;

	private List<TypeKeepers> listKeeper;

	private GridView gv;

	private SparseArray<View> contentView;

	public KeeperAdapter(Context context, List<TypeKeepers> listKeeper, GridView gv) {

		this.context = context;
		this.listKeeper = listKeeper;
		this.gv = gv;
		inflater = LayoutInflater.from(context);
		contentView = new SparseArray<View>();
	}

	@Override
	public int getCount() {

		return listKeeper.size();
	}

	@Override
	public Object getItem(int position) {

		return listKeeper.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (contentView.get(position) == null) {
			View view = inflater.inflate(R.layout.keeper_star_gv_item, null);
			TextView tvName = (TextView) view.findViewById(R.id.tv_keeper_star_item_name);
			ImageView ivHead = (ImageView) view.findViewById(R.id.iv_keeper_star_item_head);
			View divider = view.findViewById(R.id.v_keeper_star_divider);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
			if(position%3==0){
				layoutParams.setMargins(12, 0, 0, 0);
			}else if(position%3==1){
				layoutParams.setMargins(0, 0, 0, 0);
			}else if(position%3==2){
				layoutParams.setMargins(0, 0, 12, 0);
			}
			divider.setLayoutParams(layoutParams);
			TypeKeepers keeper = listKeeper.get(position);
			tvName.setText(keeper.getKeeperName());
			ivHead.setTag(position);
			Drawable cachedImage = null;
			AsyncImageLoader2 asyncImageLoader = new AsyncImageLoader2(position);
			cachedImage = asyncImageLoader.loadDrawable(keeper.getKeeperPhoto(), new ImageCallback() {

				@Override
				public void imageLoaded(Drawable imageDrawable, int position) {

					ImageView imageView = (ImageView) gv.findViewWithTag(position);
					if (imageView != null) {
						imageView.setBackgroundDrawable(imageDrawable);
						if (imageDrawable == null) {
							imageView.setBackgroundDrawable(context.getResources().getDrawable(
									R.drawable.private_housekeeper_heads_default));
						}
						KeeperAdapter.this.notifyDataSetChanged();
					}
				}
			});
			if (cachedImage != null) {
				ivHead.setBackgroundDrawable(cachedImage);
			}
			contentView.append(position, view);
		}
		return contentView.get(position);
	}

}
