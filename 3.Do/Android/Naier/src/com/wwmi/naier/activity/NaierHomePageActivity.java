/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ： 2013-2014, Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.HomePageImageShowAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonCompany;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.BitmapLoader;
import com.wwmi.naier.util.CommunicationUtil;


/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午03:48:50
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierHomePageActivity extends NaierBaseActivity implements OnClickListener, OnPageChangeListener {

	/**
	 * 主页面viewpager
	 */
	private ViewPager vpImageShow;

	/**
	 * 主页面viewpager适配器
	 */
	private HomePageImageShowAdapter homePageImageShowAdapter;

	/**
	 * application
	 */
	private NaierApplication application;

	/**
	 * imageview数组用来装底部的小圆点的imageview
	 */
	private ImageView[] dots;

	/**
	 * 装载view的list(viewpager里的view)
	 */
	private List<ImageView> viewsHomepageShowImg = new ArrayList<ImageView>();

	/**
	 * 记录当前选中位置的变量
	 */
	private int currentIndex;

	/**
	 * 主页面relativelayout
	 */
	private RelativeLayout relativeLayoutHomePage;

	/**
	 * 私人秘书
	 */
	private ImageView btnPrivateSecretary;

	/**
	 * 私人管家
	 */
	private ImageView btnPrivateSteward;

	/**
	 * 住家明星
	 */
	private ImageView btnResidenceStar;

	/**
	 * 核心业务
	 */
	private ImageView btnCoreBusiness;

	/**
	 * 服务介绍
	 */
	private ImageView btnIntroduceService;

	/**
	 * 线上商城
	 */
	private ImageView btnOnlineStore;

	/**
	 * 关于奈尔
	 */
	private ImageView btnAboutNaier;

	/**
	 * 联系我们
	 */
	private ImageView btnContactUs;

	/**
	 * 图片List
	 */
	private ArrayList<String> picturesList;

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.naier_homepage_layout);
		application = (NaierApplication) getApplication();
		if (application.getScreenWidth() == 0) {
			Display display = getWindowManager().getDefaultDisplay();
			application.setScreenWidth(display.getWidth());
			application.setScreenHeight(display.getHeight());
			display = null;
		}
		initViews();
	}

	/**
	 * Description:初始化按钮
	 * 
	 */
	private void initViews() {

		initTopBaseViews("", false, false, true, null);
		initCompanyInfor();
		initModuleViews();
	}

	/**
	 * Description:初始化公司信息
	 * 
	 */
	private void initCompanyInfor() {

		picturesList = new ArrayList<String>();
		if (CommunicationUtil.getNetworkInfo(this)) {
			new LoadingCompanyInfor().execute(0);
		} else {
			showDialog("当前没有打开网络");
		}

	}

	public void showDialog(String isOpenNet) {

		Builder dialog = new AlertDialog.Builder(this).setTitle("提示").setMessage(isOpenNet);
		dialog.setCancelable(false);
		dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				System.exit(0);
			}
		}).show();
	}

	/**
	 * Description:初始化viewpager
	 * 
	 */
	private void initViewPager() {

		vpImageShow = (ViewPager) findViewById(R.id.viewpager);
		initViewPagerViews();
		homePageImageShowAdapter = new HomePageImageShowAdapter(NaierHomePageActivity.this, viewsHomepageShowImg);
		vpImageShow.setAdapter(homePageImageShowAdapter);
		initDots();
	}

	/**
	 * Description:初始化viewpager中的图片数据
	 * 
	 */
	private void initViewPagerViews() {

		vpImageShow.setLayoutParams(application.getLpllyShape());
		vpImageShow.setOnPageChangeListener(this);
		relativeLayoutHomePage = (RelativeLayout) findViewById(R.id.rlt_homepage);
		relativeLayoutHomePage.setLayoutParams(application.getLplltShape());
		// 初始化引导图片列表（初始化）
		for (int i = 0; i < picturesList.size(); i++) {
			imageView = new ImageView(this);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setBackgroundResource(R.drawable.homepage_viewpager_default);
			viewsHomepageShowImg.add(imageView);
			new DownloadBitmap(imageView).execute(i);
		}

	}

	/**
	 * Description:首页模块初始化
	 * 
	 */
	private void initModuleViews() {

		btnPrivateSecretary = (ImageView) findViewById(R.id.btn_homepage_privatesecretary);
		btnPrivateSteward = (ImageView) findViewById(R.id.btn_homepage_privatesteward);
		btnResidenceStar = (ImageView) findViewById(R.id.btn_homepage_residencestar);
		btnCoreBusiness = (ImageView) findViewById(R.id.btn_homepage_corebusiness);
		btnIntroduceService = (ImageView) findViewById(R.id.btn_homepage_introduceservice);
		btnOnlineStore = (ImageView) findViewById(R.id.btn_homepage_onlinestore);
		btnAboutNaier = (ImageView) findViewById(R.id.btn_homepage_aboutnaier);
		btnContactUs = (ImageView) findViewById(R.id.btn_homepage_contactus);
		btnPrivateSecretary.setOnClickListener(this);
		btnPrivateSteward.setOnClickListener(this);
		btnResidenceStar.setOnClickListener(this);
		btnCoreBusiness.setOnClickListener(this);
		btnIntroduceService.setOnClickListener(this);
		btnOnlineStore.setOnClickListener(this);
		btnAboutNaier.setOnClickListener(this);
		btnContactUs.setOnClickListener(this);
	}

	/**
	 * Description:初始化底部的小点
	 * 
	 */
	private void initDots() {

		LinearLayout ll = (LinearLayout) findViewById(R.id.viewpager_bottom);
		dots = new ImageView[picturesList.size()];
		// 循环取得小点图片
		for (int i = 0; i < picturesList.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.btn_homepage_privatesecretary:
				gotoDetailActivity(NaierPrivateSecretaryActivity.class);
				break;
			case R.id.btn_homepage_privatesteward:
				gotoDetailActivity(PrivateHouseKeeperActivity.class);
				break;
			case R.id.btn_homepage_residencestar:
				Intent intent = new Intent(this, KeeperStarActivity.class);
				intent.putExtra(NaierApplication.INTENT_STR_1, "住家明星");
				intent.putExtra(NaierApplication.INTENT_STR_2, Constants.TYPE_CODE_5);
				startActivity(intent);
				break;
			case R.id.btn_homepage_corebusiness:
				gotoDetailActivity(NaierCoreBusinessActivity.class);
				break;
			case R.id.btn_homepage_introduceservice:
				gotoDetailActivity(NaierServiceIntroduceActivity.class);
				break;
			case R.id.btn_homepage_onlinestore:
				simpleToast("暂未开放，敬请期待！");
				break;
			case R.id.btn_homepage_aboutnaier:
				gotoDetailActivity(AboutusActivity.class);
				break;
			case R.id.btn_homepage_contactus:
				gotoAboutActivity(AboutusActivity.class);
				break;
			default:
				break;
		}
	}

	/**
	 * Description:页面跳转
	 * 
	 * @param activity
	 */
	public void gotoDetailActivity(Class<?> activity) {

		Intent intent = new Intent(NaierHomePageActivity.this, activity);
		startActivity(intent);
	}

	/**
	 * Description:页面跳转
	 * 
	 * @param activity
	 */
	public void gotoAboutActivity(Class<?> activity) {

		Intent intent = new Intent(NaierHomePageActivity.this, activity);
		intent.putExtra("from_homepage", true);
		startActivity(intent);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

		setCurDot(arg0);

	}

	/**
	 * Description:改变底部小点的样式
	 * 
	 * @param positon
	 */
	private void setCurDot(int positon) {

		if (positon < 0 || positon > picturesList.size() - 1 || currentIndex == positon) {
			return;
		}
		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = positon;
	}

	/**
	 * @author XiongWeimin
	 * @Description
	 * @revise
	 * @time 2013-11-28 上午11:09:07
	 * @version 1.0
	 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
	 * right.
	 */
	class LoadingCompanyInfor extends AsyncTask<Integer, String, Integer> {

		@Override
		protected void onPreExecute() {

			startProgressDialog(Constants.LOADING);
		}

		@Override
		protected void onProgressUpdate(String... params) {

			if (params[0].equals("错误")) {
				showDialog("服务器请求错误");
			} else {
				simpleToast(params[0]);
			}
		}

		@Override
		protected Integer doInBackground(Integer... params) {

			JsonCompany jsonCompany = HTTPTool.getCompanyInfo();
			if (jsonCompany == null) {
				publishProgress("错误");
				return 1;
			} else {
				if (TextUtils.isEmpty(jsonCompany.getMsg())) {
					application.setCompany(jsonCompany.getData());
					picturesList = (ArrayList<String>) jsonCompany.getData().getPicturesList();
				} else {
					publishProgress(jsonCompany.getMsg());
				}
			}
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {

			stopProgressDialog();
			if (result != 1) {
				initViewPager();
			}

		}

	}

	/**
	 * @author XiongWeimin
	 * @Description
	 * @revise
	 * @time 2013-11-25 上午10:28:01
	 * @version 1.0
	 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
	 * right.
	 */
	class DownloadBitmap extends AsyncTask<Integer, Void, Integer> {

		private ImageView image;

		private Bitmap bitmap;

		public DownloadBitmap(ImageView image) {

			this.image = image;
		}

		@Override
		protected Integer doInBackground(Integer... params) {

			bitmap = BitmapLoader.downloadBitmap(application.getCompany().getPicturesList().get(params[0]));
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {

			image.setImageBitmap(bitmap);
			homePageImageShowAdapter.notifyDataSetChanged();
		}
	}
}
