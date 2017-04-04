package de.technophilia.letmesleepalarm.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import de.technophilia.letmesleepalarm.job.AlarmJob;

/**
 * Created by alainsarti on 29/03/2017.
 */

public class AlarmJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case AlarmJob.TAG:
                return new AlarmJob();
            default:
                return null;
        }
    }
}
