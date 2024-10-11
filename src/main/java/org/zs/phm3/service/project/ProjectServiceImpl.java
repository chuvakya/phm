package org.zs.phm3.service.project;

import org.zs.phm3.dto.UnitDto;
import org.zs.phm3.dto.UnitDtoInput;
import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.FileEntity;
import org.zs.phm3.models.ProjectEntity;
import org.zs.phm3.models.ts.TsKvAttributeType;
import org.zs.phm3.repository.project.ProjectRepository;
import org.zs.phm3.util.GetNullProperties;
import org.zs.phm3.util.mapping.PhmUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.service.BaseService;
import org.zs.phm3.service.FileService;
import org.zs.phm3.service.SchemeService;
import org.zs.phm3.service.ts.TsKvAttributeTypeService;
import org.zs.phm3.service.unit.UnitService;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    SchemeService schemeService;
    @Autowired
    UnitService unitService;
    @Autowired
    private FileService fileService;

    @Autowired
    private TsKvAttributeTypeService tsKvAttributeTypeService;

    @Modifying
    @Transactional
//    @CachePut(value = "projects", key = "#projectForSave.id")
//    @CacheEvict(value = "projects", key = "'all'")

//    OLD
/*    @Caching(
            evict = {
                    @CacheEvict(value = "projects", key = "#projectId"),
                    @CacheEvict(value = "projects", key = "'all'")
            }
    )*/
    public ProjectEntity update(Integer projectId, ProjectEntity projectForUpdate){

        Optional<ProjectEntity> projectActualOpt = projectRepository.findById(projectId);
        ProjectEntity projectActual=projectActualOpt.get();
        BeanUtils.copyProperties(projectForUpdate, projectActual, GetNullProperties.getNullPropertyNames(projectForUpdate));

        ProjectEntity projectSaved = projectRepository.save(projectActual);
        return projectRepository.save(projectActual);

//        ProjectEntity projectSaved = projectRepository.save(projectForSave);
    }

    @Override
    public List<ProjectEntity> findByInactiveIsTrue() {
        return projectRepository.findByinActiveIsTrue();
    }

    public ProjectEntity save(ProjectEntity projectForSave) throws PhmException, NotFoundException, JsonProcessingException {
        ProjectEntity projectSaved;

        if (projectForSave.getPic_id() != null) {
            FileEntity picture = fileService.findById(projectForSave.getPic_id());
            projectForSave.setPicture_file(picture);
        }

        if (projectForSave.getId() == null)// new project
        {
            projectForSave.setInActive(false);
            if (projectRepository.checkName(projectForSave.getName())) {
                throw new PhmException("Such Project name '" + projectForSave.getName() + "' already exist! " +
                        "Input another name", PhmErrorCode.BAD_REQUEST_PARAMS);
            }

            ProjectEntity projectSavedFirst = projectRepository.save(projectForSave);
//          ***  creating and saving first (service) unit
            UnitDtoInput newFirstUnit = new UnitDtoInput(projectSavedFirst.getId(), "Unit for project id " +
                    projectSavedFirst.getId()+" "+
                    projectSavedFirst.getName(),"Don't Remove It!");
            UnitDto savedServiceUnit=unitService.save(newFirstUnit);
            projectSavedFirst.setFirstUnitId(PhmUtil.toShortUUID(savedServiceUnit.getId()));
            projectSaved = projectRepository.save(projectSavedFirst);

        } else {// project exist
            Optional<ProjectEntity> projectActual = projectRepository.findById(projectForSave.getId());
            projectForSave.setFirstUnitId(projectActual.get().getFirstUnitId());
            projectForSave.setInActive(projectActual.get().getInActive());
            projectSaved = projectRepository.save(projectForSave);

            List<String> standartCategories = Arrays.asList("Status", "Environment", "Signal", "Calibration", "Test/BIT",
                    "Business", "Logs");
            for (String standartCategory : standartCategories) {
                tsKvAttributeTypeService.save(new TsKvAttributeType(projectSaved.getId(), standartCategory));
            }
        }
        return projectSaved;
    }

//    @Cacheable(value = "projects", key = "#projectId")

    public ProjectEntity findProjectById(Integer projectId) throws PhmException {
        ProjectEntity projectReaded = null;
        System.out.println("Reading project id=" + projectId);
        try {
            Optional<ProjectEntity> projectReadedOpt = projectRepository.findById(projectId);
            if (projectReadedOpt.isPresent()) {
                projectReaded = projectReadedOpt.get();
                return projectReaded;
            } else throw new PhmException("Project with id " + projectId +
                    " not found", PhmErrorCode.ITEM_NOT_FOUND);
        } catch (NoSuchElementException e) {
            throw new PhmException();
        }
    }

//    @Cacheable(value = "projects", key = "'all'")
    public List<ProjectEntity> getAllProjects() {
        return (List<ProjectEntity>) projectRepository.findAllByinActiveIsFalse();
    }

    @Override
/*    @Caching(
            evict = {
                    @CacheEvict(value = "projects", key = "#projectId"),
                    @CacheEvict(value = "projects", key = "'all'")
            }
    )*/
    @Transactional
    public void deleteById(Integer projectId) {
//        projectRepository.deleteById(projectId);
        projectRepository.deactivateProjectsById(projectId);
    }


    @Override
    public Boolean isFirstProjectUnit(String unitId) {
        return projectRepository.isFirstProjectUnit(unitId);
    }

}
