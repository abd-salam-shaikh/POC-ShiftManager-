package insite.shiftmanager.config;

import core.service.BoUtils;
import insite.shiftmanager.bo.JobPosting;

/**
 * 
 * @author ashaikh
 * 
 */
public class Test {

    public static void main(String[] args) {
        Config.init();
        try {
            System.out.println(System.getProperties().keySet());
            JobPosting jp = BoUtils.getBo(JobPosting.class, "JP01");
            System.out.println(jp);
            System.out.println(jp.getShifts());
            System.out.println(jp.getShiftDetails());
        } finally {
            Config.dispose();
        }
    }

}
