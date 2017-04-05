package insite.shiftmanager.bo;

import java.util.Date;

import core.bo.Bo;
import core.service.BoUtils;
/**
 * 
 * @author ashaikh
 *
 */
public class WorkOrder extends Bo {

    private String jobSeekerId;

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    private JobSeeker jobSeeker;

    public JobSeeker getJobSeeker() {
        if (jobSeeker == null) {
            jobSeeker = BoUtils.getBo(JobSeeker.class, jobSeekerId);
        }
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
        this.jobSeekerId = BoUtils.getId(jobSeeker);
    }

    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private int numberOfShifts;

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

}
