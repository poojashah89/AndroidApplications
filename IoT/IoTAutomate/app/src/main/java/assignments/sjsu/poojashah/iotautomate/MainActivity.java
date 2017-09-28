package assignments.sjsu.poojashah.iotautomate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.IntentFilter;
import android.widget.Button;

import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private Button fanStatus = null;
    private Button sprinklerStatus = null;
    public static String fanautstat ="";
    public static String sprinklerautstat = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanStatus = (Button) findViewById(R.id.fanstatus);
        sprinklerStatus = (Button) findViewById(R.id.sprinklerstatus);

        if(fanautstat == "" && sprinklerautstat == "") {
            fanautstat = "Fan Status ?";
            sprinklerautstat = "Sprinkler Status ?";
        }
        fanStatus.setText(fanautstat);
        sprinklerStatus.setText(sprinklerautstat);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
