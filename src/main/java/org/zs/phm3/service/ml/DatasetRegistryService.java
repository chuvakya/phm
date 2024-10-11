package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DatasetRegistry;

/**
 * Interaface DatasetRegistryService
 * @author Pavel Chuvak
 */
public interface DatasetRegistryService {

    /**
     * Getting size dataset registries by project ID
     * @param projectId project ID
     * @return size dataset registries
     */
    Long getSizeByProjectId(Integer projectId);

    /**
     * Saving dataset registry
     * @param datasetRegistry dataset registry
     */
    void save(DatasetRegistry datasetRegistry);
}
