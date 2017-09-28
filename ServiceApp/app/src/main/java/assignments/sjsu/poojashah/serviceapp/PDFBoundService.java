package assignments.sjsu.poojashah.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;

/**
 * Created by poojashah on 3/29/17.
 */

public class PDFBoundService extends Service {
    private String TAG = "PDFBoundService";

    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();

    private IBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        PDFBoundService getService() {
            return PDFBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "On bind done");
        return myBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.

        Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
        URL[] urls = new URL[objUrls.length];
        for (int i=0; i<objUrls.length-1; i++) {
            urls[i] = (URL) objUrls[i];
        }
        new DoBackgroundTask().execute(urls);

        return START_STICKY;
    }


    @Override
    public void onDestroy()
    {
        Log.v(TAG, "in onDestroy(). Interrupting threads and cancelling notifications");
        super.onDestroy();
    }


    public int DownloadFile(URL url, String filename) {

        try {

            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //and connect!
            urlConnection.connect();

            //set the path where we want to save the file
            //in this case, going to save it on the root directory of the
            //sd card.
            File SDCardRoot = Environment.getExternalStorageDirectory();
            //create a new file, specifying the path, and the filename
            //which we want to save the file as.
            File file = new File(SDCardRoot +"/Download", filename);

            if(file.exists())
                file.delete();
            //this will be used to write the downloaded data into the file we created
            FileOutputStream fileOutput = new FileOutputStream(file);

            //this will be used in reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            //this is the total size of the file
            int totalSize = urlConnection.getContentLength();
            //variable to store total downloaded bytes
            int downloadedSize = 0;

            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0; //used to store a temporary size of the buffer

            //now, read through the input buffer and write the contents to the file
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                //add the data in the buffer to the file in the file output stream (the file on the sd card
                fileOutput.write(buffer, 0, bufferLength);
                //add up the size so we know how much is downloaded
                downloadedSize += bufferLength;
                //this is where you would do something to report the prgress, like this maybe
                Log.d( TAG, "Downloaded size :" + downloadedSize + "Total size :" + totalSize);

            }
            //close the output stream when done
            fileOutput.close();
            return totalSize;

        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }

    }

    private class DoBackgroundTask extends AsyncTask<URL, Integer, Integer> {
        protected Integer doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += DownloadFile(urls[i], "file_" + i + ".pdf");
                //---calculate precentage downloaded and
                // report its progress---
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return count;
        }

        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files",
                    String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(getBaseContext(),
                    String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }

        protected void onPostExecute(Integer result) {
            Toast.makeText(getBaseContext(),
                    "Downloaded " + result + " files",
                    Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }
}
