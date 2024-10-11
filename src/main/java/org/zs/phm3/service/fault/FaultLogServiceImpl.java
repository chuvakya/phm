package org.zs.phm3.service.fault;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.fault.Fault;
import org.zs.phm3.models.fault.FaultLog;
import org.zs.phm3.repository.fault.FaultLogRepository;

import java.util.List;

/**
 * Imliminating FaultLogService interface class
 * @author Pavel Chuvak
 */
@Service
public class FaultLogServiceImpl implements FaultLogService {

    /** Fault log repository */
    @Autowired
    private FaultLogRepository faultLogRepository;

    /** Fault service */
    @Autowired
    private FaultService faultService;

    /**
     * Getting the minimum and maximum time values
     * @param faultId fault ID
     * @return the minimum and maximum time values
     */
    @Override
    public String getMinMaxTimestampByFaultId(Long faultId) {
        return faultService.getMinMaxTimeByLists(faultLogRepository.getMinMaxTimestampByFaultId(faultId));
    }

    /**
     * Preservation of the essence of the fault log
     * @param faultLog fault log
     * @return fault log
     */
    @Override
    public FaultLog save(FaultLog faultLog) {
        return faultLogRepository.save(faultLog);
    }

    /**
     * Getting id, value, status, attribute name, timestamp by fault ID
     * @param faultId fault ID
     * @return json string all faultLog
     */
    @Override
    public String getAllByFaultId(Long faultId) {
        List<FaultLog> faultLogs = faultLogRepository.getAllFaultLogByFaultId(faultId);
        JSONArray jsonArray = new JSONArray();
        for (FaultLog faultLog : faultLogs) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", faultLog.getId());
            jsonObject.put("value", faultLog.getCurrentValue());
            jsonObject.put("status", faultLog.getStatus().getStatusName());
            jsonObject.put("attrName", faultLog.getAttrName());
            jsonObject.put("timestamp", faultLog.getTimestamp());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting list fault log more then timestamp by fault ID
     * @param timestamp timestamp
     * @param faultId fault ID
     * @return list fault log
     */
    @Override
    public List<FaultLog> getByMoreTimestampAndFaultId(Long timestamp, Long faultId) {
        return faultLogRepository.getByMoreTimestampAndFaultId(timestamp, faultId);
    }

    /**
     * Getting list fault log by interval and fault ID
     * @param startTime start time
     * @param endTime end time
     * @param faultId fault ID
     * @return list fault log
     */
    @Override
    public List<FaultLog> getAllByIntervalAndFaultId(Long startTime, Long endTime, Long faultId) {
        return faultLogRepository.getAllByIntervalAndFaultId(startTime, endTime, faultId);
    }
}
