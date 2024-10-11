package org.zs.phm3.models.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scheme of the model on the basis of which the learning process of the ml model is going on
 * @author Pavel Chuvak
 */
@Entity
public class ModelSchema {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Ml algorithm */
    @Enumerated(EnumType.STRING)
    private MlAlgorithm mlAlgorithm;

    /** Model Schema name */
    private String name;

    /** Create time */
    private Long createTime;

    /** Modified by field */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Window length */
    private Integer windowLength;

    /** File path featured */
    private String featuredFilePath;

    private Long trainPeriod;

    /** Dataset */
    @ManyToOne
    @JsonIgnore
    private Dataset dataset;

    /** Ml Jobs */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "modelSchema", cascade = CascadeType.ALL)
    private List<MlJob> mlJobs = new ArrayList<>();

    /** Project ID */
    private Integer projectId;

    /** Ml models */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "modelSchema")
    private List<MlModel> mlModels = new ArrayList<>();

    /** Empty constructor */
    public ModelSchema() {}

    /**
     * Constructor
     * @param mlAlgorithm ml algorithm
     * @param dataset dataset
     * @param projectId project ID
     * @param name name
     * @param windowLength window length
     */
    public ModelSchema(MlAlgorithm mlAlgorithm, Dataset dataset, Integer projectId, String name,
                       Integer windowLength, Long trainPeriod) {
        this.mlAlgorithm = mlAlgorithm;
        this.dataset = dataset;
        this.projectId = projectId;
        this.name = name;
        this.windowLength = windowLength;
        this.trainPeriod = trainPeriod;
    }

    public Long getTrainPeriod() {
        return trainPeriod;
    }

    public void setTrainPeriod(Long trainPeriod) {
        this.trainPeriod = trainPeriod;
    }

    /**
     * Gets ml models
     * @return ml models
     */
    public List<MlModel> getMlModels() {
        return mlModels;
    }

    /**
     * Sets ml models
     * @param mlModels ml models
     */
    public void setMlModels(List<MlModel> mlModels) {
        this.mlModels = mlModels;
    }

    /**
     * Gets file path featured
     * @return file path featured
     */
    public String getFeaturedFilePath() {
        return featuredFilePath;
    }

    /**
     * Sets file path featured
     * @param featuredFilePath file path featured
     */
    public void setFeaturedFilePath(String featuredFilePath) {
        this.featuredFilePath = featuredFilePath;
    }

    /**
     * Gets window length
     * @return window length
     */
    public Integer getWindowLength() {
        return windowLength;
    }

    /**
     * Sets window length
     * @param windowLength window length
     */
    public void setWindowLength(Integer windowLength) {
        this.windowLength = windowLength;
    }

    /**
     * Gets create time
     * @return create time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets modified by filed
     * @return modified by filed
     */
    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets modified by filed
     * @param modifiedBy modified by filed
     */
    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
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
     * Gets dataset
     * @return dataset
     */
    public Dataset getDataset() {
        return dataset;
    }

    /**
     * Sets dataset
     * @param dataset dataset
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
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
     * Gets model schema name
     * @return model schema name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets model schema name
     * @param name model schema name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets ml jobs
     * @return ml jobs
     */
    public List<MlJob> getMlJobs() {
        return mlJobs;
    }

    /**
     * Sets ml jobs
     * @param mlJobs
     */
    public void setMlJobs(List<MlJob> mlJobs) {
        this.mlJobs = mlJobs;
    }
}
