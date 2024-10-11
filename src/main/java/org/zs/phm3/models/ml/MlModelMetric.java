package org.zs.phm3.models.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * The class is responsible for saving the metrics ml of the model after the training process
 * @author Pavel Chuvak
 */
@Entity
public class MlModelMetric {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Metric name */
    private String name;

    /** Metric value */
    private Double value;

    /** Ml model */
    @JsonIgnore
    @ManyToOne
    private MlModel mlModel;

    /** Emtpy constructor */
    public MlModelMetric() {
    }

    /**
     * Constructor
     * @param name metric name
     * @param value metric value
     * @param mlModel ml model
     */
    public MlModelMetric(String name, Double value, MlModel mlModel) {
        this.name = name;
        this.value = value;
        this.mlModel = mlModel;
    }

    /**
     * Gets ml model
     * @return
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
     * Gets metric name
     * @return metric name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets metric name
     * @param name metric name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets metric value
     * @return metric value
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets metric value
     * @param value metric value
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
