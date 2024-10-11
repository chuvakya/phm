package org.zs.phm3.models.fault;

/**
 * Enum fault statuses
 * @author Pavel Chuvak
 */
public enum FaultStatus {
    NORMAL(0), WARNING(1), ERROR(2);

    private Integer i;

    FaultStatus(Integer i) {
        this.i = i;
    }

    public int getI() {
        return this.i;
    }

    public String getStatusName() {
        return switch (this.i) {
            case 0 -> "Normal";
            case 1 -> "Warning";
            case 2 -> "Error";
            default -> "";
        };
    }
}
