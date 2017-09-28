package assignments.sjsu.poojashah.farmmanager;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver = null;

    public static String fanstat, sprinklerstat ;
    private Button fanStatus, sprinklerStatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanStatus = (Button) findViewById(R.id.fan);
        sprinklerStatus = (Button) findViewById(R.id.fansprinkler);

        IntentFilter intentFilter = new IntentFilter("com.assignments.sjsu.poojashah.temphumidity");
        receiver = new MyReceiver();
        this.registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fanstat != null) {
            fanStatus.setText(fanstat);
        } if(sprinklerstat != null) {
            sprinklerStatus.setText(sprinklerstat);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void closeApp(View view) {
        MainActivity.this.finish();
    }

    public void turnFan(View view) {
        Intent broadcastIntent = null;
        if(fanstat != null) {
            if (fanstat.equals(getString(R.string.FanOn))) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fanoff");
                Toast.makeText(this, "Fan off", Toast.LENGTH_SHORT).show();
                fanstat = getString(R.string.FanOff);

            } else if (fanstat.equals(getString(R.string.FanOff))) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fanon");
                fanstat = getString(R.string.FanOn);
                Toast.makeText(this, "Fan On", Toast.LENGTH_SHORT).show();
            }
            sendBroadcast(broadcastIntent);
        }
    }

    public void turnFanSprinkler(View view) {

        Intent broadcastIntent = null;
        if(sprinklerstat != null) {
            if (sprinklerstat.equals(getString(R.string.FanSprinklerOn))) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fansprinkleroff");
                Toast.makeText(this, "Fan and Sprinkler on", Toast.LENGTH_SHORT).show();
                sprinklerstat = getString(R.string.FanSprinklerOff);
            } else if (sprinklerstat.equals(getString(R.string.FanSprinklerOff))) {
                broadcastIntent = new Intent("com.assignments.sjsu.poojashah.fansprinkleron");
                Toast.makeText(this, "Fan and Sprinkler off", Toast.LENGTH_SHORT).show();
                sprinklerstat = getString(R.string.FanSprinklerOn);
            }
            sendBroadcast(broadcastIntent);
        }
    }
}
