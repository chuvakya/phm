package org.zs.phm3.models.maintenance.part;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance part stock entity
 * @author Pavel Chuvak
 */
@Entity
public class PartStock {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Storage */
    private String storage;

    /** Aisle */
    private Integer aisle;

    /** Row */
    private Integer row;

    /** Cell */
    private Integer cell;

    /** Quantity */
    private Integer quantity;

    /** Min quantity */
    private Integer minQuantity;

    /** Part */
    @JsonIgnore
    @ManyToOne
    private Part part;

    /** Work order part */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "partStock")
    private List<WorkOrderPart> workOrderParts = new ArrayList<>();

    /** Work order part template */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "workOrderTemplate")
    private List<WorkOrderPartTemplate> workOrderPartTemplates = new ArrayList<>();

    /**
     * Constructor
     */
    public PartStock() {
    }

    /**
     * Constructor
     * @param storage storage
     * @param aisle aisle
     * @param row row
     * @param cell cell
     * @param quantity quantity
     * @param minQuantity min quantity
     * @param part maintenance part
     */
    public PartStock(String storage, Integer aisle, Integer row, Integer cell, Integer quantity, Integer minQuantity,
                     Part part) {
        this.storage = storage;
        this.aisle = aisle;
        this.part = part;
        this.row = row;
        this.cell = cell;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    /**
     * Gets maintenance work order part templates
     * @return maintenance work order part templates
     */
    public List<WorkOrderPartTemplate> getWorkOrderPartTemplates() {
        return workOrderPartTemplates;
    }

    /**
     * Sets maintenance work order part templates
     * @param workOrderPartTemplates maintenance work order part templates
     */
    public void setWorkOrderPartTemplates(List<WorkOrderPartTemplate> workOrderPartTemplates) {
        this.workOrderPartTemplates = workOrderPartTemplates;
    }

    /**
     * Gets maintenance work order parts
     * @return maintenance work order parts
     */
    public List<WorkOrderPart> getWorkOrderParts() {
        return workOrderParts;
    }

    /**
     * Sets maintenance work order parts
     * @param workOrderParts maintenance work order parts
     */
    public void setWorkOrderParts(List<WorkOrderPart> workOrderParts) {
        this.workOrderParts = workOrderParts;
    }

    /**
     * Gets maintenance part
     * @return maintenance part
     */
    public Part getPart() {
        return part;
    }

    /**
     * Sets maintenance part
     * @param part maintenance part
     */
    public void setPart(Part part) {
        this.part = part;
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
     * Gets storage
     * @return storage
     */
    public String getStorage() {
        return storage;
    }

    /**
     * Sets storage
     * @param storage storage
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * Gets aisle
     * @return aisle
     */
    public Integer getAisle() {
        return aisle;
    }

    /**
     * Sets aisle
     * @param aisle aisle
     */
    public void setAisle(Integer aisle) {
        this.aisle = aisle;
    }

    /**
     * Gets row
     * @return row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * Sets row
     * @param row row
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * Gets cell
     * @return cell
     */
    public Integer getCell() {
        return cell;
    }

    /**
     * Sets cell
     * @param cell cell
     */
    public void setCell(Integer cell) {
        this.cell = cell;
    }

    /**
     * Gets quantity
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity
     * @param quantity quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets min quantity
     * @return min quantity
     */
    public Integer getMinQuantity() {
        return minQuantity;
    }

    /**
     * Sets min quantity
     * @param minQuantity
     */
    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }
}
