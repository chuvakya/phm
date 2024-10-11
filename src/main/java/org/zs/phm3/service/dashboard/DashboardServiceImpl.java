package org.zs.phm3.service.dashboard;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.Dashboard;
import org.zs.phm3.models.dashboard.request.DashboardFilter;
import org.zs.phm3.repository.dashboard.DashboardEditLogRepository;
import org.zs.phm3.repository.dashboard.DashboardRepository;
import org.zs.phm3.repository.dashboard.DashboardWidgetRepository;
import org.zs.phm3.repository.dashboard.WidgetRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementing DashboardService class
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    /** Dashboard repository */
    @Autowired
    private DashboardRepository dashboardRepository;

    /** Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    /** Dashboard widget repository */
    @Autowired
    private DashboardWidgetRepository dashboardWidgetRepository;

    /** Dashboard edit log repository */
    @Autowired
    private DashboardEditLogRepository dashboardEditLogRepository;

    /**
     * Saving dashboard
     * @param dashboard dashboard
     * @return dashboard
     */
    @Override
    public Dashboard save(Dashboard dashboard) {
        return dashboardRepository.save(dashboard);
    }

    /**
     * Getting dashboard by dashboard ID
     * @param dashboardId dashboard ID
     * @return deshboard
     */
    @Override
    public Dashboard getById(Integer dashboardId) {
        return dashboardRepository.findById(dashboardId).get();
    }

    /**
     * Getting json string dashboard by dashboard ID
     * @param dashboardId dashboard ID
     * @return json string dashboard
     */
    @Override
    public String getByIdJSON(Integer dashboardId) {
        List<List<Object>> dashboard = dashboardRepository.getAllByDashboardId(dashboardId);
        JSONObject jsonObject = new JSONObject();
        JSONArray arrayWidgets = new JSONArray();
        jsonObject.put("id", dashboardId);
        jsonObject.put("name", dashboard.get(0).get(2));
        jsonObject.put("category", dashboard.get(0).get(0));
        jsonObject.put("type", dashboard.get(0).get(1));
        if (dashboard.get(0).get(3) != null) {
            jsonObject.put("unitId", PhmUtil.toLongUUID(dashboard.get(0).get(3).toString()));
        } else {
            jsonObject.put("unitId", null);
        }
        jsonObject.put("userId", dashboard.get(0).get(4));
        jsonObject.put("modelPTLId", dashboard.get(0).get(5));
        List<List<Object>> widgets = dashboardWidgetRepository.getAllWidgetsByDashboardId(dashboardId);
        List<Long> dashboardWidgetIds = new ArrayList<>();
        for (List<Object> widget : widgets) {
            dashboardWidgetIds.add(Long.parseLong(widget.get(4).toString()));
        }
        List<List<Object>> attributes = dashboardWidgetRepository.getAllAttributesByDashboardWidgetIds(dashboardWidgetIds);
        for (List<Object> widget : widgets) {
            JSONObject object = new JSONObject();
            JSONArray arrayAttributes = new JSONArray();
            object.put("json", widget.get(0));
            object.put("additionalJSON", widget.get(1));
            object.put("frontWidgetId", widget.get(2));
            object.put("name", widget.get(3));
            object.put("dashboardWidgetId", widget.get(4));
            for (List<Object> attribute : attributes) {
                if (attribute.get(0).equals(widget.get(4))) {
                    JSONObject object1 = new JSONObject();
                    object1.put("attributeId", attribute.get(1));
                    object1.put("unitId", PhmUtil.toLongUUID(attribute.get(2).toString()));
                    object1.put("name", attribute.get(3));
                    arrayAttributes.add(object1);
                }
            }
            object.put("attributes", arrayAttributes);
            arrayWidgets.add(object);
        }
        jsonObject.put("widgets", arrayWidgets);
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string system dashboards by project ID
     * @param projectId project ID
     * @return json string system dashboards
     */
    @Override
    public String getAllSystemByProjectId(Integer projectId) {
        List<List<Object>> lists = dashboardRepository.getAllSystemByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("name", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string unit dashboards by project ID
     * @param projectId project ID
     * @return json string unit dashboards
     */
    @Override
    public String getAllUnitByProjectId(Integer projectId) {
        List<List<Object>> lists = dashboardRepository.getAllUnitByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("name", list.get(2));
            jsonObject.put("dashboardType", list.get(1));
            if (list.get(3) != null) {
                jsonObject.put("unitId", PhmUtil.toLongUUID(list.get(3).toString()));
            } else {
                jsonObject.put("unitId", null);
            }
            jsonObject.put("modelPTLId", list.get(4));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string user info by dashboard ID
     * @param dashboardId dashboard ID
     * @return json string user info
     */
    @Override
    public String getUserInfoByDashboardId(Integer dashboardId) {
        List<List<Object>> firstLog = dashboardRepository.getLogByDashboardId(dashboardId);
        List<List<Object>> lastLog = dashboardEditLogRepository.getLastLogByDashboardId(dashboardId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createdTime", firstLog.get(0).get(0));
        jsonObject.put("createdUserName", firstLog.get(0).get(1));
        if (!lastLog.isEmpty()) {
            jsonObject.put("lastModifiedBy", lastLog.get(0).get(0));
            jsonObject.put("lastModifiedByUserName", lastLog.get(0).get(1));
        } else {
            jsonObject.put("lastModifiedTime", firstLog.get(0).get(0));
            jsonObject.put("lastModifiedByUserName", firstLog.get(0).get(1));
        }
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string all dashboards by dashboard filter and login
     * @param dashboardFilter dashboard filter
     * @param userLogin user login
     * @return json string all dashboards
     */
    @Transactional
    @Override
    public String getAllByFilter(DashboardFilter dashboardFilter, String userLogin) {
        StringBuilder builder = new StringBuilder("SELECT d.id, d.created_time, d.dashboard_category, d.dashboard_type, " +
                "d.name, d.project_id, d.unit_id, d.created_by_id, d.modelptl_id " +
                "FROM dashboard d " +
                "JOIN user_entity u ON d.created_by_id = u.id " +
                "WHERE d.project_id = " + dashboardFilter.getProjectId());

        if (dashboardFilter.getCustomUnit() || dashboardFilter.getUnitModel()) {
            if (dashboardFilter.getCustomUnit() && !dashboardFilter.getUnitModel()) {
                builder.append(" AND d.unit_id IS NOT NULL");
            } else if (dashboardFilter.getCustomUnit() && dashboardFilter.getUnitModel()) {
                builder.append(" AND (d.modelptl_id IS NOT NULL OR d.unit_id IS NOT NULL)");
            } else {
                builder.append(" AND d.modelptl_id IS NOT NULL");
            }
        }
        if (dashboardFilter.getMonitoringType() || dashboardFilter.getPrognosisType()) {
            if (dashboardFilter.getMonitoringType() && !dashboardFilter.getPrognosisType()) {
                builder.append(" AND d.dashboard_type = 'MONITORING'");
            } else if (dashboardFilter.getMonitoringType() && dashboardFilter.getPrognosisType()) {
                builder.append("AND (d.dashboard_type = 'MONITORING' OR d.dashboard_type = 'PROGNOSIS')");
            } else {
                builder.append("AND d.dashboard_type = 'PROGNOSIS'");
            }
        }
        if (dashboardFilter.getSystemDashboard()) {
            builder.append(" AND d.dashboard_category = 'SYSTEM'");
        } else {
            builder.append(" AND d.dashboard_category = 'UNIT'");
        }
        if (dashboardFilter.getMyDashboards()) {
            String userLoginReplace = userLogin.replaceAll("'", "''");
            builder.append(" AND (EXISTS(SELECT * FROM dashboard_edit_log del " +
                    "JOIN user_entity ue ON del.user_entity_id = ue.id " +
                    "WHERE dashboard_id = d.id AND ue.login = '" + userLoginReplace +
                    "') OR u.login = '" + userLoginReplace + "')");
        }
        if (dashboardFilter.getName() != null) {
            builder.append(" AND d.name ILIKE '%" + dashboardFilter.getName().replaceAll("'", "''") + "%'");
        }
        builder.append(" ORDER BY d.name");
        List<Dashboard> dashboards = entityManager.createNativeQuery(builder.toString(), Dashboard.class).getResultList();
        JSONArray jsonArray = new JSONArray();
        for (Dashboard dashboard : dashboards) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", dashboard.getId());
            jsonObject.put("name", dashboard.getName());
            if (dashboard.getDashboardType() != null) {
                jsonObject.put("type", dashboard.getDashboardType().toString());
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public Integer getDashboardCountByUnitId(String unitId) {
        return dashboardRepository.getDashboardCountByUnitId(unitId);
    }

    @Override
    public Integer getDashboardCountByModelPTLId(Long modelPTLId) {
        return dashboardRepository.getDashboardCountByModelPTLId(modelPTLId);
    }

}
