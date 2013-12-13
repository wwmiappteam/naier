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
import android.widget.ListView;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.ServiceIntroduceInforListAdapter;
import com.wwmi.naier.bean.JsonService.Service;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-21 下午05:32:49
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierServiceIntroduceActivity extends NaierBaseActivity implements
        OnItemClickListener {
    /**
     * PlistView
     */
    private ListView lstServiceIntroduce;
    /**
     * 
     */
    private ServiceIntroduceInforListAdapter serviceIntroduceInforListAdapter;
    /**
     * 
     */
    private ArrayList<Service> servicesArraylist;
    /**
     * 当前页数
     */
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_service_introduce_layout);
        initViews();
        initData();
    }

    /**
     * Description:初始控件
     * 
     */
    private void initViews() {
        initTopBaseViews("服务介绍", true, false, false, null);
        lstServiceIntroduce = (ListView) findViewById(R.id.lst_naier_service_introduce_content);
        lstServiceIntroduce.setOnItemClickListener(this);
    }

    /**
     * Description:初始化数据
     * 
     */
    private void initData() {
        new LoadingServiceList().execute(0);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(NaierServiceIntroduceActivity.this,
                NaierServiceDetailActivity.class);
        intent.putExtra("title", servicesArraylist.get(arg2).getServTitle());
        intent.putExtra("content", servicesArraylist.get(arg2)
                .getServIntroduce());
        startActivity(intent);
    }

    /**
     * @author XiongWeimin
     * @Description 加载服务列表数据
     * @revise
     * @time 2013-11-26 下午03:31:14
     * @version 1.0
     * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All
     *            right.
     */
    class LoadingServiceList extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            startProgressDialog(Constants.LOADING);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            servicesArraylist = (ArrayList<Service>) HTTPTool.getService(
                    currentPage + "", "1000").getData();
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            serviceIntroduceInforListAdapter = new ServiceIntroduceInforListAdapter(
                    NaierServiceIntroduceActivity.this, servicesArraylist);
            lstServiceIntroduce.setAdapter(serviceIntroduceInforListAdapter);
            stopProgressDialog();
        }
    }

}
