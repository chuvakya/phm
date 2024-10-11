package org.zs.phm3.models.rpm;

public enum CalculatedType {
    CALCULATED_TYPE(1, "Calculate"),
    MANUAL_TYPE(2, "Manual");

    private java.lang.String name;

    private Integer id;

    CalculatedType(Integer id, java.lang.String name) {
        this.name = name;
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
