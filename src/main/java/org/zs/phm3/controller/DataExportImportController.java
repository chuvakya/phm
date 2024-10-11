package org.zs.phm3.controller;

import org.zs.phm3.PhmDataExportImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.unit.ComponentService;

import java.io.IOException;

@RestController
@RequestMapping("/data")
public class DataExportImportController extends BaseController {
    @Autowired
    PhmDataExportImportService dataExportImportService;

    @Autowired
    ComponentService componentService;

//    public void multiplyComponent(String unitId, String parentUnitId, Integer number) throws PhmException {

    @PostMapping(value = "/multiply_component/{unitId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void multiplyComponent(@PathVariable("unitId") String unitId) {
    }

    @PostMapping(value = "/unit_export/{unitId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void exportUnit(@PathVariable("unitId") String unitId) throws PhmException, IOException {
        checkParameter("unitId", unitId);

        dataExportImportService.exportUnit(unitId);
        dataExportImportService.exportInitialData();

    }

    @GetMapping(value = "/unit_import/{filePath}/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void importUnit(@PathVariable("filePath") String filePath,
                           @PathVariable("projectId") Integer projectId) throws PhmException, IOException {
        checkParameter("filePath", filePath);
        checkIntParameter("projectId", projectId);

        dataExportImportService.importInitialData("target/initialData.json");
        dataExportImportService.importUnit(filePath, projectId);
    }

    @PostMapping(value = "/project_export/{projectId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void exportProject(@PathVariable("projectId") Integer projectId) throws PhmException, IOException {
        checkIntParameter("projectId", projectId);
        dataExportImportService.exportProjectData(projectId);
    }

    @GetMapping(value = "/project_import/{fileName:.+}/{projectId}")
    @ResponseStatus(HttpStatus.OK)

    public void importProject(@PathVariable("fileName") String fileName,
                              @PathVariable("projectId") Integer projectId) throws PhmException, IOException {
        checkParameter("fileName", fileName);
        checkIntParameter("projectId", projectId);
        dataExportImportService.importProjectData(fileName, projectId);

    }
}
