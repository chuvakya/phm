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
import org.zs.phm3.repository.fmea.FailureReasonRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureReasonService")
public class FailureReasonServiceImpl implements CrudCommonService<FailureReason>, ToDaoService<FailureReason> {

    private final CrudCommonService<FailureMode> failureModeService;

    @Autowired // inject ServiceImpl
    public FailureReasonServiceImpl(@Qualifier("failureModeService") CrudCommonService failureModeService) {
        this.failureModeService = failureModeService;
    }

    @Autowired
    FailureReasonRepository failureReasonRepository;

    @Override
    public List<FailureReason> getAll() {
        return (List<FailureReason>) failureReasonRepository.findAll();
    }

    @Override
    public FailureReason save(FailureReason failureReason) throws PhmException {
        return failureReasonRepository.save(toDaoData(failureReason));
    }

    @Override
    public void deleteById(Integer id) {
        failureReasonRepository.deleteById(id);
    }

    @Override
    public FailureReason getById(Integer id) throws PhmException {
        return failureReasonRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureDMode with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public FailureReason toDaoData(FailureReason failureReason) throws PhmException {
        FailureReason fr = null;
        if (failureReason.isNew()) {
            fr = new FailureReason();
            fr.setFailureMode(failureModeService.getById(failureReason.getFailureModeId()));
        //.setCategoryType(unitTypePTLService.getById(failureMode.getCategoryTypeId()));
        } else {
            fr = getById(failureReason.getId());
        }
        BeanUtils.copyProperties(failureReason, fr, GetNullProperties.getNullPropertyNames(failureReason));
        return fr;
    }
}
