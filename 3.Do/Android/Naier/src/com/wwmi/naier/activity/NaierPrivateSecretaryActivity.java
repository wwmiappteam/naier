/**
 * @company ： Chengdu Tianfu Software Park Co., Ltd.
 * @copyright ：  2013-2014,  Chengdu Tianfu Software Park Co., Ltd.
 * @since：JDK1.6
 * @used android sdk level: 9
 * @version：1.0
 */
package com.wwmi.naier.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.PrivateSecretryAreaDialogListAdapter;
import com.wwmi.naier.adapter.PrivateSecretryIndustryDialogAdapter;
import com.wwmi.naier.adapter.PrivateSecretryInforListAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonSecretary;
import com.wwmi.naier.bean.JsonSecretaryTpyeAndRegion;
import com.wwmi.naier.bean.Secretary;
import com.wwmi.naier.bean.SecretaryRegion;
import com.wwmi.naier.bean.SecretaryTpye;
import com.wwmi.naier.bean.SecretaryTpyeFather;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.DialogUtil;
import com.wwmi.naier.view.SelectDialog;
import com.wwmi.naier.view.plistview.PListView;
import com.wwmi.naier.view.plistview.PListView.PListViewListener;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-18 下午05:34:54
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierPrivateSecretaryActivity extends NaierBaseActivity implements
        OnClickListener, OnItemClickListener, PListViewListener {

    /**
     * 搜索框
     */
    private EditText edtSearch;
    private ImageView imgSearch;
    /**
     * 区域按钮
     */
    private Button btnArea;
    /**
     * 行业按钮
     */
    private Button btnIndustry;
    /**
     * 信息列表
     */
    private PListView lstInfor;

    /**
     * 私人秘书信息列表
     */
    private ArrayList<Secretary> secretariesList;

    /**
     * application
     */
    private NaierApplication application;

    /**
     * 信息列表适配器
     */
    private PrivateSecretryInforListAdapter privateSecretryInforListAdapter;

    /**
     * 地区dialog列表
     */
    private ListView lstPrivateSecretryArea;
    /**
     * 行业dialog双层列表
     */
    private ExpandableListView expdLstPrivateSecretryIndustry;

    /**
     * 地区dialog listadapter
     */
    private PrivateSecretryAreaDialogListAdapter privateSecretryAreaDialogListAdapter;

    private PrivateSecretryIndustryDialogAdapter privateSecretryIndustryDialogAdapter;

    private final static int MSG_WHAT_0 = 0;
    private final static int TYPE_AREA = 1;
    private final static int TYPE_INDUSTRY = 2;
    private final static int MSG_WHAT_3 = 3;
    private final static int MSG_WHAT_4 = 4;
    private int currentPage = 1;
    private String typeID = "";
    private String regionID = "";
    private String title = "";
    private JsonSecretaryTpyeAndRegion jsonSecretaryTpyeAndRegion;
    private SelectDialog digArea;
    private SelectDialog digIndustry;
    private int totalPage = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            switch (msg.what) {
            case 0:
                if (msg.obj == null
                        || ((JsonSecretary) msg.obj).getData().size() == 0) {
                    if (secretariesList.size() == 0) {
                        findViewById(R.id.tv_private_secretry_default)
                                .setVisibility(View.VISIBLE);
                        lstInfor.setVisibility(View.GONE);
                    }
                } else {
                    findViewById(R.id.tv_private_secretry_default)
                            .setVisibility(View.GONE);
                    lstInfor.setVisibility(View.VISIBLE);
                    if (secretariesList.size() == 0) {
                        privateSecretryInforListAdapter = new PrivateSecretryInforListAdapter(
                                NaierPrivateSecretaryActivity.this,
                                secretariesList);
                        lstInfor.setAdapter(privateSecretryInforListAdapter);
                    }
                    secretariesList.addAll(((JsonSecretary) msg.obj).getData());
                    totalPage = Integer.parseInt(((JsonSecretary) msg.obj)
                            .getTotalPage());
                    privateSecretryInforListAdapter.notifyDataSetChanged();
                    lstInfor.onLoadFinish();
                    checkTotalPage();
                }
                break;
            case TYPE_AREA:
            case TYPE_INDUSTRY:
                if (msg.obj == null) {
                    simpleToast("获取数据失败，请稍后重试");
                } else if (!TextUtils
                        .isEmpty(((JsonSecretaryTpyeAndRegion) msg.obj)
                                .getMsg())) {
                    simpleToast(((JsonSecretaryTpyeAndRegion) msg.obj).getMsg());
                } else {
                    jsonSecretaryTpyeAndRegion = (JsonSecretaryTpyeAndRegion) msg.obj;
                    List<SecretaryTpyeFather> fatherList = jsonSecretaryTpyeAndRegion
                            .getData().getTypeList();
                    for (SecretaryTpyeFather father : fatherList) {
                        father.getChildTypeList().add(
                                0,
                                new SecretaryTpye(father.getTypeID(), "全部"
                                        + father.getTypeName()));
                    }
                    showSelectDialog(msg.what);
                }
                break;
            case MSG_WHAT_3:
                // 刷新
                if (msg.obj != null
                        && ((JsonSecretary) msg.obj).getData().size() != 0) {
                    secretariesList.clear();
                    secretariesList.addAll(((JsonSecretary) msg.obj).getData());
                    totalPage = Integer.parseInt(((JsonSecretary) msg.obj)
                            .getTotalPage());
                    privateSecretryInforListAdapter.notifyDataSetChanged();
                }
                lstInfor.onLoadFinish();
                checkTotalPage();
                break;
            case MSG_WHAT_4:
                // 翻页
                if (msg.obj != null
                        && ((JsonSecretary) msg.obj).getData().size() != 0) {
                    JsonSecretary temp = (JsonSecretary) msg.obj;
                    secretariesList.addAll(temp.getData());
                    totalPage = Integer.parseInt(temp.getTotalPage());
                    privateSecretryInforListAdapter.notifyDataSetChanged();
                }
                lstInfor.onLoadFinish();
                checkTotalPage();
                break;
            default:
                break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_private_secretary_layout);
        initViews();
    }

    /**
     * Description:初始化views
     * 
     */
    private void initViews() {
        secretariesList = new ArrayList<Secretary>();
        application = (NaierApplication) getApplication();
        initTopBaseViews("私人秘书", true, true, false, R.drawable.icon_call);
        edtSearch = (EditText) findViewById(R.id.edt_private_secretary_searchinput);
        imgSearch = (ImageView) findViewById(R.id.btn_private_secretary_search);
        btnArea = (Button) findViewById(R.id.btn_private_secretary_area);
        btnIndustry = (Button) findViewById(R.id.btn_private_secretary_industry);
        lstInfor = (PListView) findViewById(R.id.lst_private_secretary_infor);
        findViewById(R.id.imgbtn_top_menu).setOnClickListener(this);
        lstInfor.setPListViewListener(this);
        lstInfor.setOnItemClickListener(this);
        btnArea.setOnClickListener(this);
        btnIndustry.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        loadData(true, MSG_WHAT_0);
    }

    private void loadData(boolean needProgress, final int msgWhat) {
        if (needProgress)
            startProgressDialog(Constants.LOADING);
        new Thread() {
            @Override
            public void run() {
                JsonSecretary result = HTTPTool.getSecretary(currentPage + "",
                        Constants.PAGE_ROWS, typeID, regionID, title);
                handler.sendMessage(handler.obtainMessage(msgWhat, result));
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_private_secretary_area:
            loadingAreaIndustryData(TYPE_AREA);
            break;
        case R.id.btn_private_secretary_industry:
            loadingAreaIndustryData(TYPE_INDUSTRY);
            break;
        case R.id.imgbtn_top_menu:
            makePhoneCall();
            break;
        case R.id.btn_industry_dialog_unlimited:
            if (digIndustry.isShowing()) {
                digIndustry.dismiss();
            }
            typeID = "";
            clearPage();
            btnIndustry.setText("行业");
            loadData(true, MSG_WHAT_0);
            break;
        case R.id.btn_area_dialog_unlimited:
            if (digArea.isShowing()) {
                digArea.dismiss();
            }
            regionID = "";
            clearPage();
            btnArea.setText("区域");
            loadData(true, MSG_WHAT_0);
            break;
        case R.id.btn_private_secretary_search:
            title = edtSearch.getText().toString().trim();
            clearPage();
            loadData(true, MSG_WHAT_0);
            break;
        default:
            break;
        }
    }

    /**
     * Description:拨打电话
     * 
     */
    private void makePhoneCall() {
        if (application.getCompany() == null) {
            simpleToast("电话数据正在获取中，请稍后再试");
        } else {
            DialogUtil.createMakePhonecallDialog(this, application.getCompany()
                    .getSecretary_tel());
        }
    }

    private void clearPage() {
        currentPage = 1;
        secretariesList.clear();
    }

    /**
     * Description:加载区域和行业数据
     * 
     * @param type
     *            1 Area 2 Industry
     */
    private void loadingAreaIndustryData(final int type) {
        if (jsonSecretaryTpyeAndRegion == null) {
            startProgressDialog(Constants.LOADING);
            new Thread() {
                @Override
                public void run() {
                    JsonSecretaryTpyeAndRegion jsonTemp = HTTPTool
                            .getSecretaryTpyeAndRegion();
                    handler.sendMessage(handler.obtainMessage(type, jsonTemp));
                }
            }.start();
        } else {
            showSelectDialog(type);
        }
    }

    private void showSelectDialog(int type) {
        switch (type) {
        case TYPE_AREA:
            digArea = new SelectDialog(this,
                    R.layout.private_secretary_area_dialog_layout,
                    R.style.dialog);
            digArea.findViewById(R.id.btn_area_dialog_unlimited)
                    .setOnClickListener(this);
            lstPrivateSecretryArea = (ListView) digArea
                    .findViewById(R.id.lst_private_secretary_areacontent);
            privateSecretryAreaDialogListAdapter = new PrivateSecretryAreaDialogListAdapter(
                    NaierPrivateSecretaryActivity.this,
                    jsonSecretaryTpyeAndRegion.getData().getRegionList());
            lstPrivateSecretryArea
                    .setAdapter(privateSecretryAreaDialogListAdapter);
            digArea.show();
            lstPrivateSecretryArea
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent,
                                View view, int position, long id) {
                            SecretaryRegion temp = jsonSecretaryTpyeAndRegion
                                    .getData().getRegionList().get(position);
                            regionID = temp.getRegionID();
                            digArea.dismiss();
                            btnArea.setText(temp.getRegionName());
                            clearPage();
                            loadData(true, MSG_WHAT_0);
                        }
                    });
            break;
        case TYPE_INDUSTRY:
            digIndustry = new SelectDialog(NaierPrivateSecretaryActivity.this,
                    R.layout.private_secretary_industry_dialog_layout,
                    R.style.dialog);
            digIndustry.findViewById(R.id.btn_industry_dialog_unlimited)
                    .setOnClickListener(this);
            expdLstPrivateSecretryIndustry = (ExpandableListView) digIndustry
                    .findViewById(R.id.explst_private_secretary_industry_dialogcontent);
            privateSecretryIndustryDialogAdapter = new PrivateSecretryIndustryDialogAdapter(
                    NaierPrivateSecretaryActivity.this,
                    jsonSecretaryTpyeAndRegion.getData().getTypeList());
            expdLstPrivateSecretryIndustry
                    .setAdapter(privateSecretryIndustryDialogAdapter);
            digIndustry.show();
            expdLstPrivateSecretryIndustry
                    .setOnChildClickListener(new OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent,
                                View v, int groupPosition, int childPosition,
                                long id) {
                            SecretaryTpye typeTemp = jsonSecretaryTpyeAndRegion
                                    .getData().getTypeList().get(groupPosition)
                                    .getChildTypeList().get(childPosition);
                            typeID = typeTemp.getTypeID();
                            btnIndustry.setText(typeTemp.getTypeName());
                            digIndustry.dismiss();
                            clearPage();
                            loadData(true, MSG_WHAT_0);
                            return true;
                        }
                    });
            break;
        default:
            break;
        }
    }

    /**
     * Description:详细页面跳转
     * 
     * @param activity
     */
    public void gotoDetailActivity(Class<?> activity, int index) {
        Intent intent = new Intent(NaierPrivateSecretaryActivity.this, activity);
        intent.putExtra("id", secretariesList.get(index - 1).getSecretaryID());
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
        if (arg0 == lstInfor &&
        // Header
                index != 0 &&
                // Footer
                index != secretariesList.size() + 1) {
            gotoDetailActivity(NaierPrivateSecretaryDetailActivity.class, index);
        }
    }

    /*
     * (non-Javadoc)下拉刷新
     * 
     * @see
     * com.wwmi.naier.view.plistview.PListView.PListViewListener#onRefresh()
     */
    @Override
    public void onRefresh() {
        currentPage = 1;
        loadData(false, MSG_WHAT_3);
    }

    /*
     * (non-Javadoc)上拉
     * 
     * @see
     * com.wwmi.naier.view.plistview.PListView.PListViewListener#onLoadMore()
     */
    @Override
    public void onLoadMore() {
        if (currentPage < totalPage) {
            currentPage++;
            loadData(false, MSG_WHAT_4);
        } else {
            simpleToast("已加载全部数据");
            lstInfor.onLoadFinish();
        }
    }

    private void checkTotalPage() {

        if (currentPage >= totalPage) {
            lstInfor.setPullLoadEnable(false);
        } else {
            lstInfor.setPullLoadEnable(true);
        }
    }
}
