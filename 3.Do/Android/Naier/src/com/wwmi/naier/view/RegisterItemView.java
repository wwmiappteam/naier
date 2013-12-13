package com.wwmi.naier.view;

import com.wwmi.naier.R;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterItemView extends LinearLayout {

    private static final LayoutParams LPW = new LayoutParams(0, LayoutParams.WRAP_CONTENT,2);
    private static final LayoutParams LPM = new LayoutParams(0, LayoutParams.MATCH_PARENT,5);

    private Context context;

    private TextView tvHead;
    private EditText edtValue;

    public RegisterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public RegisterItemView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.register_edt_bg));

        tvHead = new TextView(context);
        tvHead.setTextAppearance(context, R.style.register_head);
        tvHead.setGravity(Gravity.CENTER_HORIZONTAL);
        tvHead.getPaint().setFakeBoldText(true);

        edtValue = new EditText(context);
        edtValue.setBackgroundDrawable(null);
        edtValue.setSingleLine();

        this.addView(tvHead, LPW);
        this.addView(edtValue, LPM);
    }

    public void setFactor(String head, String hint, InputFilter[] filter,int valueStyle) {
        tvHead.setText(head);
        edtValue.setHint(hint);
        edtValue.setFilters(filter);
        edtValue.setTextAppearance(context, valueStyle);
    }

    public void setInputType(int type) {
        edtValue.setInputType(type);
    }

    public String getValue() {
        return edtValue.getText().toString().trim();
    }

    public void setValue(String value) {
        edtValue.setText(value);
    }
}
