package org.zs.phm3.repository.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.WidgetEditLog;

import java.util.List;

@Transactional
@Repository
public interface WidgetEditLogRepository extends CrudRepository<WidgetEditLog, Long> {

    @Query(value = "SELECT wel.timestamp, ue.name FROM widget_edit_log wel " +
            "JOIN user_entity ue ON wel.user_entity_id = ue.id " +
            "WHERE widget_id = ?1 ORDER BY timestamp LIMIT 1", nativeQuery = true)
    List<List<Object>> getFirstLogByWidgetId(Long widgetId);

    @Query(value = "SELECT wel.timestamp, ue.name FROM widget_edit_log wel " +
            "JOIN user_entity ue ON wel.user_entity_id = ue.id " +
            "WHERE widget_id = ?1 ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    List<List<Object>> getLastLogByWidgetId(Long widgetId);
}
