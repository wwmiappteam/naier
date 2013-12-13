package com.wwmi.naier.view;

import com.wwmi.naier.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class NaierTextView extends TextView {

    public NaierTextView(Context context) {
        super(context);
    }

    public NaierTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            this.setTextColor(getResources().getColor(R.color.red));
            break;
        case MotionEvent.ACTION_UP:
            this.setTextColor(getResources().getColor(R.color.grey_white));
            break;
        default:
            break;
        }
        return super.onTouchEvent(event);
    }

}
