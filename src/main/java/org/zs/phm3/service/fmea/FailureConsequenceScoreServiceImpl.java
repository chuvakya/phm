package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureConsequenceScore;
import org.zs.phm3.repository.fmea.FailureConsequenceScoreRepository;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureConsequenceScoreService")
public class FailureConsequenceScoreServiceImpl implements CrudCommonServiceWithStringId<FailureConsequenceScore>,
        ToDaoService<FailureConsequenceScore> {

    @Autowired
    FailureConsequenceScoreRepository failureConsequenceScoreRepository;

    @Override
    public List<FailureConsequenceScore> getAll() {
        return (List<FailureConsequenceScore>) failureConsequenceScoreRepository.findAll();
    }

    @Override
    public FailureConsequenceScore save(FailureConsequenceScore failureConsequenceScore) throws PhmException {
        return failureConsequenceScoreRepository.save(toDaoData(failureConsequenceScore));
    }

    @Override
    public void deleteById(String id) {
        failureConsequenceScoreRepository.deleteById(id);
    }

    @Override
    public FailureConsequenceScore getById(String id) throws PhmException {
        return failureConsequenceScoreRepository.findById(id).orElseThrow(() -> (new
                PhmException("OccurrenceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (failureConsequenceScoreRepository.countById(id)==0);
    }

    @Override
    public FailureConsequenceScore toDaoData(FailureConsequenceScore failureConsequenceScoreRepository) throws PhmException {
        if (isNew(failureConsequenceScoreRepository.getId())) {
            return failureConsequenceScoreRepository;
        }else{
            FailureConsequenceScore fcs = getById(failureConsequenceScoreRepository.getId());
            BeanUtils.copyProperties(failureConsequenceScoreRepository, fcs, GetNullProperties.getNullPropertyNames(failureConsequenceScoreRepository));
            return fcs;
        }
    }
}
