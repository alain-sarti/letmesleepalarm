package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnAddAlarm)
    Button btnAddAlarm;

    private static long OFFSET = 1000 * 30; // 1000 * 60 * 15
    private static long TIME = 1000 * 30; // 1000 * 60 * 60 * 8

    @OnClick(R.id.btnAddAlarm)
    public void addAlarm() {
        scheduleNotification(getNotification("WAKE UP!!!!"), OFFSET, TIME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void scheduleNotification(Notification notification, long delay, long time) {
        // Intent notificationIntent = new Intent(this, AlarmNotificationPublisher.class);
        Intent notificationIntent = new Intent(this, AlarmPlaySoundPublisher.class);
        notificationIntent.putExtra(AlarmNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay + time;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Let Me Sleep Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}
