package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureConsequence;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.repository.fmea.FailureConsequenceRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureConsequenceService")
public class FailureConsequenceServiceImpl implements CrudCommonService<FailureConsequence>,
        ToDaoService<FailureConsequence> {

    private final CrudCommonService<FailureMode> failureModeService;

    @Autowired // inject ServiceImpl
    public FailureConsequenceServiceImpl(@Qualifier("failureModeService") CrudCommonService failureModeService) {
        this.failureModeService = failureModeService;
    }

    @Autowired
    FailureConsequenceRepository failureConsequenceRepository;

    @Override
    public List<FailureConsequence> getAll() {
        return (List<FailureConsequence>) failureConsequenceRepository.findAll();
    }

    @Override
    public FailureConsequence save(FailureConsequence failureConsequence) throws PhmException {
        return failureConsequenceRepository.save(toDaoData(failureConsequence));
    }

    @Override
    public void deleteById(Integer id) {
        failureConsequenceRepository.deleteById(id);
    }

    @Override
    public FailureConsequence getById(Integer id) throws PhmException {
        return failureConsequenceRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureDMode with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public FailureConsequence toDaoData(FailureConsequence failureConsequence) throws PhmException {
        FailureConsequence fc = null;
        if (failureConsequence.isNew()) {
            fc = new FailureConsequence();
            fc.setFailureMode(failureModeService.getById(failureConsequence.getFailureModeId()));
        } else {
            fc = getById(failureConsequence.getId());
        }

        BeanUtils.copyProperties(failureConsequence, fc, GetNullProperties.getNullPropertyNames(failureConsequence));
        return fc;
    }
}
