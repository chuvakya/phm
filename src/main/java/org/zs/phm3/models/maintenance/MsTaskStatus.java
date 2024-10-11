package org.zs.phm3.models.maintenance;

public enum MsTaskStatus {

    PLANNED(1, "Planned"),
    RECALCULATED(2, "Recalculated"),
    PERFORMED(3, "Performed"),
    EMERGENCY(4, "Emergency"),
    COMPLETED(5, "Completed");

    private String name;

    private Integer id;

    MsTaskStatus(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
