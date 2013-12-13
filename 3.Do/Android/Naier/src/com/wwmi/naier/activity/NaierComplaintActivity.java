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

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.ComplaintKeepersAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Company;
import com.wwmi.naier.bean.Custom.Order;
import com.wwmi.naier.bean.JsonMsg;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.BitmapLoader;
import com.wwmi.naier.util.DialogUtil;
import com.wwmi.naier.view.SelectDialog;

/**
 * @author XiongWeimin
 * @Description
 * @revise
 * @time 2013-11-14 下午04:35:19
 * @version 1.0
 * @copyright Copyright @2013, Chengdu Tianfu Software Park Co., Ltd. All right.
 */
public class NaierComplaintActivity extends NaierBaseActivity {

    private NaierApplication application;

    private String keeperID;

    private ImageView imgPhoto;
    private TextView tvName;
    private EditText edtContent;
    private SelectDialog dialogKeepers;
    private ListView listviewKeepers;
    private List<Order> keeperList;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            switch (msg.what) {
            case 0:
                imgPhoto.setImageBitmap((Bitmap) msg.obj);
                break;
            case 1:
                if (msg.obj == null) {
                    simpleToast("提交失败，请稍后重试");
                } else if (!TextUtils.isEmpty(((JsonMsg) msg.obj).getMsg())) {
                    simpleToast(((JsonMsg) msg.obj).getMsg());
                } else {
                    new AlertDialog.Builder(NaierComplaintActivity.this)
                            .setMessage("您的投诉已提交，我们将尽快处理后给予您答复，感谢您使用奈儿家政。")
                            .setPositiveButton("确定", null).show();
                    clear();
                }
                break;
            default:
                break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_complaint_layout);
        application = (NaierApplication) getApplication();
        imgPhoto = (ImageView) findViewById(R.id.complaint_photo);
        tvName = (TextView) findViewById(R.id.complaint_name);
        edtContent = ((EditText) findViewById(R.id.complaint_edt_content));

        keeperID = getIntent().getStringExtra("keeper_id");
        if (TextUtils.isEmpty(keeperID)) {
            initTopBaseViews("监查投诉 ", false, true, false, R.drawable.call_telephone);
        } else {
            initTopBaseViews("监查投诉 ", true, true, false, R.drawable.call_telephone);
            tvName.setText(getIntent().getStringExtra("keeper_name"));
            new BitmapThread(getIntent().getStringExtra("keeper_photo"))
                    .start();
        }

        addMenuClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogUtil.createMakePhonecallDialog(
                        NaierComplaintActivity.this, application.getCompany()
                                .getComplain_tel());
            }
        });
    }

    public void submit(View v) {
        final String content = edtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            simpleToast("请输入您的投诉内容");
        } else if (TextUtils.isEmpty(keeperID)) {
            simpleToast("请选择您要投诉的家政人员");
        } else {
            startProgressDialog(Constants.LOADING);
            new Thread() {
                @Override
                public void run() {
                    JsonMsg msg = HTTPTool.complainAdd(application.getCustom()
                            .getCustomID(), keeperID, content);
                    handler.sendMessage(handler.obtainMessage(1, msg));
                }
            }.start();
        }
    }

    private void clear() {
        imgPhoto.setImageResource(R.drawable.keeper_photo_default);
        tvName.setText("");
        edtContent.setText("");
        keeperID = "";
    }

    public void chooseKeeper(View v) {
        if (application.getCustom() == null) {
            startActivity(new Intent(NaierComplaintActivity.this,
                    LoginActivity.class));
        } else {
            keeperList = new ArrayList<Order>(application.getCustom()
                    .getOrderList());
            if (keeperList == null || keeperList.size() == 0) {
                final Company company = application.getCompany();
                if (company != null) {
                    new AlertDialog.Builder(this)
                            .setCancelable(false)
                            .setIcon(R.drawable.icon_dialog)
                            .setMessage(
                                    "您还没有预约过任何家政人员，若对我们的服务有任何建议，可直接拨打电话：\n"
                                            + company.getAdvise_tel()
                                            + "\n是否拨打？")
                            .setPositiveButton("是", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    Uri call = Uri.parse("tel:"
                                            + company.getAdvise_tel());
                                    Intent intentAftermarket = new Intent(
                                            Intent.ACTION_CALL, call);
                                    startActivity(intentAftermarket);
                                }
                            }).setNegativeButton("否", null).show();
                } else {
                    simpleToast("您还没有预约过任何家政人员");
                }
                return;
            }
            // 姓名和分类同时相同时,去重
            for (int i = 0; i < keeperList.size() - 1; i++) {
                for (int j = keeperList.size() - 1; j > i; j--) {
                    if (keeperList.get(j).getKeeperName()
                            .equals(keeperList.get(i).getKeeperName())
                            && keeperList
                                    .get(j)
                                    .getKeeperTypeDescription()
                                    .equals(keeperList.get(i)
                                            .getKeeperTypeDescription())) {
                        keeperList.remove(j);
                    }
                }
            }
            dialogKeepers = new SelectDialog(this,
                    R.layout.private_secretary_area_dialog_layout,
                    R.style.dialog);
            ((TextView) dialogKeepers.findViewById(R.id.dialog_title))
                    .setText("请选择");
            dialogKeepers.findViewById(R.id.btn_area_dialog_unlimited)
                    .setVisibility(View.GONE);
            listviewKeepers = (ListView) dialogKeepers
                    .findViewById(R.id.lst_private_secretary_areacontent);
            ComplaintKeepersAdapter adapter = new ComplaintKeepersAdapter(this,
                    keeperList);
            listviewKeepers.setAdapter(adapter);
            dialogKeepers.show();
            listviewKeepers.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    final Order orderItem = keeperList.get(position);
                    keeperID = orderItem.getKeeperID();
                    dialogKeepers.dismiss();
                    tvName.setText(orderItem.getKeeperName());
                    imgPhoto.setImageResource(R.drawable.keeper_photo_default);
                    startProgressDialog(Constants.LOADING);
                    new BitmapThread(orderItem.getKeeperPhoto()).start();
                }
            });
        }
    }

    private class BitmapThread extends Thread {
        private String url;

        public BitmapThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            Bitmap photo = BitmapLoader.downloadBitmap(url);
            handler.sendMessage(handler.obtainMessage(0, photo));
        }

    }
}
