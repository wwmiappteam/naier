package com.wwmi.naier.util;


/***
 * company ： Chengdu Tianfu Software Park Co., Ltd.
 * copyright ： -, Chengdu Tianfu Software Park Co., Ltd.
 * 
 * @since：JDK.
 * used android sdk level:
 * @version：.
 * @see：
 ***/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;


/**
 * 
 * AsyncImageLoader2 summary:
 * 
 * @author ZhongSen</br>
 * Description: TODO
 * Create Time: 2013-12-2 下午3:41:07</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class AsyncImageLoader2 {

	private int position;

	private Handler handler;

	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader2(int position) {

		imageCache = new HashMap<String, SoftReference<Drawable>>();
		this.position = position;
	}

	public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {

		// 缓存里面存有值就直接返回
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}
		handler = new Handler() {

			public void handleMessage(Message message) {

				if (message.obj != null) {
					imageCallback.imageLoaded((Drawable) message.obj, position);
				} else {
					imageCallback.imageLoaded(null, position);
				}
			}
		};
		new Thread() {

			@Override
			public void run() {

				Drawable drawable = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}

	public static Drawable loadImageFromUrl(String url) {

		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}

	public interface ImageCallback {

		public void imageLoaded(Drawable imageDrawable, int position);
	}

}
