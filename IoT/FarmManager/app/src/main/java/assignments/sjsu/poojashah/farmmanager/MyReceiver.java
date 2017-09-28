package assignments.sjsu.poojashah.farmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.Writer;
import java.util.Date;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Created by poojashah on 3/10/17.
 *
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundles = intent.getExtras();
        if(bundles != null) {
            double temp = bundles.getDouble("Temperature");
            double humidity = bundles.getDouble("Humidity");
            Intent broadcastIntent = null;

            if (temp > 90 && temp < 125) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fansprinkleron");
                Log.d("In MyReceiver", "temp > 90 && temp < 125 -> FAN ON");
                MainActivity.fanstat = context.getResources().getString(R.string.FanOn);
                MainActivity.sprinklerstat = context.getResources().getString(R.string.FanSprinklerOn);

            } else if (temp >= 70 && temp < 50) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fansprinkleron");
                Log.d("In MyReceiver", "temp > 70 && humidity >= 50 -> FAN ON");
                MainActivity.fanstat = context.getResources().getString(R.string.FanOn);
                MainActivity.sprinklerstat = context.getResources().getString(R.string.SprinklerOff);

            }  else {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fansprinkleroff");
                Log.d("In MyReceiver", "temp < 70 -> FAN and SPRINKLER OFF");
                MainActivity.fanstat = context.getResources().getString(R.string.FanOff);
                MainActivity.sprinklerstat = context.getResources().getString(R.string.FanSprinklerOff);

            }
            Toast.makeText(context, "Values Analyzed!", Toast.LENGTH_LONG).show();
            context.sendBroadcast(broadcastIntent);
        }

    }


}
