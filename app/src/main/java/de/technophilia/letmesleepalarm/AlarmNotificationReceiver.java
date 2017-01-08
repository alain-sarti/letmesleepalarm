package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by alainsarti on 31/12/2016.
 * Waites for the Alarm
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, AlarmPlaySoundPublisher.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), Settings.ALARM_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmPendingIntent);
        alarmPendingIntent.cancel();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(Settings.NOTIFICATION_ID);

        Toast.makeText(context, "Alarm cancelled!", Toast.LENGTH_LONG).show();
    }
}
