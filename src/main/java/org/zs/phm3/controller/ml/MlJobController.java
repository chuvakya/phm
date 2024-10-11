package org.zs.phm3.controller.ml;

import org.zs.phm3.models.ml.MlJob;
import org.zs.phm3.models.ml.ModelSchema;
import org.zs.phm3.service.ml.ModelSchemaService;
import org.zs.phm3.service.user.UserService;
import org.zs.phm3.util.GetNullProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.util.SQLHelper;
import org.zs.phm3.service.ml.MlJobService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ml-job/")
public class MlJobController {

    @Autowired
    private MlJobService mlJobService;

    @Autowired
    private ModelSchemaService modelSchemaService;

    @Autowired
    private SQLHelper SQLHelper;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createJob(@RequestBody List<Long> mlSchemaIds) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM model_schema WHERE id IN (");
        for (int i = 0; i < mlSchemaIds.size(); i++) {
            if (i == 0 && mlSchemaIds.size() > 1 || (i > 0 && mlSchemaIds.size() - 1 != i)) {
                stringBuilder.append(mlSchemaIds.get(i).toString() + ",");
            } else if (i == mlSchemaIds.size() - 1) {
                stringBuilder.append(mlSchemaIds.get(i).toString() + ")");
                break;
            }
        }
        List<ModelSchema> modelSchemas =
                entityManager.createNativeQuery(stringBuilder.toString(), ModelSchema.class).getResultList();
        for (ModelSchema modelSchema : modelSchemas) {
            mlJobService.createJobAndAddToQueue(userService.getByLogin("Admin"), modelSchema);
        }

    }

    @GetMapping(value = "getAll")
    public List<MlJob> getAllJobs() {
        return mlJobService.getAll();
    }

    @GetMapping(value = "getAll/{projectId}", produces = "application/json")
    public String getAllJobsForUI(@PathVariable Integer projectId) {
        return mlJobService.getAllForUI(projectId);
    }

    @GetMapping(value = "get/{id}")
    public MlJob getById(@PathVariable Long id) {
        return mlJobService.getById(id);
    }

    @DeleteMapping(value = "delete/{mlJobId}")
    public void delete(@PathVariable Long mlJobId) {
        mlJobService.deleteByIdSQL(mlJobId);
    }

    @DeleteMapping(value = "deleteAll")
    public void deleteAll(@RequestBody List<Long> mlJobIds) {
        SQLHelper.deleteAll("ml_job", "id", mlJobIds);
    }

    @PatchMapping(value = "update")
    public MlJob update(@RequestParam Long id, @RequestBody MlJob mlJob) {
        MlJob job = mlJobService.getById(id);
        BeanUtils.copyProperties(mlJob, job, GetNullProperties.getNullPropertyNames(mlJob));
        mlJob.setModifiedBy(userService.getByLogin("Admin"));
        mlJobService.save(job);
        return job;
    }

    @GetMapping(value = "getCount/{projectId}")
    public Integer getCountByProjectId(@PathVariable Integer projectId) {
        return mlJobService.getCountByProjectId(projectId);
    }

    @GetMapping(value = "getByOffsetAndLimit/{offset}/{limit}/{projectId}", produces = "application/json")
    public String getByOffsetAndLimitAndProjectId(@PathVariable Integer offset, @PathVariable Integer limit,
                                                  @PathVariable Integer projectId) {
        return mlJobService.getListByProjectIdAndOffsetAndLimit(projectId, offset, limit);
    }

}
