package org.zs.phm3.models.maintenance;

public enum DataSourceType {

    DOCUMENTATION(1, "Documentation"),
    DIAGNOSTICS(2, "Diagnostics"),
    FAILURE(3, "Failure"),
    MANUAL(4, "Manual");

    private String name;

    private Integer id;

    DataSourceType(Integer id, String name) {
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
