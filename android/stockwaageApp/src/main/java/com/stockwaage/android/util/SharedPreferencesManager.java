package com.stockwaage.android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";

    // <properties
    private static final String GCM_REGID = "GCM_GEGID";

    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getGcmRegId(Context context) {
        return getSharedPreferences(context).getString(GCM_REGID, null);
    }

    public static void setGcmRegId(Context context, String regId) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(GCM_REGID, regId);
        editor.commit();
    }

}
