package com.wwmi.naier.activity;

import com.wwmi.naier.R;
import com.wwmi.naier.adapter.OrderlistAdapter;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Custom;
import com.wwmi.naier.bean.JsonMsg;
import com.wwmi.naier.bean.Custom.Order;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.DataUtil;
import com.wwmi.naier.view.SelectDialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PersonalActivity extends NaierBaseActivity {

    private NaierApplication application;

    private Custom custom;
    private ListView listOrder;

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;

    private SelectDialog dialogComplaint;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            if (msg.obj == null) {
                simpleToast("修改失败，请稍后重试");
            } else if (!TextUtils.isEmpty(((JsonMsg) msg.obj).getMsg())) {
                simpleToast(((JsonMsg) msg.obj).getMsg());
            } else {
                simpleToast("修改成功");
                update();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        initTopBaseViews(getString(R.string.personal_title), true, false,
                false, null);
        application = (NaierApplication) getApplication();
        initviews();
    }

    @Override
    protected void onResume() {
        update();
        super.onResume();
    }

    private void initviews() {
        tvName = (TextView) findViewById(R.id.personal_name);
        tvPhone = (TextView) findViewById(R.id.personal_phone);
        tvAddress = (TextView) findViewById(R.id.personal_address);

    }

    private void update() {
        custom = application.getCustom();
        tvName.setText(Html.fromHtml(String.format(
                getString(R.string.personal_name_text), custom.getName())));
        tvPhone.setText(String.format(getString(R.string.personal_phone_text),
                custom.getCellphone()));
        tvAddress
                .setText(String.format(
                        getString(R.string.personal_address_text),
                        custom.getAddress()));
        if (custom.getOrderList() == null || custom.getOrderList().size() == 0) {
            findViewById(R.id.personal_no_orderlist)
                    .setVisibility(View.VISIBLE);
            findViewById(R.id.personal_lly_orderlist).setVisibility(View.GONE);
        } else {
            findViewById(R.id.personal_no_orderlist).setVisibility(View.GONE);
            findViewById(R.id.personal_lly_orderlist).setVisibility(
                    View.VISIBLE);
            listOrder = (ListView) findViewById(R.id.personal_lst_orders);
            OrderlistAdapter adapter = new OrderlistAdapter(
                    custom.getOrderList(), this);
            listOrder.setAdapter(adapter);
            listOrder.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    final Order orderItem = custom.getOrderList().get(position);
                    dialogComplaint = new SelectDialog(PersonalActivity.this,
                            R.layout.personal_selector_dialog, R.style.dialog);
                    dialogComplaint.show();
                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                            case R.id.personal_dialog_btn_complaint:
                                dialogComplaint.dismiss();
                                Intent intent = new Intent(PersonalActivity.this,
                                        NaierComplaintActivity.class);
                                intent.putExtra("keeper_id", orderItem.getKeeperID());
                                intent.putExtra("keeper_name", orderItem.getKeeperName());
                                intent.putExtra("keeper_photo", orderItem.getKeeperPhoto());
                                startActivity(intent);
                                break;
                            case R.id.personal_dialog_btn_cancel:
                                dialogComplaint.dismiss();
                                break;
                            default:
                                break;
                            }
                        }
                    };
                    dialogComplaint.findViewById(
                            R.id.personal_dialog_btn_complaint)
                            .setOnClickListener(listener);
                    dialogComplaint.findViewById(
                            R.id.personal_dialog_btn_cancel)
                            .setOnClickListener(listener);
                }
            });
        }
    }

    public void clickBtn(View v) {
        switch (v.getId()) {
        case R.id.personal_name:
            doChangePassword();
            break;
        case R.id.personal_phone:
            doChangePhone();
            break;
        case R.id.personal_address:
            doChangeAddress();
            break;

        default:
            break;
        }
    }

    private void doChangePassword() {
        final View changePasswordView = LayoutInflater.from(this).inflate(
                R.layout.dialog_changepassword, null);
        final EditText etOldPassword = (EditText) changePasswordView
                .findViewById(R.id.menu_et_oldpassword);
        final EditText etNewPassword = (EditText) changePasswordView
                .findViewById(R.id.menu_et_newpassword);
        etOldPassword.setFilters(Constants.loginFilters);
        etNewPassword.setFilters(Constants.loginFilters);
        new AlertDialog.Builder(this).setCancelable(false).setTitle("修改密码")
                .setIcon(R.drawable.icon_dialog).setView(changePasswordView)
                .setPositiveButton("修改", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String oldPassword = etOldPassword.getText().toString().trim();
                        final String newPassword = etNewPassword.getText().toString().trim();
                        if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword)) {
                            simpleToast("您的输入不正确");
                        } else if (oldPassword.length() < 6) {
                            simpleToast("旧密码有误，修改失败");
                        } else
                        // 检查新密码长度是否大于6位
                        if (newPassword.length() >= 6) {
                            startProgressDialog(Constants.LOADING);
                            new Thread(){
                                @Override
                                public void run() {
                                    JsonMsg msg = HTTPTool.userinfoModify(custom.getCustomID(), "", "", oldPassword, newPassword);
                                    if (msg != null && TextUtils.isEmpty(msg.getMsg())) {
                                        application.getCustom().setPassword(
                                                newPassword);
                                        DataUtil.updatePreferencesUser(
                                                PersonalActivity.this,
                                                custom.getUserName(),
                                                newPassword);
                                    }
                                    handler.sendMessage(handler.obtainMessage(0, msg));
                                }
                            }.start();
                        } else {
                            simpleToast("新密码长度必须大于6位！");
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }

    private void doChangePhone() {
        final View changePhoneView = LayoutInflater.from(this).inflate(
                R.layout.dialog_changephone, null);
        new AlertDialog.Builder(this).setCancelable(false).setTitle("修改手机")
                .setIcon(R.drawable.icon_dialog).setView(changePhoneView)
                .setPositiveButton("修改", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etPhone = (EditText) changePhoneView
                                .findViewById(R.id.dialog_et_phone);
                        final String phone = etPhone.getText().toString().trim();
                        if (TextUtils.isEmpty(phone)) {
                            simpleToast("您的输入不正确");
                        } else if (!DataUtil.isCellphone(phone)) {
                            simpleToast("您输入的手机号码格式不正确");
                        } else {
                            startProgressDialog(Constants.LOADING);
                            new Thread(){
                                @Override
                                public void run() {
                                    JsonMsg msg = HTTPTool.userinfoModify(custom.getCustomID(), phone, "", "", "");
                                    if (msg != null && TextUtils.isEmpty(msg.getMsg())) {
                                        application.getCustom().setCellphone(phone);
                                    }
                                    handler.sendMessage(handler.obtainMessage(0, msg));
                                }
                            }.start();
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }

    private void doChangeAddress() {
        final View changeAddressView = LayoutInflater.from(this).inflate(
                R.layout.dialog_changeaddress, null);
        final EditText etAddress = (EditText) changeAddressView
                .findViewById(R.id.dialog_et_address);
        etAddress.setText(custom.getAddress());
        new AlertDialog.Builder(this).setCancelable(false).setTitle("修改地址")
                .setIcon(R.drawable.icon_dialog).setView(changeAddressView)
                .setPositiveButton("修改", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String address = etAddress.getText().toString().trim();
                        if (TextUtils.isEmpty(address)) {
                            simpleToast("您的输入不正确");
                        } else {
                            startProgressDialog(Constants.LOADING);
                            new Thread(){
                                @Override
                                public void run() {
                                    JsonMsg msg = HTTPTool.userinfoModify(custom.getCustomID(), "", address, "", "");
                                    if (msg != null && TextUtils.isEmpty(msg.getMsg())) {
                                        application.getCustom().setAddress(address);
                                    }
                                    handler.sendMessage(handler.obtainMessage(0, msg));
                                }
                            }.start();
                        }
                    }
                }).setNegativeButton("取消", null).show();
    }
}
