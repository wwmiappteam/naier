package com.wwmi.naier.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class BitmapLoader {

    /**
     * 内存图片软引用缓冲
     */
    private HashMap<String, SoftReference<Bitmap>> imageCache = null;

    private List<Integer> lstIndex;

    public BitmapLoader() {

        imageCache = new HashMap<String, SoftReference<Bitmap>>();
        lstIndex = new ArrayList<Integer>();
    }

    public Bitmap loadBitmap(final int position, final ImageView imgview,
            final String imageURL, final ImageCallBack imageCallBack) {

        // 在内存缓存中，则返回Bitmap对象
        if (imageCache.containsKey(imageURL)) {
            SoftReference<Bitmap> reference = imageCache.get(imageURL);
            Bitmap bitmap = reference.get();
            if (bitmap != null) {
                return bitmap;
            }
        } else if (lstIndex.contains(position)) {
            return null;
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                imageCallBack.imageLoad(imgview, (Bitmap) msg.obj);
            }
        };

        // 如果不在内存缓存中，也不在本地（被jvm回收掉），则开启线程下载图片
        new Thread() {

            @Override
            public void run() {
                lstIndex.add(position);
                Bitmap bitmap = downloadBitmap(imageURL);
                if (bitmap == null)
                    return;
                imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap));
                Message msg = handler.obtainMessage(0, bitmap);
                handler.sendMessage(msg);
            }
        }.start();

        return null;
    }

    /**
     * 从网络下载图片
     * 
     * @param url
     *            图片地址
     * @return bitmap对象
     * @throws IOException
     */
    public static Bitmap downloadBitmap(String url) {

        Bitmap bitmap = null;
        InputStream is = null;

        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setConnectTimeout(5000);
            int length = (int) conn.getContentLength();
            if (length != -1) {
                is = conn.getInputStream();
                byte[] imgData = new byte[length];
                byte[] buf = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(buf)) > 0) {
                    System.arraycopy(buf, 0, imgData, destPos, readLen);
                    destPos += readLen;
                }
                Options options = new Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bitmap = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length, options);
            }
        } catch (IOException e) {
            e.printStackTrace();
            bitmap = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    /**
     * 回调接口
     * 
     * @author onerain
     * 
     */
    public interface ImageCallBack {
        public void imageLoad(ImageView imgview, Bitmap bitmap);
    }
}
