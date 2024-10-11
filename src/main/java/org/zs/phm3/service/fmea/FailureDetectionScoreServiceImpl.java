package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureDetectionScore;
import org.zs.phm3.repository.fmea.FailureDetectionScoreRepository;
import org.zs.phm3.service.fmea.CrudCommonServiceWithStringId;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureDetectionScoreService")
public class FailureDetectionScoreServiceImpl implements CrudCommonServiceWithStringId<FailureDetectionScore>,
        ToDaoService<FailureDetectionScore> {

    @Autowired
    FailureDetectionScoreRepository failureDetectionScoreRepository;

    @Override
    public List<FailureDetectionScore> getAll() {
        return (List<FailureDetectionScore>) failureDetectionScoreRepository.findAll();
    }

    @Override
    public FailureDetectionScore save(FailureDetectionScore failureDetectionScore) throws PhmException {
        return failureDetectionScoreRepository.save(toDaoData(failureDetectionScore));
    }

    @Override
    public void deleteById(String id) {
        failureDetectionScoreRepository.deleteById(id);
    }

    @Override
    public FailureDetectionScore getById(String id) throws PhmException {
        return failureDetectionScoreRepository.findById(id).orElseThrow(() -> (new
                PhmException("OccurrenceScoreScale with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (failureDetectionScoreRepository.countById(id)==0);
    }

    @Override
    public FailureDetectionScore toDaoData(FailureDetectionScore failureDetectionScore) throws PhmException {
        if (isNew(failureDetectionScore.getId())) {
            return failureDetectionScore;
        }else{
            FailureDetectionScore fds = getById(failureDetectionScore.getId());
            BeanUtils.copyProperties(failureDetectionScore, fds, GetNullProperties.getNullPropertyNames(failureDetectionScore));
            return fds;
        }
    }
}
