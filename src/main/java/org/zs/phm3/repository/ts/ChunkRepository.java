package org.zs.phm3.repository.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.zs.phm3.models.ts.TsKeyValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@SuppressWarnings("unchecked")
public class ChunkRepository {
	
	private static final String FROM_WHERE_ID = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";
	private static final String FROM_WHERE_IDKEY = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";
	private static final String FROM_WHERE_IDKEYTS = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";
	private static final String FROM_WHERE_IDTS = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.ts > :startTs AND tskv.ts <= :endTs GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";

	
	private static final String GET_MAX_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, MAX(COALESCE(tskv.dbl_v, 0.0)) AS doubleValue, MAX(COALESCE(tskv.long_v, 0)) AS longValue ";
	
	private static final String GET_MIN_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, MIN(COALESCE(tskv.dbl_v, 0.0)) AS doubleValue, MIN(COALESCE(tskv.long_v, 0)) AS longValue ";

	private static final String GET_AVG_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, AVG(COALESCE(tskv.dbl_v, 0.0)) AS doubleValue, AVG(COALESCE(tskv.long_v, 0)) AS longValue ";

	private static final String GET_SUM_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, SUM(COALESCE(tskv.dbl_v, 0.0)) AS doubleValue, SUM(COALESCE(tskv.long_v, 0)) AS longValue ";

	private static final String GET_COUNT_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, SUM(CASE WHEN tskv.dbl_v IS NULL THEN 0 ELSE 1 END) AS doubleValue, SUM(CASE WHEN tskv.long_v IS NULL THEN 0 ELSE 1 END) AS longValue ";
	
	private static final String GET_LAST_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, LAST(tskv.dbl_v, tskv.ts) AS doubleValue, LAST(tskv.long_v, tskv.ts) AS longValue ";

	private static final String GET_FIRST_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key AS key, FIRST(tskv.dbl_v, tskv.ts) AS doubleValue, FIRST(tskv.long_v, tskv.ts) AS longValue ";

	
	private static final int WHERE_ID = 1;		// by ID
	private static final int WHERE_IDKEY = 2;	// by ID and KEY
	private static final int WHERE_IDKEYTS = 3;	// by ID, KEY and TIMESERIES
	private static final int WHERE_IDTS = 4;	// by ID and TIMESERIES
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// aggregate by chunk
	
	// max values
	// получение списка максимальных значений по Unit и чанку
	// entityId - идентификатор Unit
	// timeBucket - рамер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMax(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_MAX_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка максимальных значений по Unit, ключу атрибута и чанку
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// timeBucket - рамер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMax(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_MAX_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка максимальных значений по Unit, ключу атрибута, периоду и чанку
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// startTs - начало периода
	// endTs - конец периода
	// timeBucket - рамер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMax(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_MAX_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка максимальных значений по Unit, периоду и чанку
	// entityId - идентификатор Unit
	// startTs - начало периода
	// endTs - конец периода
	// timeBucket - рамер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMax(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_MAX_VALUES, WHERE_IDTS));
	    return resultList;
	}
	
	// min values
	// получение списка минимальных значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMin(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_MIN_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка минимальных значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMin(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_MIN_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка минимальных значений по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMin(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_MIN_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка минимальных значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findMin(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_MIN_VALUES, WHERE_IDTS));
	    return resultList;
	}
	
	// avg values
	// получение списка средних значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findAvg(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_AVG_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка средних значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findAvg(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_AVG_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка средних значений по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findAvg(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_AVG_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка средних значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findAvg(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_AVG_VALUES, WHERE_IDTS));
	    return resultList;
	}
	
	// sum values
	// получение списка сумм значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findSum(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_SUM_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка сумм значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findSum(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_SUM_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка сумм значений по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findSum(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_SUM_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка сумм значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findSum(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_SUM_VALUES, WHERE_IDTS));
	    return resultList;
	}

	// count values
	// получение списка количества значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findCount(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_COUNT_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка количества значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findCount(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_COUNT_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка сумм значений по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findCount(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_COUNT_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка сумм значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findCount(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_COUNT_VALUES, WHERE_IDTS));
	    return resultList;
	}
	
	// last values
	// получение списка последних значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findLast(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_LAST_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка последних значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findLast(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_LAST_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка последних по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findLast(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_LAST_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка последних значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findLast(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_LAST_VALUES, WHERE_IDTS));
	    return resultList;
	}
	
	// first values
	// получение списка первых значений по Unit и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findFirst(String entityId, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, timeBucket, getQuery(GET_FIRST_VALUES, WHERE_ID));
	    return resultList;
	}
	
	// получение списка первых значений по Unit, ключу атрибута и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findFirst(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_FIRST_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение списка первых по Unit, ключу атрибута, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findFirst(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_FIRST_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// получение списка первых значений по Unit, периоду и чанку
	@Transactional(readOnly=true)
	public List<TsKeyValue> findFirst(String entityId, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyValue> resultList = getResultList(entityId, startTs, endTs, timeBucket, getQuery(GET_FIRST_VALUES, WHERE_IDTS));
	    return resultList;
	}

	// формирование запроса по условию
	private String getQuery(String typeQuery, int typeWhere) {
		if (typeWhere == WHERE_ID) {
			return typeQuery + FROM_WHERE_ID;
		} else if (typeWhere == WHERE_IDKEY) {
			return typeQuery + FROM_WHERE_IDKEY;
		} else if (typeWhere == WHERE_IDKEYTS) {
			return typeQuery + FROM_WHERE_IDKEYTS;
		} else if (typeWhere == WHERE_IDTS) {
			return typeQuery + FROM_WHERE_IDTS;
		}
		return " ";
	}
	
	private List getResultList(String entityId, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
	
	private List getResultList(String entityId, String attributeKey, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
	
	private List getResultList(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
	
	private List getResultList(String entityId, Long startTs, Long endTs, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
}
