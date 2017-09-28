package assignments.sjsu.poojashah.activitylifecycle;

import android.os.Handler;
import android.widget.TextView;

import java.util.List;

/**
 * Created by poojashah on 3/4/17.
 */

public class PrintCounter {
    private static StatusTracker mStatusTracker = StatusTracker.getInstance();

    /**
     * Helper method to print out the lifecycle counter of each Activity.  Note this has
     * been wrapped in a Handler to delay the output due to overlaps in lifecycle state
     * changes as one Activity launches another.
     * @link http://developer.android.com/guide/topics/fundamentals/activities.html#CoordinatingActivities
     * @param viewStatus TextView to list out the status of all Activity classes
     */
    public static void printStatus( final TextView viewStatus) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Get the stack of Activity lifecycle methods called and print to TextView
                int resumeCnt = mStatusTracker.getStatus();
                String counter = String.format("%04d", resumeCnt);

                if(viewStatus != null) {
                    viewStatus.setText("Thread Counter : " + counter);
                }
            }
        }, 750);
    }
}
