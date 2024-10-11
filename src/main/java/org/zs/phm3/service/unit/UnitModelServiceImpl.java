package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitModel;
import org.zs.phm3.repository.unit.UnitModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitModelServiceImpl implements UnitModelService {
    @Autowired
    UnitModelRepository unitModelRepository;

    @Override
    public UnitModel save(UnitModel unitModelForSave) {
        return unitModelRepository.save(unitModelForSave);
    }

    @Override
    public UnitModel findById(Integer unitModelId) throws PhmException {
        return unitModelRepository.findById(unitModelId)
                .orElseThrow(() -> (new PhmException("UnitModel with id " + unitModelId +
                        " not found", PhmErrorCode.ITEM_NOT_FOUND)));
    }

    @Override
    public List<UnitModel> getAll() {
        return (List<UnitModel>) unitModelRepository.findAll();
    }

    @Override
    public void deleteById(Integer unitModelId) {
        unitModelRepository.deleteById(unitModelId);
    }

    @Override
    public void delete(UnitModel unitModelForDeleting) {
        unitModelRepository.delete(unitModelForDeleting);
    }

    @Override
    public void saveAll(List<UnitModel> unitModelsList) {
        unitModelRepository.saveAll(unitModelsList);
    }

    @Override
    public List<UnitModel> getAllByTypeId(Integer typeId) {
        return unitModelRepository.getAllByType_Id(typeId);
    }
}
