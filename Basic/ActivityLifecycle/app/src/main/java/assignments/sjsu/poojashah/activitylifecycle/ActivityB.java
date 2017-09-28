package assignments.sjsu.poojashah.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by poojashah on 3/4/17.
 */

public class ActivityB extends Activity{
    private TextView threadCounter;

    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("lifecycle","Activity B onCreate invoked");
        threadCounter = (TextView)findViewById(R.id.threadCounter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("lifecycle", "Activity B Start invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","Activity B Stop invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","Activity B Pause invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus("onResume");
        Log.d("lifecycle","Activity B Resume invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","Activity B Restart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","Activity B Destroy invoked");
    }
    public void finishActivityB(View v) {
        ActivityB.this.finish();
    }
}
