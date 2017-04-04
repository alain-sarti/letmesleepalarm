package de.technophilia.letmesleepalarm;

import android.app.Application;

import com.evernote.android.job.JobManager;

import de.technophilia.letmesleepalarm.job.AlarmJobCreator;

/**
 * Created by alainsarti on 29/03/2017.
 */

public class LetmesleepAlarmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new AlarmJobCreator());
    }
}
