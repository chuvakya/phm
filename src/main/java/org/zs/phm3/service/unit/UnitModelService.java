package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitModel;

import java.util.List;

public interface UnitModelService {
    UnitModel save(UnitModel unitModelForSave);
    UnitModel findById(Integer unitModelId) throws PhmException;
    List<UnitModel> getAll();
    void deleteById(Integer unitModelId);
    void delete(UnitModel unitModelForDeleting);
    void saveAll(List<UnitModel> unitModelsList);
    List<UnitModel> getAllByTypeId(Integer typeId);
}
