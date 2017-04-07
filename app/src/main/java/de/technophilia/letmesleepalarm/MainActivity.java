package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
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
import de.technophilia.letmesleepalarm.util.IntentFactory;
import de.technophilia.letmesleepalarm.util.NotificationFactory;
import de.technophilia.letmesleepalarm.util.Settings;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.etHours)
    EditText etHours;

    @BindView(R.id.etOffset)
    EditText etOffset;

    @OnClick(R.id.btnAddAlarm)
    public void addAlarm() {
        scheduleNotification(calculateOffset(), calculateTime());
        scheduleAlarm(calculateOffset(), calculateTime());
    }

    @OnClick(R.id.btnAddJob)
    public void addJob() {
        scheduleNotification(calculateOffset(), calculateTime());
        scheduleJob(calculateOffset(), calculateTime());
    }

    @OnClick(R.id.btnTest)
    public void openTestActivity() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    private long calculateTime() {
        long time = 1000 * 60 * 60 * 8;
        if(!TextUtils.isEmpty(etHours.getText().toString())) {
            time = Long.parseLong(etHours.getText().toString()) * 1000 * 60 * 60;
        }

        return time;
    }

    private long calculateOffset() {
        long offset = 1000 * 60 * 15;
        if(!TextUtils.isEmpty(etOffset.getText().toString())) {
            offset = Long.parseLong(etOffset.getText().toString()) * 1000 * 60;
        }

        return offset;
    }

    private void scheduleNotification(long delay, long time) {
        Notification notification = NotificationFactory.buildNotification(getAlarmTime(delay, time), IntentFactory.prepareNotificationIntent(this), this);
        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).notify(Settings.NOTIFICATION_ID, notification);

        Toast.makeText(this, this.getString(R.string.toast_alarm_set, DateFormat.format("hh:mm", getAlarmTime(delay, time))), Toast.LENGTH_LONG).show();
    }

    private void scheduleAlarm(long delay, long time) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delay + time, IntentFactory.prepareAlarmIntent(this));
        } else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delay + time, IntentFactory.prepareAlarmIntent(this));
        }
    }

    private Date getAlarmTime(long delay, long time) {
        return new Date(System.currentTimeMillis() + delay + time);
    }

    private void scheduleJob(long offset, long time) {
        AlarmJob.scheduleJob(offset + time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
