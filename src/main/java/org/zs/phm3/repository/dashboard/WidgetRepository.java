package org.zs.phm3.repository.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.Widget;

import java.util.List;

@Transactional
@Repository
public interface WidgetRepository extends CrudRepository<Widget, Long> {

    @Query(value = "SELECT wa.ts_kv_attribute_id, tka.unit_id, wa.name FROM widget_attribute wa " +
            "JOIN ts_kv_attribute tka ON wa.ts_kv_attribute_id = tka.id " +
            "WHERE wa.widget_id = ?1", nativeQuery = true)
    List<List<Object>> getAllAttributesByWidgetId(Long widgetId);

    @Query(value = "SELECT wa.widget_id, wa.ts_kv_attribute_id, tka.unit_id, wa.name FROM widget_attribute wa " +
            "JOIN ts_kv_attribute tka ON wa.ts_kv_attribute_id = tka.id " +
            "WHERE wa.widget_id IN ?1", nativeQuery = true)
    List<List<Object>> getAllAttributesByWidgetIds(List<Long> widgetIds);

    @Query(value = "SELECT id, front_widget_id, name, default_widget, json " +
            "FROM widget WHERE (project_id = ?1 OR project_id IS NULL) ORDER BY name", nativeQuery = true)
    List<List<Object>> getAllByProjectIdForAddWidget(Integer projectId);

    @Query(value = "SELECT id, front_widget_id, name, default_widget " +
            "FROM widget WHERE (project_id = ?1 OR project_id IS NULL) ORDER BY name", nativeQuery = true)
    List<List<Object>> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT w.created_time, ue.name FROM widget w " +
            "JOIN user_entity ue ON w.created_by_id = ue.id " +
            "WHERE w.id = ?1", nativeQuery = true)
    List<List<Object>> getLogByWidgetId(Long widgetId);

    @Query(value = "SELECT wa.name, wa.ts_kv_attribute_id FROM widget_attribute wa WHERE widget_id = ?1", nativeQuery = true)
    List<List<Object>> getAllAttributesIdByWidgetId(Long widgetId);

}
