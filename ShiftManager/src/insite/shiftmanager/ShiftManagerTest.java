package insite.shiftmanager;

import insite.shiftmanager.api.SmContext;
import insite.shiftmanager.api.SmListener;
import insite.shiftmanager.api.SmShift;
import insite.shiftmanager.bo.JobPosting;
import insite.shiftmanager.config.Config;
import insite.shiftmanager.ui.JobPostingSmListener;

import java.util.List;

public class ShiftManagerTest {

    public static void main(String[] args) {
        Config.init();
        try {
            SmListener<JobPosting> listener = new JobPostingSmListener();
            SmContext<JobPosting> context = new SmContext<JobPosting>();
            context.setObjectId("JP01");
            List<SmShift> shifts = listener.onLoad(context);
            if (shifts == null || shifts.size() != 8) {
                throw new RuntimeException("Assertion Failure! didnt find expected number of shifts!");
            }
            System.out.println(shifts);
        } finally {
            Config.dispose();
        }

    }

}
