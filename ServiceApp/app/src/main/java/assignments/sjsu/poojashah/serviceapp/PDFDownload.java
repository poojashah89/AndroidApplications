package assignments.sjsu.poojashah.serviceapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class PDFDownload extends AppCompatActivity {
    //For Bound service class
    PDFBoundService pdfBoundService;
    boolean mBound = false;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private EditText pdf1;
    private EditText pdf2;
    private EditText pdf3;
    private EditText pdf4;
    private EditText pdf5;
    private PDFBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfdownload);

        IntentFilter filter = new IntentFilter(PDFBroadcastReceiver.PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new PDFBroadcastReceiver();
        registerReceiver(receiver, filter);

        pdf1 = (EditText) findViewById(R.id.editText6);
        pdf2 = (EditText) findViewById(R.id.editText2);
        pdf3 = (EditText) findViewById(R.id.editText3);
        pdf4 = (EditText) findViewById(R.id.editText4);
        pdf5 = (EditText) findViewById(R.id.editText5);

        //Permission to access storage
        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void downloadPDFs(View view ) {

        /**
         * Started Intent Service Implementation
         */
        //Intent intent = new Intent(getBaseContext(), PDFIntentService.class);

        /**
         * Bound Service Implementation
         */
        if (mBound) {
            Intent intent = new Intent(getBaseContext(), PDFBoundService.class);

            try {
                URL[] urls = new URL[]{
                        new URL(pdf1.getText().toString()),
                        new URL(pdf2.getText().toString()),
                        new URL(pdf3.getText().toString()),
                        new URL(pdf4.getText().toString()),
                        new URL(pdf5.getText().toString())};
                intent.putExtra("URLs", urls);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        startService(intent); }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, PDFBoundService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to pdfBoundService, cast the IBinder and get pdfBoundService instance
            PDFBoundService.MyBinder binder = (PDFBoundService.MyBinder) service;
            pdfBoundService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };



    /**
     * method for checking is the user allow or denie
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Granted, Now you can use local drive .", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Permission Denied, You cannot use local drive .", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    public class PDFBroadcastReceiver extends BroadcastReceiver {

        public static final String PROCESS_RESPONSE = "com.intent.action.PROCESS_RESPONSE";

        NotificationManager notificationManager;

        @Override
        public void onReceive(Context context, Intent intent) {
            String reponseMessages = intent.getStringExtra(PDFIntentService.RESPONSE_MESSAGES);
            String reponseMessage = intent.getStringExtra(PDFIntentService.RESPONSE_MESSAGE);


            TextView myTextView = (TextView) findViewById(R.id.status);
            myTextView.setText(reponseMessage);

            showNotification(context, reponseMessages);
        }

        private void showNotification(Context context, String reponseMessages) {
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, PDFDownload.class), 0);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setContentTitle("Download Status")
                            .setSmallIcon(R.drawable.logo)
                            .setContentText(reponseMessages);
            mBuilder.setContentIntent(contentIntent);
            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
            mBuilder.setAutoCancel(true);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

        }

    }

}

