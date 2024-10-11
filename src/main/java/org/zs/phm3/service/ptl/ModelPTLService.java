package org.zs.phm3.service.ptl;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.models.ptl.ModelPTL;

import java.util.List;

/**
 * Interface ModelPTLService
 * @author Pavel Chuvak
 */
public interface ModelPTLService {

    /**
     * Saving model PTL
     * @param modelPTL model PTL
     * @return model PTL
     */
    ModelPTL save(ModelPTL modelPTL);

    /**
     * Deleting model PTL by model PTL ID
     * @param modelId model PTL ID
     */
    void deleteByIdSQL(Long modelId);

    /**
     * Getting model PTL by model PTL ID
     * @param modelId model PTL ID
     * @return model PTL
     */
    ModelPTL getById(Long modelId);

    /**
     * Getting json string by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return json string
     */
    String getAllManufacturersAndModelsByUnitTypeId(Integer unitTypeId);

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    String getListByOffsetAndLimit(Integer offset, Integer limit);

    /**
     * Getting list models PTL by manufacturer PTL ID and unit type PTL ID
     * @param manufacturerId manufacturer PTL
     * @param unitTypeId unit type PTL ID
     * @return list models PTL
     */
    List<ModelPTL> getAllByManufacturerIdAndUnitTypeId(Long manufacturerId, Integer unitTypeId);

    /**
     * Getting json string by manufacturer PTL ID and unit type PTL ID
     * @param manufacturerId manufacturer PTL ID
     * @param unitTypeId unit type PTL ID
     * @return json string
     */
    String getAllIdAndNameByManufacturerIdAndUnitTypeId(Long manufacturerId, Integer unitTypeId);

    /**
     * Getting list manufacturers PTL by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return list manufacturers PTL
     */
    List<ManufacturerPTL> getAllManufacturersByUnitTypeId(Integer unitTypeId);
    ModelPTL getEmptyModelForUnitType(Integer unitTypeId) throws PhmException;

    /**
     * Getting count models PTL
     * @return count models PTL
     */
    Long getCount();

    /**
     * Getting json string by model PTL name
     * @param modelName model PTL name
     * @return json string
     */
    String getAllIdAndRevisionByModelName(String modelName);
}
