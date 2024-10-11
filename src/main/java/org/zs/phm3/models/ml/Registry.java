package org.zs.phm3.models.ml;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for DataSchema, Dataset, ModelSchema
 * @author Pavel Chuvak
 */
@MappedSuperclass
public class Registry {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Project ID */
    private Integer projectId;

    /** Entity name */
    private String name;

    /** Empty constructor */
    public Registry() {
    }

    /**
     * Constructor
     * @param name entity name
     * @param projectId project ID
     */
    public Registry(String name, Integer projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    /**
     * Gets project ID.
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
     * Gets entity name.
     * @return entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets entity name
     * @param name entity name
     */
    public void setName(String name) {
        this.name = name;
    }
}
