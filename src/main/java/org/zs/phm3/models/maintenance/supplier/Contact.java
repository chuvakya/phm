package org.zs.phm3.models.maintenance.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Maintenance supplier contact entity
 * @author Pavel Chuvak
 */
@Entity
public class Contact {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Contact name */
    private String contactName;

    /** Description */
    private String description;

    /** Supplier */
    @JsonIgnore
    @ManyToOne
    private Supplier supplier;

    /**
     * Constructor
     */
    public Contact() {
    }

    /**
     * Constructor
     * @param contactName contact name
     * @param description description
     * @param supplier supplier
     */
    public Contact(String contactName, String description, Supplier supplier) {
        this.contactName = contactName;
        this.description = description;
        this.supplier = supplier;
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
     * Gets contact name
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name
     * @param contactName contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets supplier
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets supplier
     * @param supplier supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
