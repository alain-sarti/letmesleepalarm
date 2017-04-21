package de.technophilia.letmesleepalarm.alarm;

import android.media.RingtoneManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by alainsarti on 21.04.17.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AlarmRingtoneManagerITest {
    private AlarmRingtoneManager ringtoneManager;

    @Before
    public void createRingtoneManager() {
        ringtoneManager = new AlarmRingtoneManager(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void getAlarmUri() {
        assertThat(ringtoneManager.getAlarmUri(), notNullValue());
    }

    @Test
    public void getAlarmRingtone() {
        assertThat(ringtoneManager.getAlarmRingtone(), notNullValue());
    }

    @Test
    public void checkIfAlarmIsPending() {
        assertThat(ringtoneManager.checkIfAlarmIsPending(), is(false));
    }
}
