package org.zs.phm3.controller.ml;

import org.zs.phm3.models.ml.Dataset;
import org.zs.phm3.models.ml.MlAlgorithm;
import org.zs.phm3.models.ml.ModelSchema;
import org.zs.phm3.models.ml.ModelSchemaRegistry;
import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.repository.ml.ModelSchemaRegistryRepository;
import org.zs.phm3.service.ml.DatasetService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ml.MlJobService;
import org.zs.phm3.service.ml.ModelSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/modelSchema/")
public class ModelSchemaController {

    @Autowired
    private ModelSchemaService modelSchemaService;

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private MlJobService mlJobService;

    @Autowired
    private UserService userService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private ModelSchemaRegistryRepository modelSchemaRegistryRepository;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam String name, @RequestParam MlAlgorithm algorithm,
                         @RequestParam Long datasetId, @RequestParam Boolean runJobAfterCreated,
                         @RequestParam(required = false) Integer windowLength,
                         @RequestParam(required = false) Integer period) {
        Dataset dataset = datasetService.getById(datasetId);
        if (modelSchemaService.getAllByNameAndProjectId(name, dataset.getProjectId()).size() > 0) {
            return "This name already exist!";
        }
        Long periodTime = null;
        if (period != null) {
            periodTime = 3600 * 1000 * 24 * 30L * period;
        }
        ModelSchema modelSchema = new ModelSchema(algorithm, dataset, dataset.getProjectId(), name, windowLength, periodTime);
        modelSchema.setCreateTime(System.currentTimeMillis());
        UserEntity user = userService.getByLogin("Admin");
        modelSchema.setModifiedBy(user);
        modelSchemaService.save(modelSchema);
        modelSchemaRegistryRepository.save(new ModelSchemaRegistry(name, dataset.getProjectId()));
        if (runJobAfterCreated) {
            mlJobService.createJobAndAddToQueue(user, modelSchema);
        }
        return "Success!";
    }

    @GetMapping(value = "getCount/{projectId}")
    public Integer getCountByProjectId(@PathVariable Integer projectId) {
        return modelSchemaService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getByOffsetAndLimit/{offset}/{limit}/{projectId}", produces = "application/json")
    public String getByOffsetAndLimitAndProjectId(@PathVariable Integer offset, @PathVariable Integer limit,
                                                  @PathVariable Integer projectId) {
        return modelSchemaService.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

    @DeleteMapping(value = "delete/{modelSchemaId}")
    public void delete(@PathVariable Long modelSchemaId) {
        modelSchemaService.deleteByIdSQL(modelSchemaId);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> modelSchemaIds) {
        SQLHelper.deleteAll("model_schema", "id", modelSchemaIds);
    }

    @GetMapping(value = "getNameForNewModelSchema/{projectId}")
    public String getNameForNewModelSchema(@PathVariable Integer projectId) {
        return modelSchemaService.getNameForNewModelSchema(projectId);
    }

    @GetMapping(value = "getById/{modelSchemaId}", produces = "application/json")
    public String getById(@PathVariable Long modelSchemaId) {
        return modelSchemaService.getByIdJSON(modelSchemaId);
    }

    @PatchMapping(value = "updateById/{modelSchemaId}")
    public String updateById(@PathVariable Long modelSchemaId, @RequestParam(required = false) Integer windowLength,
                             @RequestParam String name, @RequestParam Long datasetId,
                             @RequestParam MlAlgorithm mlAlgorithm, @RequestParam(required = false) Integer period) {
        ModelSchema modelSchema = modelSchemaService.getById(modelSchemaId);
        if (modelSchemaService.getAllByNameAndProjectId(name, modelSchema.getProjectId()).size() > 0 &&
                !modelSchema.getName().equals(name)) {
            return "This name already exist!";
        }
        Long periodTime = null;
        if (period != null) {
            periodTime = 3600 * 1000 * 24 * 30L * period;
        }
        modelSchema.setTrainPeriod(periodTime);
        modelSchema.setName(name);
        modelSchema.setWindowLength(windowLength);
        modelSchema.setMlAlgorithm(mlAlgorithm);
        modelSchema.setDataset(datasetService.getById(datasetId));
        modelSchema.setModifiedBy(userService.getByLogin("Admin"));
        modelSchemaService.save(modelSchema);
        return "Success!";
    }

}
