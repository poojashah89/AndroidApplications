package assignments.sjsu.poojashah.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String activityName;
    private TextView threadCounter;

    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifecycle","Activity A onCreate invoked");

        threadCounter = (TextView)findViewById(R.id.threadCounter);
        mStatusTracker.setStatus("onCreate");
        //PrintCounter.printStatus(threadCounter);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(MainActivity.this, ActivityB.class);
        startActivity(intent);
    }

    public void startDialog(View v) {
        Intent intent = new Intent(MainActivity.this, Dialog.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("lifecycle", "Activity A Start invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","Activity A Stop invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","Activity A Pause invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus("onResume");
        PrintCounter.printStatus(threadCounter);
        Log.d("lifecycle","Activity A Resume invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","Activity A Restart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","Activity A Destroy invoked");
        mStatusTracker.clear();
    }

    public void closeA(View v) {
        MainActivity.this.finish();
    }
}
