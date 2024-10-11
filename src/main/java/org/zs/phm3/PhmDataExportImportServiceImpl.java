package org.zs.phm3;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.data.UUIDConverter;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.models.unit.UnitModel;
import org.zs.phm3.models.unit.UnitModelAttribute;
import org.zs.phm3.models.unit.UnitType;
import org.zs.phm3.service.BaseService;
import org.zs.phm3.service.FileService;
import org.zs.phm3.service.project.ProjectService;
import org.zs.phm3.service.unit.UnitModelAttributeService;
import org.zs.phm3.service.unit.UnitModelService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.service.unit.UnitTypeService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhmDataExportImportServiceImpl extends BaseService implements PhmDataExportImportService {
    @Autowired
    UnitService unitService;
    @Autowired
    ProjectService projectService;
    @Autowired
    FileService fileService;
    @Autowired
    UnitTypeService unitTypeService;
    @Autowired
    UnitModelService unitModelService;
    @Autowired
    UnitModelAttributeService unitModelAttributeService;
    @Value("${global.dataExchangePath}")
    private String exchangeFolder;
    String fileName;

    //    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    @Transactional
    public String exportUnit(String unitId) throws PhmException, IOException {
        UnitEntity unitLoaded = unitService.findByIdReturnEntity(unitId, true);
        fileName = "unit_" + unitId + ".json";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(exchangeFolder + fileName), unitLoaded);
        return null;
    }

    @Override
    /*
    icon, background, model, parent, childs
     */
    public String importUnit(String filePath, Integer projectId) throws PhmException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UnitEntity unitReaded = objectMapper.readValue(new File(filePath), UnitEntity.class);
        unitReaded.setDescription(unitReaded.getDescription() + " import_from_file");
        unitReaded.setDateCreated(LocalDateTime.now());
        unitReaded.setDateChanged(LocalDateTime.now());
        unitReaded.setProject(projectService.findProjectById(projectId));
        unitService.SaveAfterImport(unitReaded);
        return null;
    }

    @Override
    public String exportInitialData() throws IOException {

        InitialData initialData = prepareInitialDate();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File("target/initialData.json"), initialData);
        return null;
    }

    private InitialData prepareInitialDate() {
        return new InitialData(fileService.findAll(),
                (ArrayList<UnitType>) unitTypeService.getAll(),
                (ArrayList<UnitModel>) unitModelService.getAll(),
                (ArrayList<UnitModelAttribute>) unitModelAttributeService.getAll()
        );
    }


    @Override
    public String importInitialData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InitialData initialData = objectMapper.readValue(new File(filePath), InitialData.class);
        saveInitialData(initialData);
        return null;
    }

    @Override
    public String exportProjectData(Integer projectId) throws IOException {
        List<String[]> unitIdParentIdsList = unitService.getUnitIdParentIds();

        ArrayList<UnitEntity> unitsList = new ArrayList<>();
        unitIdParentIdsList.forEach(line -> {
            try {
                unitsList.add(unitService.findByIdReturnEntity(UUIDConverter.fromString(line[0]).toString(), true));
            } catch (PhmException e) {
                e.printStackTrace();
            }
        });

        ProjectData projectData = new ProjectData(unitsList,
                (ArrayList<String[]>) unitIdParentIdsList, prepareInitialDate());

        fileName = "Data_for_project_" + projectId + "_from_" +
                LocalDateTime.now(ZoneId.of("Europe/Minsk")) + ".json";
        String fileCor = fileName.replace(':', '_');
        String filePath = exchangeFolder + fileCor;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File(filePath), projectData);
        return null;
    }

    @Override
    public String importProjectData(String fileName, Integer projectId) throws PhmException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProjectData projectData = objectMapper.readValue(new File(exchangeFolder + fileName), ProjectData.class);
            saveInitialData(projectData.getInitialData());

            projectData.getUnitEntitiesData().forEach(unit -> {
                try {
                    /*  we don't need to import the previous project unit root (we have a new one),
                        so find it by name in list and drop it  */
                    if (!unit.getName().contains("Unit for project id"))//
                        saveUnitData(unit, projectId);
                } catch (PhmException e) {
                    e.printStackTrace();
                }
            });
            String actualProjectFirstUnitId = projectService.findProjectById(projectId).getFirstUnitId();
            ArrayList<String[]> parentMapping = projectData.getParentMapping();
            final String[] importedProjectUnitId = new String[1];
            /*  find the the previous Project First Unit id, it haven't a parent*/
            parentMapping.forEach(line -> {
                if (line[1] == null)
                    importedProjectUnitId[0] = line[0];
            });
            parentMapping.forEach(line -> {
                if (line[1] != null) {
                    if (line[1].equals(importedProjectUnitId[0])) {
                        /*replace with actual*/
                        unitService.assaignParent(line[0], actualProjectFirstUnitId);
                    } else
                        unitService.assaignParent(line[0], line[1]);
                }
            });


        } catch (Exception e) {
            throw handleException(e);
        }
        return null;
    }

    private void saveInitialData(InitialData initialData) {
        if (initialData.getFilesData() != null)
            fileService.saveAll(initialData.getFilesData());

        if (initialData.getUnitTypesData() != null)
            unitTypeService.saveAll(initialData.getUnitTypesData());

        if (initialData.getUnitModelsData() != null)
            unitModelService.saveAll(initialData.getUnitModelsData());

        if (initialData.getUnitModelAttributesData() != null)
            unitModelAttributeService.saveAll(initialData.getUnitModelAttributesData());
//        UnitModelAttributeService unitModelAttributeService;

    }

    private void saveUnitData(UnitEntity unitReaded, Integer projectId) throws PhmException {
        unitReaded.setDescription(unitReaded.getDescription() + " import_from_file");
        unitReaded.setDateCreated(LocalDateTime.now());
        unitReaded.setDateChanged(LocalDateTime.now());
        unitReaded.setProject(projectService.findProjectById(projectId));
        unitService.SaveAfterImport(unitReaded);
    }

}
