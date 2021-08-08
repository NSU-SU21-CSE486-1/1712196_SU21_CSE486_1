package com.istiaksaif.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver = new CustomReceiver();

//    private static final String ACTION_CUSTOM_BROADCAST =
//            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        IntentFilter filter1 = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(mReceiver, filter1);

        this.registerReceiver(mReceiver, filter);

        // Register the receiver to receive custom broadcast.
//        LocalBroadcastManager.getInstance(this).registerReceiver
//                (mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    public void sendCustomBroadcast(View view) {
//        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}