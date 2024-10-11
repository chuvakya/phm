package org.zs.phm3.models.ml;

import javax.persistence.*;

/**
 * Registry of names for datasets.
 * @author Pavel Chuvak
 */
@Entity
public class DatasetRegistry extends Registry {

    /** Empty constructor for DatasetRegistry */
    public DatasetRegistry() {
        super();
    }

    /**
     * Constructor
     * @param name dataset name
     * @param projectId project ID
     */
    public DatasetRegistry(String name, Integer projectId) {
        super(name, projectId);
    }
}
