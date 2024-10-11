package org.zs.phm3.service.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.dto.ptl.*;
import org.zs.phm3.dto.ptl.mapping.ManufacturerMapper;
import org.zs.phm3.dto.ptl.mapping.ModelMapper;
import org.zs.phm3.dto.ptl.mapping.UnitTypeMapper;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.zs.phm3.service.ptl.ManufacturerPTLService;
import org.zs.phm3.service.ptl.ModelPTLService;
import org.zs.phm3.service.ptl.UnitTypePTLService;

import java.util.List;

@Service
public class UnitPtlServiceImpl implements UnitPtlService {

    @Autowired
    UnitTypePTLService unitTypeService;
    @Autowired
    ManufacturerPTLService manufacturerService;
    @Autowired
    ModelPTLService modelService;
    @Autowired
    UnitTypeMapper unitTypeMapper;
    @Autowired
    ManufacturerMapper manufacturerMapper;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ModelPTLService modelPTLService;

    @Autowired
    UnitService unitService;

    //    when selecting one unit...
    @Override
    public PtlData getPtlData(String unitId) {
        Long modelPtlId = unitService.findById(unitId).getPtlOutput().getModelId();
        Long manufacturerId = null;
        Integer manufacturerIdInt = null;
        List<ManufacturerPTL> manufacturerEntitiesList = null;

        ModelPTL modelPtl = modelService.getById(modelPtlId);
        Integer unitTypeId = modelPtl.getUnitTypePTL().getId();

        if (modelPtl.getManufacturerPTL() != null) {
            manufacturerId = modelPtl.getManufacturerPTL().getId();
            manufacturerIdInt = Math.toIntExact(manufacturerId);
            manufacturerEntitiesList = modelPTLService.getAllManufacturersByUnitTypeId(unitTypeId);
        }
        Integer modelId = Math.toIntExact(modelPtl.getId());

        List<UnitTypePTL> unitTypesEntitiesList = unitTypeService.getAllMainEntity();
        List<DtoIdIntName> unitTypesList = unitTypeMapper.map(unitTypesEntitiesList);


        List<DtoIdIntName> manufacturerList = manufacturerMapper.map(manufacturerEntitiesList);

        List<ModelPTL> modelEntitiesList = modelService.getAllByManufacturerIdAndUnitTypeId(manufacturerId, unitTypeId);
        List<DtoIdIntName> modelList = modelMapper.map(modelEntitiesList);

        return new PtlData(unitTypesList, manufacturerList, modelList, unitTypeId, manufacturerIdInt, modelId);
    }
}
