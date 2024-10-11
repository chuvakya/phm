package org.zs.phm3.models.ml;

import org.zs.phm3.models.calculation.CalculationAttribute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the created ml model
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "ml_model")
public class MlModel {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Start time model service */
    private Long startTime;

    /** Stop time model service */
    private Long endTime;

    /** Model name */
    @Column(length = 1000)
    private String name;

    /** Model ID from ml service (train module) */
    private Long modelId;

    /** Model service state */
    private String mlServiceState = "";

    /** Modified by filed (User Entity) */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Whether the model was manually stopped */
    private Boolean isStop = false;

    /** Ml algorithm used ml model */
    @Enumerated(EnumType.STRING)
    private MlAlgorithm mlAlgorithm;

    /** Type ml algorithm */
    @Enumerated(EnumType.STRING)
    private MlType mlType;

    /** The data schema that the model was based on */
    @JsonIgnore
    @ManyToOne
    private DataSchema dataSchema;

    /** Unit ID */
    private String unitId;

    /** Time of last scan for telemetry */
    private Long lastScan;

    /** Collection with the results of the work of the ml model */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "mlModel", cascade = CascadeType.ALL)
    private List<MlServiceResult> mlServiceResults = new ArrayList<>();

    /** Calculation attribute for ml model */
    @JsonIgnore
    @OneToOne(mappedBy = "mlModel", cascade = CascadeType.ALL)
    private CalculationAttribute calculationAttribute;

    /** Model Schema */
    @ManyToOne
    private ModelSchema modelSchema;

    /** Project ID */
    private Integer projectId;

    private Long timeToDataset;

    /** Ml model metrics */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "mlModel", cascade = CascadeType.ALL)
    private List<MlModelMetric> mlModelMetrics = new ArrayList<>();

    /**
     * Empty constructor
     */
    public MlModel() {}

    /**
     * Constructor
     * @param name ml model name
     * @param mlAlgorithm ml model algorithm
     * @param dataSchema data schema
     * @param modelId model ID
     * @param modelSchema model schema
     */
    public MlModel(String name, MlAlgorithm mlAlgorithm, DataSchema dataSchema, Long modelId, ModelSchema modelSchema) {
        this.dataSchema = dataSchema;
        this.mlAlgorithm = mlAlgorithm;
        this.name = name;
        this.modelId = modelId;
        this.modelSchema = modelSchema;
    }

    public Long getTimeToDataset() {
        return timeToDataset;
    }

    public void setTimeToDataset(Long timeToDataset) {
        this.timeToDataset = timeToDataset;
    }

    /**
     * Gets last scan telemetry
     * @return last scan telemetry
     */
    public Long getLastScan() {
        return lastScan;
    }

    /**
     * Sets last scan telemetry
     * @param lastScan last scan telemetry
     */
    public void setLastScan(Long lastScan) {
        this.lastScan = lastScan;
    }

    /**
     * Gets model metrics
     * @return model metrics
     */
    public List<MlModelMetric> getMlModelMetrics() {
        return mlModelMetrics;
    }

    /**
     * Sets model metrics
     * @param mlModelMetrics model metrics
     */
    public void setMlModelMetrics(List<MlModelMetric> mlModelMetrics) {
        this.mlModelMetrics = mlModelMetrics;
    }

    /**
     * Gets model schema
     * @return model schema
     */
    public ModelSchema getModelSchema() {
        return modelSchema;
    }

    /**
     * Sets model schema
     * @param modelSchema model schema
     */
    public void setModelSchema(ModelSchema modelSchema) {
        this.modelSchema = modelSchema;
    }

    /**
     * Gets model ID from ml service (train module)
     * @return model ID from ml service (train module)
     */
    public Long getModelId() {
        return modelId;
    }

    /**
     * Sets model ID from ml service (train module)
     * @param modelId model ID from ml service (train module)
     */
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    /**
     * Gets calculation attribute
     * @return calculation attribute
     */
    public CalculationAttribute getCalculationAttribute() {
        return calculationAttribute;
    }

    /**
     * Sets calculation attribute
     * @param calculationAttribute calculation attribute
     */
    public void setCalculationAttribute(CalculationAttribute calculationAttribute) {
        this.calculationAttribute = calculationAttribute;
    }

    /**
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets project ID
     * @return project ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets project ID
     * @param projectId project ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets stop status
     * @return stop status
     */
    public Boolean getStop() {
        return isStop;
    }

    /**
     * Gets algorithm type
     * @return algorithm type
     */
    public MlType getMlType() {
        return mlType;
    }

    /**
     * Sets algorithm type
     * @param mlType algorithm type
     */
    public void setMlType(MlType mlType) {
        this.mlType = mlType;
    }

    /**
     * Sets stop status
     * @param stop stop status
     */
    public void setStop(Boolean stop) {
        isStop = stop;
    }

    /**
     * Gets start time ml service
     * @return start time ml service
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * Sets start time ml service
     * @param startTime start time ml service
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time ml service
     * @return end time ml service
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * Sets end time ml service
     * @param endTime end time ml service
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets ml service state
     * @return ml service state
     */
    public String getMlServiceState() {
        return mlServiceState;
    }

    /**
     * Sets ml service state
     * @param mlServiceState ml service state
     */
    public void setMlServiceState(String mlServiceState) {
        this.mlServiceState = mlServiceState;
    }

    /**
     * Gets list with results ml model
     * @return list with results ml model
     */
    public List<MlServiceResult> getMlServiceResults() {
        return mlServiceResults;
    }

    /**
     * Gets list with results ml model
     * @param mlServiceResults list with results ml model
     */
    public void setMlServiceResults(List<MlServiceResult> mlServiceResults) {
        this.mlServiceResults = mlServiceResults;
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
     * Gets model name
     * @return model name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets model name
     * @param name model name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets modified by (UserEntity)
     * @return modified by (UserEntity)
     */
    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets modified by (UserEntity)
     * @param modifiedBy modified by (UserEntity)
     */
    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets ml algorithm
     * @return ml algorithm
     */
    public MlAlgorithm getMlAlgorithm() {
        return mlAlgorithm;
    }

    /**
     * Sets ml algorithm
     * @param mlAlgorithm ml algorithm
     */
    public void setMlAlgorithm(MlAlgorithm mlAlgorithm) {
        this.mlAlgorithm = mlAlgorithm;
    }

    /**
     * Gets data schema
     * @return data schema
     */
    public DataSchema getDataSchema() {
        return dataSchema;
    }

    /**
     * Sets data schema
     * @param dataSchema data schema
     */
    public void setDataSchema(DataSchema dataSchema) {
        this.dataSchema = dataSchema;
    }

}
