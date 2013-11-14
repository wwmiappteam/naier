/*
 * Copyright © 2009-2013 zhong.sen96@gmail.com</br>
 * Project: Naier
 * Package: com.wwmi.naier.application
 * Version: 1.0</br>
 * Android SDK: min sdk level: 7</br>
 * Create Time: 2013-11-14</br>
 */
package com.wwmi.naier.application;

import android.app.Application;

/**
 * NaierApplication summary:
 * 
 * @author ZhongSen</br> Description: TODO Create Time: 2013-11-14
 *         上午10:29:42</br> </br>History：</br> Editor **** Time **** Mantis No
 *         **** Operation **** Description ***
 *** 
 *** 
 *** 
 *** 
 */
public class NaierApplication extends Application {
    // TODO
    /**
     * 网络是否可用
     */
    private boolean networkAvailable;

    public boolean isNetworkAvailable() {
        return networkAvailable;
    }

    public void setNetworkAvailable(boolean networkAvailable) {
        this.networkAvailable = networkAvailable;
    }
}
