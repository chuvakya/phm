package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureSeverity;
import org.zs.phm3.repository.fmea.FailureSeverityRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureSeverityService")
public class FailureSeverityServiceImpl implements CrudCommonServiceWithStringId<FailureSeverity>,
        ToDaoService<FailureSeverity> {

    @Autowired
    FailureSeverityRepository failureSeverityRepository;

    @Override
    public List<FailureSeverity> getAll() {
        return (List<FailureSeverity>) failureSeverityRepository.findAll();
    }

    @Override
    public FailureSeverity save(FailureSeverity failureSeverity) throws PhmException {
        return failureSeverityRepository.save(toDaoData(failureSeverity));
    }

    @Override
    public void deleteById(String id) {
        failureSeverityRepository.deleteById(id);
    }

    @Override
    public FailureSeverity getById(String id) throws PhmException {
        return failureSeverityRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureSeverity with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public Boolean isNew(String id) {
        return (failureSeverityRepository.countById(id)==0);
    }

    @Override
    public FailureSeverity toDaoData(FailureSeverity failureSeverity) throws PhmException {
        if (isNew(failureSeverity.getId())) {
            return failureSeverity;
        }else{
            FailureSeverity fs = getById(failureSeverity.getId());
            BeanUtils.copyProperties(failureSeverity, fs, GetNullProperties.getNullPropertyNames(failureSeverity));
            return fs;
        }
    }
}
