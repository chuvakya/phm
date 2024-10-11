package org.zs.phm3.service.fpc;

//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.thingsboard.server.common.data.Device;
//import org.thingsboard.server.common.data.Tenant;
//import org.thingsboard.server.common.data.UUIDConverter;
//import org.thingsboard.server.common.data.asset.Asset;
//import org.thingsboard.server.common.data.exception.ThingsboardException;
//import org.thingsboard.server.common.data.id.AssetId;
//import org.thingsboard.server.common.data.id.DeviceId;
//import org.thingsboard.server.common.data.id.TenantId;
//import org.thingsboard.server.common.data.kv.BasicTsKvEntry;
//import org.thingsboard.server.common.data.kv.DoubleDataEntry;
//import org.thingsboard.server.common.data.kv.KvEntry;
//import org.thingsboard.server.common.data.kv.TsKvEntry;
//import org.thingsboard.server.dao.asset.AssetService;
//import org.thingsboard.server.dao.attributes.AttributesService;
//import org.thingsboard.server.dao.device.DeviceService;
//import org.thingsboard.server.dao.sql.phm.AssetPhmRepository;
//import org.thingsboard.server.dao.sql.phm.AttributeKvPhmRepository;
//import org.thingsboard.server.dao.tenant.TenantService;
//import org.thingsboard.server.dao.timeseries.TimeseriesService;
//import org.thingsboard.server.dao.user.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import static org.thingsboard.server.common.data.exception.ThingsboardErrorCode.INVALID_ARGUMENTS;
//import static org.thingsboard.server.common.data.exception.ThingsboardErrorCode.ITEM_NOT_FOUND;

/**
 * S.Zeniuk version#2
 */
//TODO Class for code reorganization !!!
//@Slf4j
@Transactional
@Service
public class FpcServiceImpl implements FpcService {
/*    @Autowired
    AttributeKvPhmRepository attributeKvPhmRepository;
    @Autowired
    FtaService ftaService;
    @Autowired
    TimeseriesService tsService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    AssetService assetService;
    @Autowired
    UserService userService;
    @Autowired
    TenantService tenantService;
    @Autowired
    AttributesService attributesService;
    @Autowired
    AssetPhmRepository assetPhmRepository;
    private static PhmRestClient phmRestClient;
    private static final String HTTPS_URL_DEV = "http://192.168.100.110:5000";

    @Override
    public void process(TenantId defTenantId, Integer day) throws ThingsboardException {
        log.trace("Executing FPC process");
        JSONArray jsonArray = convertToFpcData(attributeKvPhmRepository.getFpcDataSQL(), day);
        //            *** 1st calling REST
        String rest1Responce = sendRest(jsonArray);
        Map<String, Double> fpcDevicesDataList = PhmUtils.jsonMultiLineParsing(rest1Responce);
        //      SavingToTelemetry
        SavingToTelemetry(fpcDevicesDataList, defTenantId, 1, day);
        log.trace("FPC: Device Data Received [{}]", fpcDevicesDataList);
        String ftaProbFaultXml = PhmUtils.updateFtaXml(getFtaXml(), fpcDevicesDataList);
        //            *** 2nd calling REST
        String fpcAssetData = phmRestClient.fpcPostXmlScram(ftaProbFaultXml);
        String scrumResult = PhmUtils.readScramXml(fpcAssetData);
//        fpcDevicesData.clear();
        Map<String, Double> fpcAssetDataMap = new HashMap<>();
        fpcAssetDataMap.put("ZZZ", parseDouble(scrumResult));
        SavingToTelemetry(fpcAssetDataMap, defTenantId, 2, day);
        System.out.println("V2 Process is done sucessfully");
    }

    private JSONArray convertToFpcData(List<Object[]> fpcDataSet, Integer day) throws ThingsboardException {
        if (fpcDataSet.size() == 0) {
            log.error("No data for FPC operation.");
            throw new ThingsboardException("No data for FPC operation", INVALID_ARGUMENTS);
        }
        JSONArray jsonArray = new JSONArray();
        phmRestClient = new PhmRestClient(HTTPS_URL_DEV);
//        phmRestClient.login("tenant_admin1@gmail.com","admin_1");

        for (Object obj[] : fpcDataSet) {
            if (!((obj[1] == null) || (obj[2] == null))) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", obj[0]);
//              FPC Time Calculation
                double timeValue = (System.currentTimeMillis() + (day * 60 * 60 * 1000) - ((BigDecimal) obj[1]).longValue()) / 3600000;
                jsonObject.put("time", timeValue);
                jsonObject.put("lambda", obj[2]);
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    private String sendRest(JSONArray jsonArray) throws ThingsboardException {
        return phmRestClient.fpcPostDevicesData(jsonArray);
    }

    @Override
    public void SavingToTelemetryTest(Tenant defTenant) {

    }

    private void SavingToTelemetry(Map<String, Double> fpcDevicesData, TenantId defTenantId, Integer step, Integer day) {
//        List<TsKvEntry> tsKvEntryList = new ArrayList<>();
        Tenant defTenant=tenantService.findTenantById(defTenantId);

        fpcDevicesData.forEach((k, v) -> {
            KvEntry dentry = new DoubleDataEntry("FPC_day_" + day + "_step" + step, v);
            TsKvEntry tsKvEntry = new BasicTsKvEntry(System.currentTimeMillis(), dentry);
            try {
//                Device actDevice = new Device();
                if (step == 1) {
                    Device actDevice = getDeviceById(defTenant, new DeviceId(UUIDConverter.fromString(k)));
                    tsService.save(defTenantId, actDevice.getId(), tsKvEntry);
                } else {
//                    List<String> assetIdList = new ArrayList<>();
                    List<String> assetIdList = assetPhmRepository.getSystemDevicesIdSql();
                    String SystemAssetId = assetIdList.get(0);
                    Asset actAsset = getAssetById(defTenant, new AssetId(UUIDConverter.fromString(SystemAssetId)));
                    tsService.save(defTenantId, actAsset.getId(), tsKvEntry);
                }
            } catch (ThingsboardException e) {
                e.printStackTrace();
            }

        });
    }

    private Asset getAssetById(Tenant defTenant, AssetId strAssetId) throws ThingsboardException {
//        checkParameter(ASSET_ID, strAssetId);
        try {
            //            AssetId assetId = new AssetId(toUUID(strAssetId));
//            return checkAssetId(assetId, Operation.READ);
            return assetService.findAssetById(defTenant.getTenantId(), strAssetId);

        } catch (Exception e) {
            throw new ThingsboardException("Asset by id not found", ITEM_NOT_FOUND);
        }
    }

    private Device getDeviceById(Tenant defTenant, DeviceId deviceId) throws ThingsboardException {
        try {
//            validateId(deviceId, "Incorrect deviceId " + deviceId);
            //            checkNotNull(device);
//            accessControlService.checkPermission(getCurrentUser(), Resource.DEVICE, operation, deviceId, device);
            return deviceService.findDeviceById(defTenant.getTenantId(), deviceId);
        } catch (Exception e) {
//            throw handleException(e, false);
            throw new ThingsboardException("Device by id not found", ITEM_NOT_FOUND);
        }
    }

    private String getFtaXml() {
        return ftaService.getFta().getXmlBody();
    }

    @Override
    public void SavingAttributeTest(Tenant defTenant) throws ThingsboardException {
// for test only
    }*/
}
