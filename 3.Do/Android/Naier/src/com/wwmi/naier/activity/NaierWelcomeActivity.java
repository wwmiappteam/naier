/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.activity
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-14</br>
 */
package com.wwmi.naier.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wwmi.naier.R;

/**
 * WelcomeActivity summary:
 * 
 * @author ZhongSen</br> Description: TODO Create Time: 2013-11-14
 *         上午10:30:35</br> </br>History：</br> Editor **** Time **** Mantis No
 *         **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class NaierWelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naier_welcome_layout);
    }
}
