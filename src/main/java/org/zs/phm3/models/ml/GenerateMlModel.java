package org.zs.phm3.models.ml;

import java.util.ArrayDeque;

public class GenerateMlModel {

    private static ArrayDeque<GenerateMlModel> generateMlModels = new ArrayDeque<>();

    private String unitId;
    private Long mlModelId;
    private String modelName;
    private String userName;

    public GenerateMlModel() {
    }

    public GenerateMlModel(String unitId, Long mlModelId, String modelName, String userName) {
        this.unitId = unitId;
        this.mlModelId = mlModelId;
        this.modelName = modelName;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static ArrayDeque<GenerateMlModel> getGenerateMlModels() {
        return generateMlModels;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Long getMlModelId() {
        return mlModelId;
    }

    public void setMlModelId(Long mlModelId) {
        this.mlModelId = mlModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
