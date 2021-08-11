package com.istiaksaif.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            String toastMessage = context.getString(R.string.unknown_action);
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = context.getString(R.string.power_connected);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = context.getString(R.string.power_disconnected);
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    int state = intent.getIntExtra("state", -1);
                    switch (state) {
                        case 0:
                            toastMessage = context.getString(R.string.head_set_disconnect);
                            break;
                        case 1:
                            toastMessage = context.getString(R.string.head_set_connect);
                            break;
                    }
                    break;
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
