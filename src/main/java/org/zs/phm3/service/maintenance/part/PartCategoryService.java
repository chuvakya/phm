package org.zs.phm3.service.maintenance.part;

import org.zs.phm3.models.maintenance.part.PartCategory;

import java.util.List;

public interface PartCategoryService {
    PartCategory save(PartCategory partCategory);
    void deleteByIdSQL(Integer partCategoryId);
    PartCategory getById(Integer partCategoryId);
    String getAllByProjectId(Integer projectId);
    Long getCountByName(String name);
}
