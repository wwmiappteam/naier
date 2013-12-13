/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.view
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-12-1</br>
 */
package com.wwmi.naier.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * LevelImageView summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-1 下午2:15:41</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class LevelImageView extends ImageView {

	private final int MAX = 10;

	private int width = 250;

	public LevelImageView(Context context, AttributeSet attrs) {

		super(context, attrs);
	}

	public void setLevel(int level) {

		if (level <= MAX) {
			width = (int) (this.getMeasuredWidth() * (level / (double) MAX));
			setMeasuredDimension(width, 30);
			invalidate();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(width, 30);
	}

}
