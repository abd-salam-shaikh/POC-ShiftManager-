package insite.shiftmanager.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import insite.shiftmanager.api.SmCalendar;
import insite.shiftmanager.api.SmContext;
import insite.shiftmanager.api.SmListener;
import insite.shiftmanager.api.SmMessages;
import insite.shiftmanager.api.SmShift;
import insite.shiftmanager.api.SmShiftDate;
import insite.shiftmanager.api.SmShiftDateInfo;
import insite.shiftmanager.bo.JobPostingShiftDetail;
import insite.shiftmanager.bo.JobSeeker;
import insite.shiftmanager.bo.JobSeekerShiftDetail;
import insite.shiftmanager.bo.ShiftSchedule;

public class JobSeekerSmListener implements SmListener<JobSeeker> {

	@Override
	public List<SmShift> onLoad(SmContext<JobSeeker> context) {
		
		  String jobSeekerId = context.getObjectId();
	        JobSeeker jobSeeker = context.getObject();
//	        LoginService loginService = (LoginService) ServiceManager.getInstance().lookup(LoginService.ROLE);
//	        Login login = loginService.getDefaultLogin("ZOOM");
//	        if(jobSeeker == null){
//	            jobSeeker = (JobSeeker)((JobSeekerService)ServiceManager.getInstance().lookup(JobSeekerService.ROLE)).load(login, jobSeekerId);
//	        }   
	        List<SmShift> shifts = new ArrayList<SmShift>();
	        for (JobPostingShiftDetail s : jobSeeker.getJobPosting().getShiftDetails()) {
	            SmShift shift = new SmShift();
	            shift.setId(s.getId());
	            shift.setName(s.getShiftSchedule().getName());
	            shifts.add(shift);
	        }
	        return shifts;
	}

	@Override
	public SmCalendar onRefreshCalendar(SmContext<JobSeeker> context,
			List<String> shiftIds) {
//		  JobSeeker jobSeeker = context.getObject();
//		  SmCalendar smCalendar = new SmCalendar();
//	         smCalendar.setEndDate(endDate);
//	         List<Date> selectableDates = new ArrayList<Date>();
//	         for(JobPostingShiftDetail jobPostingShiftDetail : jobSeeker.getJobPosting().getShiftDetails()){
//	             if(StringUtil.blanknull(shiftIds.get(0)).equals(jobPostingShiftDetail.getShiftScheduleId())) {
//	                 selectableDates.add(jobPostingShiftDetail.getDate());
//	             }
//	         } 
//	         
//	         List<String> selectedIds = new ArrayList<String>();
//	         for(JobSeekerShiftDetail jobSeekerShiftDetail: jobSeeker.getJobSeekerShiftDetail()){
//	        	 selectedIds.add(jobSeekerShiftDetail.getJobPostingShiftId());
//	         }
//	         
//	         List<SmShiftDate> smShiftDates = new ArrayList<SmShiftDate>();
//	         for(JobPostingShiftDetail jobPostingShiftDetail:jobSeeker.getJobPosting().getShiftDetails()){
//	        	 if(shiftIds.get(0).equals(jobPostingShiftDetail.getId())) {
//	        		 if(selectedIds.contains(jobPostingShiftDetail)) {
//	        			 SmShiftDate smShiftDate = new SmShiftDate();
//	        			 smShiftDate.setAction(action);
//	        			 smShiftDate.setCount(count);
//	        			 smShiftDate.setDate(date);
//	        			 smShiftDate.setShiftId(shiftId);
//	        			 smShiftDates.add(smShiftDate);
//	        		 }
//	        	 }
//	         }
//	         smCalendar.setShiftDates(smShiftDates);
//	         smCalendar.setStartDate(startDate);
		return null;
	}

	@Override
	public SmMessages onValidateShiftDate(SmContext<JobSeeker> context,
			SmShiftDate shiftDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmShiftDateInfo> onShiftDateInfo(SmContext<JobSeeker> context,
			List<String> shiftIds, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmMessages onUpdateShiftDates(SmContext<JobSeeker> context,
			List<String> shiftIds, List<SmShiftDate> shiftDates) {
		// TODO Auto-generated method stub
		return null;
	}
//	 public static <T extends ObjectCustomField> Set<T> getCustomFieldsForUpdate(CustomFieldEnabled cfe) {
//	        Set<T> newSet = new HashSet<T>();
//	        Set<String> visibleIds = new HashSet<String>();
//	        Set<T> visibleSet = cfe.getCustomFieldSet();
//	        for (T ocf : visibleSet) {
//	            visibleIds.add(ocf.getCustomFieldId());
//	            newSet.add(ocf);
//	        }
//	        try {
//	            cfe.setCustomFieldSet(null);
//	            Set<T> fullSet = cfe.getCustomFieldSet();
//	            // Find the CustomFields which are not in "visibleSet"
//	            // and add them to "newSet"
//	            for (T ocf : fullSet) {
//	                if (!visibleIds.contains(ocf.getCustomFieldId())) {
//	                    newSet.add(ocf);
//	                }
//	            }
//	        } finally {
//	            cfe.setCustomFieldSet(visibleSet);
//	        }
//	        return newSet;
//	}
}
