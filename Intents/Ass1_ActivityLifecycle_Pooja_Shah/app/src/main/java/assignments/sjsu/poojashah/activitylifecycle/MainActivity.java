package assignments.sjsu.poojashah.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String activityName;
    private TextView threadCounter;
    private int counter;

    //private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifecycle","Activity A onCreate invoked");
        threadCounter = (TextView)findViewById(R.id.threadCounter);

        //savedInstanceState.putInt("Thread Counter", counter);
        //super.onSaveInstanceState(savedInstanceState);
        //mStatusTracker.setStatus("onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if(bundle != null){
            counter = bundle.getInt("Thread Counter");
        }
        counter++;

        //mStatusTracker.setStatus("onResume");

        printStatus(threadCounter, counter);
        Log.d("lifecycle","Activity A Resume invoked");

    }

    public void startActivityB(View v) {
        Intent intent = new Intent(MainActivity.this, ActivityB.class);
        intent.putExtra("Thread Counter", counter);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void startDialog(View v) {
        //Intent intent = new Intent(MainActivity.this, Dialog.class);
        //intent.putExtra("Thread Counter", counter);
        //startActivity(intent);
        //MainActivity.this.finish();

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        Button finishD = (Button) dialog.findViewById(R.id.finishDialog);
        finishD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","Activity A Restart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","Activity A Destroy invoked");
        //mStatusTracker.clear();
    }

    public void closeA(View v) {
        MainActivity.this.finish();
    }


    public static void printStatus(final TextView viewStatus, int cntr) {
        String threadC = String.format("%04d", cntr);

        if(viewStatus != null) {
            viewStatus.setText("Thread Counter : " + threadC);
        }
    }
}
