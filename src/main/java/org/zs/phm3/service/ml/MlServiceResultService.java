package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.MlServiceResult;

import java.util.List;

/**
 * Interface MlServiceResultService
 * @author Pavel Chuvak
 */
public interface MlServiceResultService {

    /**
     * Saving ml service result
     * @param mlServiceResult ml service result
     */
    void save(MlServiceResult mlServiceResult);

    /**
     * Getting all ml service result
     * @return all ml service result
     */
    List<MlServiceResult> findAll();

    /**
     * Getting ml service results from ml model and unit ID
     * @param mlModel ml model
     * @param unitId unit ID
     * @return ml service results
     */
    List<MlServiceResult> getResultsFromMlModelAndUnit(Long mlModel, String unitId);

    /**
     * Deleting ml service result by ml service result ID
     * @param id ml service result ID
     */
    void delete(Long id);
}
