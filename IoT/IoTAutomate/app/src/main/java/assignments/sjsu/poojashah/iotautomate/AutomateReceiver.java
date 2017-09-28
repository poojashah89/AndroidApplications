package assignments.sjsu.poojashah.iotautomate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by poojashah on 3/13/17.
 */

public class AutomateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "In Automate Receiver", Toast.LENGTH_SHORT);

        Intent newintent = new Intent(context, MainActivity.class);
        if (intent.getAction().equals("com.assignments.sjsu.poojashah.fanon")) {
            MainActivity.fanautstat = context.getResources().getString(R.string.FanOn);
            MainActivity.sprinklerautstat = context.getResources().getString(R.string.SprinklerOff);
            Log.d("In automate receiver", "FanOn received!");
        }

        else if (intent.getAction().equals("com.assignments.sjsu.poojashah.fansprinkleron")) {
            MainActivity.fanautstat = context.getResources().getString(R.string.FanOn);
            MainActivity.sprinklerautstat = context.getResources().getString(R.string.SprinklerOn);
            Log.d("In automate receiver", "FanOn and SprinkerlerOn received!");
        }

        else if (intent.getAction().equals("com.assignments.sjsu.poojashah.fanoff")) {
            MainActivity.fanautstat = context.getResources().getString(R.string.FanOff);
            MainActivity.sprinklerautstat = context.getResources().getString(R.string.SprinklerOn);
            Log.d("In automate receiver", "FanOff received!");
        }

        else if (intent.getAction().equals("com.assignments.sjsu.poojashah.fansprinkleroff")) {
            MainActivity.fanautstat = context.getResources().getString(R.string.FanOff);
            MainActivity.sprinklerautstat = context.getResources().getString(R.string.SprinklerOff);
            Log.d("In automate receiver", "FanOff and SprinkerlerOff received!");
        }
        newintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(newintent);
    }
}