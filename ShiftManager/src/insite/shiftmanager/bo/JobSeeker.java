package insite.shiftmanager.bo;

import java.util.List;

import core.bo.Bo;
import core.service.BoUtils;
/**
 * 
 * @author ashaikh
 *
 */
public class JobSeeker extends Bo {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String jobPostingId;

    public String getJobPostingId() {
        return jobPostingId;
    }

    public void setJobPostingId(String jobPostingId) {
        this.jobPostingId = jobPostingId;
    }

    private JobPosting jobPosting;

    public JobPosting getJobPosting() {
        if (jobPosting == null) {
            jobPosting = BoUtils.getBo(JobPosting.class, jobPostingId);
        }
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
        this.jobPostingId = BoUtils.getId(jobPosting);
    }

    private int numberOfShifts;

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

    private JobSeeker jobSeekerId;
    private List<JobSeekerShiftDetail> jobSeekerShiftDetail;

	public List<JobSeekerShiftDetail> getJobSeekerShiftDetail() {
		if(jobSeekerShiftDetail == null) {
			jobSeekerShiftDetail =BoUtils.getBoList(JobSeekerShiftDetail.class, "jobSeekerId", getId());
		}
		return jobSeekerShiftDetail;
	}
	
	public void setJobSeekerShiftDetail(
			List<JobSeekerShiftDetail> jobSeekerShiftDetail) {
		this.jobSeekerShiftDetail = jobSeekerShiftDetail;
	}
    
}
