package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureDetectionMethod;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.repository.fmea.FailureDetectionMethodRepository;
import org.zs.phm3.util.GetNullProperties;

import java.util.List;

@Service
@Qualifier("failureDetectionMethodService")
public class FailureDetectionMethodServiceImpl implements CrudCommonService<FailureDetectionMethod>, ToDaoService<FailureDetectionMethod> {

    @Autowired
    FailureDetectionMethodRepository failureDetectionMethodRepository;

    @Override
    public List<FailureDetectionMethod> getAll() {
        return (List<FailureDetectionMethod>) failureDetectionMethodRepository.findAll();
    }

    @Override
    public FailureDetectionMethod save(FailureDetectionMethod failureDetectionMethod) throws PhmException {
        return failureDetectionMethodRepository.save(toDaoData(failureDetectionMethod));
    }

    @Override
    public void deleteById(Integer id) {
        failureDetectionMethodRepository.deleteById(id);
    }

    @Override
    public FailureDetectionMethod getById(Integer id) throws PhmException {
        return failureDetectionMethodRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureDetectionMethod with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public FailureDetectionMethod toDaoData(FailureDetectionMethod failureDetectionMethod) throws PhmException {
        FailureDetectionMethod fdm = null;
        if (failureDetectionMethod.isNew()) {
            fdm = new FailureDetectionMethod();
        } else {
            fdm = getById(failureDetectionMethod.getId());
        }

        BeanUtils.copyProperties(failureDetectionMethod, fdm, GetNullProperties.getNullPropertyNames(failureDetectionMethod));
        return fdm;
    }
}
