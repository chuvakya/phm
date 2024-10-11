package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;

import java.util.List;

/**
 * Interface ModelAttrTypePTLService
 * @author Pavel Chuvak
 */
public interface ModelAttrTypePTLService {

    /**
     * Saving model attribute type PTL
     * @param modelAttrTypePTL model attribute type PTL
     * @return model attribute type PTL
     */
    ModelAttrTypePTL save(ModelAttrTypePTL modelAttrTypePTL);

    /**
     * Deleting model attribute type by model attribute type ID
     * @param modelAttrTypeId model attribute type ID
     */
    void deleteByIdSQL(Integer modelAttrTypeId);

    /**
     * Getting list all model attribute types PTL
     * @return list all model attribute types PTL
     */
    List<ModelAttrTypePTL> getAll();

    /**
     * Getting model attribute type PTL by model attribute type PTL ID
     * @param modelAttrTypeId model attribute type PTL ID
     * @return model attribute type PTL
     */
    ModelAttrTypePTL getById(Integer modelAttrTypeId);
}
