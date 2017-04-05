package insite.shiftmanager.bo;

import core.bo.Bo;
import core.service.BoUtils;
/**
 * 
 * @author ashaikh
 *
 */
public class JobSeekerShiftDetail extends Bo {

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

    private JobPostingShiftDetail jobPostingShift;

    public JobPostingShiftDetail getJobPostingShift() {
        if (jobPostingShift == null) {
            jobPostingShift = BoUtils.getBo(JobPostingShiftDetail.class, jobPostingShiftId);
        }
        return jobPostingShift;
    }

    public void setJobPostingShift(JobPostingShiftDetail jobPostingShift) {
        this.jobPostingShift = jobPostingShift;
        this.jobPostingShiftId = BoUtils.getId(jobPostingShift);
    }

}
