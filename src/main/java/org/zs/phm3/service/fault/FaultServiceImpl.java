package org.zs.phm3.service.fault;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.fault.*;
import org.zs.phm3.repository.fault.FaultRepository;
import org.zs.phm3.repository.unit.UnitRepository;
import org.zs.phm3.util.mapping.PhmUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Imliminating FaultService interface class
 * @author Pavel Chuvak
 */
@Service
public class FaultServiceImpl implements FaultService {

    /** Fault repository */
    @Autowired
    private FaultRepository faultRepository;

    /** Unit repository */
    @Autowired
    private UnitRepository unitRepository;

    /** Entity manager */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getting all unit child IDs including parent unit ID by parent unit ID
     * @param unitId parent unit ID
     * @return list child unit IDs
     */
    public List<String> getAllUnitIdsByUnitId(String unitId) {
        List<String> unitIds = new ArrayList<>();
        unitIds.add(unitId);
        return getAllUnitIds(unitId, unitIds);
    }

    /**
     * Getting all unit child IDs unit ID by parent unit ID
     * @param unitId parent unit ID
     * @param unitIds list unit IDs
     * @return list child unit IDs
     */
    private List<String> getAllUnitIds(String unitId, List<String> unitIds) {
        List<String> childIds = unitRepository.getAllIdByParentId(unitId);
        unitIds.addAll(childIds);
        for (String childId : childIds) {
            getAllUnitIds(childId, unitIds);
        }
        return unitIds;
    }

    /**
     * Getting all faults by unit ID
     * @param unitId unit ID
     * @return all faults
     */
    @Override
    public List<Fault> getFaultsByUnitId(String unitId) {
        List<String> unitIds = getAllUnitIdsByUnitId(unitId);
        return faultRepository.getAllByUnitIds(unitIds);
    }

