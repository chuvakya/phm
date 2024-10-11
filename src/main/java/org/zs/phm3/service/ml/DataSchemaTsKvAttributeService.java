package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchemaTsKvAttribute;
import org.zs.phm3.models.ts.TsKvAttribute;

import java.util.List;

/**
 * Interface DataSchemaTsKvAttributeService
 * @author Pavel Chuvak
 */
public interface DataSchemaTsKvAttributeService {

    /**
     * Getting all attributes
     * @param dataSchemaId data schema ID
     * @return all attributes
     */
    List<TsKvAttribute> getAllAttributesByDataSchemaId(Long dataSchemaId);

    /**
     * Saving data schema ts kv attribute by data schema ID
     * @param dataSchemaTsKvAttribute data schema ts kv attribute
     * @return data schema ts kv attribute
     */
    DataSchemaTsKvAttribute save(DataSchemaTsKvAttribute dataSchemaTsKvAttribute);

    /**
     * Deleting data schema ts kv attribute by data schema ID
     * @param dataSchemaId data schema ID
     */
    void deleteByDataSchemaIdSQL(Long dataSchemaId);
}
