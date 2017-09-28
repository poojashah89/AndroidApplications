package assignments.sjsu.poojashah.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.content.Intent;

public class Dialog extends Activity {
    private TextView threadCounter;
    //private StatusTracker mStatusTracker = StatusTracker.getInstance();
    int threadCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //mStatusTracker.setStatus("onResume");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            threadCount = bundle.getInt("Thread Counter");
            threadCount++;
        }
        Log.d("lifecycle","Dialog Activity Resume invoked");
    }

    public void finishDialog(View v) {

        Intent intent=new Intent(Dialog.this, MainActivity.class);
        intent.putExtra("Thread Counter", threadCount);
        startActivity(intent);
        Dialog.this.finish();
    }
}
