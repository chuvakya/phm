package org.zs.phm3.service.maintenance.dashboard;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceDashboard;
import org.zs.phm3.repository.maintenance.dashboard.MaintenanceDashboardRepository;

import java.util.List;

@Service
public class MaintenanceDashboardServiceImpl implements MaintenanceDashboardService {

    @Autowired
    private MaintenanceDashboardRepository maintenanceDashboardRepository;

    @Override
    public MaintenanceDashboard save(MaintenanceDashboard maintenanceDashboard) {
        return maintenanceDashboardRepository.save(maintenanceDashboard);
    }

    @Override
    public MaintenanceDashboard getById(Integer dashboardId) {
        return maintenanceDashboardRepository.findById(dashboardId).get();
    }

    @Override
    public String getAllForMenuByLoginAndProjectId(String login, Integer projectId) {
        List<List<Object>> lists = maintenanceDashboardRepository.getAllForMenuByLoginAndProjectId(login, projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("name", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

}
