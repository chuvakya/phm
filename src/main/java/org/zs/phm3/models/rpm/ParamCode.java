package org.zs.phm3.models.rpm;

public enum ParamCode {
    Λ(1, "λ"),//λ
    MTBF(2, "MTBF");

    private String name;

    private Integer id;

    ParamCode(Integer id, String name) {
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
