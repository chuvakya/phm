package org.zs.phm3.service.ml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.ml.ModelSchemaRegistry;
import org.zs.phm3.repository.ml.ModelSchemaRegistryRepository;

/**
 * Implementing ModelSchemaRegistryService class
 * @author Pavel Chuvak
 */
@Service
public class ModelSchemaRegistryServiceImpl implements ModelSchemaRegistryService {

    /** Model Schema registry repository */
    @Autowired
    private ModelSchemaRegistryRepository modelSchemaRegistryRepository;

    /**
     * Getting size model schema registry by project ID
     * @param projectId project ID
     * @return size model schema registry
     */
    @Override
    public Long getSizeByProjectId(Integer projectId) {
        return modelSchemaRegistryRepository.getSizeByProjectId(projectId);
    }

    /**
     * Saving model schema registry
     * @param modelSchemaRegistry model schema registry
     */
    @Override
    public void save(ModelSchemaRegistry modelSchemaRegistry) {
        modelSchemaRegistryRepository.save(modelSchemaRegistry);
    }
}
