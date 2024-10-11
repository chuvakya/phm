package org.zs.phm3.models.calculation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculation attribute type
 * @author Pavel Chuvak
 */
@Entity
public class CalculationAttrType {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Type name */
    private String name;

    /** Attributes */
    @OneToMany(mappedBy = "attrType")
    private List<CalculationAttribute> attributeList = new ArrayList<>();

    /** Constructor */
    public CalculationAttrType() {}

    /**
     * Sets type name
     * @param name type name
     */
    public CalculationAttrType(String name) {
        this.name = name;
    }

    /**
     * Gets attributes
     * @return attributes
     */
    public List<CalculationAttribute> getAttributeList() {
        return attributeList;
    }

    /**
     * Sets attributes
     * @param attributeList attributes
     */
    public void setAttributeList(List<CalculationAttribute> attributeList) {
        this.attributeList = attributeList;
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
     * Gets attribute name
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets attribute name
     * @param name attribute name
     */
    public void setName(String name) {
        this.name = name;
    }
}
