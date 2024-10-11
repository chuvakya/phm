package org.zs.phm3.models.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ptl.ManufacturerPTL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintenance supplier entity
 * @author Pavel Chuvak
 */
@Entity
public class Supplier {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Supplier name */
    private String name;

    /** Is manufacturer */
    private Boolean isManufacturer;

    /** Is supplier */
    private Boolean isSupplier;

    /** Is tenant */
    private Boolean isTenant;

    /** Notes */
    private String notes;

    /** Is manufacturer PTL */
    private Boolean isManufacturerPTL;

    /** Currency */
    @Enumerated(EnumType.STRING)
    private Currency currency;

    /** Project ID */
    private Integer projectId;

    /** Contacts */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Constructor
     */
    public Supplier() {
    }

    /**
     * Constructor
     * @param name supplier name
     * @param isManufacturer is manufacturer
     * @param isSupplier is supplier
     * @param isTenant is tenant
     * @param notes notes
     * @param currency currency
     * @param projectId project ID
     * @param isManufacturerPTL is manufacturer PTL
     */
    public Supplier(String name, Boolean isManufacturer, Boolean isSupplier, Boolean isTenant,
                    String notes, Currency currency, Integer projectId, Boolean isManufacturerPTL) {
        this.name = name;
        this.isManufacturerPTL = isManufacturerPTL;
        this.isManufacturer = isManufacturer;
        this.isSupplier = isSupplier;
        this.isTenant = isTenant;
        this.notes = notes;
        this.currency = currency;
        this.projectId = projectId;
    }

    /**
     * Gets manufacturer PTL
     * @return manufacturer PTL
     */
    public Boolean getManufacturerPTL() {
        return isManufacturerPTL;
    }

    /**
     * Sets manufacturer PTL
     * @param manufacturerPTL manufacturer PTL
     */
    public void setManufacturerPTL(Boolean manufacturerPTL) {
        isManufacturerPTL = manufacturerPTL;
    }

    /**
     * Get project ID
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
     * Gets supplier name
     * @return supplier name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets supplier name
     * @param name supplier name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets is manufacturer
     * @return is manufacturer
     */
    public Boolean getManufacturer() {
        return isManufacturer;
    }

    /**
     * Sets is manufacturer
     * @param manufacturer manufacturer
     */
    public void setManufacturer(Boolean manufacturer) {
        isManufacturer = manufacturer;
    }

    /**
     * Gets is supplier
     * @return is supplier
     */
    public Boolean getSupplier() {
        return isSupplier;
    }

    /**
     * Sets is supplier
     * @param supplier is supplier
     */
    public void setSupplier(Boolean supplier) {
        isSupplier = supplier;
    }

    /**
     * Gets is tenant
     * @return
     */
    public Boolean getTenant() {
        return isTenant;
    }

    /**
     * Gets is tenant
     * @param tenant is tenant
     */
    public void setTenant(Boolean tenant) {
        isTenant = tenant;
    }

    /**
     * Gets notes
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes
     * @param notes notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets currency
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets currency
     * @param currency currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Gets contacts
     * @return contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Sets contacts
     * @param contacts contacts
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
