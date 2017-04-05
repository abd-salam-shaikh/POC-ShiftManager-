package insite.shiftmanager.ui;

import insite.shiftmanager.bo.JobPosting;
import insite.shiftmanager.bo.JobPostingShift;
import insite.shiftmanager.bo.JobPostingShiftDetail;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.service.BoService;
import core.service.BoUtils;

/**
 * 
 * @author rjaywant
 * 
 */
@WebServlet("/job_posting.do")
public class JobPostingDetailServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public JobPostingDetailServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("action") == null) {
                onInitialRequest(req, resp);
            } else if ("getShiftDates".equals(req.getParameter("action"))) {
                onGetShiftDates(req, resp);
            } else if ("updateShiftDates".equals(req.getParameter("action"))) {
                onUpdateShiftDates(req, resp);
            }
        } catch (ServletException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void onInitialRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoService<JobPosting> service = BoUtils.getService(JobPosting.class);
        JobPosting jp = null;
        String jpId = req.getParameter("id");
        if (jpId != null) {
            jp = service.load(jpId);
            req.getSession().setAttribute("jobPosting", jp);
        } else {
            jp = (JobPosting) req.getSession().getAttribute("jobPosting");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        req.setAttribute("startDate", sdf.format(jp.getStartDate()));
        req.setAttribute("endDate", sdf.format(jp.getEndDate()));
        setShiftsJson(req, jp);
        showPage(req, resp);
    }

    private static void setShiftsJson(HttpServletRequest req, JobPosting jp) {
        List<Map<String, Object>> shifts = new ArrayList<Map<String, Object>>();
        for (JobPostingShift s : jp.getShifts()) {
            Map<String, Object> shift = new HashMap<String, Object>();
            shift.put("id", s.getShiftSchedule().getId());
            shift.put("name", s.getShiftSchedule().getName());
            shift.put("startTime", s.getStartTime());
            shift.put("endTime", s.getEndTime());
            shifts.add(shift);
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        req.setAttribute("shiftsJson", gson.toJson(shifts));
    }

    private void onGetShiftDates(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Set<String> shiftIds = new HashSet<String>(Arrays.asList(req.getParameter("shiftIds").split("\\,")));
        JobPosting jp = (JobPosting) req.getSession().getAttribute("jobPosting");
        writeShiftDatesToJson(resp, shiftIds, jp);
    }

    private void writeShiftDatesToJson(HttpServletResponse resp, Set<String> shiftIds, JobPosting jp) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List<Map<String, Object>>> shiftDatesById = new HashMap<String, List<Map<String, Object>>>();
        for (JobPostingShiftDetail sd : jp.getShiftDetails()) {
            if (shiftIds == null || shiftIds.contains(sd.getShiftScheduleId())) {
                Map<String, Object> shiftDate = new HashMap<String, Object>();
                shiftDate.put("shiftId", sd.getShiftScheduleId());
                shiftDate.put("shiftName", sd.getShiftSchedule().getName());
                shiftDate.put("date", sdf.format(sd.getDate()));
                shiftDate.put("shiftCount", sd.getShiftCount());
                shiftDate.put("startTime", sd.getStartTime());
                shiftDate.put("endTime", sd.getEndTime());
                List<Map<String, Object>> list = shiftDatesById.get(sd.getShiftScheduleId());
                if (list == null) {
                    list = new ArrayList<Map<String, Object>>();
                    shiftDatesById.put(sd.getShiftScheduleId(), list);
                }
                list.add(shiftDate);
            }
        }
        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
        Gson gson = new GsonBuilder().serializeNulls().create();
        gson.toJson(shiftDatesById, resp.getWriter());
    }

    private void onUpdateShiftDates(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,
            ParseException {
        JobPosting jobPosting = (JobPosting) req.getSession().getAttribute("jobPosting");
        List<Map<String, String>> deleted = parseParams(req, "deletedShiftDates", "shiftId", "date");
        List<Map<String, String>> forms = parseParams(req, //
                "shiftDates", "shiftId", "date", "shiftCount", "startTime", "endTime");

        List<JobPostingShiftDetail> shiftDetails = new ArrayList<JobPostingShiftDetail>();
        List<JobPostingShiftDetail> toUpdate = new ArrayList<JobPostingShiftDetail>();
        List<JobPostingShiftDetail> toDelete = new ArrayList<JobPostingShiftDetail>();
        List<JobPostingShiftDetail> toAdd = new ArrayList<JobPostingShiftDetail>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (JobPostingShiftDetail item : jobPosting.getShiftDetails()) {
            String date = sdf.format(item.getDate());
            Map<String, String> tForm = null;
            String shiftId = item.getShiftScheduleId();
            for (Map<String, String> form : deleted) {
                if (shiftId.equals(form.get("shiftId")) && date.equals(form.get("date"))) {
                    tForm = form;
                    break;
                }
            }
            if (tForm != null) {
                toDelete.add(item);
                deleted.remove(tForm);
                continue;
            }
            tForm = null;
            for (Map<String, String> form : forms) {
                if (shiftId.equals(form.get("shiftId")) && date.equals(form.get("date"))) {
                    tForm = form;
                    break;
                }
            }
            if (tForm != null) {
                item.setShiftCount(Integer.parseInt(tForm.get("shiftCount")));
                item.setStartTime(tForm.get("startTime"));
                item.setEndTime(tForm.get("endTime"));
                toUpdate.add(item);
                forms.remove(tForm);
            }
            shiftDetails.add(item);
        }

        for (Map<String, String> form : forms) {
            JobPostingShiftDetail newItem = new JobPostingShiftDetail();
            newItem.setJobPosting(jobPosting);
            newItem.setShiftScheduleId(form.get("shiftId"));
            newItem.setDate(sdf.parse(form.get("date")));
            newItem.setShiftCount(Integer.parseInt(form.get("shiftCount")));
            newItem.setStartTime(form.get("startTime"));
            newItem.setEndTime(form.get("endTime"));
            toAdd.add(newItem);
            shiftDetails.add(newItem);
        }

        BoUtils.deleteAll(toDelete);
        BoUtils.updateAll(toUpdate);
        BoUtils.addAll(toAdd);
        jobPosting.setShiftDetails(shiftDetails);
        writeShiftDatesToJson(resp, null, jobPosting);
    }

    private static List<Map<String, String>> parseParams(HttpServletRequest req, String prefix, String... propertyNames) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int index = 0; req.getParameter(prefix + "[" + index + "]." + propertyNames[0]) != null; index++) {
            Map<String, String> item = new HashMap<String, String>();
            for (int i = 0; i < propertyNames.length; i++) {
                item.put(propertyNames[i], req.getParameter(prefix + "[" + index + "]." + propertyNames[i]));
            }
            list.add(item);
        }
        return list;
    }
}
