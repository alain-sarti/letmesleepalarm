package de.technophilia.letmesleepalarm;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

/**
 * Created by alainsarti on 29/03/2017.
 */

public class AlarmJob extends Job {
    public static final String TAG = "alarm_job_tag";

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        //TODO: play Alarm
        return Result.SUCCESS;
    }

    public static void scheduleJob(long sleepTime) {
        new JobRequest.Builder(AlarmJob.TAG)
                .setExact(sleepTime)
                .build()
                .schedule();
    }
}
