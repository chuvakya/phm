package org.zs.phm3.controller.ml;

import org.zs.phm3.models.ml.*;
import org.zs.phm3.service.ml.DataSchemaService;
import org.zs.phm3.service.ml.DatasetRegistryService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.util.GetNullProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ml.DatasetService;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.unit.UnitService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dataset/")
public class DatasetController {

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    @Autowired
    private DatasetRegistryService datasetRegistryService;

    @Autowired
    private DataSchemaService dataSchemaService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UserService userService;

    @Value("${ml-service.dataset-folder}")
    private String datasetFolder;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDataset(@RequestParam Long timeFrom, @RequestParam Long timeTo,
                                @RequestParam String name,
                                @RequestParam Long dataSchemaId) {
        DataSchema dataSchema = dataSchemaService.getById(dataSchemaId);
        if (datasetService.getAllByNameAndProjectId(name, dataSchema.getProjectId()).size() > 0) {
            return "This name already exist!";
        }
        Dataset dataset = new Dataset(name, null, dataSchema.getProjectId(), dataSchema);
        dataset.setTimeTo(timeTo);
        dataset.setTimeFrom(timeFrom);
        dataset.setModifiedBy(userService.getByLogin("Admin"));

        if (DatasetQueued.getDatasetJobs().isEmpty()) {
            dataset.setState("RUNNING");
        } else {
            dataset.setState("QUEUED");
        }
        datasetService.save(dataset);
        datasetRegistryService.save(new DatasetRegistry(name, dataSchema.getProjectId()));
        DatasetQueued.getDatasetJobs().addLast(dataset);
        return "Process is running!";
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteDataset(@PathVariable("id") Long id) {
        new File(datasetService.getById(id).getPath()).delete();
        datasetService.deleteByIdSQL(id);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> datasetIds) {
        SQLHelper.deleteAll("dataset", "id", datasetIds);
    }

    @GetMapping(value = "getAll/{projectId}")
    public List<Dataset> getAll(@PathVariable Integer projectId) {
        return datasetService.getAllByProjectId(projectId);
    }


    @PatchMapping(value = "update")
    public String update(@RequestParam Long datasetId, @RequestBody Dataset datasetUpdate) {
        Dataset dataset = datasetService.getById(datasetId);
        if (datasetUpdate.getName() != null && !dataset.getName().equals(datasetUpdate.getName()) &&
                datasetService.getAllByNameAndProjectId(datasetUpdate.getName(), dataset.getProjectId()).size() > 0) {
            return "This name already exist!";
        }
        BeanUtils.copyProperties(datasetUpdate, dataset,
                GetNullProperties.getNullPropertyNames(datasetUpdate));
        dataset.setModifiedBy(userService.getByLogin("Admin"));
        datasetService.save(dataset);
        return "Success!";
    }

    @GetMapping(value = "viewDataset/{datasetId}/{n}", produces = "application/json")
    public String viewDataset(@PathVariable Long datasetId, @PathVariable Long n) {
        return datasetService.viewDataset(datasetId, n);
    }

    @GetMapping(value = "getNameForNewDataset/{projectId}")
    public String getNameForNewDataset(@PathVariable Integer projectId) {
        return datasetService.getNameForNewDataset(projectId);
    }

    @GetMapping(value = "getCount/{projectId}")
    public Integer getCountByProjectId(@PathVariable Integer projectId) {
        return datasetService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getByOffsetAndLimit/{offset}/{limit}/{projectId}", produces = "application/json")
    public String getByOffsetAndLimitAndProjectId(@PathVariable Integer offset, @PathVariable Integer limit,
                                                  @PathVariable Integer projectId) {
        return datasetService.getByOffsetAndLimitAndProjectId(offset, limit, projectId);
    }

    @GetMapping(value = "getAllIdAndNameByDataSchemaId/{dataSchemaId}", produces = "application/json")
    public String getAllIdAndNameByDataSchemaId(@PathVariable Long dataSchemaId) {
        return datasetService.getAllIdAndNameByDataSchemaId(dataSchemaId);
    }

    @GetMapping(value = "getAllIdAndNameByDataSchemaId/{dataSchemaId}/{typeTask}", produces = "application/json")
    public String getAllIdAndNameByDataSchemaId(@PathVariable Long dataSchemaId,
                                                @PathVariable MlType typeTask) {
        return switch(typeTask) {
            case ANOMALY -> datasetService.getAllIdAndNameByDataSchemaIdAnomaly(dataSchemaId);
            case FAULT -> datasetService.getAllIdAndNameByDataSchemaIdFault(dataSchemaId);
            case RUL -> datasetService.getAllIdAndNameByDataSchemaIdRUL(dataSchemaId);
        };
    }
}
