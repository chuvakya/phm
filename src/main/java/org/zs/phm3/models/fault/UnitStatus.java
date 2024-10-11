package org.zs.phm3.models.fault;

/**
 * Helper class unit status
 */
public class UnitStatus {

    /** Unit ID */
    private String unitId;

    /** Status */
    private Integer status;

    /** Constructor */
    public UnitStatus() {
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     * @param status status
     */
    public UnitStatus(String unitId, Integer status) {
        this.unitId = unitId;
        this.status = status;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets status
     * @param status status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Gets status
     * @return status
     */
    public Integer getStatus() {
        return status;
    }
}
