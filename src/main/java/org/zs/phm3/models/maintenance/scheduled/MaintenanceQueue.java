package org.zs.phm3.models.maintenance.scheduled;

import org.zs.phm3.models.maintenance.workorder.WorkOrder;

import java.util.ArrayDeque;

/**
 * Helper class maintenance queue
 * @author Pavel Chuvak
 */
public class MaintenanceQueue {

    /** Rules */
    private static ArrayDeque<MaintenanceRule> rules = new ArrayDeque<>();

    /** Triggers */
    private static ArrayDeque<Trigger> triggers = new ArrayDeque<>();

    /**
     * Gets maintenance rules
     * @return maintenance rules
     */
    public static ArrayDeque<MaintenanceRule> getRules() {
        return rules;
    }

    /**
     * Gets triggers
     * @return triggers
     */
    public static ArrayDeque<Trigger> getTriggers() {
        return triggers;
    }
}
