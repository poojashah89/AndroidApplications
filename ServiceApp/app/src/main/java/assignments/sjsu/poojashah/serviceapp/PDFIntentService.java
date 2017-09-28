package assignments.sjsu.poojashah.serviceapp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import assignments.sjsu.poojashah.serviceapp.PDFDownload.PDFBroadcastReceiver;

/**
 * Created by poojashah on 3/29/17.
 */

public class PDFIntentService extends IntentService {
    public static final String DOWNLOAD_ERROR = "ERROR";
    public static final String DOWNLOAD_SUCCESS = "SUCCESS";

    private static final String TAG = "PDFIntentService";
    public static final String RESPONSE_MESSAGES = "myResponseMessages";
    public static final String RESPONSE_MESSAGE = "myResponseMessage";

    Context ctx = null;

    public PDFIntentService() {
        super("PDFIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        ctx = getApplicationContext();
        try {
            //---send a broadcast to inform the activity
            // that the file has been downloaded---
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(PDFBroadcastReceiver.PROCESS_RESPONSE);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            StringBuffer filestatus = new StringBuffer();
            Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
            URL[] urls = new URL[objUrls.length];
            int fileCnt= 1;
            for (int i=0; i<objUrls.length; i++) {
                urls[i] = (URL) objUrls[i];
                String status  = DownloadFile(urls[i], "File" + fileCnt + ".pdf");
                filestatus.append(fileCnt + ". " + urls[i]+ " file downloaded with status :" + status + " \n\n\n");
                fileCnt++;
            }
            broadcastIntent.putExtra(RESPONSE_MESSAGE, filestatus.toString());
            broadcastIntent.putExtra(RESPONSE_MESSAGES, "All Files downloaded.");
            sendBroadcast(broadcastIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String DownloadFile(URL url, String filename) {

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

            //Log.d("File path" , file.getPath());
            return DOWNLOAD_SUCCESS;
        } catch (Exception e) {

            e.printStackTrace();
            return DOWNLOAD_ERROR;
        }

    }



}
