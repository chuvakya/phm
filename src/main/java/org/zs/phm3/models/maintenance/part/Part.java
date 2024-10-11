package org.zs.phm3.models.maintenance.part;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.maintenance.scheduled.WorkOrderPartTemplate;
import org.zs.phm3.models.maintenance.workorder.WorkOrderPart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance part entity
 * @author Pavel Chuvak
 */
@Entity
public class Part {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Part name */
    private String name;

    /** Storage */
    private String storage;

    /** Total stock */
    private Long totalStock;

    /** Part stock list */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    private List<PartStock> partStockList = new ArrayList<>();

    /** Part category */
    @JsonIgnore
    @ManyToOne
    private PartCategory partCategory;

    /** Work order parts */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "part")
    private List<WorkOrderPart> workOrderParts = new ArrayList<>();

    /** Work order part templates */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "workOrderTemplate")
    private List<WorkOrderPartTemplate> workOrderPartTemplates = new ArrayList<>();

    /** Project ID */
    private Integer projectId;

    /**
     * Constructor
     */
    public Part() {
    }

    /**
     * Constructor
     * @param name maintenance part name
     * @param projectId project ID
     * @param partCategory part category
     */
    public Part(String name, Integer projectId, PartCategory partCategory) {
        this.name = name;
        this.partCategory = partCategory;
        this.projectId = projectId;
    }

    /**
     * Gets work order part templates
     * @return work order part templates
     */
    public List<WorkOrderPartTemplate> getWorkOrderPartTemplates() {
        return workOrderPartTemplates;
    }

    /**
     * Sets work order part templates
     * @param workOrderPartTemplates work order part templates
     */
    public void setWorkOrderPartTemplates(List<WorkOrderPartTemplate> workOrderPartTemplates) {
        this.workOrderPartTemplates = workOrderPartTemplates;
    }

    /**
     * Gets work order parts
     * @return work order parts
     */
    public List<WorkOrderPart> getWorkOrderParts() {
        return workOrderParts;
    }

    /**
     * Sets work order parts
     * @param workOrderParts work order parts
     */
    public void setWorkOrderParts(List<WorkOrderPart> workOrderParts) {
        this.workOrderParts = workOrderParts;
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
     * Gets total stock
     * @return total stock
     */
    public Long getTotalStock() {
        return totalStock;
    }

    /**
     * Sets total stock
     * @param totalStock total stock
     */
    public void setTotalStock(Long totalStock) {
        this.totalStock = totalStock;
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
     * Gets maintenance part name
     * @return maintenance part name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets maintenance part name
     * @param name maintenance part name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets maintenance part stocks
     * @return maintenance part stocks
     */
    public List<PartStock> getPartStockList() {
        return partStockList;
    }

    /**
     * Sets maintenance part stocks
     * @param partStockList maintenance part stocks
     */
    public void setPartStockList(List<PartStock> partStockList) {
        this.partStockList = partStockList;
    }

    /**
     * Gets part category
     * @return part category
     */
    public PartCategory getPartCategory() {
        return partCategory;
    }

    /**
     * Sets part category
     * @param partCategory part category
     */
    public void setPartCategory(PartCategory partCategory) {
        this.partCategory = partCategory;
    }
}
