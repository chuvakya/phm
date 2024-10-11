package org.zs.phm3.models.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;

/**
 * The class responsible for the task of creating the ml model.
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "ml_job")
public class MlJob {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Create time entity */
    private Long createTime;

    /** Entity name */
    private String name;

    /** Elapsed time job */
    private Long elapsedTime;

    /** State */
    private String state;

    /** UserEntity modifiedBy */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Job result */
    @Type(type = "text")
    private String result;

    /** Model Schema */
    @ManyToOne
    @JsonIgnore
    private ModelSchema modelSchema;

    /** Project ID */
    private Integer projectId;

    /**
     * Instantiates a new Ml job.
     */
    public MlJob() {}

    /**
     * Instantiates a new Ml job.
     *
     * @param name        the name
     * @param modifiedBy  the modified by
     * @param modelSchema the model schema
     */
    public MlJob(String name, UserEntity modifiedBy, ModelSchema modelSchema) {
        this.name = name;
        this.modifiedBy = modifiedBy;
        this.modelSchema = modelSchema;
        this.projectId = modelSchema.getProjectId();
    }

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets project id.
     *
     * @param projectId the project id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ModelSchema getModelSchema() {
        return modelSchema;
    }

    public void setModelSchema(ModelSchema modelSchema) {
        this.modelSchema = modelSchema;
    }
}
