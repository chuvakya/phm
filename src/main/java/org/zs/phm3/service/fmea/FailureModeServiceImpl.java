package org.zs.phm3.service.fmea;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.basic.ToDaoService;
import org.zs.phm3.models.fmea.FailureMode;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.zs.phm3.repository.fmea.FailureModeRepository;
import org.zs.phm3.service.ptl.UnitTypePTLService;
import org.zs.phm3.util.GetNullProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Qualifier("failureModeService")
public class FailureModeServiceImpl implements CrudCommonService<FailureMode>, ToDaoService<FailureMode> {

    @Autowired
    UnitTypePTLService unitTypePTLService;
    @Autowired
    FailureModeRepository failureModeRepository;

    @Override
    public List<FailureMode> getAll() {
        return (List<FailureMode>) failureModeRepository.findAll();
    }

    @Override
    public FailureMode save(FailureMode failureMode) throws PhmException {
        return failureModeRepository.save(toDaoData(failureMode));
    }

    @Override
    public void deleteById(Integer id) {
        failureModeRepository.deleteById(id);
    }

    @Override
    public FailureMode getById(Integer id) throws PhmException {
        return failureModeRepository.findById(id).orElseThrow(() -> (new
                PhmException("FailureMode with id " + id +
                " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }
    @Override
    public List<FailureMode> findAllByName(String searchedString){
        return (List<FailureMode>) failureModeRepository.findAllByNameContains(searchedString);
    }

    @Override
    public FailureMode toDaoData(FailureMode failureMode) throws PhmException {
        FailureMode fm = null;
        if (failureMode.isNew()) {
            fm = new FailureMode();
        } else {
            fm = getById(failureMode.getId());
//            fm.setCategoryTypes(null);
        }
        BeanUtils.copyProperties(failureMode, fm, GetNullProperties.getNullPropertyNames(failureMode));
        if (failureMode.getCategoryTypeIdsSet() != null) {
//            if (failureMode.getCategoryTypeIdsSet().size() > 0) {
                fm.setCategoryTypes(FindCategoryTypes(failureMode.getCategoryTypeIdsSet(), unitTypePTLService));
//            }
        }
        return fm;
    }

    private static Set<UnitTypePTL> FindCategoryTypes(Set<Integer> categoryTypesSet, UnitTypePTLService unitTypePTLService) throws PhmException {
        Set<UnitTypePTL> categoryTypesFinded = new HashSet<>();
        for (Integer categoryTypeId : categoryTypesSet) {
            if (categoryTypeId != null) {
                categoryTypesFinded.add(unitTypePTLService.getById(categoryTypeId));
            }
        }
        return categoryTypesFinded;
    }

    @Override
    public List<FailureMode> findByCategoryTypeId(Integer categoryTypeId) {
        return (List<FailureMode>) failureModeRepository.findByCategoryTypes_Id(categoryTypeId);
    }
}
