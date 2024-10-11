package org.zs.phm3.models.ptl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the types of model attributes
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "model_attr_type_ptl")
public class ModelAttrTypePTL {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Type name */
    private String name;

    /** Model attributes PTL */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "modelAttrTypePTL")
    private List<ModelAttributePTL> modelAttributePTLS = new ArrayList<>();

    /** Constructor */
    public ModelAttrTypePTL() {
    }

    /**
     * Constructor
     * @param name type name
     */
    public ModelAttrTypePTL(String name) {
        this.name = name;
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
     * Gets type name
     * @return type name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets type name
     * @param name type name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets model attributes PTL
     * @return model attributes PTL
     */
    public List<ModelAttributePTL> getModelAttributePTLS() {
        return modelAttributePTLS;
    }

    /**
     * Sets model attributes PTL
     * @param modelAttributePTLS model attributes PTL
     */
    public void setModelAttributePTLS(List<ModelAttributePTL> modelAttributePTLS) {
        this.modelAttributePTLS = modelAttributePTLS;
    }
}
