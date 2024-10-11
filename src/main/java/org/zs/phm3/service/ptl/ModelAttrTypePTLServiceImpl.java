package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.zs.phm3.repository.ptl.ModelAttrTypePTLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing ModelAttrTypePTLService class
 * @author Pavel Chuvak
 */
@Service
public class ModelAttrTypePTLServiceImpl implements ModelAttrTypePTLService {

    /** Model attribute type PTL repository */
    @Autowired
    private ModelAttrTypePTLRepository modelAttrTypePTLRepository;

    /**
     * Saving model attribute type PTL
     * @param modelAttrTypePTL model attribute type PTL
     * @return model attribute type PTL
     */
    @Override
    public ModelAttrTypePTL save(ModelAttrTypePTL modelAttrTypePTL) {
        return modelAttrTypePTLRepository.save(modelAttrTypePTL);
    }

    /**
     * Deleting model attribute type by model attribute type ID
     * @param modelAttrTypeId model attribute type ID
     */
    @Override
    public void deleteByIdSQL(Integer modelAttrTypeId) {
        modelAttrTypePTLRepository.deleteByIdSQL(modelAttrTypeId);
    }

    /**
     * Getting list all model attribute types PTL
     * @return list all model attribute types PTL
     */
    @Override
    public List<ModelAttrTypePTL> getAll() {
        return (List<ModelAttrTypePTL>) modelAttrTypePTLRepository.findAll();
    }

    /**
     * Getting model attribute type PTL by model attribute type PTL ID
     * @param modelAttrTypeId model attribute type PTL ID
     * @return model attribute type PTL
     */
    @Override
    public ModelAttrTypePTL getById(Integer modelAttrTypeId) {
        return modelAttrTypePTLRepository.findById(modelAttrTypeId).get();
    }
}
