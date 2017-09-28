package assignments.sjsu.poojashah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import assignments.sjsu.poojashah.drivers.ExecuteTHTask;
import assignments.sjsu.poojashah.thsensordriver.R;
import assignments.sjsu.poojashah.utils.ITHDriver;


public class MainActivity extends AppCompatActivity implements ITHDriver{

    private ExecuteTHTask exec = null;
    private EditText temp, humidity, activity, readingVal;
    public Button cancelBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = (EditText)this.findViewById(R.id.tempVal);
        humidity = (EditText) this.findViewById(R.id.humidityVal);
        activity = (EditText) this.findViewById(R.id.activityVal);
        readingVal = (EditText) this.findViewById(R.id.readingVal);
        cancelBtn = (Button) this.findViewById(R.id.cancel);
    }

    @Override
    public void generate(int temp, int humidity, int activity, int outputCnt) {
        TextView tv = getTextView();
        tv.setText(tv.getText() +
                "\n Output : " + outputCnt +
                "\n Temperature :" + temp +
                "\n Humidity : " + humidity +
                "\n Activity : " + activity +"\n");
    }

    /**
     * After clicking on generate
     * @param view
     */
    public void generateValues(View view) {
        if( temp.getText().toString().length() == 0 || humidity.getText().toString().length() == 0 ||
            activity.getText().toString().length() == 0 || readingVal.getText().toString().length() == 0 ) {
            Toast.makeText(this, "Error! Values required.", Toast.LENGTH_SHORT).show();
        }
        else {
            emptyText(); // Clear first values before generating new
            exec = new ExecuteTHTask(this, this);
            exec.execute(temp.getText().toString(),
                    humidity.getText().toString(),
                    activity.getText().toString(),
                    readingVal.getText().toString());
        }
    }

    /**
     * clear messages
     */
    public void emptyText(){
        TextView tv = getTextView();
        tv.setText("");
    }

    public void cancelTask(View view) {
        exec.cancel(true);
        Toast.makeText(this, "Task stopped", Toast.LENGTH_SHORT).show();
    }

    /**
     * Get output text field
     * @return
     */
    private TextView getTextView(){
        return (TextView)this.findViewById(R.id.output);
    }

}
