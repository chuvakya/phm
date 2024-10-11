package org.zs.phm3.dto.ptl;

public class PtlOutput {
    private Long modelId;
    private String modelName;

    private Integer unitTypeId;
    private String unitTypeName;

    private Integer manufacturerId;
    private String manufacturerName;

    private Integer iconUnitTypeId;

    public PtlOutput(Long modelId, String modelName, Integer unitTypeId, String unitTypeName, Integer manufacturerId,
                     String manufacturerName, Integer iconUnitTypeId) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.unitTypeId = unitTypeId;
        this.unitTypeName = unitTypeName;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.iconUnitTypeId=iconUnitTypeId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModeId(Long modelPtlId) {
        this.modelId = modelPtlId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getIconUnitTypeId() {
        return iconUnitTypeId;
    }

    public void setIconUnitTypeId(Integer iconUnitTypeId) {
        this.iconUnitTypeId = iconUnitTypeId;
    }

    @Override
    public String toString() {
        return "PtlOutput{" +
                "modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", unitTypePtlId=" + unitTypeId +
                ", unitTypeName='" + unitTypeName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturerName='" + manufacturerName + '\'' +
                '}';
    }
}
