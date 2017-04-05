package insite.shiftmanager.config;

import insite.shiftmanager.bo.JobPosting;
import insite.shiftmanager.bo.JobPostingShiftDetail;
import insite.shiftmanager.bo.JobPostingShift;
import insite.shiftmanager.bo.JobSeeker;
import insite.shiftmanager.bo.ShiftSchedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.service.BoUtils;

/**
 * 
 * @author ashaikh
 * 
 */
public class DataSetup {

    public static void create() {
        createShiftSchedules();
        createJobPostings();
        createJobPostingShifts();
        createJobPostingDetails();
        createJobSeekers();
        createWorkOrders();
        createWorkers();
    }

    private static void createShiftSchedules() {
        addShiftSchedule("S01");
        addShiftSchedule("S02");
        addShiftSchedule("S03");
        addShiftSchedule("S04");
        addShiftSchedule("S05");
        addShiftSchedule("S06");
        addShiftSchedule("S07");
        addShiftSchedule("S08");
    }

    private static void createJobPostings() {
        addJobPosting("JP01", "Carpenter", "2013-09-01", "2014-03-31", 10);
    }

    private static void createJobPostingShifts() {
        addJobPostingShift("JP01", "S01", "08:00 AM", "04:00 PM");
        addJobPostingShift("JP01", "S02", "10:00 AM", "06:00 PM");
        addJobPostingShift("JP01", "S03", "04:00 PM", "12:00 AM");
        addJobPostingShift("JP01", "S04", "12:00 AM", "08:00 AM");
    }

    private static void createJobPostingDetails() {
        addJobPostingDetail("JP01", "S01", "2013-09-01", "08:00 AM", "04:00 PM", 1);
        addJobPostingDetail("JP01", "S01", "2013-09-02", "08:00 AM", "04:00 PM", 1);
        addJobPostingDetail("JP01", "S01", "2013-09-03", "08:00 AM", "04:00 PM", 1);
        addJobPostingDetail("JP01", "S02", "2013-09-01", "10:00 AM", "06:00 PM", 1);
        addJobPostingDetail("JP01", "S03", "2013-09-01", "04:00 PM", "12:00 AM", 1);
        addJobPostingDetail("JP01", "S03", "2013-09-02", "04:00 PM", "12:00 AM", 1);
        addJobPostingDetail("JP01", "S03", "2013-09-03", "04:00 PM", "12:00 AM", 1);
        addJobPostingDetail("JP01", "S03", "2013-09-04", "04:00 PM", "12:00 AM", 1);
        addJobPostingDetail("JP01", "S03", "2013-09-15", "04:00 PM", "12:00 AM", 1);
        addJobPostingDetail("JP01", "S04", "2013-09-01", "12:00 AM", "08:00 AM", 1);
    }

    private static void createJobSeekers() {
        addJobSeeker("JS01", "Jay Zerone", "JP01", 10);
        addJobSeeker("JS02", "Ess Zerotoo", "JP01", 5);
        addJobSeeker("JS03", "Aey Tree", "JP01", 5);
    }

    private static void createWorkOrders() {

    }

    private static void createWorkers() {

    }

    private static void addShiftSchedule(String shiftCode) {
        ShiftSchedule ss = new ShiftSchedule();
        ss.setId(shiftCode);
        ss.setShortCode(shiftCode);
        ss.setName("Shift " + shiftCode);
        BoUtils.insert(ss);
    }

    private static void addJobPosting(//
            String ref, String name, String startDate, String endDate, int numShifts) {
        JobPosting jp = new JobPosting();
        jp.setId(ref);
        jp.setRef(ref);
        jp.setName(name);
        jp.setDescription(name);
        jp.setStartDate(parseYmdDate(startDate));
        jp.setEndDate(parseYmdDate(endDate));
        jp.setNumberOfShifts(numShifts);
        BoUtils.insert(jp);
    }

    private static void addJobPostingShift(//
            String jpId, String ssId, String startTime, String endTime) {
        JobPostingShift jpss = new JobPostingShift();
        jpss.setId(jpId + ssId);
        jpss.setJobPostingId(jpId);
        jpss.setShiftScheduleId(ssId);
        jpss.setStartTime(startTime);
        jpss.setEndTime(endTime);
        BoUtils.insert(jpss);
    }

    private static void addJobPostingDetail(//
            String jpId, String ssId, String date, String startTime, String endTime, int shiftCount) {
        JobPostingShiftDetail jps = new JobPostingShiftDetail();
        jps.setId(jpId + ssId + date.replace("-", ""));
        jps.setJobPostingId(jpId);
        jps.setShiftScheduleId(ssId);
        jps.setDate(parseYmdDate(date));
        jps.setStartTime(startTime);
        jps.setEndTime(endTime);
        jps.setShiftCount(shiftCount);
        BoUtils.insert(jps);
    }

    private static void addJobSeeker(//
            String id, String name, String jpId, int shiftCount) {
        JobSeeker js = new JobSeeker();
        js.setId(id);
        js.setName(name);
        js.setJobPostingId(jpId);
        js.setNumberOfShifts(shiftCount);
        BoUtils.insert(js);
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static Date parseYmdDate(String date) {
        Date dt = null;
        try {
            dt = sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dt;
    }

}
