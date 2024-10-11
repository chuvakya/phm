package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.ModelSchemaRegistry;

/**
 * Interface ModelSchemaRegistryService
 * @author Pavel Chuvak
 */
public interface ModelSchemaRegistryService {

    /**
     * Getting size model schema registry by project ID
     * @param projectId project ID
     * @return size model schema registry
     */
    Long getSizeByProjectId(Integer projectId);

    /**
     * Saving model schema registry
     * @param modelSchemaRegistry model schema registry
     */
    void save(ModelSchemaRegistry modelSchemaRegistry);
}
