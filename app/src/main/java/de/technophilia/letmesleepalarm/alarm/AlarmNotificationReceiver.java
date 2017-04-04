package de.technophilia.letmesleepalarm.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import de.technophilia.letmesleepalarm.util.Settings;

/**
 * Created by alainsarti on 31/12/2016.
 * Waites for the Alarm
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        cancelAlarm(context);
        cancelNotification(context);

        Toast.makeText(context, "Alarm cancelled!", Toast.LENGTH_LONG).show();
    }

    private PendingIntent getAlarmPendingIntent(Context context) {
        Intent alarmIntent = new Intent(context, AlarmPlaySoundPublisher.class);
        return PendingIntent.getBroadcast(context.getApplicationContext(), Settings.ALARM_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void cancelAlarm(Context context) {
        PendingIntent alarmPendingIntent = getAlarmPendingIntent(context);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmPendingIntent);
        alarmPendingIntent.cancel();
    }

    private void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(Settings.NOTIFICATION_ID);
    }
}
