package org.zs.phm3.service.ts;

import org.zs.phm3.models.ts.TsKvAttributeType;
import org.zs.phm3.repository.ts.TsKvAttributeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsKvAttributeTypeServiceImpl implements TsKvAttributeTypeService {

    @Autowired
    private TsKvAttributeTypeRepository tsKvAttributeTypeRepository;

    @Override
    public TsKvAttributeType getById(Integer categoryId) {
        return tsKvAttributeTypeRepository.findById(categoryId).get();
    }

    @Override
    public List<TsKvAttributeType> getAllByProjectId(Integer projectId) {
        return tsKvAttributeTypeRepository.getAllByProjectId(projectId);
    }

    @Override
    public List<TsKvAttributeType> getAllUsersTypesByProjectId(Integer projectId) {
        return tsKvAttributeTypeRepository.getAllUsersTypesByProjectId(projectId);
    }

    @Override
    public TsKvAttributeType save(TsKvAttributeType tsKvAttributeType) {
        return tsKvAttributeTypeRepository.save(tsKvAttributeType);
    }

    @Override
    public void deleteById(Integer categoryId) {
        tsKvAttributeTypeRepository.deleteById(categoryId);
    }

    @Override
    public List<TsKvAttributeType> getAllAttributeTypeByUnitId(String unitId) {
        return tsKvAttributeTypeRepository.getAllAttributeTypeByUnitId(unitId);
    }

    @Override
    public TsKvAttributeType saveIfNotExist(TsKvAttributeType tsKvAttributeType) {
        List<TsKvAttributeType> categories = tsKvAttributeTypeRepository.getAllByProjectIdAndName(
                tsKvAttributeType.getProjectId(), tsKvAttributeType.getName());
        if (categories.size() == 0) {
            return tsKvAttributeTypeRepository.save(tsKvAttributeType);
        } else {
            return null;
        }

    }
}
