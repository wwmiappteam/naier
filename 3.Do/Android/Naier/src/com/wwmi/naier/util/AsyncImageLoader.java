/***
 * company ： Chengdu Tianfu Software Park Co., Ltd.
 * copyright ： -, Chengdu Tianfu Software Park Co., Ltd.
 * 
 * @since：JDK.
 * used android sdk level:
 * @version：.
 * @see：
 ***/

package com.wwmi.naier.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * 
 * AsyncImageLoader summary:
 * 封装异步拿取图片
 * 
 * @author zhongsen</br>
 * Description:
 * Create Time: 2013-1-11 下午4:01:52</br>
 * </br>History：</br>
 * Editor **** Time **** Mantis No **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class AsyncImageLoader extends AsyncTask<String, Integer, String> {

	private View view;

	private Bitmap bmp;

	private Dialog dialog;

	private ProgressBar pb;

	private Context context;

	private int width;

	private int height;

	private String img_url;

	private String savePath;

	public AsyncImageLoader(Context context) {

		this.context = context;
	}

	public void setDialog(Dialog dlg) {

		this.dialog = dlg;
	}

	@Override
	protected String doInBackground(String... params) {

		String folderName = null;
		if (params == null) {
			return null;
		} else {
			if (params.length > 0) img_url = params[0];
			if (params.length > 1) folderName = params[1];
		}
		bmp = downloadBitmap(img_url);
		return null;
	}

	@Override
	protected void onPostExecute(String path) {

		if (null != view && bmp != null) {
			if (width != 0 && height != 0) {
				bmp = resizeBitmap(width, height, bmp);
			}
			if (view.getVisibility() == View.GONE) {
				view.setVisibility(View.VISIBLE);
			}
			view.setBackgroundDrawable(new BitmapDrawable(bmp));
			if (view.getParent() != null && view.getParent().getParent() != null) {
				((View) (view.getParent().getParent())).invalidate();
			}

		}

		if (null != pb) {
			pb.setVisibility(View.INVISIBLE);
		}
		if (dialog != null && bmp == null) {
			dialog.cancel();
			Toast.makeText(context, "加载网络图片失败！", Toast.LENGTH_LONG).show();
		}
		super.onPostExecute(path);
	}

	public Bitmap getBitmap() {

		return bmp;
	}

	/**
	 * Summary : 设置读取图片后显示的载体</br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return void </br>
	 * @throws </br>
	 */
	public void setView(View v) {

		this.view = v;
	}

	/**
	 * 
	 * Summary : </br>
	 * <p>
	 * Method expatiate:
	 * <p>
	 * 
	 * @param </br>
	 * @return String </br>
	 * @throws </br>
	 */
	public static String getUTF8String(String url) {

		String urlUTF8 = "";
		try {
			urlUTF8 = URLEncoder.encode(url, "UTF-8");
			urlUTF8 = urlUTF8.replaceAll("%3A", ":");
			urlUTF8 = urlUTF8.replaceAll("%2F", "/");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		// return to String Formed
		return urlUTF8;
	}

	/**
	 * 从网络下载图片
	 * 
	 * @param url
	 * 图片地址
	 * @return bitmap对象
	 * @throws IOException
	 */
	public static Bitmap downloadBitmap(String url) {

		if (url == null) {
			return null;
		}
//		System.out.println("url:" + url);
		Bitmap bitmap = null;
		try {
			URL u = new URL(getUTF8String(url));
			URLConnection conn = u.openConnection();
			conn.setConnectTimeout(1000);
			int length = (int) conn.getContentLength();
			if (length != -1) {
				InputStream is = conn.getInputStream();
				byte[] imgData = new byte[length];
				byte[] buf = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(buf)) > 0) {
					System.arraycopy(buf, 0, imgData, destPos, readLen);
					destPos += readLen;
				}
				BitmapFactory.Options opt = new BitmapFactory.Options();
				opt.inPreferredConfig = Bitmap.Config.RGB_565;
				opt.inPurgeable = true;
				opt.inInputShareable = true;
				bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length, opt);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			bitmap = null;
		}
		return bitmap;
	}

	public void setPb(ProgressBar pb) {

		this.pb = pb;
		if (this.pb != null) {
			this.pb.setVisibility(View.VISIBLE);
		}
	}

	public static Bitmap resizeBitmap(int width, int height, Bitmap bmp) {

		Bitmap bitmap = null;
		if (bmp != null) {
			if (width < height) {
				float bmpWidth = bmp.getWidth();
				float bmpHeight = bmp.getHeight();
				float bmpNewHeight = width * (bmpHeight / bmpWidth);
				bitmap = Bitmap.createScaledBitmap(bmp, width, (int) bmpNewHeight, false);

			} else {
				float bmpWidth = bmp.getWidth();
				float bmpHeight = bmp.getHeight();
				float bmpNewWidth = height * (bmpWidth / bmpHeight);
				bitmap = Bitmap.createScaledBitmap(bmp, (int) bmpNewWidth, height, false);
			}
		}
		return bitmap;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {

		this.width = width;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {

		this.height = height;
	}

}