    /**
     * Getting all faults by unit ID and offset and limit
     * @param unitId unit ID
     * @param offset offset
     * @param limit limit
     * @return faults
     */
    @Override
    public String getFaultsByUnitIdAndOffsetAndLimit(String unitId, Integer offset, Integer limit) {
        List<String> unitIds = getAllUnitIdsByUnitId(unitId);
        List<List<Object>> lists = faultRepository.getAllByUnitIdsAndOffsetAndLimit(unitIds, offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("faultId", list.get(0));
            jsonObject.put("startTime", list.get(1));
            jsonObject.put("endTime", list.get(2));
            jsonObject.put("name", list.get(3));
            jsonObject.put("priority", Priority.getNameByCode((int) list.get(4)));
            jsonObject.put("status", list.get(5).toString());
            jsonObject.put("severity", Severity.getNameByCode((int) list.get(6)));
            jsonObject.put("message", list.get(7));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Saving fault
     * @param fault fault
     * @return fault
     */
    @Override
    public Fault save(Fault fault) {
        return faultRepository.save(fault);
    }

    /**
     * Getting menu by project ID
     * @param projectId project ID
     * @return json string menu
     */
    @Override
    public String getMenu(Integer projectId) {
        List<List<Object>> listUnits = unitRepository.getAllUnitIdsAndNameAndParentIdSQL(projectId);
        String mainUnitId = null;
        // Find Main unit
            for (List<Object> unitFields : listUnits) {
            if (unitFields.get(2) == null) {
                mainUnitId = (String) unitFields.get(0);
                break;
            }
        }
        JSONArray jsonArray = new JSONArray();
        for (List<Object> unitFields: listUnits) {
            if (mainUnitId.equals(unitFields.get(0))) continue;
            JSONObject jsonObjectUnit = new JSONObject();
            if (unitFields.get(2) != null && !mainUnitId.equals(unitFields.get(2))) {
                jsonObjectUnit.put("parentId", PhmUtil.toLongUUID((String) unitFields.get(2)));
            }
            if (unitRepository.getCountChildByParentId((String) unitFields.get(0)) > 0) {
                jsonObjectUnit.put("hasChild", true);
            }
            jsonObjectUnit.put("id", PhmUtil.toLongUUID((String) unitFields.get(0)));
            jsonObjectUnit.put("name", unitFields.get(1));
            if (unitFields.get(3) != null)
                jsonObjectUnit.put("unitTypePictureId", unitFields.get(3));
            jsonArray.add(jsonObjectUnit);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count faults by unit ID
     * @param unitId unit ID
     * @return count faults
     */
    @Override
    public Long getCountByUnitId(String unitId) {
        List<String> unitIds = getAllUnitIdsByUnitId(unitId);
        return faultRepository.getCountByUnitIds(unitIds);
    }

    /**
     * Getting fault by case ID
     * @param caseId case ID
     * @return fault
     */
    @Override
    public Fault getByCaseId(Long caseId) {
        return faultRepository.getByCaseId(caseId);
    }

    /**
     * Gettsing all unit status by project ID
     * @param projectId project ID
     * @return all unit status
     */
    @Override
    public List<UnitStatus> getAllUnitStatusByProjectId(Integer projectId) {
        List<List<Object>> lists = faultRepository.getUnitIdAndFaultStatusByProjectId(projectId);
        List<UnitStatus> unitStatuses = new ArrayList<>();
        for (List<Object> list : lists) {
            boolean flag = true;
            if (!unitStatuses.isEmpty()) {
                for (UnitStatus unitStatus : unitStatuses) {
                    if (unitStatus.getUnitId().equals(PhmUtil.toLongUUID((String) list.get(0)))) {
                        if (unitStatus.getStatus() < (Integer) list.get(1)) {
                            unitStatus.setStatus((Integer) list.get(1));
                        }
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    unitStatuses.add(new UnitStatus(PhmUtil.toLongUUID((String) list.get(0)), (Integer) list.get(1)));
                }
            } else {
                unitStatuses.add(new UnitStatus(PhmUtil.toLongUUID((String) list.get(0)), (Integer) list.get(1)));
            }
        }
        return unitStatuses;
    }

    /**
     * Getting json by fault filter
     * @param faultFilter fault filter
     * @return json string faults
     */
    @Override
    public String getAllByFilter(FaultFilter faultFilter) {
        List<String> unitIds = getAllUnitIdsByUnitId(PhmUtil.toShortUUID(faultFilter.getUnitId()));
        StringBuilder query = new StringBuilder("SELECT DISTINCT f.id, f.case_id, f.end_time, f.is_enabled, f.is_error, " +
                "f.message, f.name, f.priority, f.rule_type, f.severity, f.start_time, f.status " +
                "FROM fault_unit fu " +
                "JOIN fault f ON fu.fault_id = f.id WHERE fu.unit_id IN " + generateValuesFromList(unitIds));
        if (!faultFilter.getStatuses().isEmpty()) {
            query.append("AND f.status IN " + generateValuesFromList(faultFilter.getStatuses()));
        }
        if (!faultFilter.getPriorities().isEmpty()) {
            query.append("AND f.priority IN " + generateValuesFromList(faultFilter.getPriorities()));
        }
        if (!faultFilter.getSeverities().isEmpty()) {
            query.append("AND f.severity IN " + generateValuesFromList(faultFilter.getSeverities()));
        }
        if (!faultFilter.getEventNames().isEmpty()) {
            query.append("AND f.name IN " + generateValuesFromList(faultFilter.getEventNames()));
        }
        if (faultFilter.getDifferentTime() != null) {
            query.append("AND EXISTS(SELECT * FROM fault_log WHERE fu.fault_id = f.id AND timestamp >= " +
                    (System.currentTimeMillis() - faultFilter.getDifferentTime()) + " AND (status = 1 OR status = 2)) ");
        }
        if (faultFilter.getStartTime() != null) {
            query.append("AND EXISTS(SELECT * FROM fault_log WHERE fu.fault_id = f.id AND timestamp >= " +
                    faultFilter.getStartTime() + " AND timestamp <= " + faultFilter.getEndTime() +
                    " AND (status = 1 OR status = 2)) ");
        }
        if (faultFilter.getSearch() != null) {
            query.append("AND f.name ILIKE '%" + faultFilter.getSearch() + "%' ");
        }
        if (faultFilter.getSortBy() != null) {
            query.append("ORDER BY " + faultFilter.getSortBy() + " " + faultFilter.getSortType() + " ");
        }
        query.append("OFFSET " + faultFilter.getOffset() + " LIMIT " + faultFilter.getLimit());

        List<Fault> lists = (List<Fault>) entityManager.createNativeQuery(query.toString(), Fault.class).getResultList();
        JSONArray jsonArray = new JSONArray();
        for (Fault list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("faultId", list.getId());
            jsonObject.put("startTime", list.getStartTime());
            jsonObject.put("endTime", list.getEndTime());
            jsonObject.put("name", list.getName());
            if (list.getPriority() == null) {
                jsonObject.put("priority", null);
            } else {
                jsonObject.put("priority", Priority.getNameByCode(list.getPriority()));
            }
            if (list.getStatus() == null) {
                jsonObject.put("status", null);
            } else {
                jsonObject.put("status", list.getStatus().getI());
            }
            if (list.getSeverity() == null) {
                jsonObject.put("severity", null);
            } else {
                jsonObject.put("severity", Severity.getNameByCode(list.getSeverity()));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting min and max timestamp fault log by project ID
     * @param projectId project ID
     * @return json string
     */
    @Override
    public String getMinAndMaxTimestampFaultLogByProjectId(Integer projectId) {
        return getMinMaxTimeByLists(faultRepository.getMinAndMaxTimestampFaultLogByProjectId(projectId));
    }

    /**
     * Getting min and max start time by project ID
     * @param projectId project ID
     * @return json string min and max start time
     */
    @Override
    public String getMinMaxStartTimeByProjectId(Integer projectId) {
        return getMinMaxTimeByLists(faultRepository.getMinMaxStartTimeByProjectId(projectId));
    }

    /**
     * Getting min and max end time by project ID
     * @param projectId project ID
     * @return json string min and max end time
     */
    @Override
    public String getMinMaxEndTimeByProjectId(Integer projectId) {
        return getMinMaxTimeByLists(faultRepository.getMinMaxEndTimeByProjectId(projectId));
    }

    /**
     * Getting main and max time by lists
     * @param lists lists
     * @return json max and min time
     */
    @Override
    public String getMinMaxTimeByLists(List<List<Object>> lists) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("min", lists.get(0).get(0));
        jsonObject.put("max", lists.get(0).get(1));
        return jsonObject.toJSONString();
    }

    @Override
    public String getMessageByFaultId(Long faultId) {
        String message = faultRepository.getMessageByFaultId(faultId);
        return message == null ? "" : message;
    }

    /**
     * Generating values from list
     * @param values values
     * @return string values
     */
    private String generateValuesFromList(List values) {
        StringBuilder stringBuilder = null;
        if (values.get(0) instanceof Integer) {
            stringBuilder = new StringBuilder("(" + values.get(0));
        } else {
            stringBuilder = new StringBuilder("('" + values.get(0).toString().replaceAll("'", "''") + "'");
        }
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) instanceof Integer) {
                stringBuilder.append(", " + values.get(i));
            } else {
                stringBuilder.append(", '" + values.get(i).toString().replaceAll("'", "''") + "'");
            }
        }
        stringBuilder.append(") ");
        return stringBuilder.toString();
    }


}
