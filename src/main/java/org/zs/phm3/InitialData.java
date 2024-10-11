package org.zs.phm3;

import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.unit.UnitModel;
import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitType;

import java.util.ArrayList;

public class InitialData {
    private ArrayList<FileEntity> filesData;
    private ArrayList<UnitType> unitTypesData;
    private ArrayList<UnitModel> unitModelsData;
    private ArrayList<UnitModelAttribute> unitModelAttributesData;

    public ArrayList<FileEntity> getFilesData() {
        return filesData;
    }

    public void setFilesData(ArrayList<FileEntity> filesData) {
        this.filesData = filesData;
    }

    public ArrayList<UnitModel> getUnitModelsData() {
        return unitModelsData;
    }

    public InitialData(ArrayList<FileEntity> filesData, ArrayList<UnitType> unitTypesData,
                       ArrayList<UnitModel> unitModelsData, ArrayList<UnitModelAttribute> unitModelAttributesData) {
        this.filesData = filesData;
        this.unitTypesData=unitTypesData;
        this.unitModelsData = unitModelsData;
        this.unitModelAttributesData=unitModelAttributesData;
    }

    public ArrayList<UnitType> getUnitTypesData() {
        return unitTypesData;
    }

    public void setUnitTypesData(ArrayList<UnitType> unitTypesData) {
        this.unitTypesData = unitTypesData;
    }

    public void setUnitModelsData(ArrayList<UnitModel> unitModelsData) {
        this.unitModelsData = unitModelsData;
    }

    public ArrayList<UnitModelAttribute> getUnitModelAttributesData() {
        return unitModelAttributesData;
    }

    public void setUnitModelAttributesData(ArrayList<UnitModelAttribute> unitModelAttributesData) {
        this.unitModelAttributesData = unitModelAttributesData;
    }

    public InitialData() {
    }
}
