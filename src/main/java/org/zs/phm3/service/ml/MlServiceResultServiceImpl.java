package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.MlServiceResult;
import org.zs.phm3.repository.ml.MlServiceResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing MlServiceResultService class
 * @author Pavel Chuvak
 */
@Service
public class MlServiceResultServiceImpl implements MlServiceResultService {

    /** Ml service result repository */
    @Autowired
    private MlServiceResultRepository mlServiceResultRepository;

    /**
     * Saving ml service result
     * @param mlServiceResult ml service result
     */
    @Override
    public void save(MlServiceResult mlServiceResult) {
        mlServiceResultRepository.save(mlServiceResult);
    }

    /**
     * Getting all ml service result
     * @return all ml service result
     */
    @Override
    public List<MlServiceResult> findAll() {
        return (List<MlServiceResult>) mlServiceResultRepository.findAll();
    }

    /**
     * Getting ml service results from ml model and unit ID
     * @param mlModel ml model
     * @param unitId unit ID
     * @return ml service results
     */
    @Override
    public List<MlServiceResult> getResultsFromMlModelAndUnit(Long mlModel, String unitId) {
        return mlServiceResultRepository.getAllForUnitAndMlModel(unitId, mlModel);
    }

    /**
     * Deleting ml service result by ml service result ID
     * @param id ml service result ID
     */
    @Override
    public void delete(Long id) {
        mlServiceResultRepository.deleteById(id);
    }
}
