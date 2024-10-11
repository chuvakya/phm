package org.zs.phm3.service.ptl;

import org.springframework.web.bind.annotation.PathVariable;
import org.zs.phm3.models.ptl.BIT;

import java.util.List;
import java.util.Map;

/**
 * Interface BITService
 * @author Pavel Chuvak
 */
public interface BITService {

    /**
     * Saving BIT
     * @param bit bit
     * @return bit
     */
    BIT save(BIT bit);

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    String getAllByOffsetAndLimit(Integer offset, Integer limit);

    /**
     * Deleting by bit ID
     * @param bitId bit ID
     */
    void deleteByIdSQL(Long bitId);

    /**
     * Getting bit by bit ID
     * @param bitId bit ID
     * @return bit
     */
    BIT getById(Long bitId);

    /**
     * Getting count bit's
     * @return count bit's
     */
    Long getCount();

    /**
     * Getting map from list
     * @param list list
     * @return map
     */
    List<Map<String, String>> getMapFromList(List<String> list);

    /**
     * Getting list error codes by model ID
     * @param modelId model ID
     * @return list error codes
     */
    List<String> getErrorCodesByModelId(Long modelId);

    /**
     * Getting json string by bit ID
     * @param bitId bit ID
     * @return json string
     */
    String getSaveBITById(Long bitId);

    /**
     * Getting json string bit codes by ts kv attribute ID
     * @param tsKvAttributeId ts kv attribute ID
     * @return json string bit codes
     */
    String getBITCodesByTsKvAttributeId(Long tsKvAttributeId);
}
