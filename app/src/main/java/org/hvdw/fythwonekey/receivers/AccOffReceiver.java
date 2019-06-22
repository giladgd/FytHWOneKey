package org.hvdw.fythwonekey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class AccOffReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-AccOffReceiver";
    //private boolean use_root_access;
    private boolean switch_wifi_off;
    private boolean pause_player;
    private String sys_call;

    Utils myUtils = new Utils();


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        //use_root_access = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.USE_ROOT_ACCESS, true);
        switch_wifi_off = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.SWITCH_WIFI_OFF, true);
        pause_player = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.PAUSE_PLAYER, true);
        sys_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.ACCOFF_SYSCALL_ENTRY, "");

        Log.d(TAG, "Detected an ACCOFF broadcast");

        if (switch_wifi_off == true) {
            Log.d(TAG, "Switch Off WiFi");
            WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            Log.d(TAG, "Switched Off WiFi");
        } else {
            Log.d(TAG, "It is not requested to switch off WiFi");
        }

        if (pause_player == true) {
            Log.d(TAG, "Pause the active media player");
            myUtils.shellExec("input keyevent 127");
        }

        if (!"".equals(sys_call)) {
            Log.d(TAG, "do a system call");
            myUtils.shellExec(sys_call);
        }
    }


}