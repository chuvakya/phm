package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.models.fmea.FailureReason;
import org.zs.phm3.models.fmea.Recommendation;
import org.zs.phm3.repository.fmea.FailureReasonRepository;
import org.zs.phm3.repository.fmea.RecommendationRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("recommendationService")
public class RecommendationServiceImpl implements CrudCommonService<Recommendation>, ToDaoService<Recommendation> {

    private final CrudCommonService<FailureMode> failureModeService;

    @Autowired // inject ServiceImpl
    public RecommendationServiceImpl(@Qualifier("failureModeService") CrudCommonService failureModeService) {
        this.failureModeService = failureModeService;
    }

    @Autowired
    RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getAll() {
        return (List<Recommendation>) recommendationRepository.findAll();
    }

    @Override
    public Recommendation save(Recommendation recommendation) throws PhmException {
        return recommendationRepository.save(toDaoData(recommendation));
    }

    @Override
    public void deleteById(Integer id) {
        recommendationRepository.deleteById(id);
    }

    @Override
    public Recommendation getById(Integer id) throws PhmException {
        return recommendationRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureDMode with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Recommendation toDaoData(Recommendation recommendation) throws PhmException {
        Recommendation rc = null;
        if (recommendation.isNew()) {
            recommendation.setFailureMode(failureModeService.getById(recommendation.getFailureModeId()));
            return recommendation;
        } else {
            rc = getById(recommendation.getId());
            BeanUtils.copyProperties(recommendation, rc, GetNullProperties.getNullPropertyNames(recommendation));
            return rc;
        }
    }
}
