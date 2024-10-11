package org.zs.phm3.service.dashboard;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.Widget;
import org.zs.phm3.models.dashboard.request.WidgetAttributeRequest;
import org.zs.phm3.models.dashboard.request.WidgetFilter;
import org.zs.phm3.repository.dashboard.WidgetEditLogRepository;
import org.zs.phm3.repository.dashboard.WidgetRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementing WidgetService class
 * @author Pavel Chuvak
 */
@Service
public class WidgetServiceImpl implements WidgetService {

    /** Widget repository */
    @Autowired
    private WidgetRepository widgetRepository;

    /** Widget edit log repository */
    @Autowired
    private WidgetEditLogRepository widgetEditLogRepository;

    /** Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getting widget by widget ID
     * @param widgetId widget ID
     * @return widget
     */
    @Override
    public Widget getById(Long widgetId) {
        return widgetRepository.findById(widgetId).get();
    }

    /**
     * Saving widget
     * @param widget widget
     * @return widget
     */
    @Override
    public Widget save(Widget widget) {
        return widgetRepository.save(widget);
    }

    /**
     * Adding attributes from attributes and widget ID
     * @param attributes attributes
     * @param widgetId widget ID
     */
    @Transactional
    @Override
    public void addAttributesByWidgetId(List<WidgetAttributeRequest> attributes, Long widgetId) {
        if (!attributes.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO widget_attribute (ts_kv_attribute_id, widget_id, name) " +
                    "VALUES (" + attributes.get(0).getAttributeId() + ", " + widgetId + ", '" +
                    attributes.get(0).getName().replaceAll("'", "''") + "');");
            for (int i = 1; i < attributes.size(); i++) {
                stringBuilder.append("INSERT INTO widget_attribute (ts_kv_attribute_id, widget_id, name) " +
                        "VALUES (" + attributes.get(i).getAttributeId() + ", " + widgetId + ", '" +
                        attributes.get(i).getName().replaceAll("'", "''")+ "');");
            }
            entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
        }
    }

    /**
     * Getting json string all widgets by project ID
     * @param projectId project ID
     * @return json string all widgets
     */
    @Override
    public String getAllByProjectId(Integer projectId) {
        List<List<Object>> widgets = widgetRepository.getAllByProjectId(projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> widget : widgets) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", widget.get(0));
            jsonObject.put("frontWidgetId", widget.get(1));
            jsonObject.put("name", widget.get(2));
            jsonObject.put("defaultWidget", widget.get(3));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string widget by widget ID
     * @param widgetId widget ID
     * @return json string widget
     */
    @Override
    public String getByIdJSON(Long widgetId) {
        Widget widget = widgetRepository.findById(widgetId).get();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("id", widget.getId());
        jsonObject.put("name", widget.getName());
        jsonObject.put("json", widget.getJson());
        jsonObject.put("frontWidgetId", widget.getFrontWidgetId());
        jsonObject.put("type", widget.getType());
        List<List<Object>> attrs = widgetRepository.getAllAttributesByWidgetId(widgetId);
        for (List<Object> attr : attrs) {
            JSONObject object = new JSONObject();
            object.put("attributeId", attr.get(0));
            object.put("unitId", PhmUtil.toLongUUID((String) attr.get(1)));
            object.put("name", attr.get(2));
            jsonArray.add(object);
        }
        jsonObject.put("attributes", jsonArray);
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string user info by widget ID
     * @param widgetId widget ID
     * @return json string user info
     */
    @Override
    public String getUserInfoByWidgetId(Long widgetId) {
        List<List<Object>> firstLog = widgetRepository.getLogByWidgetId(widgetId);
        List<List<Object>> lastLog = widgetEditLogRepository.getLastLogByWidgetId(widgetId);
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
     * Getting json string all widgets by widget filter and user login
     * @param widgetFilter widget filter
     * @param userLogin user login
     * @return json string all widgets
     */
    @Transactional
    @Override
    public String getAllByFilter(WidgetFilter widgetFilter, String userLogin) {
        StringBuilder builder = new StringBuilder("SELECT w.id, w.created_time, w.front_widget_id, w.json, w.name, " +
                "w.project_id, w.type, w.created_by_id, w.default_widget " +
                "FROM widget w " +
                "JOIN user_entity ue ON w.created_by_id = ue.id " +
                "WHERE (w.project_id = " + widgetFilter.getProjectId() + " OR w.project_id IS NULL)");
        if (widgetFilter.getDefault() || widgetFilter.getUserWidget()) {
            if (widgetFilter.getDefault() && !widgetFilter.getUserWidget()) {
                builder.append(" AND w.default_widget = true");
            } else if (widgetFilter.getDefault() && widgetFilter.getUserWidget()) {
                builder.append(" AND (w.default_widget = false OR w.default_widget = true)");
            } else {
                builder.append(" AND w.default_widget = false");
            }
        }
        if (widgetFilter.getMyWidgets()) {
            String login = userLogin.replaceAll("'", "''");
            builder.append(" AND (EXISTS(SELECT * FROM widget_edit_log wel " +
                    "JOIN user_entity u on wel.user_entity_id = u.id WHERE u.login = '" + login + "') OR ue.login = '" +
                    login + "')");
        }
        if (widgetFilter.getName() != null) {
            builder.append(" AND w.name ILIKE '%" + widgetFilter.getName().replaceAll("'", "''") + "%'");
        }
        if (!widgetFilter.getTypes().isEmpty()) {
            builder.append(" AND w.type IN ('" + widgetFilter.getTypes().get(0).replaceAll("'", "''") + "'");
            for (int i = 1; i < widgetFilter.getTypes().size(); i++) {
                builder.append(", '" + widgetFilter.getTypes().get(i).replaceAll("'", "''") + "'");
            }
            builder.append(")");
        }
        builder.append(" ORDER BY w.name");
        List<Widget> widgets = entityManager.createNativeQuery(builder.toString(), Widget.class).getResultList();
        JSONArray jsonArray = new JSONArray();
        for (Widget widget : widgets) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", widget.getId());
            jsonObject.put("frontWidgetId", widget.getFrontWidgetId());
            jsonObject.put("name", widget.getName());
            jsonObject.put("default", widget.getDefaultWidget());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Coping attributes by widget ID and copy widget ID
     * @param widgetId widget ID
     * @param copyWidgetId copy widget ID
     */
    @Transactional
    @Override
    public void copyAttributes(Long widgetId, Long copyWidgetId) {
        List<List<Object>> attributes = widgetRepository.getAllAttributesIdByWidgetId(widgetId);
        List<WidgetAttributeRequest> attributeRequests = new ArrayList<>();
        for (List<Object> attribute : attributes) {
            attributeRequests.add(new WidgetAttributeRequest((String) attribute.get(0),
                    Long.parseLong(attribute.get(1).toString())));
        }
        addAttributesByWidgetId(attributeRequests, copyWidgetId);
    }

    /**
     * Getting json string all widgets by project ID
     * @param projectId project ID
     * @return json string all widgets
     */
    @Override
    public String getAllByProjectIdForAddWidget(Integer projectId) {
        List<List<Object>> widgets = widgetRepository.getAllByProjectIdForAddWidget(projectId);
        List<Long> widgetIds = new ArrayList<>();
        for (List<Object> widget : widgets) {
            widgetIds.add(Long.parseLong(widget.get(0).toString()));
        }
        List<List<Object>> attributes = widgetRepository.getAllAttributesByWidgetIds(widgetIds);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> widget : widgets) {
            JSONObject jsonObject = new JSONObject();
            JSONArray arrayAttributes = new JSONArray();
            jsonObject.put("id", widget.get(0));
            jsonObject.put("frontWidgetId", widget.get(1));
            jsonObject.put("name", widget.get(2));
            jsonObject.put("defaultWidget", widget.get(3));
            jsonObject.put("json", widget.get(4));
            for (List<Object> attribute : attributes) {
                if (widget.get(0).equals(attribute.get(0))) {
                    JSONObject object = new JSONObject();
                    object.put("attributeId", attribute.get(1));
                    object.put("unitId", PhmUtil.toLongUUID(attribute.get(2).toString()));
                    object.put("name", attribute.get(3));
                    arrayAttributes.add(object);
                }
            }
            jsonObject.put("attributes", arrayAttributes);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

}
