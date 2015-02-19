package com.catchingnow.tinyclipboardmanager;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

public class LaunchServiceAtStartup extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
//            boolean forceShowNotification = preference.getBoolean(ActivitySetting.NOTIFICATION_PIN, false);
//            CBWatcherService.startCBService(context, forceShowNotification);
            Log.v(ActivityMain.PACKAGE_NAME, "LaunchServiceAtStartup");
            CBWatcherService.startCBService(context, true);
            Storage db = Storage.getInstance(context);
            List<ClipObject> clipObjects = db.getClipHistory();
            if (clipObjects.size() > 0) {
                String clips = clipObjects.get(0).getText();
                ClipboardManager cb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cb.setText(clips);
            }
        }
    }
}
