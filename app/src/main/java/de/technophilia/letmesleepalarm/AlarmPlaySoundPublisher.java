package de.technophilia.letmesleepalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by alainsarti on 31/12/2016.
 */

public class AlarmPlaySoundPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(notification != null) {
            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
            r.play();
        }
    }
}
