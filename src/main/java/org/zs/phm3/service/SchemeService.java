package org.zs.phm3.service;

import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeEntityId;
import org.zs.phm3.models.SchemeType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

public interface SchemeService {
    SchemeEntity saveForProject(SchemeEntity schemeForSave, Integer projectId);

    SchemeEntity save(SchemeEntity schemeForSave);

    SchemeEntity saveFull(Integer projectId, String unitId, SchemeType schemeType, JsonNode jsonBody,
                          String name, String label) throws JsonProcessingException;

    Optional<SchemeEntity> findById(SchemeEntityId schemeEntityId);

    void deleteById(SchemeEntityId schemeEntityId);

    void delete(SchemeEntity schemeForDel);

    List<SchemeEntity> getAllByUnitId(String unitId);

    List<SchemeEntity> getAllByProjectId(Integer projectId);

    List<SchemeEntity> save(List<SchemeEntity> schemeEntityList);
    List<String> getSchemeForProject(Integer projectId);
}
