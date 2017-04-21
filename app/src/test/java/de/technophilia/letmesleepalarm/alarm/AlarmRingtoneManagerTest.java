package de.technophilia.letmesleepalarm.alarm;

import android.app.PendingIntent;
import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by alainsarti on 17.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlarmRingtoneManagerTest {
    @Mock
    Context mockContext;
    @Mock
    PendingIntent pendingIntent;

    @Test
    public void getInstance() throws Exception {
        AlarmRingtoneManager manager = AlarmRingtoneManager.getInstance(mockContext);
        assertThat(manager, notNullValue());
    }

    @Test
    public void checkIfAlarmIsPending() throws Exception {
//        AlarmRingtoneManager manager = new AlarmRingtoneManager(mockContext);
//        when(PendingIntent.getBroadcast(mockContext, 0, new Intent(mockContext, AlarmPlaySoundPublisher.class), PendingIntent.FLAG_NO_CREATE)).thenReturn(null);
//        assertThat(manager.checkIfAlarmIsPending(), is(false));
    }

}