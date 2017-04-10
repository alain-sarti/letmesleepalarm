package de.technophilia.letmesleepalarm.alarm;

import android.app.NotificationManager;
import android.content.Context;

import de.technophilia.letmesleepalarm.util.Settings;

/**
 * Created by alainsarti on 10/04/2017.
 */

public class AlarmNotificationManager {
    public static void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(Settings.NOTIFICATION_ID);
    }
}
