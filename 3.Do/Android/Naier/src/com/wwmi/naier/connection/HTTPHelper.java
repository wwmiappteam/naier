/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 7
 * @version：1.0
 */

package com.wwmi.naier.connection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.wwmi.naier.common.Constants;

/**
 * @author gongwei
 * @Description HTTP request and response helper
 * @revise
 * @time 2013-7-22 上午10:42:08
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class HTTPHelper {

    // /**
    // * 请求返回的响应字符串
    // */
    // private static String postResponse;

    /**
     * Description : 发送Http Post请求得到返回的字符串
     * 
     * @param urlStr
     *            请求url
     * @param params
     *            请求参数
     * @return String 返回的响应字符串
     * @throws :
     */
    public static String doPost(final String urlStr,
            final HashMap<String, String> params) {
        // 设置连接超时
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(urlStr);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

        if (params != null) {
            Set<Entry<String, String>> entrys = params.entrySet();
            for (Entry<String, String> entry : entrys) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }

        String postResponse = null;
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,
                    Constants.ENCODING_CHARACTER);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            HttpEntity httpEntity = response.getEntity();
            postResponse = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postResponse;
    }

    // /**
    // * Description : 从流中得到字符串
    // *
    // * @param in 要得到字符串的输入流
    // * @return String 返回的字符串
    // * @throws :
    // */
    // private static String getStringFromStream(InputStream in) {
    // BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    // String response = null;
    // StringBuffer sb = new StringBuffer();
    // try {
    // while ((response = reader.readLine()) != null) {
    // sb.append(response);
    // }
    // return sb.toString();
    // } catch (IOException e) {
    // e.printStackTrace();
    // return null;
    // } finally {
    // try {
    // reader.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // }

    /**
     * Description : 从指定URL下载文件到指定位置
     * 
     * @param urlstr
     *            url字符串
     * @param fileStr
     *            下载到的路径
     * @return boolean 下载是否成功
     * @throws :
     */
    public static boolean downloadFile(String urlstr, String fileStr) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(urlstr);
            in = url.openStream();
            byte[] buffer = new byte[1024 * 8];
            File file = new File(fileStr);
            file.getParentFile().mkdirs();
            file.createNewFile();
            out = new FileOutputStream(file);
            int length = 0;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}