package assignments.sjsu.poojashah.serviceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDownloadService(View view) {

        Intent intent =  new Intent(this, PDFDownload.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void closeApp(View view) {
        MainActivity.this.finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



}
