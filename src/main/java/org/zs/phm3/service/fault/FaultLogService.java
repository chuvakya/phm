package org.zs.phm3.service.fault;

import org.zs.phm3.models.fault.FaultLog;

import java.util.List;

/**
 * Fault log service interface
 * @author Pavel Chuvak
 */
public interface FaultLogService {

    /**
     * Getting the minimum and maximum time values
     * @param faultId fault ID
     * @return the minimum and maximum time values
     */
    String getMinMaxTimestampByFaultId(Long faultId);

    /**
     * Preservation of the essence of the fault log
     * @param faultLog fault log
     * @return fault log
     */
    FaultLog save(FaultLog faultLog);

    /**
     * Getting id, value, status, attribute name, timestamp by fault ID
     * @param faultId fault ID
     * @return json string
     */
    String getAllByFaultId(Long faultId);

    /**
     * Getting list fault log more then timestamp by fault ID
     * @param timestamp timestamp
     * @param faultId fault ID
     * @return list fault log
     */
    List<FaultLog> getByMoreTimestampAndFaultId(Long timestamp, Long faultId);

    /**
     * Getting list fault log by interval and fault ID
     * @param startTime start time
     * @param endTime end time
     * @param faultId fault ID
     * @return list fault log
     */
    List<FaultLog> getAllByIntervalAndFaultId(Long startTime, Long endTime, Long faultId);
}
