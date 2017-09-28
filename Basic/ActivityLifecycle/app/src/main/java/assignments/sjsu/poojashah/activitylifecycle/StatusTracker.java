package assignments.sjsu.poojashah.activitylifecycle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by poojashah on 3/5/17.
 */

public class StatusTracker {

    private int counter;
    private static StatusTracker ourInstance = new StatusTracker();

    public static StatusTracker getInstance() {
        return ourInstance;
    }

    private StatusTracker() {
        counter = 0;
    }

    /**
     * Adds the status value for the given activityName into the Map.
     *
     * @param activityName
     */
    public void setStatus(String activityName) {
       if(activityName.equals("onResume")) {
           counter++;
       }
    }

    /**
     * Gets the status value for the given activityName.
     *
     * @return
     */
    public int getStatus() {
        return counter;
    }

    public void clear() {
        counter = 0;
    }

}
