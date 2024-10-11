package org.zs.phm3.models.maintenance.workorder;

/**
 * Helper class work order part request
 * @author Pavel Chuvak
 */
public class WorkOrderPartRequest {

    /** Part ID */
    private Long partId;

    /** Part stock ID */
    private Long partStockId;

    /** Actual quantity used */
    private Integer actualQuantityUsed;

    /**
     * Gets part stock ID
     * @return part stock ID
     */
    public Long getPartStockId() {
        return partStockId;
    }

    /**
     * Sets part stock ID
     * @param partStockId part stock ID
     */
    public void setPartStockId(Long partStockId) {
        this.partStockId = partStockId;
    }

    /**
     * Gets actual quantity used
     * @return actual quantity used
     */
    public Integer getActualQuantityUsed() {
        return actualQuantityUsed;
    }

    /**
     * Sets actual quantity used
     * @param actualQuantityUsed actual quantity used
     */
    public void setActualQuantityUsed(Integer actualQuantityUsed) {
        this.actualQuantityUsed = actualQuantityUsed;
    }

    /**
     * Gets part ID
     * @return part ID
     */
    public Long getPartId() {
        return partId;
    }

    /**
     * Sets part ID
     * @param partId part ID
     */
    public void setPartId(Long partId) {
        this.partId = partId;
    }
}
