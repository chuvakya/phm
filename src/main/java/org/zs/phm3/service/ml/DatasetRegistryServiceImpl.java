package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DatasetRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.repository.ml.DatasetRegistryRepository;

/**
 * Implementing DatasetRegistryService class
 * @author Pavel Chuvak
 */
@Service
public class DatasetRegistryServiceImpl implements DatasetRegistryService {

    /** Dataset registry repository */
    @Autowired
    private DatasetRegistryRepository datasetRegistryRepository;

    /**
     * Getting size dataset registries by project ID
     * @param projectId project ID
     * @return size dataset registries
     */
    @Override
    public Long getSizeByProjectId(Integer projectId) {
        return datasetRegistryRepository.getSizeByProjectId(projectId);
    }

    /**
     * Saving dataset registry
     * @param datasetRegistry dataset registry
     */
    @Override
    public void save(DatasetRegistry datasetRegistry) {
        datasetRegistryRepository.save(datasetRegistry);
    }

}
