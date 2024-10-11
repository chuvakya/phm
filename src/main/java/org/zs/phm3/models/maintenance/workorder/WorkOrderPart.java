package org.zs.phm3.models.maintenance.workorder;

import org.zs.phm3.models.maintenance.part.Part;
import org.zs.phm3.models.maintenance.part.PartStock;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderTemplate;

import javax.persistence.*;

/**
 * Work order part
 * @author Pavel Chuvak
 */
@Entity
public class WorkOrderPart {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Part */
    @ManyToOne
    private Part part;

    /** Part stock */
    @ManyToOne
    private PartStock partStock;

    /** Actual quantity used */
    private Integer actualQuantityUsed;

    /** Work order */
    @ManyToOne
    private WorkOrder workOrder;

    public WorkOrderPart() {
    }

    public WorkOrderPart(Part part, PartStock partStock, Integer actualQuantityUsed, WorkOrder workOrder) {
        this.part = part;
        this.partStock = partStock;
        this.actualQuantityUsed = actualQuantityUsed;
        this.workOrder = workOrder;
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
     * Gets work order
     * @return work order
     */
    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    /**
     * Sets work order
     * @param workOrder work order
     */
    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
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

}
