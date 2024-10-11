package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchemaRegistry;
import org.zs.phm3.repository.ml.DataSchemaRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementing DataSchemaRegistryService class
 * @author Pavel Chuvak
 */
@Service
public class DataSchemaRegistryServiceImpl implements DataSchemaRegistryService {

    /** Data schema registry repository */
    @Autowired
    private DataSchemaRegistryRepository dataSchemaRegistryRepository;

    /**
     * Getting size data schema registries by project ID
     * @param projectId project ID
     * @return size data schema registries
     */
    @Override
    public Long getSizeByProjectId(Integer projectId) {
        return dataSchemaRegistryRepository.getSizeByProjectId(projectId);
    }

    /**
     * Saving data schema registry
     * @param dataSchemaRegistry data schema registry
     */
    @Override
    public void save(DataSchemaRegistry dataSchemaRegistry) {
        dataSchemaRegistryRepository.save(dataSchemaRegistry);
    }


}
