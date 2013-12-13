package com.wwmi.naier.activity;

import com.wwmi.naier.R;
import com.wwmi.naier.application.NaierApplication;
import com.wwmi.naier.bean.Custom;
import com.wwmi.naier.bean.JsonMsg;
import com.wwmi.naier.common.Constants;
import com.wwmi.naier.connection.HTTPTool;
import com.wwmi.naier.util.DataUtil;
import com.wwmi.naier.view.RegisterItemView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class FeedbackActivity extends NaierBaseActivity {

    private NaierApplication application;
    private EditText edtContent;
    private RegisterItemView itemPhone;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            stopProgressDialog();
            if ((JsonMsg) msg.obj == null) {
                simpleToast("提交失败，请稍后重试");
            } else if (!TextUtils.isEmpty(((JsonMsg) msg.obj).getMsg())) {
                simpleToast(((JsonMsg) msg.obj).getMsg());
            } else {
                new AlertDialog.Builder(FeedbackActivity.this)
                        .setMessage("感谢您对奈儿家政提出的宝贵意见，我们将继续努力为您提供最优质的服务！")
                        .setPositiveButton("确定", null).show();
                clear();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        initTopBaseViews("意见反馈", true, false, false, null);

        initviews();
    }

    private void clear() {
        edtContent.setText("");
        itemPhone.setValue("");
    }

    private void initviews() {
        application = (NaierApplication) getApplication();
        edtContent = (EditText) findViewById(R.id.feedback_edt_content);
        itemPhone = (RegisterItemView) findViewById(R.id.feedback_phone);
        itemPhone.setFactor("电 话", "请输入手机号",
                new InputFilter[] { new InputFilter.LengthFilter(11) },
                R.style.register_edt);
        itemPhone.setInputType(InputType.TYPE_CLASS_NUMBER);

        if (application.getCustom() != null) {
            itemPhone.setValue(application.getCustom().getCellphone());
        }
    }

    public void submit(View v) {
        final String content = edtContent.getText().toString().trim();
        final String phone = itemPhone.getValue().toString().trim();
        if (TextUtils.isEmpty(content)) {
            simpleToast("请输入您宝贵的意见");
        } else if (TextUtils.isEmpty(phone)) {
            simpleToast("请输入您的手机号码，以便我们可以联系到您");
        } else if (!DataUtil.isCellphone(phone)) {
            simpleToast("您输入的手机号码格式不正确");
        } else {
            startProgressDialog(Constants.LOADING);
            new Thread() {
                @Override
                public void run() {
                    Custom custom = application.getCustom();
                    final String customID = custom == null ? "" : custom
                            .getCustomID();
                    JsonMsg msg = HTTPTool.adviseAdd(customID, phone, content);
                    handler.sendMessage(handler.obtainMessage(0, msg));
                }
            }.start();
        }
    }
}
