package insite.shiftmanager.bo;

import core.bo.Bo;
import core.service.BoUtils;
/**
 * 
 * @author ashaikh
 *
 */
public class Worker extends Bo {

    private String workOrderId;

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    private WorkOrder workOrder;

    public WorkOrder getWorkOrder() {
        if (workOrder == null) {
            workOrder = BoUtils.getBo(WorkOrder.class, workOrderId);
        }
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
        this.workOrderId = BoUtils.getId(workOrder);
    }

    private int numberOfShifts;

    public int getNumberOfShifts() {
        return numberOfShifts;
    }

    public void setNumberOfShifts(int numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

}
