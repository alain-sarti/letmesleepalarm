package de.technophilia.letmesleepalarm;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.technophilia.letmesleepalarm.alarm.AlarmNotificationManager;
import de.technophilia.letmesleepalarm.alarm.AlarmRingtoneManager;
import de.technophilia.letmesleepalarm.util.IntentFactory;

public class StopActivity extends AppCompatActivity {

    @OnClick(R.id.ivStopAlarm)
    public void stopAlarm() {
        dismissAlarm();
        AlarmNotificationManager.cancelNotification(this);

        this.finish();
    }

    @OnClick(R.id.ivSnoozeAlarm)
    public void snoozeAlarm() {
        dismissAlarm();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int snooze = Integer.parseInt(sharedPref.getString("snooze", "15")) * 60 * 1000;

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if(Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + snooze, IntentFactory.prepareAlarmIntent(this));
        } else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + snooze, IntentFactory.prepareAlarmIntent(this));
        }

        this.finish();
    }

    private void dismissAlarm() {
        AlarmRingtoneManager alarmRingtoneManager = AlarmRingtoneManager.getInstance(getApplicationContext());
        alarmRingtoneManager.stopAlarm();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }
}
