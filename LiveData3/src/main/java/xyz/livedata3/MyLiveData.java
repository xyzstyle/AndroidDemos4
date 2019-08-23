package xyz.livedata3;

import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by xyz on 2019/8/18.
 * Project Name:AndroidDemos4
 */
public class MyLiveData extends LiveData<Integer> {
    private static final String TAG = "xyz:MyLiveData";
    private static MyLiveData sData;
    private WeakReference<Context> mContextWeakReference;

    static MyLiveData getInstance(Context context) {
        if (sData == null) {
            sData = new MyLiveData(context);
        }
        return sData;
    }

    private MyLiveData(Context context) {
        mContextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d(TAG, "onActive: ");
        registerReceiver();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onInactive: ");
        unregisterReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        mContextWeakReference.get().registerReceiver(mReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        mContextWeakReference.get().unregisterReceiver(mReceiver);
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action = " + action);
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifiManager != null) {
                    int wifiState = wifiManager.getWifiState();
                    sData.setValue(wifiState);
                }
            }
        }
    };
}
