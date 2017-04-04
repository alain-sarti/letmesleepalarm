package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.technophilia.letmesleepalarm.alarm.AlarmNotificationReceiver;
import de.technophilia.letmesleepalarm.alarm.AlarmPlaySoundPublisher;
import de.technophilia.letmesleepalarm.job.AlarmJob;
import de.technophilia.letmesleepalarm.util.NotificationFactory;
import de.technophilia.letmesleepalarm.util.Settings;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.etHours)
    EditText etHours;

    @BindView(R.id.etOffset)
    EditText etOffset;

    @OnClick(R.id.btnAddAlarm)
    public void addAlarm() {
        scheduleNotification(getOffset(), getTime());
    }

    @OnClick(R.id.btnAddJob)
    public void addJob() {
        scheduleJob(getOffset(), getTime());
    }

    private long getTime() {
        long time = 1000 * 60 * 60 * 8;
        if(!TextUtils.isEmpty(etHours.getText().toString())) {
            time = Long.parseLong(etHours.getText().toString()) * 1000 * 60 * 60;
        }

        return time;
    }

    private long getOffset() {
        long offset = 1000 * 60 * 15;
        if(!TextUtils.isEmpty(etOffset.getText().toString())) {
            offset = Long.parseLong(etOffset.getText().toString()) * 1000 * 60;
        }

        return offset;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void scheduleNotification(long delay, long time) {
        PendingIntent notificationPendingIntent = prepareAlarmIntent(delay, time);
        Notification notification = NotificationFactory.buildNotification(getAlarmTime(delay, time), notificationPendingIntent, this);
        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).notify(Settings.NOTIFICATION_ID, notification);

        Toast.makeText(this, this.getString(R.string.toast_alarm_set, DateFormat.format("hh:mm", getAlarmTime(delay, time))), Toast.LENGTH_LONG).show();
    }

    private Date getAlarmTime(long delay, long time) {
        return new Date(System.currentTimeMillis() + delay + time);
    }

    private PendingIntent prepareAlarmIntent(long delay, long time) {
        Intent alarmIntent = new Intent(this, AlarmPlaySoundPublisher.class);
        Intent notificationIntent = new Intent(this, AlarmNotificationReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Settings.ALARM_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent notificationPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        setAlarm(SystemClock.elapsedRealtime() + delay + time, alarmPendingIntent);

        return notificationPendingIntent;
    }

    private void setAlarm(long futureInMillis, PendingIntent alarmPendingIntent) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, alarmPendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, alarmPendingIntent);
        }
    }

    private void scheduleJob(long offset, long time) {
        AlarmJob.scheduleJob(offset + time);
    }
}
