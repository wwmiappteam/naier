package com.wwmi.naier.activity;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Custom;
import com.wwmi.naier.bean.JsonLogin;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.DataUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends NaierBaseActivity {

    private NaierApplication application;

    private EditText edtUsername;
    private EditText edtPassword;

    private String username;
    private String password;
    private JsonLogin jsonLogin;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            if (jsonLogin == null) {
                simpleToast("抱歉，登录失败，请稍后重试");
            } else if (!TextUtils.isEmpty(jsonLogin.getMsg())) {
                simpleToast(jsonLogin.getMsg());
            } else if (jsonLogin.getData() == null) {
                simpleToast("抱歉，登录失败，请稍后重试");
            } else {
                application.setCustom(jsonLogin.getData());
                DataUtil.updatePreferencesUser(LoginActivity.this, jsonLogin
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
        setContentView(R.layout.login);
        initTopBaseViews(getString(R.string.login_title), true, false, false,
                null);

        application = (NaierApplication) getApplication();

        Custom tustomTemp = DataUtil.getPreferencesUser(this);
        edtUsername = (EditText) findViewById(R.id.login_edt_username);
        edtUsername.setFilters(Constants.loginFilters);
        edtUsername.setText(tustomTemp.getUserName());

        edtPassword = (EditText) findViewById(R.id.login_edt_password);
        edtPassword.setFilters(Constants.loginFilters);
        edtPassword.setText(tustomTemp.getPassword());
    }

    public void clickButton(View v) {
        switch (v.getId()) {
        case R.id.login_btn_login:
            username = edtUsername.getText().toString().toString();
            password = edtPassword.getText().toString().toString();
            String invalidate = valueInvalidate();
            if (TextUtils.isEmpty(invalidate)) {
                startProgressDialog(Constants.LOADING);
                new Thread() {
                    @Override
                    public void run() {
                        jsonLogin = HTTPTool.login(username, password);
                        handler.sendEmptyMessage(0);
                    }
                }.start();
            } else {
                simpleToast(invalidate);
            }
            break;
        case R.id.login_btn_register:
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
            break;
        default:
            break;
        }
    }

    private String valueInvalidate() {
        StringBuilder sd = new StringBuilder();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            sd.append("账号和密码均不能为空");
        } else if (username.length() < 6 || password.length() < 6) {
            sd.append("账号或密码错误");
        }
        return sd.toString();
    }
}
