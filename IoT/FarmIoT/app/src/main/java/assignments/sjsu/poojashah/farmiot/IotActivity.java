package assignments.sjsu.poojashah.farmiot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IotActivity extends AppCompatActivity {

    private EditText textTemperature, textHumidity;
    double tempVal, humidityVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);

        textTemperature = (EditText) findViewById(R.id.tempText);
        textHumidity = (EditText) findViewById(R.id.humidityText);

    }

    public void broadcastIntent(View view){
        tempVal = Double.parseDouble(textTemperature.getText().toString());
        humidityVal = Double.parseDouble(textHumidity.getText().toString());

        Intent broadcastValues = new Intent();
        broadcastValues.setAction("com.assignments.sjsu.poojashah.temphumidity");
        broadcastValues.putExtra("Temperature", tempVal); //Broadcasting temperature Value
        broadcastValues.putExtra("Humidity", humidityVal);//Broadcasting Humidity Value
        sendBroadcast(broadcastValues);
        Toast.makeText(this, "Values Sent", Toast.LENGTH_LONG).show();
    }

    public void cancelTextValues(View view){
        textTemperature.setText("0");
        textHumidity.setText("0");
    }


}
