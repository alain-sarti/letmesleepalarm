package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnAddAlarm)
    Button btnAddAlarm;

    @BindView(R.id.etHours)
    EditText etHours;

    @BindView(R.id.etOffset)
    EditText etOffset;

    private static long OFFSET = 1000 * 60 * 15;
    private static long TIME = 1000 * 60 * 60 * 8;

    @OnClick(R.id.btnAddAlarm)
    public void addAlarm() {
        long time = TIME;
        long offset = OFFSET;
        if(!TextUtils.isEmpty(etHours.getText().toString())) {
            time = Long.parseLong(etHours.getText().toString()) * 1000 * 60 * 60;
        }
        if(!TextUtils.isEmpty(etOffset.getText().toString())) {
            offset = Long.parseLong(etOffset.getText().toString()) * 1000 * 60;
        }
        scheduleNotification(offset, time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void scheduleNotification(long delay, long time) {
        Intent notificationIntent = new Intent(this, AlarmPlaySoundPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay + time;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        Notification notification = getNotification(String.format(getString(R.string.notification_alarm_time), DateFormat.format("hh:mm", new Date(futureInMillis))), pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

        Toast.makeText(this, R.string.toast_alarm_set, Toast.LENGTH_LONG).show();
    }

    private Notification getNotification(String content, PendingIntent pendingIntent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Let Me Sleep Notification");
//        builder.setContentIntent(pendingIntent);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}
