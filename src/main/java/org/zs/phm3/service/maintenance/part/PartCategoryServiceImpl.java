package org.zs.phm3.service.maintenance.part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.maintenance.part.PartCategory;
import org.zs.phm3.repository.maintenance.part.PartCategoryRepository;

import java.util.List;

@Service
public class PartCategoryServiceImpl implements PartCategoryService {

    @Autowired
    private PartCategoryRepository partCategoryRepository;

    @Override
    public PartCategory save(PartCategory partCategory) {
        return partCategoryRepository.save(partCategory);
    }

    @Override
    public void deleteByIdSQL(Integer partCategoryId) {
        partCategoryRepository.deleteByIdSQL(partCategoryId);
    }

    @Override
    public PartCategory getById(Integer partCategoryId) {
        return partCategoryRepository.findById(partCategoryId).get();
    }

    @Override
    public String getAllByProjectId(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        for (List<Object> category : partCategoryRepository.getIdAndNameByProjectId(projectId)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", category.get(0));
            jsonObject.put("name", category.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public Long getCountByName(String name) {
        return partCategoryRepository.getCountByName(name);
    }
}
