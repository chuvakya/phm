package org.zs.phm3.models.maintenance.scheduled;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.maintenance.part.Part;
import org.zs.phm3.models.maintenance.part.PartStock;
import org.zs.phm3.models.maintenance.workorder.WorkOrder;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPart;

import javax.persistence.*;

/**
 * Scheduled maintenance work order part template
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "maintenance_work_order_part_template")
public class WorkOrderPartTemplate {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Part */
    @JsonIgnore
    @ManyToOne
    private Part part;

    /** Part ID */
    @Transient
    private Long partId;

    /** Part stock ID */
    @Transient
    private Long partStockId;

    /** Part stock */
    @JsonIgnore
    @ManyToOne
    private PartStock partStock;

    /** Actual quantity used */
    private Integer actualQuantityUsed;

    /** Work order template */
    @JsonIgnore
    @ManyToOne
    private WorkOrderTemplate workOrderTemplate;

    /**
     * Constructor
     */
    public WorkOrderPartTemplate() {
    }

    /**
     * Constructor
     * @param part part
     * @param partStock part stock
     * @param actualQuantityUsed actual quantity used
     * @param workOrderTemplate work order template
     */
    public WorkOrderPartTemplate(Part part, PartStock partStock, Integer actualQuantityUsed, WorkOrderTemplate workOrderTemplate) {
        this.part = part;
        this.partStock = partStock;
        this.actualQuantityUsed = actualQuantityUsed;
        this.workOrderTemplate = workOrderTemplate;
    }

    /**
     * Gets part stock
     * @return part stock
     */
    public PartStock getPartStock() {
        return partStock;
    }

    /**
     * Sets part stock
     * @param partStock part stock
     */
    public void setPartStock(PartStock partStock) {
        this.partStock = partStock;
    }

    /**
     * Gets actual quantity used
     * @return actual quantity used
     */
    public Integer getActualQuantityUsed() {
        return actualQuantityUsed;
    }

    /**
     * Sets actual quantity used
     * @param actualQuantityUsed actual quantity used
     */
    public void setActualQuantityUsed(Integer actualQuantityUsed) {
        this.actualQuantityUsed = actualQuantityUsed;
    }

    /**
     * Gets work order template
     * @return work order template
     */
    public WorkOrderTemplate getWorkOrderTemplate() {
        return workOrderTemplate;
    }

    /**
     * Sets work order template
     * @param workOrderTemplate work order template
     */
    public void setWorkOrderTemplate(WorkOrderTemplate workOrderTemplate) {
        this.workOrderTemplate = workOrderTemplate;
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
     * Gets part
     * @return part
     */
    public Part getPart() {
        return part;
    }

    /**
     * Sets part
     * @param part part
     */
    public void setPart(Part part) {
        this.part = part;
    }

    /**
     * Gets part ID
     * @return part ID
     */
    public Long getPartId() {
        return partId;
    }

    /**
     * Sets part ID
     * @param partId part ID
     */
    public void setPartId(Long partId) {
        this.partId = partId;
    }

    /**
     * Gets part stock ID
     * @return part stock ID
     */
    public Long getPartStockId() {
        return partStockId;
    }

    /**
     * Sets part stock ID
     * @param partStockId part stock ID
     */
    public void setPartStockId(Long partStockId) {
        this.partStockId = partStockId;
    }
}
