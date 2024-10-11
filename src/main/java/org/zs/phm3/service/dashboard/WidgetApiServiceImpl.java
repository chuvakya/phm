package org.zs.phm3.service.dashboard;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.UomTypes;
import org.zs.phm3.models.ts.TsKvAttrDataTypes;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.repository.dashboard.DashboardWidgetRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.UomService;

import java.util.List;

/**
 * Implementing WidgetApiService class
 * @author Pavel Chuvak
 */
@Service
public class WidgetApiServiceImpl implements WidgetApiService {

    /** Dashboard widget repository */
    @Autowired
    private DashboardWidgetRepository dashboardWidgetRepository;

    /** Ts kv repository */
    @Autowired
    private TsKvRepository tsKvRepository;

    /** Uom service */
    @Autowired
    private UomService uomService;

    /**
     * Getting json string bar chart values by dashboard widget ID
     * @param dashboardWidgetId dashboard widget ID
     * @return json string bar chart values
     */
    @Override
    public String getBarChartValues(Long dashboardWidgetId) {
        List<List<Object>> attrs = dashboardWidgetRepository.getAllAttributesByDashboardWidgetId(dashboardWidgetId);
        JSONArray arrayNames = new JSONArray();
        JSONArray arrayValues = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray arraySymbols = new JSONArray();
        Long time = System.currentTimeMillis();
        for (List<Object> attr : attrs) {
            TsKvEntity tsKvEntity = tsKvRepository.getLastValueByKeyAndDeviceIdAndTs((String) attr.get(0),
                    (String) attr.get(1),  time);
            arrayNames.add(attr.get(3));
            arraySymbols.add(attr.get(7));
            if (tsKvEntity == null) {
                arrayValues.add(null);
                continue;
            }
            addInArrayFromTsKv(attr, tsKvEntity, arrayValues);
        }
        jsonObject.put("names", arrayNames);
        jsonObject.put("values", arrayValues);
        jsonObject.put("uomSymbols", arraySymbols);
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string area chart values (X) by dashboard widget ID and period
     * @param dashboardWidgetId dashboard widget ID
     * @param period period
     * @return json string area chart values (X)
     */
    @Override
    public String getAreaChartValuesX(Long dashboardWidgetId, Long period) {
        List<List<Object>> attrs = dashboardWidgetRepository.getAllAttributesByDashboardWidgetId(dashboardWidgetId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonData = new JSONArray();
        JSONArray jsonNames = new JSONArray();
        JSONArray jsonSymbols = new JSONArray();
        Long timeTo = System.currentTimeMillis();
        for (List<Object> attr : attrs) {
            JSONArray arrayValues = new JSONArray();
            jsonNames.add(attr.get(3));
            jsonSymbols.add(attr.get(7));
            List<TsKvEntity> tsKvEntities = tsKvRepository.getAllFromTimeKeyAndDeviceIdLimit(
                    timeTo - period, timeTo, (String) attr.get(1), (String) attr.get(0), 1000000);
            for (TsKvEntity tsKvEntity : tsKvEntities) {
                JSONArray array = new JSONArray();
                array.add(tsKvEntity.getId().getTs());
                addInArrayFromTsKv(attr, tsKvEntity, array);
                arrayValues.add(array);
            }
            jsonData.add(arrayValues);
        }
        jsonObject.put("data", jsonData);
        jsonObject.put("names", jsonNames);
        jsonObject.put("uomSymbols", jsonSymbols);
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string area chart values (Y) by dashboard widget ID and period
     * @param dashboardWidgetId dashboard widget ID
     * @param period period
     * @return json string area chart values (Y)
     */
    @Override
    public String getAreaChartValuesY(Long dashboardWidgetId, Long period) {
        List<List<Object>> attrs = dashboardWidgetRepository.getAllAttributesByDashboardWidgetId(dashboardWidgetId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonData = new JSONArray();
        JSONArray jsonNames = new JSONArray();
        JSONArray jsonSymbols = new JSONArray();
        Long timeTo = System.currentTimeMillis();
        for (List<Object> attr : attrs) {
            JSONArray arrayValues = new JSONArray();
            jsonNames.add(attr.get(3));
            jsonSymbols.add(attr.get(7));
            List<TsKvEntity> tsKvEntities = tsKvRepository.getAllFromTimeKeyAndDeviceIdLimit(
                    timeTo - period, timeTo, (String) attr.get(1), (String) attr.get(0), 1000000);
            for (TsKvEntity tsKvEntity : tsKvEntities) {
                JSONArray array = new JSONArray();
                addInArrayFromTsKv(attr, tsKvEntity, array);
                arrayValues.add(array);
                array.add(tsKvEntity.getId().getTs());
            }
            jsonData.add(arrayValues);
        }
        jsonObject.put("data", jsonData);
        jsonObject.put("names", jsonNames);
        jsonObject.put("uomSymbols", jsonSymbols);
        return jsonObject.toJSONString();
    }

    /**
     * Helper method add in array from ts kv and attributes
     * @param attr attributes
     * @param tsKvEntity ts kv
     * @param array array
     */
    private void addInArrayFromTsKv(List<Object> attr, TsKvEntity tsKvEntity, JSONArray array) {
        UomEntity uomInput = new UomEntity((String) attr.get(4), null, UomTypes.valueOf((String) attr.get(6)),
                null);
        UomEntity uomOutput = new UomEntity((String) attr.get(5), null, null, null);
        switch (TsKvAttrDataTypes.valueOf((String) attr.get(2))) {
            case LONG -> array.add(uomService.convert(uomInput, uomOutput, tsKvEntity.getLongValue().doubleValue()));
            case DOUBLE -> array.add(uomService.convert(uomInput, uomOutput, tsKvEntity.getDoubleValue()));
        }
    }

}
