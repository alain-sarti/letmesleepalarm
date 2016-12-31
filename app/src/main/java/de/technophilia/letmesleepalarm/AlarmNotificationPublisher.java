package de.technophilia.letmesleepalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by alainsarti on 31/12/2016.
 * Waites for the Alarm
 */

public class AlarmNotificationPublisher extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "letmesleep-id";
    public static String NOTIFICATION = "letmesleep";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);
    }
}
