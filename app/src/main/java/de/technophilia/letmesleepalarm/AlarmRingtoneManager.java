package de.technophilia.letmesleepalarm;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

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
}
