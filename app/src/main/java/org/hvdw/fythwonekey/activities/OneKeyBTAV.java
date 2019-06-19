package org.hvdw.fythwonekey;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class OneKeyBTAV extends Activity {
    public static final String TAG = "OneKeyBTAV";
    public static Context mContext;
    private static PackageManager pm;
    private String packagename_call;
    private String intent_call;
    private String sys_call;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.d(TAG, "Started OneKeyBTAV; in OnCreate void");

        packagename_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_PACKAGENAME_ENTRY, "");
        intent_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_INTENT_ENTRY, "");
        sys_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_SYSCALL_ENTRY, "");

        //Toast mToast = Toast.makeText(OneKeyBTAV.this, "In On Create", Toast.LENGTH_LONG);
        //mToast.show();
        Utils myUtils = new Utils();

        if ("".equals(packagename_call)) {
            //packagename_call unknown, start setup
            Log.d(TAG, getString(R.string.pkg_notconfigured_short));
            mToast = Toast.makeText(OneKeyBTAV.this, getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
            mToast.show();
            myUtils.startActivityByPackageName(mContext, "org.hvdw.fythwonekey");
        } else {
            Log.d(TAG, getString(R.string.pkg_configured_short) + " " + packagename_call);
            //Start BTAV app or whatever app the user has configured
            myUtils.startActivityByPackageName(mContext, packagename_call);
        }

        finish();
    }

}
