package org.zs.phm3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("api/scheme/")
public class SchemeController extends BaseController {
/*    @Autowired
    ProjectService projectService;
    public static final String PROJECT_ID = "projectId";


    @PostMapping(value = "/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectEntity saveProject(@RequestBody ProjectEntity savingProject) throws PhmException {
        ActionType actionType = savingProject.isNew() ? ActionType.PROJECT_ADDED : ActionType.PROJECT_UPDATED;
        final String ACTION =savingProject.isNew() ?"New Project "+savingProject.getName()+"Saving":
                "Project "+savingProject.getId()+", "+savingProject.getName()+" Updating";
//        final String ACTION = "Project "+savingProject.getName()+"Saving";
        try {

            ProjectEntity savedProject = projectService.save(savingProject);
//            ActionType actionType = savingProject.getId() == null ? ActionType.PROJECT_ADDED : ActionType.PROJECT_UPDATED;
            logAction(savedProject.getId().toString(), "", "USER", actionType, ActionStatus.SUCCESS,
                    ACTION, "");
            return savedProject;
        } catch (Exception e) {
            logAction("", "", "USER",
                    actionType, ActionStatus.FAILURE, ACTION, e.toString());
//            throw handleException(e);
            throw (PhmException) e;
        }
    }

    @DeleteMapping(value = "/{projectId}")
    public void delete(@PathVariable(PROJECT_ID) Integer projectId) throws PhmException {
//        checkParameter(PROJECT_ID,projectId);
        final String ACTION="Project "+projectId+"Deleting";
        try {
        projectService.deleteById(projectId);
            logAction(projectId.toString(), "", "USER", ActionType.PROJECT_DELETED,
                    ActionStatus.SUCCESS, ACTION, "");
        } catch (Exception e) {
            logAction(projectId.toString(), "", "USER", ActionType.PROJECT_DELETED,
                    ActionStatus.FAILURE, ACTION, e.getMessage());
            throw handleException(e);
        }
    }

    @GetMapping(value = "/{projectId}")
//    @ResponseBody
    public ProjectEntity getProjectById(@PathVariable(PROJECT_ID) Integer projectId) throws PhmException {
//        checkParameter(CUSTOMER_ID, strCustomerId);
        try {
            Optional<ProjectEntity> projectEntityReadedOpt=projectService.findProjectById(projectId);
            return projectEntityReadedOpt.get();
//            CustomerId customerId = new CustomerId(toUUID(strCustomerId));
//            return checkCustomerId(customerId, Operation.READ);
        } catch (Exception e) {
            throw handleException(e);
        }
    }*/

    @GetMapping(value = "/test")
    public void test(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("*********************************");
        writer.println("*   PHM Scheme Controller Test  *");
        writer.println("*********************************");
    }
}
