package assignments.sjsu.poojashah.drivers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;

import assignments.sjsu.poojashah.utils.ITHDriver;

/**
 * Created by poojashah on 3/15/17.
 *
 * Async class will take params, progress and results values as parameters
 * This class will use the feature of AsyncTask and update the progress asynchronously
 * doInBackground method will generate random values
 */

public class ExecuteTHTask extends AsyncTask<String, Integer, Integer> {

    ITHDriver driver;
    Context ctx;

    ProgressDialog progressDialog = null;

    public ExecuteTHTask(ITHDriver driver, Context ctx) {
        this.driver = driver;
        this.ctx = ctx;
    }

    @Override
    protected Integer doInBackground(String... params) {
        //Runs on a worker thread
         int tempValue = Integer.parseInt(params[0]); // Temperature Value
            int humidityValue = Integer.parseInt(params[1]); //Humidity Value
            int activityValue = Integer.parseInt(params[2]); //Activity Value
            int readingValue = Integer.parseInt(params[3]); // Counter Value

            //Displaying entered values first
            publishProgress(tempValue, humidityValue, activityValue, 1);

            //Generating random values for remaining counter values
            Random r = new Random();
            for (int i = 1; i < readingValue; i++) {

                tempValue = r.nextInt(100);
                humidityValue = r.nextInt(100);
                activityValue = r.nextInt(1000);
                progressDialog.setProgress(i);
                int ctr = i + 1;
                publishProgress(tempValue, humidityValue, activityValue, ctr);
                if (isCancelled()) break;

                /*try{
                    Thread.sleep(1000);
                } catch (Exception ex) {

                }*/
            }

        return 1;
    }

    @Override
    protected void onPreExecute() {

        Toast.makeText(ctx, "Generating values", Toast.LENGTH_SHORT).show();

        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Generating Values");
        progressDialog.setMessage("Recorded Value : ");
        progressDialog.setCancelable(true);

        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(10);
        //Dialog is hidden to work on cancel
        //progressDialog.show();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Toast.makeText(ctx, "Values generated", Toast.LENGTH_SHORT).show();
        progressDialog.cancel();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[3]); // Progress values
        this.driver.generate(values[0], values[1], values[2], values[3]);  //Temperature, humidity, activity and count values
    }
}
