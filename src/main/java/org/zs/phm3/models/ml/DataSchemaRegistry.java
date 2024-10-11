package org.zs.phm3.models.ml;

import javax.persistence.Entity;

/**
 * This class is responsible for the naming registry for the data schema. Heir to the registry.
 * @author Pavel Chuvak
 */
@Entity
public class DataSchemaRegistry extends Registry {

    /**
     * Empty constructor
     */
    public DataSchemaRegistry() {
        super();
    }

    /**
     * Constructor for DataSchemaRegistry
     * @param name data schema name
     * @param projectId project ID
     */
    public DataSchemaRegistry(String name, Integer projectId) {
        super(name, projectId);
    }
}
