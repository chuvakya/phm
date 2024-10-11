package org.zs.phm3;

import org.zs.phm3.models.unit.UnitEntity;

import java.util.ArrayList;

public class ProjectData {
    private ArrayList<UnitEntity> unitEntitiesData;
    private ArrayList<String[]> parentMapping;
    private InitialData initialData;

    public ProjectData(ArrayList<UnitEntity> unitEntitiesData, ArrayList<String[]> parentMapping, InitialData initialData) {
        this.unitEntitiesData = unitEntitiesData;
        this.parentMapping = parentMapping;
        this.initialData = initialData;
    }

    public ArrayList<UnitEntity> getUnitEntitiesData() {
        return unitEntitiesData;
    }

    public void setUnitEntitiesData(ArrayList<UnitEntity> unitEntitiesData) {
        this.unitEntitiesData = unitEntitiesData;
    }

    public ArrayList<String[]> getParentMapping() {
        return parentMapping;
    }

    public void setParentMapping(ArrayList<String[]> parentMapping) {
        this.parentMapping = parentMapping;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public ProjectData() {
    }
}
