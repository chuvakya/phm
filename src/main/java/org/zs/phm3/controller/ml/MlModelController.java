package org.zs.phm3.controller.ml;

import org.zs.phm3.models.ml.*;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ml.MlModelService;
import org.zs.phm3.util.GetNullProperties;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/ml-model/")
public class MlModelController {

    @Autowired
    private MlModelService mlModelService;

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper SQLHelper;

    @DeleteMapping(value = "delete/{mlModelId}")
    public void deleteModels(@PathVariable Long mlModelId) {
        mlModelService.deleteByIdSQL(mlModelId);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> mlModelIds) {
        SQLHelper.deleteAll("ml_model", "id", mlModelIds);
    }

    @GetMapping(value = "getAll")
    public List<MlModel> getAll() {
        return mlModelService.getAll();
    }

    @GetMapping(value = "getListModels/{projectId}", produces = "application/json")
    public String getListModels(@PathVariable Integer projectId) {
        return mlModelService.getListModels(projectId);
    }

    @GetMapping(value = "getListBindModels/{projectId}", produces = "application/json")
    public String getListBindModels(@PathVariable Integer projectId) {
        return mlModelService.getListBindModels(projectId);
    }

    @GetMapping(value = "get/{id}")
    public MlModel getById(@PathVariable("id") Long id) {
        return mlModelService.getById(id);
    }

    @PatchMapping(value = "update")
    public MlModel update(@RequestParam Long mlModelId, @RequestBody MlModel model) {
        MlModel mlModel = mlModelService.getById(mlModelId);
        BeanUtils.copyProperties(model, mlModel, GetNullProperties.getNullPropertyNames(model));
        mlModel.setModifiedBy(userService.getByLogin("Admin"));
        mlModelService.save(mlModel);
        return mlModel;
    }

    @PostMapping(value = "stopServiceModel")
    public void stopServiceModel(@RequestBody List<Long> mlModelsId) {
        mlModelService.setStopAndEndTimeAndMlServiceStateByModelIds(true, System.currentTimeMillis(),
                "", mlModelsId);
    }

    @PostMapping(value = "startServiceModel")
    public void startServiceModel(@RequestBody List<Long> mlModelsId) {
        mlModelService.setStopAndEndTimeAndMlServiceStateAndStartTimeByModelIds(false, null,
                "LAUNCHED", mlModelsId, System.currentTimeMillis());
    }

    @GetMapping(value = "getModelsByMlType/{projectId}/{type}")
    public List<MlModel> getModelsByMlType(@PathVariable String type, @PathVariable Integer projectId) {
        return mlModelService.getMlModelsByMlType(MlType.valueOf(type), projectId);
    }

    @GetMapping(value = "getCount/{projectId}")
    public Integer getCountByProjectId(@PathVariable Integer projectId) {
        return mlModelService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getByOffsetAndLimit/{offset}/{limit}/{projectId}", produces = "application/json")
    public String getByOffsetAndLimitAndProjectId(@PathVariable Integer offset, @PathVariable Integer limit,
                                                  @PathVariable Integer projectId) {
        return mlModelService.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

    @PostMapping(value = "generateNewMlModel")
    public void generate(@RequestParam String unitId, @RequestParam Long mlModelId, @RequestParam String modelName) {
        GenerateMlModel.getGenerateMlModels().add(new GenerateMlModel(unitId, mlModelId, modelName, "Pavel Chuvak"));
    }

    @GetMapping(value = "getAllMetricsByMlModelId/{mlModelId}")
    public List<MlModelMetric> getAllMetricsByMlModelId(@PathVariable Long mlModelId) {
        return mlModelService.getMetricsByMlModelId(mlModelId);
    }

}