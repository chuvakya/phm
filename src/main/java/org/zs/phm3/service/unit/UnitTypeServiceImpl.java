package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.unit.UnitType;
import org.zs.phm3.repository.unit.UnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.service.FileService;

import java.util.List;

@Service
public class UnitTypeServiceImpl implements UnitTypeService {
    @Autowired
    UnitTypeRepository unitTypeRepository;
    @Autowired
    private FileService fileService;
    @Override
    public UnitType save(UnitType unitTypeForSave) throws PhmException {
        if (unitTypeForSave.getPic_id() != null) {
            FileEntity picture = fileService.findById(unitTypeForSave.getPic_id());
            unitTypeForSave.setPicture_file(picture);
        }
        return unitTypeRepository.save(unitTypeForSave);
    }

    @Override
    public UnitType findById(Integer unitTypeId) throws PhmException {
        return unitTypeRepository.findById(unitTypeId).orElseThrow(() ->
                new PhmException("No Unit Type found with key=" + unitTypeId,
                        PhmErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public List<UnitType> getAll() {
        return (List<UnitType>) unitTypeRepository.findAll();
    }

    @Override
    public void deleteById(Integer unitTypeIdForDeleting) {
        unitTypeRepository.deleteById(unitTypeIdForDeleting);
    }

    @Override
    public void deleteBy(UnitType unitTypeForDeleting) {
        unitTypeRepository.delete(unitTypeForDeleting);
    }

    @Override
    public void saveAll(List<UnitType> unitTypesList) {
        unitTypeRepository.saveAll(unitTypesList);
    }
}
