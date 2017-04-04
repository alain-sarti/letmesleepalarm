package de.technophilia.letmesleepalarm.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

import de.technophilia.letmesleepalarm.R;

/**
 * Created by alainsarti on 04/04/2017.
 */

public class NotificationFactory {
    public static Notification buildNotification(Date time, PendingIntent pendingIntent, Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Let Me Sleep Notification");
        builder.setContentIntent(pendingIntent);
        builder.setContentText(String.format(context.getString(R.string.notification_alarm_time), DateFormat.format("hh:mm", time)));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}
