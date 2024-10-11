package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchemaRegistry;

/**
 * Interface DataSchemaRegistryService
 * @author Pavel Chuvak
 */
public interface DataSchemaRegistryService {

    /**
     * Getting size data schema registries by project ID
     * @param projectId project ID
     * @return size data schema registries
     */
    Long getSizeByProjectId(Integer projectId);

    /**
     * Saving data schema registry
     * @param dataSchemaRegistry data schema registry
     */
    void save(DataSchemaRegistry dataSchemaRegistry);
}
