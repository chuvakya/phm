package org.zs.phm3.repository.maintenance.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.dashboard.MaintenanceDashboard;

import java.util.List;

@Transactional
@Repository
public interface MaintenanceDashboardRepository extends CrudRepository<MaintenanceDashboard, Integer> {

    @Query(value = "SELECT md.id, md.name FROM maintenance_dashboard md " +
            "WHERE md.user_role_id = (SELECT ur.user_role_id FROM user_entity_user_role ur " +
            "JOIN user_entity ue ON ur.user_entity_id = ue.id " +
            "WHERE ue.login = ?1) AND md.project_id = ?2 ORDER BY md.name", nativeQuery = true)
    List<List<Object>> getAllForMenuByLoginAndProjectId(String login, Integer projectId);
}
