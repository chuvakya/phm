package org.zs.phm3.models.fault;

import java.util.ArrayDeque;

/**
 * Fault queue class
 * @author Pavel Chuvak
 */
public class FaultQueue {
    private static ArrayDeque<FaultAndFaultLog> faultAndFaultLogs = new ArrayDeque<>();
    public static ArrayDeque<FaultAndFaultLog> getFaultAndFaultLogs() {
        return faultAndFaultLogs;
    }
}
