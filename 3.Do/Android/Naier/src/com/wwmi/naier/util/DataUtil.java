package com.wwmi.naier.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wwmi.naier.bean.Custom;
import com.wwmi.naier.common.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DataUtil {

    public static boolean isCellphone(String mobiles) {
        Pattern p = Pattern.compile("^1[358]{1}[0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    
    public static boolean updatePreferencesUser(Context context, String username, String password) {

        SharedPreferences sharedPreferences =
                context.getSharedPreferences(Constants.PREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
        return true;
    }
    
    public static Custom getPreferencesUser(Context context) {
        SharedPreferences prf = context.getSharedPreferences(
                Constants.PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return new Custom("", "", "", "", null, prf.getString("password", ""),
                prf.getString("username", ""));
    }
}
