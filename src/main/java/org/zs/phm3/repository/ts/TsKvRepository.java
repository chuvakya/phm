package org.zs.phm3.repository.ts;

import org.springframework.cache.annotation.Cacheable;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.ts.TsKvId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TsKvRepository extends CrudRepository<TsKvEntity, TsKvId> {

    @Query(value = "SELECT * FROM ts_kv " +
            "WHERE unit_id=?1", nativeQuery = true)
    List<TsKvEntity> getAllByUnitId(String unitId);

/*    @Query(value="SELECT row_number() AS ID, DISTINCT key FROM ts_kv AS TEXT"+
            "WHERE unit_id=?1", nativeQuery = true)
    List<IdTextReturn> getAllAttrKeysForUnitId(String unitId);*/

    @Query(value = "SELECT DISTINCT key AS text FROM ts_kv WHERE unit_id=?1", nativeQuery = true)
    List<String> getAllAttrKeysForUnitId(String unitId);

    @Query(value = "SELECT * FROM ts_kv WHERE unit_id=?1 AND key=?2 AND ts BETWEEN ?3 AND ?4 ORDER BY ts " +
            " FETCH FIRST ?5 ROWS ONLY",
            nativeQuery = true)
    List<TsKvEntity> findForDataSetOneKey(String unitId, String key, Long from, Long to, Integer number);


    @Query(value = "SELECT * FROM ts_kv WHERE ts >= ?1 AND ts <= ?2 AND device_id = ?3 AND key = ?4 ORDER BY ts ASC", nativeQuery = true)
    List<TsKvEntity> getAllFromTimeKeyAndDeviceId(long timeFrom, long timeTo, String deviceId, String key);

    @Query(value = "SELECT * FROM ts_kv WHERE ts >= ?1 AND ts <= ?2 AND device_id = ?3 AND key = ?4 ORDER BY ts ASC LIMIT ?5", nativeQuery = true)
    List<TsKvEntity> getAllFromTimeKeyAndDeviceIdLimit(long timeFrom, long timeTo, String deviceId, String key, Integer limit);

    @Query(value = "SELECT * FROM ts_kv WHERE ts = ?1 AND key = ?2 AND unit_id = ?3", nativeQuery = true)
    TsKvEntity getTsKvFromUnitByTsAndKey(Long ts, String key, String unitId);

    @Query(value = "SELECT DISTINCT unit_id, ts FROM ts_kv ORDER BY unit_id", nativeQuery = true)
    List<Object[]> getAllUnitIdWithTsData();

    @Query(value = "SELECT * FROM ts_kv WHERE device_id = ?1 AND key = ?2 ORDER BY ts ASC LIMIT 1", nativeQuery = true)
    TsKvEntity getFirstByDeviceIdAndKey(String deviceId, String key);

    @Query(value = "SELECT * FROM ts_kv WHERE device_id = ?1 AND key = ?2 ORDER BY ts DESC LIMIT 1", nativeQuery = true)
    TsKvEntity getLastByDeviceIdAndKey(String deviceId, String key);

    @Query(value = "SELECT * FROM (SELECT * FROM ts_kv WHERE device_id = ?1 AND key = ?2 ORDER BY ts DESC LIMIT ?3) t ORDER BY ts ASC", nativeQuery = true)
    List<TsKvEntity> getLastByDeviceIdAndKeyAndN(String deviceId, String key, Long n);

//    @Query(value = "SELECT DISTINCT key AS text FROM ts_kv WHERE unit_id=?1", nativeQuery = true)
//    List<String> getAllTsKvAttributesByUnit(String unitId);


    /* Learning scheduler */

    @Query(value = "SELECT * FROM ts_kv WHERE ts <= ?1 AND device_id = ?2 AND key = ?3 ORDER BY ts ASC LIMIT 1000000", nativeQuery = true)
    List<TsKvEntity> getAllLessTimeToAndDeviceIdAndKey(long timeTo, String deviceId, String key);

    @Query(value = "SELECT EXISTS(SELECT tk.ts FROM ts_kv tk " +
            "WHERE tk.device_id = ?1 AND tk.key = ?2 AND tk.dbl_v = ?3 AND tk.ts > ?4 AND tk.ts <= ?5 " +
            "ORDER BY tk.ts LIMIT 1000000)", nativeQuery = true)
    Boolean getCountBitCodeDouble(String deviceId, String key, Double errorCodeValue, Long timeFrom, Long timeTo);

    @Query(value = "SELECT EXISTS(SELECT tk.ts FROM ts_kv tk \n" +
            "WHERE tk.device_id = ?1 AND tk.key = ?2 AND tk.str_v = ?3 AND tk.ts > ?4 AND tk.ts <= ?5 " +
            "ORDER BY tk.ts LIMIT 1000000)", nativeQuery = true)
    Boolean getCountBitCodeString(String deviceId, String key, String errorCodeValue, Long timeFrom, Long timeTo);

    @Query(value = "SELECT EXISTS(SELECT tk.ts FROM ts_kv tk " +
            "WHERE tk.device_id = ?1 AND tk.key = ?2 AND tk.long_v = ?3 AND tk.ts > ?4 AND tk.ts <= ?5 " +
            "ORDER BY tk.ts LIMIT 1000000)", nativeQuery = true)
    Boolean getCountBitCodeLong(String deviceId, String key, Long errorCodeValue, Long timeFrom, Long timeTo);

    @Query(value = "SELECT EXISTS(SELECT tk.ts FROM ts_kv tk " +
            "WHERE tk.device_id = ?1 AND tk.key = ?2 AND tk.bool_v = ?3 AND tk.ts > ?4 AND tk.ts <= ?5 " +
            "ORDER BY tk.ts LIMIT 1000000)", nativeQuery = true)
    Boolean getCountBitCodeBoolean(String deviceId, String key, Boolean errorCodeValue, Long timeFrom, Long timeTo);

    @Query(value = "SELECT * FROM ts_kv WHERE key = ?1 AND device_id = ?2 AND ts < ?3 ORDER BY ts DESC LIMIT 1",
            nativeQuery = true)
    TsKvEntity getLastValueByKeyAndDeviceIdAndTs(String key, String deviceId, Long ts);
}
