package org.zs.phm3.repository.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.dashboard.Dashboard;

import java.util.List;

@Repository
@Transactional
public interface DashboardRepository extends CrudRepository<Dashboard, Integer> {

    @Query(value = "SELECT id, name " +
            "FROM dashboard WHERE project_id = ?1 AND dashboard_category = 'SYSTEM' ORDER BY name", nativeQuery = true)
    List<List<Object>> getAllSystemByProjectId(Integer projectId);

    @Query(value = "SELECT id, dashboard_type, name, unit_id, modelptl_id " +
            "FROM dashboard WHERE project_id = ?1 AND dashboard_category = 'UNIT' ORDER BY name", nativeQuery = true)
    List<List<Object>> getAllUnitByProjectId(Integer projectId);

    @Query(value = "SELECT dashboard_category, dashboard_type, name, unit_id, created_by_id, modelptl_id " +
            "FROM dashboard WHERE id = ?1", nativeQuery = true)
    List<List<Object>> getAllByDashboardId(Integer dashboardId);

    @Query(value = "SELECT d.created_time, ue.name FROM dashboard d " +
            "JOIN user_entity ue ON d.created_by_id = ue.id WHERE d.id = ?1", nativeQuery = true)
    List<List<Object>> getLogByDashboardId(Integer dashboardId);

    @Query(value = "SELECT d.id, d.unit_id FROM dashboard d " +
            "LEFT JOIN model_ptl mp ON d.modelptl_id = mp.id " +
            "WHERE d.dashboard_category = 'UNIT' AND (EXISTS(SELECT * FROM units u WHERE u.id = ?1 AND u.model = d.modelptl_id) " +
            "OR d.unit_id = ?1) ORDER BY d.unit_id", nativeQuery = true)
    List<Integer> getAllDashboardIdsByUnitId(String unitId);

    @Query(value = "SELECT COUNT(*) FROM dashboard WHERE unit_id = ?1", nativeQuery = true)
    Integer getDashboardCountByUnitId(String unitId);

    @Query(value = "SELECT COUNT(*) FROM dashboard WHERE modelptl_id = ?1", nativeQuery = true)
    Integer getDashboardCountByModelPTLId(Long modelPTLId);

}
