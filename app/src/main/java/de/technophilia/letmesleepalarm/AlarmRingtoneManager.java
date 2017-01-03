package de.technophilia.letmesleepalarm;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

/**
 * Created by alainsarti on 01/01/2017.
 */

public class AlarmRingtoneManager {
    private static AlarmRingtoneManager instance = null;
    private Context context;
    private Ringtone alarmRingtone;

    public AlarmRingtoneManager(Context context) {
        this.context = context;
    }

    public Uri getAlarmUri() {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    }

    public Ringtone getAlarmRingtone() {
        return RingtoneManager.getRingtone(context.getApplicationContext(), getAlarmUri());
    }

    public void playAlarm() {
        alarmRingtone = getAlarmRingtone();
        if(alarmRingtone != null) {
            setAlarmType();
            alarmRingtone.play();
        }
    }

    public void stopAlarm() {
        if(alarmRingtone != null && alarmRingtone.isPlaying()) {
            alarmRingtone.stop();
        }
    }

    public static synchronized AlarmRingtoneManager getInstance(Context context) {
        if(instance == null)
            instance = new AlarmRingtoneManager(context);
        return instance;
    }

    private void setAlarmType() {
        if(Build.VERSION.SDK_INT >= 21)
            alarmRingtone.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build());
        else
            alarmRingtone.setStreamType(AudioManager.STREAM_ALARM);
    }
}
