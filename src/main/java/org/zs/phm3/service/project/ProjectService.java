package org.zs.phm3.service.project;

import org.zs.phm3.exception.NotFoundException;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ProjectEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProjectService {
    ProjectEntity save(ProjectEntity projectForSave) throws PhmException, NotFoundException, JsonProcessingException;
    ProjectEntity findProjectById(Integer projectId) throws PhmException;

    List<ProjectEntity> getAllProjects();
    void deleteById(Integer projectId);
    Boolean isFirstProjectUnit(String unitId);
    ProjectEntity update( Integer projectId, ProjectEntity projectForUpdate);
    List<ProjectEntity> findByInactiveIsTrue();
}
