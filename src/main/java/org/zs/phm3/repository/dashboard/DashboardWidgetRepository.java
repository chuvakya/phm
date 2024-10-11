package org.zs.phm3.repository.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.DashboardWidget;

import java.util.List;

@Transactional
@Repository
public interface DashboardWidgetRepository extends CrudRepository<DashboardWidget, Long> {

    @Query(value = "SELECT dw.json, dw.additionaljson, dw.front_widget_id, dw.name, dw.id " +
            "FROM dashboard_widget dw " +
            "WHERE dw.dashboard_id = ?1", nativeQuery = true)
    List<List<Object>> getAllWidgetsByDashboardId(Integer dashboardId);

    @Query(value = "SELECT dw.json, dw.front_widget_id, dw.name, dw.id " +
            "FROM dashboard_widget dw " +
            "WHERE dw.dashboard_id = ?1", nativeQuery = true)
    List<List<Object>> getAllWidgetsByDashboardIdForExplorer(Integer dashboardId);

    @Query(value = "SELECT dwa.dashboard_widget_id, dwa.ts_kv_attribute_id, tka.unit_id, dwa.name " +
            "FROM dashboard_widget_attribute dwa " +
            "JOIN ts_kv_attribute tka ON dwa.ts_kv_attribute_id = tka.id " +
            "WHERE dwa.dashboard_widget_id IN ?1", nativeQuery = true)
    List<List<Object>> getAllAttributesByDashboardWidgetIds(List<Long> dashboardWidgetIds);

    @Query(value = "SELECT tka.attribute_key, tka.device_id, tka.data_type, wa.name AS waName, " +
            "ui.name AS uomInputName, uo.name AS uomOutputName, ui.type, tka.output_symbol " +
            "FROM dashboard_widget_attribute wa " +
            "JOIN ts_kv_attribute tka ON wa.ts_kv_attribute_id = tka.id " +
            "JOIN uom ui ON tka.uom_input_id = ui.id " +
            "JOIN uom uo ON tka.uom_output_id = uo.id " +
            "WHERE wa.dashboard_widget_id = ?1", nativeQuery = true)
    List<List<Object>> getAllAttributesByDashboardWidgetId(Long dashboardWidgetId);
}
