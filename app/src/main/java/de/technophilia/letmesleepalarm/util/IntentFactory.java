package de.technophilia.letmesleepalarm.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import de.technophilia.letmesleepalarm.alarm.AlarmNotificationReceiver;
import de.technophilia.letmesleepalarm.alarm.AlarmPlaySoundPublisher;

/**
 * Created by alainsarti on 04/04/2017.
 */

public class IntentFactory {

    public static PendingIntent prepareAlarmIntent(Context context) {
        Intent alarmIntent = new Intent(context, AlarmPlaySoundPublisher.class);
        return PendingIntent.getBroadcast(context.getApplicationContext(), Settings.ALARM_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent prepareNotificationIntent(Context context) {
        Intent notificationIntent = new Intent(context, AlarmNotificationReceiver.class);
        return PendingIntent.getBroadcast(context.getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
