package de.technophilia.letmesleepalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by alainsarti on 31/12/2016.
 */

public class AlarmPlaySoundPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmRingtoneManager alarmRingtoneManager = AlarmRingtoneManager.getInstance(context);
        alarmRingtoneManager.playAlarm();
    }
}
