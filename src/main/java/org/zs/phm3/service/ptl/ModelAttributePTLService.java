package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.ModelAttributePTL;

import java.util.List;

/**
 * Interface ModelAttributePTLService
 * @author Pavel Chuvak
 */
public interface ModelAttributePTLService {

    /**
     * Saving model attribute PTL
     * @param modelAttributePTL model attribute PTL
     * @return model attribute PTL
     */
    ModelAttributePTL save(ModelAttributePTL modelAttributePTL);

    /**
     * Deleting model attribute PTL by model attribute ID
     * @param modelAttributeId model attribute ID
     */
    void deleteByIdSQL(Long modelAttributeId);

    /**
     * Getting list all model attributes PTL
     * @return list all model attributes PTL
     */
    List<ModelAttributePTL> getAll();

    /**
     * Getting model attribute PTL by model attribute PTL ID
     * @param modelAttributeId model attribute PTL ID
     * @return model attribute PTL
     */
    ModelAttributePTL getById(Long modelAttributeId);

    /**
     * Getting json string by model PTL ID
     * @param modelId model PTL ID
     * @return json string
     */
    String getAllByModelId(Long modelId);

}
