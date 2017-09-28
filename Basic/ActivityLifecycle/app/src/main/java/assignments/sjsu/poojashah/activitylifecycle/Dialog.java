package assignments.sjsu.poojashah.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Dialog extends Activity {
    private TextView threadCounter;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus("onResume");
        Log.d("lifecycle","Dialog Activity Resume invoked");
    }

    public void finishDialog(View v) {
        Dialog.this.finish();
    }
}
