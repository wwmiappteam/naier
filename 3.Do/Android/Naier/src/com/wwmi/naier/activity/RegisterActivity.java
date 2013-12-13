package com.wwmi.naier.activity;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.JsonLogin;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.DataUtil;
import com.wwmi.naier.view.RegisterItemView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

public class RegisterActivity extends NaierBaseActivity {

    private NaierApplication application;
    private RegisterItemView itemUsername;
    private RegisterItemView itemPassword;
    private RegisterItemView itemName;
    private RegisterItemView itemPhone;
    private RegisterItemView itemAddress;

    private String username;
    private String password;
    private String name;
    private String cellphone;
    private String address;

    private JsonLogin jsonLogin;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            if (jsonLogin == null) {
                simpleToast("抱歉，注册失败，请稍后重试");
            } else if (!TextUtils.isEmpty(jsonLogin.getMsg())) {
                simpleToast(jsonLogin.getMsg());
            } else if (jsonLogin.getData() == null) {
                simpleToast("抱歉，注册失败，请稍后重试");
            } else {
                application.setCustom(jsonLogin.getData());
                DataUtil.updatePreferencesUser(RegisterActivity.this, jsonLogin
                        .getData().getUserName(), jsonLogin.getData()
                        .getPassword());
                simpleToast("登录成功");
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initTopBaseViews(getString(R.string.register_title), true, false,
                false, null);
        application = (NaierApplication) getApplication();
        initviews();
    }

    private void initviews() {
        itemUsername = (RegisterItemView) findViewById(R.id.register_username);
        itemPassword = (RegisterItemView) findViewById(R.id.register_password);
        itemName = (RegisterItemView) findViewById(R.id.register_name);
        itemPhone = (RegisterItemView) findViewById(R.id.register_phone);
        itemAddress = (RegisterItemView) findViewById(R.id.register_address);

        itemUsername.setFactor(getString(R.string.register_username), "请输入账号",
                Constants.loginFilters, R.style.register_edt);
        itemPassword.setFactor(getString(R.string.register_password),
                "请输入6-20位密码", Constants.loginFilters, R.style.register_edt_pwd);
        itemName.setFactor(getString(R.string.register_name), "请输入姓名",
                new InputFilter[] { new InputFilter.LengthFilter(10) },
                R.style.register_edt);
        itemPhone.setFactor(getString(R.string.register_phone), "请输入手机号",
                new InputFilter[] { new InputFilter.LengthFilter(11) },
                R.style.register_edt);
        itemAddress.setFactor(getString(R.string.register_address),
                getString(R.string.register_address_hint),
                new InputFilter[] { new InputFilter.LengthFilter(50) },
                R.style.register_edt);

        itemPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void clickRegister(View v) {
        username = itemUsername.getValue();
        password = itemPassword.getValue();
        name = itemName.getValue();
        cellphone = itemPhone.getValue();
        address = itemAddress.getValue();

        if (checkEmpty()) {
            String invalidate = valueInvalidate();
            if (TextUtils.isEmpty(invalidate)) {
                startProgressDialog(Constants.LOADING);
                new Thread() {
                    @Override
                    public void run() {
                        jsonLogin = HTTPTool.register(username, password, name,
                                cellphone, address);
                        handler.sendEmptyMessage(0);
                    }
                }.start();
            } else {
                simpleToast(invalidate);
            }
        } else {
            simpleToast("抱歉，请填写完整后再提交");
        }
    }

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(name) || TextUtils.isEmpty(cellphone)
                || TextUtils.isEmpty(address)) {
            return false;
        }
        return true;
    }

    private String valueInvalidate() {
        StringBuilder sd = new StringBuilder();
        if (username.length() < 6) {
            sd.append("账号长度须大于6位");
        } else if (password.length() < 6) {
            sd.append("密码长度须大于6位");
        } else if (!DataUtil.isCellphone(cellphone)) {
            sd.append("您输入的手机号码格式不正确");
        }
        return sd.toString();
    }
}