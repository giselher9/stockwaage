package com.stockwaage.android.util;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {

    public static void makeToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
