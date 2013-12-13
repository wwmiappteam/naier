/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.CoreBusinessInforListAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonBusiness.Business;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.view.plistview.PListView;
import com.wwmi.naier.view.plistview.PListView.PListViewListener;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-21 上午11:35:35
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierCoreBusinessActivity extends NaierBaseActivity implements
        OnItemClickListener, PListViewListener {
    private Button button;

    /**
     * 核心业务列表
     */
    private PListView lstCoreBusiness;

    /**
     * listadapter
     */
    private CoreBusinessInforListAdapter coreBusinessInforListAdapter;
    /**
     * list数据
     */
    private ArrayList<Business> arrayList;
    private NaierApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_core_business_layout);
        startProgressDialog(Constants.LOADING);
        initViews();
        initListData();
    }

    /**
     * Description:初始化listview数据
     * 
     */
    private void initListData() {
        new LoadingBusinessList().execute(0);
    }

    /**
     * Description:初始化控件
     * 
     */
    private void initViews() {
        application = (NaierApplication) getApplication();
        initTopBaseViews("核心业务", true, false, false, null);
        lstCoreBusiness = (PListView) findViewById(R.id.lst_core_business_content);
        lstCoreBusiness.setPListViewListener(this);
        lstCoreBusiness.setOnItemClickListener(this);
    }

    private int currentPage = 1;

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (arg0 == lstCoreBusiness &&
        // Header
                arg2 != 0 &&
                // Footer
                arg2 != arrayList.size() + 1) {
            Intent intent = new Intent(NaierCoreBusinessActivity.this,
                    NaierCoreBusinessDetailActivity.class);
            intent.putExtra("title", arrayList.get(arg2 - 1).getBusiTitle());
            intent.putExtra("price", arrayList.get(arg2 - 1).getBusiPrice());
            intent.putExtra("introduce", arrayList.get(arg2 - 1)
                    .getBusiIntroduce());
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        new LoadingBusinessList().execute(0);
    }

    @Override
    public void onLoadMore() {
        if (currentPage < totalPage) {
            currentPage++;
            new LoadingMoreBusinessList().execute(0);
        } else {
            simpleToast("已加载全部数据");
            lstCoreBusiness.onLoadFinish();
        }

    }

    private int totalPage;

    /**
     * @author XiongWeimin
     * @Description 加载列表数据
     * @revise
     * @time 2013-11-26 下午04:24:39
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingBusinessList extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(Integer... params) {
            arrayList = (ArrayList<Business>) HTTPTool.getBusiness(
                    currentPage + "", Constants.PAGE_ROWS).getData();
            totalPage = Integer.parseInt(HTTPTool.getBusiness(currentPage + "",
                    Constants.PAGE_ROWS).getTotalPage());
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            coreBusinessInforListAdapter = new CoreBusinessInforListAdapter(
                    NaierCoreBusinessActivity.this, arrayList);
            lstCoreBusiness.setAdapter(coreBusinessInforListAdapter);
            updateListTime(lstCoreBusiness);
            lstCoreBusiness.stopRefresh();
            stopProgressDialog();
            checkTotalPage();
        }
    }

    /**
     * @author XiongWeimin
     * @Description 加载更多数据
     * @revise
     * @time 2013-11-26 下午04:25:35
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingMoreBusinessList extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(Integer... params) {
            ArrayList<Business> jsonBusinesses = (ArrayList<Business>) HTTPTool
                    .getBusiness(currentPage + "", Constants.PAGE_ROWS)
                    .getData();
            for (int i = 0; i < jsonBusinesses.size(); i++) {
                arrayList.add(jsonBusinesses.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            lstCoreBusiness.stopLoadMore();
            coreBusinessInforListAdapter.notifyDataSetChanged();
            checkTotalPage();
        }
    }

    private void checkTotalPage() {

        if (currentPage >= totalPage) {
            lstCoreBusiness.setPullLoadEnable(false);
        } else {
            lstCoreBusiness.setPullLoadEnable(true);
        }
    }
}
