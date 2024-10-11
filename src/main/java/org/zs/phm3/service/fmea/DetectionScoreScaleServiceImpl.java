package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.DetectionScoreScale;
import org.zs.phm3.models.fmea.FailureSeverity;
import org.zs.phm3.repository.fmea.DetectionScoreScaleRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("detectionScoreScaleService")
public class DetectionScoreScaleServiceImpl implements CrudCommonServiceWithStringId<DetectionScoreScale>,
        ToDaoService<DetectionScoreScale> {

    @Autowired
    DetectionScoreScaleRepository detectionScoreScaleRepository;

    @Override
    public List<DetectionScoreScale> getAll() {
        return (List<DetectionScoreScale>) detectionScoreScaleRepository.findAll();
    }

    @Override
    public DetectionScoreScale save(DetectionScoreScale detectionScoreScale) throws PhmException {
        return detectionScoreScaleRepository.save(toDaoData(detectionScoreScale));
    }

    @Override
    public void deleteById(String id) {
        detectionScoreScaleRepository.deleteById(id);
    }

    @Override
    public DetectionScoreScale getById(String id) throws PhmException {
        return detectionScoreScaleRepository.findById(id).orElseThrow(() -> (new
                PhmException("SignificanceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (detectionScoreScaleRepository.countById(id) == 0);
    }

    @Override
    public DetectionScoreScale toDaoData(DetectionScoreScale detectionScoreScale) throws PhmException {
        if (isNew(detectionScoreScale.getId())) {
            return detectionScoreScale;
        } else {
            DetectionScoreScale dss = getById(detectionScoreScale.getId());
            BeanUtils.copyProperties(detectionScoreScale, dss, GetNullProperties.getNullPropertyNames(detectionScoreScale));
            return dss;
        }
    }
}
