package org.zs.phm3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeEntityId;
import org.zs.phm3.models.SchemeType;
import org.zs.phm3.repository.SchemeRepository;
import org.zs.phm3.repository.project.ProjectRepository;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;

import java.util.List;
import java.util.Optional;

@Service
public class SchemeServiceImpl implements SchemeService {

    @Autowired
    SchemeRepository schemeRepository;
    @Autowired
    private UnitService unitService;


    @Autowired
    ProjectRepository projectRepository;

    @Override
    public SchemeEntity saveForProject(SchemeEntity schemeForSave, Integer projectId) {

        return null;
    }

    @Override
    @Modifying
    @Transactional
    public SchemeEntity save(SchemeEntity schemeForSave) {

        SchemeEntity schemeSaved=schemeRepository.save(schemeForSave);
        return schemeSaved;//schemeRepository.save(schemeForSave);
    }


    @Override
    public SchemeEntity saveFull(Integer projectId, String unitId, SchemeType schemeType, JsonNode jsonBody,
                                 String name, String label) throws JsonProcessingException {

        SchemeEntity newScheme=new SchemeEntity(unitId, schemeType,
                name, jsonBody, label);

        return schemeRepository.save(newScheme);//}

    }

    @Override
    public Optional<SchemeEntity> findById(SchemeEntityId schemeEntityId) {
        return schemeRepository.findById(schemeEntityId);

    }

    @Override
    public void deleteById(SchemeEntityId schemeEntityId) {
        schemeRepository.deleteById(schemeEntityId);
    }

    @Override
    public void delete(SchemeEntity schemeForDel) {
//        schemeRepository.findById(schemeEntityId);
    }

    @Override
    public List<SchemeEntity> getAllByUnitId(String unitId) {

        return schemeRepository.getAllByUnitId(PhmUtil.toShortUUID(unitId));
    }

    @Override
    public List<SchemeEntity> getAllByProjectId(Integer projectId) {
        return schemeRepository.getAllByProjectId(projectId);
    }

    @Override
    public List<SchemeEntity> save(List<SchemeEntity> schemeEntityList) {
        return (List<SchemeEntity>) schemeRepository.saveAll(schemeEntityList);
    }

    @Override
    public List<String> getSchemeForProject(Integer projectId) {
        return schemeRepository.getSchemeForProject(projectId);
    }
}
