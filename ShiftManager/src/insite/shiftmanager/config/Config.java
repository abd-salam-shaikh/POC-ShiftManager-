package insite.shiftmanager.config;

import insite.shiftmanager.bo.JobPosting;
import insite.shiftmanager.bo.JobPostingShiftDetail;
import insite.shiftmanager.bo.JobPostingShift;
import insite.shiftmanager.bo.JobSeeker;
import insite.shiftmanager.bo.JobSeekerShiftDetail;
import insite.shiftmanager.bo.ShiftSchedule;
import insite.shiftmanager.bo.WorkOrder;
import insite.shiftmanager.bo.Worker;
import insite.shiftmanager.bo.WorkerShiftDetail;
import core.bean.Beans;
import core.service.BoUtils;

/**
 * 
 * @author ashaikh
 * 
 */
public class Config {

    public static void init() {

        // Register BO Services
        BoUtils.addService(ShiftSchedule.class);
        BoUtils.addService(JobPosting.class);
        BoUtils.addService(JobPostingShift.class);
        BoUtils.addService(JobPostingShiftDetail.class);
        BoUtils.addService(JobSeeker.class);
        BoUtils.addService(JobSeekerShiftDetail.class);
        BoUtils.addService(WorkOrder.class);
        BoUtils.addService(Worker.class);
        BoUtils.addService(WorkerShiftDetail.class);

        // Create test data
        DataSetup.create();

    }

    public static void dispose() {
        Beans.dispose();
    }

}
