package org.zs.phm3.controller;

import org.zs.phm3.data.Validator;
import org.zs.phm3.models.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.exception.PhmException;

import org.zs.phm3.service.project.ProjectService;

import java.util.List;

//import javax.validation.Valid;

@RestController
@RequestMapping("api/project/")
public class ProjectController extends BaseController {
    @Autowired
    ProjectService projectService;
    private static final String PROJECT_ID = "projectId";


    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)

        public ProjectEntity save(@RequestBody ProjectEntity savingProject) throws PhmException, Exception {
        ActionType actionType = savingProject.isNew() ? ActionType.PROJECT_ADDED : ActionType.PROJECT_UPDATED;
        final String ACTION = savingProject.isNew() ? "New Project " + savingProject.getName() + "Saving" :
                "Project " + savingProject.getId() + ", " + savingProject.getName() + " Updating";
//        try {

            ProjectEntity savedProject = projectService.save(savingProject);
            logAction(savedProject.getId().toString(), "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return savedProject;
//        } catch (Exception e) {
//            logAction("", "", "USER",
//                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
//            throw handleException(e);
//        }
    }

    @DeleteMapping(value = "/{projectId}")
    public void delete(@PathVariable(PROJECT_ID) Integer projectId) throws PhmException {
        Validator.validateId(projectId, "");

        final String ACTION = "Project " + projectId + "Deleting";
//        try {
            projectService.deleteById(projectId);
            logAction(projectId.toString(), "", "USER", ActionType.PROJECT_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");
//        } catch (Exception e) {
//            logAction(projectId.toString(), "", "USER", ActionType.PROJECT_DELETED,
//                    ActionStatus.FAILURE, ACTION, e.getMessage());
//            throw handleException(e);
//        }
    }

    @GetMapping(value = "/{projectId}")
    public ProjectEntity getById(@PathVariable(PROJECT_ID) Integer projectId) throws PhmException {
            return projectService.findProjectById(projectId);
    }

    @GetMapping(value = "/all")
    public List<ProjectEntity> getAll() throws PhmException {
//        checkParameter(CUSTOMER_ID, strCustomerId);
//        try {
            List<ProjectEntity> projectsListAll = projectService.getAllProjects();
            System.out.println("size=" + projectsListAll.size());
            return projectsListAll;
//        } catch (Exception e) {
//            throw handleException(e);
//        }
    }

    @GetMapping(value = "/inactive")
    public List<ProjectEntity> findAllInactive() {
            List<ProjectEntity> projectsInactiveList = projectService.findByInactiveIsTrue();
            System.out.println("size=" + projectsInactiveList.size());
            return projectsInactiveList;
    }

    @PatchMapping(value = "/{projectId}")
    public ProjectEntity update(@PathVariable(PROJECT_ID) Integer projectId,
                                @RequestBody ProjectEntity projectForUpdate) throws PhmException {
        return projectService.update(projectId,projectForUpdate);
    }
}
