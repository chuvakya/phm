package org.zs.phm3.models.fmea;

public enum ProductLifeCycleStage {

    DEMONSTRATION(1, "Demonstration and planning phase"),
    ENGINEERING(2, "Engineering design and revision phase"),
    PRODUCTION(3, "Production phase"),
    USE(4, "Use phase");

    /*  Demonstration and planning phase
        Engineering design and revision stage
        Production phase
        Use phase */
    private String name;

    private Integer id;

    ProductLifeCycleStage(Integer id, String name ) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
