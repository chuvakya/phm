package org.zs.phm3.models.ml;

import javax.persistence.Entity;

/**
 * Model schema registry names class
 * @author Pavel Chuvak
 */
@Entity
public class ModelSchemaRegistry extends Registry {

    /** Empty constructor */
    public ModelSchemaRegistry() {
        super();
    }

    /**
     * Constructor
     * @param name model schema name
     * @param projectId project ID
     */
    public ModelSchemaRegistry(String name, Integer projectId) {
        super(name, projectId);
    }
}
