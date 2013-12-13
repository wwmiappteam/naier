/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.SecretaryDetail;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.BitmapLoader;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-19 下午05:16:57
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierPrivateSecretaryDetailActivity extends NaierBaseActivity
        implements OnClickListener {
    /**
     * 标题
     */
    private TextView txtTitle;
    /**
     * 行业
     */
    private TextView txtIndustry;
    /**
     * 地址
     */
    private TextView txtAddress;
    /**
     * 电话
     */
    private TextView txtTelephone;
    /**
     * 人均消费
     */
    private TextView txtConsumption;
    /**
     * 图片
     */
    private ImageView imgImage;
    /**
     * 介绍
     */
    private TextView txtCharacteristic;

    /**
     * application
     */
    private NaierApplication application;

    /**
     * 内容
     */
    private LinearLayout lltPrivateSecretaryDetail;

    /**
     * 文章详细
     */
    private WebView webIntroduce;
    private SecretaryDetail secretaryDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_private_secretary_detail_layout);
        intiViews();
    }

    private void intiViews() {
        application = (NaierApplication) getApplication();
        initTopBaseViews("私人秘书", true, false, false, null);
        addMenuClickListener(this);
        lltPrivateSecretaryDetail = (LinearLayout) findViewById(R.id.llt_private_secretary_detail_layout);
        txtTitle = (TextView) findViewById(R.id.txt_private_secretary_detail_title);
        txtIndustry = (TextView) findViewById(R.id.txt_private_secretary_detail_industry);
        txtAddress = (TextView) findViewById(R.id.txt_private_secretary_detail_address);
        txtTelephone = (TextView) findViewById(R.id.txt_private_secretary_detail_telephone);
        txtConsumption = (TextView) findViewById(R.id.txt_private_secretary_detail_consumption);
        txtCharacteristic = (TextView) findViewById(R.id.txt_private_secretary_detail_characteristic);
        initImageView();
        initData();
    }

    /**
     * Description:初始化页面数据
     * 
     */
    private void initData() {
        new LoadingData().execute(0);
    }

    /**
     * Description:初始化view数据
     * 
     */
    private void initViesData(String title, String industry, String address,
            String telephone, String consumption, String characteristic,
            String picture, String describtion) {
        txtTitle.setText(title);
        txtIndustry.setText(industry);
        if (!TextUtils.isEmpty(address)) {
            txtAddress.setText("地址：" + address);
        } else {
            txtAddress.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(telephone)) {
            txtTelephone.setText("电话：" + telephone);
        } else {
            txtTelephone.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(consumption)) {
            txtConsumption.setText("人均消费：" + consumption + "元");
        } else {
            txtConsumption.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(characteristic)) {
            txtCharacteristic.setText("特色：" + characteristic);
        } else {
            txtCharacteristic.setVisibility(View.GONE);
        }
        imgImage.setBackgroundResource(R.drawable.image_show);
        imgImage.setScaleType(ScaleType.FIT_XY);
        new LoadingImage(picture).execute(0);
        webIntroduce = new WebView(NaierPrivateSecretaryDetailActivity.this);
        webIntroduce.setOnTouchListener(Constants.touchListenerFroWebview);
        webIntroduce.setHorizontalScrollBarEnabled(false);
        webIntroduce.setVerticalScrollBarEnabled(false);
        webIntroduce.loadDataWithBaseURL(null, describtion, "text/html",
                "utf-8", null);
        lltPrivateSecretaryDetail.addView(webIntroduce);
    }

    /**
     * @author XiongWeimin
     * @Description 加载图片
     * @revise
     * @time 2013-11-26 下午03:06:09
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingImage extends AsyncTask<Integer, Void, Integer> {

        String picture;
        Bitmap bitmap;

        public LoadingImage(String picture) {
            this.picture = picture;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            bitmap = BitmapLoader.downloadBitmap(picture);
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            imgImage.setImageBitmap(bitmap);
        }
    }

    /**
     * Description:初始化图片
     * 
     */
    private void initImageView() {
        imgImage = (ImageView) findViewById(R.id.img_private_secretary_detail_image);
        imgImage.setLayoutParams(application.getDetailImageShape());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.imgbtn_top_menu:
            Uri uri = Uri.parse("tel:15828254338");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
            break;
        default:
            break;
        }
    }

    /**
     * @author XiongWeimin
     * @Description
     * @revise
     * @time 2013-11-26 下午03:00:37
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingData extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            startProgressDialog(Constants.LOADING);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            secretaryDetail = HTTPTool.getSecretaryDetail(
                    getIntent().getExtras().getString("id")).getData();
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            initViesData(secretaryDetail.getTitle(),
                    secretaryDetail.getTypeName(),
                    secretaryDetail.getAddress(), secretaryDetail.getTel(),
                    secretaryDetail.getPrice(), secretaryDetail.getSpecial(),
                    secretaryDetail.getImages(),
                    secretaryDetail.getDescription());
            stopProgressDialog();
        }

    }

}
