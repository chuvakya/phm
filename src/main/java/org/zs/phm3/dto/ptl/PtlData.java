package org.zs.phm3.dto.ptl;

import org.zs.phm3.dto.DtoIdName;

import java.util.List;

//Data Structure
public class PtlData {
    List<DtoIdIntName> unitType;
    List<DtoIdIntName> manufacturer;
    List<DtoIdIntName> model;
    Integer unitTypeId;
    Integer manufacturerId;
    Integer modelId;

    public PtlData(List<DtoIdIntName> unitTypeList, List<DtoIdIntName> manufacturerList, List<DtoIdIntName> modelList,
                   Integer unitTypeId, Integer manufacturerId, Integer modelId) {
        this.unitType = unitTypeList;
        this.manufacturer = manufacturerList;
        this.model = modelList;

        this.unitTypeId= unitTypeId;
        this.manufacturerId= manufacturerId;
        this.modelId= modelId;
    }

    public List<DtoIdIntName> getUnitType() {
        return unitType;
    }

    public List<DtoIdIntName> getManufacturer() {
        return manufacturer;
    }

    public List<DtoIdIntName> getModel() {
        return model;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public Integer getModelId() {
        return modelId;
    }
}
