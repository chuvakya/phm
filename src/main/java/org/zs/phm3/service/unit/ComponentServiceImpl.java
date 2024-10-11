package org.zs.phm3.service.unit;

import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.unit.UnitAttribute;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.repository.unit.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.service.SchemeService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    UnitService unitService;
    @Autowired
    SchemeService schemeService;
    @Autowired
    UnitAttributeService unitAttributeService;
    @Autowired
    ComponentRepository componentRepository;

    @Override
    public void multiplyComponent(String componentId, String parentUnitId, Integer number) throws PhmException {
        multiplyUnit(componentRepository.findById(componentId).get().getUnit(), parentUnitId, number);
    }

//    TODO create conversion from unitDtoOutput -> unitDtoIntput for both methods

    @Override
    public void multiplyUnit(String unitId, String parentUnitId, Integer number) throws PhmException {
/*        UnitDto unitDtoFromComponent = unitService.findById(unitId);

        assert unitDtoFromComponent != null : "No unit found with key=" + unitId;
        String unitName = unitDtoFromComponent.getName();
        String unitDescription = unitDtoFromComponent.getDescription();
//        createNewUnitFromComponent(unitDtoFromComponent);
        for (int i = 1; i <= number; i++) {
            unitDtoFromComponent.setId(null);
            unitDtoFromComponent.setName(unitName + "_" + i);
            unitDtoFromComponent.setDescription(unitDescription + " multipyed from component");
            if (!parentUnitId.equals(""))
                unitDtoFromComponent.setParentId(parentUnitId);
            UnitDto unitDtoSaved = unitService.save(unitDtoFromComponent);
            String newUnitId = UUIDConverter.fromTimeUUID(UUID.fromString(unitDtoSaved.getId()));
      *//* Scheme *//*
            List<SchemeEntity> unitSchemesFromComponent = unitDtoFromComponent.getSchemes();
            unitSchemesFromComponent.forEach(scheme -> {
                scheme.setUnitId(newUnitId);

            });
      *//* Attributes *//*
            List<UnitAttribute> unitAttributesFromComponent = unitAttributeService.getAllByUnitId(unitId);
            List<UnitAttribute> unitAttributesForSaving = new ArrayList<>();
            unitAttributesFromComponent.forEach(attribute -> {
                UnitAttributeKey unitAttributeKey = attribute.getId();
                unitAttributeKey.setUnitId(unitDtoSaved.getId());
                attribute.setId(unitAttributeKey);
                unitAttributesForSaving.add(attribute);
            });
            unitAttributeService.saveAll(unitAttributesForSaving);
        }*/
    }

    private UnitDto createNewUnitFromComponent(UnitDto unitDtoFromComponent) {

/*        UnitDto newUnitDto = new UnitDto();
        newUnitDto.setId("");
        newUnitDto.setProject_id(unitDtoFromComponent.getProject_id());
        newUnitDto.setName(unitDtoFromComponent.getName());
        newUnitDto.setDescription(unitDtoFromComponent.getDescription());
        newUnitDto.setPic_id(unitDtoFromComponent.getPic_id());
        newUnitDto.setPicbckgr_id(unitDtoFromComponent.getPicbckgr_id());
        newUnitDto.setCustomId(unitDtoFromComponent.getCustomId());

//        newUnitDto.setSchemes(unitDtoFromComponent.getSchemes());
//      todo finish later with new data
        newUnitDto.setAttributes(unitDtoFromComponent.getAttributes());
//      todo finish later with new data
        newUnitDto.setChilds(unitDtoFromComponent.getChilds());

        return newUnitDto;*/
        return  null;
    }
}
