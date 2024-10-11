package org.zs.phm3.models.maintenance.part;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance part category entity
 * @author Pavel Chuvak
 */
@Entity
public class PartCategory {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Part category name */
    private String name;

    /** Project ID */
    @JsonIgnore
    private Integer projectId;

    /** Parts */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "partCategory")
    private List<Part> parts = new ArrayList<>();

    /**
     * Constructor
     */
    public PartCategory() {}

    /**
     * Constructor
     * @param name maintenance part category name
     * @param projectId project ID
     */
    public PartCategory(String name, Integer projectId) {
        this.name = name;
        this.projectId = projectId;
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
     * Gets maintenance parts
     * @return maintenance parts
     */
    public List<Part> getParts() {
        return parts;
    }

    /**
     * Sets maintenance parts
     * @param parts maintenance parts
     */
    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets maintenance part category name
     * @return maintenance part category name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets maintenance part category name
     * @param name maintenance part category name
     */
    public void setName(String name) {
        this.name = name;
    }
}
