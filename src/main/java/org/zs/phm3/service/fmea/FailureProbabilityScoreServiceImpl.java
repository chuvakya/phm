package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureProbabilityScore;
import org.zs.phm3.repository.fmea.FailureProbabilityScoreRepository;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureProbabilityScoreService")
public class FailureProbabilityScoreServiceImpl implements CrudCommonServiceWithStringId<FailureProbabilityScore>,
        ToDaoService<FailureProbabilityScore> {

    @Autowired
    FailureProbabilityScoreRepository FailureProbabilityScoreRepository;

    @Override
    public List<FailureProbabilityScore> getAll() {
        return (List<FailureProbabilityScore>) FailureProbabilityScoreRepository.findAll();
    }

    @Override
    public FailureProbabilityScore save(FailureProbabilityScore failureProbabilityScore) throws PhmException {
        return FailureProbabilityScoreRepository.save(toDaoData(failureProbabilityScore));
    }

    @Override
    public void deleteById(String id) {
        FailureProbabilityScoreRepository.deleteById(id);
    }

    @Override
    public FailureProbabilityScore getById(String id) throws PhmException {
        return FailureProbabilityScoreRepository.findById(id).orElseThrow(() -> (new
                PhmException("OccurrenceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (FailureProbabilityScoreRepository.countById(id)==0);
    }

    @Override
    public FailureProbabilityScore toDaoData(FailureProbabilityScore failureProbabilityScore) throws PhmException {
        if (isNew(failureProbabilityScore.getId())) {
            return failureProbabilityScore;
        }else{
            FailureProbabilityScore fps = getById(failureProbabilityScore.getId());
            BeanUtils.copyProperties(failureProbabilityScore, fps, GetNullProperties.getNullPropertyNames(failureProbabilityScore));
            return fps;
        }
    }
}
