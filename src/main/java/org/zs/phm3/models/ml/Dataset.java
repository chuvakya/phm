package org.zs.phm3.models.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the generated dataset according to the selected data schema.
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "dataset")
public class Dataset {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Dataset name */
    private String name;

    /** Path to the created dataset on the hard disk */
    private String path;

    /** The state in which the dataset is located */
    private String state;

    /** Start time of data selection by attributes from the ts_kv table */
    private Long timeFrom;

    /** End time of data sampling by attributes from the ts_kv table */
    private Long timeTo;

    /** Create time dataset entity */
    private Long createTime;

    /** Elapsed time for create dataset */
    private Long elapsedTime;

    /** UserEntity who made the last change */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Error message during dataset creation */
    @Column(length = 1000)
    private String errorMessage;

    /** Whether the dataset is suitable for tasks such as anomaly detection */
    private Boolean isAnomaly;

    /** Whether the dataset is suitable for tasks such as fault */
    private Boolean isFault;

    /** Whether the dataset is suitable for tasks such as RUL */
    private Boolean isRul;

    /** Was the dataset validated */
    private Boolean isValid;

    /** Project ID */
    private Integer projectId;

    /** The data schema by which the dataset was created */
    @JsonIgnore
    @ManyToOne
    private DataSchema dataSchema;

    /** Collection Model schema that were created for this dataset */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "dataset", cascade = CascadeType.ALL)
    private List<ModelSchema> modelSchemas = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Dataset() {}

    /**
     * Constructor
     * @param name dataset name
     * @param path dataset path
     * @param projectId project ID
     * @param dataSchema DataSchema entity
     */
    public Dataset(String name, String path, Integer projectId, DataSchema dataSchema) {
        this.name = name;
        this.path = path;
        this.projectId = projectId;
        this.dataSchema = dataSchema;
    }

    /**
     * Gets status validation dataset
     * @return validation status
     */
    public Boolean getValid() {
        return isValid;
    }

    /**
     * Sets validation status
     * @param valid validation status
     */
    public void setValid(Boolean valid) {
        isValid = valid;
    }

    /**
     * Gets state anomaly task
     * @return state anomaly task
     */
    public Boolean getAnomaly() {
        return isAnomaly;
    }

    /**
     * Sets anomaly task
     * @param anomaly anomaly task
     */
    public void setAnomaly(Boolean anomaly) {
        isAnomaly = anomaly;
    }

    /**
     * Gets state fault task
     * @return state fault task
     */
    public Boolean getFault() {
        return isFault;
    }

    /**
     * Sets state fault task
     * @param fault state fault task
     */
    public void setFault(Boolean fault) {
        isFault = fault;
    }

    /**
     * Gets state RUL task
     * @return state RUL task
     */
    public Boolean getRul() {
        return isRul;
    }

    /**
     * Sets state RUL task
     * @param rul state RUL task
     */
    public void setRul(Boolean rul) {
        isRul = rul;
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

    /**
     * Gets state dataset
     * @return state dataset
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state dataset
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets time from dataset
     * @return time from dataset
     */
    public Long getTimeFrom() {
        return timeFrom;
    }

    /**
     * Sets time from dataset
     * @param timeFrom time from dataset
     */
    public void setTimeFrom(Long timeFrom) {
        this.timeFrom = timeFrom;
    }

    /**
     * Gets time to dataset
     * @return time to dataset
     */
    public Long getTimeTo() {
        return timeTo;
    }

    /**
     * Sets time to dataset
     * @param timeTo time to dataset
     */
    public void setTimeTo(Long timeTo) {
        this.timeTo = timeTo;
    }

    /**
     * Gets create time dataset entity
     * @return create time dataset entity
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time dataset entity
     * @param createTime create time dataset entity
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets elapsed time to creation dataset
     * @return elapsed time to creation dataset
     */
    public Long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets elapsed time to creation dataset
     * @param elapsedTime elapsed time to creation dataset
     */
    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Gets modified by field (UserEntity)
     * @return modified by field (UserEntity)
     */
    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets modified by field (UserEntity)
     * @param modifiedBy modified by field (UserEntity)
     */
    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets error message
     * @return error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message
     * @param errorMessage error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
     * Gets list model schemas
     * @return list model schemas
     */
    public List<ModelSchema> getModelSchemas() {
        return modelSchemas;
    }

    /**
     * Sets list model schemas
     * @param modelSchemas list model schemas
     */
    public void setModelSchemas(List<ModelSchema> modelSchemas) {
        this.modelSchemas = modelSchemas;
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
     * Gets dataset name
     * @return dataset name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dataset name
     * @param name dataset name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets dataset path on hard disk
     * @return dataset path on hard disk
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets dataset path on hard disk
     * @param path dataset path on hard disk
     */
    public void setPath(String path) {
        this.path = path;
    }
}
