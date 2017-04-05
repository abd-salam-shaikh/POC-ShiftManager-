package insite.shiftmanager.bo;

import core.bo.Bo;
import core.service.BoUtils;
/**
 * 
 * @author ashaikh
 *
 */
public class WorkerShiftDetail extends Bo {

    private String jobSeekerId;

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    private String jobPostingShiftId;

    public String getJobPostingShiftId() {
        return jobPostingShiftId;
    }

    public void setJobPostingShiftId(String jobPostingShiftId) {
        this.jobPostingShiftId = jobPostingShiftId;
    }

    private String workerId;

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    private Worker worker;

    public Worker getWorker() {
        if (worker == null) {
            worker = BoUtils.getBo(Worker.class, workerId);
        }
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
        this.workerId = BoUtils.getId(worker);
    }

}
