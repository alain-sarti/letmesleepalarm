package de.technophilia.letmesleepalarm.alarm;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alainsarti on 17.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlarmRingtoneManagerTest {
    @Mock
    Context mockContext;

    @Test
    public void getAlarmUri() throws Exception {

    }

    @Test
    public void getAlarmRingtone() throws Exception {

    }

    @Test
    public void playAlarm() throws Exception {

    }

    @Test
    public void stopAlarm() throws Exception {

    }

    @Test
    public void getInstance() throws Exception {

    }

    @Test
    public void checkIfAlarmIsPending() throws Exception {
        AlarmRingtoneManager manager = new AlarmRingtoneManager(mockContext);
        assertThat(manager.checkIfAlarmIsPending(), is(false));
    }

}