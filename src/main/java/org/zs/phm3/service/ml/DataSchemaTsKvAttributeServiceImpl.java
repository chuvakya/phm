package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchemaTsKvAttribute;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.repository.ml.DataSchemaTsKvAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing DataSchemaTsKvAttributeService class
 * @author Pavel Chuvak
 */
@Service
public class DataSchemaTsKvAttributeServiceImpl implements DataSchemaTsKvAttributeService {

    /** Data schema ts kv attribute repository */
    @Autowired
    private DataSchemaTsKvAttributeRepository dataSchemaTsKvAttributeRepository;

    /**
     * Getting all attributes by data schema ID
     * @param dataSchemaId data schema ID
     * @return all attributes
     */
    @Override
    public List<TsKvAttribute> getAllAttributesByDataSchemaId(Long dataSchemaId) {
        return dataSchemaTsKvAttributeRepository.getAllAttributesByDataSchemaId(dataSchemaId);
    }

    /**
     * Saving data schema ts kv attribute
     * @param dataSchemaTsKvAttribute data schema ts kv attribute
     * @return data schema ts kv attribute
     */
    @Override
    public DataSchemaTsKvAttribute save(DataSchemaTsKvAttribute dataSchemaTsKvAttribute) {
        return dataSchemaTsKvAttributeRepository.save(dataSchemaTsKvAttribute);
    }

    /**
     * Deleting data schema ts kv attribute by data schema ID
     * @param dataSchemaId data schema ID
     */
    @Override
    public void deleteByDataSchemaIdSQL(Long dataSchemaId) {
        dataSchemaTsKvAttributeRepository.deleteByDataSchemaIdSQL(dataSchemaId);
    }
}
