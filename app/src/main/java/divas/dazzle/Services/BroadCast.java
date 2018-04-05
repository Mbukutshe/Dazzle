package divas.dazzle.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Wiseman on 2018-03-25.
 */

public class BroadCast extends BroadcastReceiver {
    Sensor sensor;
    @Override
    public void onReceive(Context context, Intent intent) {
        sensor = new Sensor(context);
        context.startService(new Intent(context, Sensor.class));
    }
}
