package de.technophilia.letmesleepalarm.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.technophilia.letmesleepalarm.StopActivity;

/**
 * Created by alainsarti on 31/12/2016.
 */

public class AlarmPlaySoundPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        playAlarm(context);
        showStopActivity(context);
    }

    private void showStopActivity(Context context) {
        Intent intent = new Intent(context, StopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void playAlarm(Context context) {
        AlarmRingtoneManager alarmRingtoneManager = AlarmRingtoneManager.getInstance(context);
        alarmRingtoneManager.playAlarm();
    }
}
