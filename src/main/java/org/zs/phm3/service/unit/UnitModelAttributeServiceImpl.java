package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitModelAttributeKey;
import org.zs.phm3.repository.unit.UnitModelAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitModelAttributeServiceImpl implements UnitModelAttributeService {
    @Autowired
    UnitModelAttributeRepository unitModelAttributeRepository;

    @Override
    public UnitModelAttribute save(UnitModelAttribute unitModelAttributeForSave) {

        UnitModelAttribute unitModelAttributeSaved=unitModelAttributeRepository.save(unitModelAttributeForSave);
        return unitModelAttributeSaved;
    }

    @Override
    public UnitModelAttribute findById(UnitModelAttributeKey UnitModelAttributeId) throws PhmException {
        return unitModelAttributeRepository.findById(UnitModelAttributeId)
                .orElseThrow(()->(new PhmException("UnitModel Attribute with id "+UnitModelAttributeId+
                        " not found", PhmErrorCode.ITEM_NOT_FOUND)));

    }

    @Override
    public List<UnitModelAttribute> getAll() {
        List<UnitModelAttribute> unitModelAttributeList= (List<UnitModelAttribute>) unitModelAttributeRepository.findAll();
        return unitModelAttributeList;
    }

    @Override
    public void deleteById(UnitModelAttributeKey UnitModelAttributeId) {
        unitModelAttributeRepository.deleteById(UnitModelAttributeId);
    }

    @Override
    public Boolean isNew(String attributeKey, Integer unitId) {
        return unitModelAttributeRepository.isNew(attributeKey, unitId);
    }

    @Override
    public List<UnitModelAttribute> getAllByUnitModelId(Integer unitModelId) {
        return unitModelAttributeRepository.getAllByUnitModelId(unitModelId);
    }

    @Override
    public void saveAll(List<UnitModelAttribute> unitModelAttributesList) {
        unitModelAttributeRepository.saveAll(unitModelAttributesList);
    }
}
