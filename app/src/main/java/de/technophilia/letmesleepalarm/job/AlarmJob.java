package de.technophilia.letmesleepalarm.job;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import de.technophilia.letmesleepalarm.alarm.AlarmRingtoneManager;
import de.technophilia.letmesleepalarm.StopActivity;

/**
 * Created by alainsarti on 29/03/2017.
 */

public class AlarmJob extends Job {
    public static final String TAG = "alarm_job_tag";

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        playAlarm();
        showStopActivity();
        return Result.SUCCESS;
    }

    public static void scheduleJob(long sleepTime) {
        new JobRequest.Builder(AlarmJob.TAG)
                .setExact(sleepTime)
                .build()
                .schedule();
    }

    private void showStopActivity() {
        Intent intent = new Intent(getContext(), StopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

    private void playAlarm() {
        AlarmRingtoneManager alarmRingtoneManager = AlarmRingtoneManager.getInstance(getContext());
        alarmRingtoneManager.playAlarm();
    }
}
