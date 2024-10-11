package org.zs.phm3.models.ml;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * The class is responsible for saving the results of the work of the ml model
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "ml_service_result")
public class MlServiceResult {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Ml model result */
    @Type(type = "text")
    private String result;

    /** Unit ID */
    private String unitId;

    /** Ml model */
    @ManyToOne
    private MlModel mlModel;

    /** Result time */
    private Long time;

    /** Empty constructor */
    public MlServiceResult() {}

    /**
     * Constructor
     * @param time result time
     * @param result result
     * @param unitId unit ID
     * @param mlModel ml model
     */
    public MlServiceResult(long time, String result, String unitId, MlModel mlModel) {
        this.result = result;
        this.unitId = unitId;
        this.time = time;
        this.mlModel = mlModel;
    }

    /**
     * Gets ml model
     * @return ml model
     */
    public MlModel getMlModel() {
        return mlModel;
    }

    /**
     * Sets ml model
     * @param mlModel ml model
     */
    public void setMlModel(MlModel mlModel) {
        this.mlModel = mlModel;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets result time
     * @return result time
     */
    public Long getTime() {
        return time;
    }

    /**
     * Sets result time
     * @param time result time
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * Gets result
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets result
     * @param result result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Gets unit ID
     * @return ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
