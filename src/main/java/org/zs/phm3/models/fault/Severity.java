package org.zs.phm3.models.fault;

/**
 * Enum severity
 * @author Pavel Chuvak
 */
public enum Severity {

    LOW(0), MEDIUM(1), HIGH(2), CRITICAL(3);

    private int i;

    Severity(int i) {
        this.i = i;
    }

    public static String getNameByCode(Integer i) {
        return switch(i) {
            case 0 -> "Low";
            case 1 -> "Medium";
            case 2 -> "High";
            case 3 -> "Critical";
            default -> null;
        };
    }

    public static String getNameByCode(Severity i) {
        return switch(i) {
            case LOW -> "Low";
            case MEDIUM -> "Medium";
            case HIGH -> "High";
            case CRITICAL -> "Critical";
        };
    }
}
