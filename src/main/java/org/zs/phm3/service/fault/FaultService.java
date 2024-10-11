package org.zs.phm3.service.fault;

import org.zs.phm3.models.fault.Fault;
import org.zs.phm3.models.fault.FaultFilter;
import org.zs.phm3.models.fault.UnitStatus;

import java.util.List;

/**
 * Interface FaultService
 * @author Pavel Chuvak
 */
public interface FaultService {

    /**
     * Getting json string all faults by unit ID
     * @param unitId unit ID
     * @return json string all faults
     */
    List<Fault> getFaultsByUnitId(String unitId);

    /**
     * Getting all faults by unit ID and offset and limit
     * @param unitId unit ID
     * @param offset offset
     * @param limit limit
     * @return faults
     */
    String getFaultsByUnitIdAndOffsetAndLimit(String unitId, Integer offset, Integer limit);

    /**
     * Saving fault
     * @param fault fault
     * @return fault
     */
    Fault save(Fault fault);

    /**
     * Getting menu by project ID
     * @param projectId project ID
     * @return json string menu
     */
    String getMenu(Integer projectId);

    /**
     * Getting count faults by unit ID
     * @param unitId unit ID
     * @return count faults
     */
    Long getCountByUnitId(String unitId);

    /**
     * Getting fault by case ID
     * @param caseId case ID
     * @return fault
     */
    Fault getByCaseId(Long caseId);

    /**
     * Gettsing all unit status by project ID
     * @param projectId project ID
     * @return all unit status
     */
    List<UnitStatus> getAllUnitStatusByProjectId(Integer projectId);

    /**
     * Getting json by fault filter
     * @param faultFilter fault filter
     * @return json string faults
     */
    String getAllByFilter(FaultFilter faultFilter);

    /**
     * Getting min and max timestamp fault log by project ID
     * @param projectId project ID
     * @return json string
     */
    String getMinAndMaxTimestampFaultLogByProjectId(Integer projectId);

    /**
     * Getting min and max start time by project ID
     * @param projectId project ID
     * @return json string min and max start time
     */
    String getMinMaxStartTimeByProjectId(Integer projectId);

    /**
     * Getting min and max end time by project ID
     * @param projectId project ID
     * @return json string min and max end time
     */
    String getMinMaxEndTimeByProjectId(Integer projectId);

    /**
     * Getting main and max time by lists
     * @param lists lists
     * @return json max and min time
     */
    String getMinMaxTimeByLists(List<List<Object>> lists);

    String getMessageByFaultId(Long faultId);
}
