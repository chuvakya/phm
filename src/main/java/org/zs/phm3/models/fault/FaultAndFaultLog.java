package org.zs.phm3.models.fault;

/**
 * Helper class for rule service queue
 * @author Pavel Chuvak
 */
public class FaultAndFaultLog {

    /** Fault */
    private Fault fault;

    /** Fault log */
    private FaultLog faultLog;

    /** Constructor */
    public FaultAndFaultLog() {
    }

    /**
     * Constructor
     * @param fault fault
     * @param faultLog fault log
     */
    public FaultAndFaultLog(Fault fault, FaultLog faultLog) {
        this.fault = fault;
        this.faultLog = faultLog;
    }

    /**
     * Gets fault
     * @return fault
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * Sets fault
     * @param fault fault
     */
    public void setFault(Fault fault) {
        this.fault = fault;
    }

    /**
     * Gets fault log
     * @return fault log
     */
    public FaultLog getFaultLog() {
        return faultLog;
    }

    /**
     * Sets fault log
     * @param faultLog fault log
     */
    public void setFaultLog(FaultLog faultLog) {
        this.faultLog = faultLog;
    }
}
