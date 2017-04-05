package insite.shiftmanager.bo;

import java.util.Date;
import java.util.List;

import core.bo.Bo;
import core.service.BoUtils;

/**
 * Job Posting
 * 
 * @author ashaikh
 * 
 */
public class JobPosting extends Bo {

    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    private List<JobPostingShift> shifts;

    public List<JobPostingShift> getShifts() {
        if (shifts == null) {
            shifts = BoUtils.getBoList(JobPostingShift.class, "jobPostingId", getId());
        }
        return shifts;
    }

    public void setShifts(List<JobPostingShift> shiftShedules) {
        this.shifts = shiftShedules;
    }

    private List<JobPostingShiftDetail> shiftDetails;

    public List<JobPostingShiftDetail> getShiftDetails() {
        if (shiftDetails == null) {
            shiftDetails = BoUtils.getBoList(JobPostingShiftDetail.class, "jobPostingId", getId());
        }
        return shiftDetails;
    }

    public void setShiftDetails(List<JobPostingShiftDetail> shiftDetails) {
        this.shiftDetails = shiftDetails;
    }

}
