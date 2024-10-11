package org.zs.phm3.repository.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class AggregationRepository {

	private static final String FROM_WHERE_ID = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId GROUP BY tskv.entity_id, tskv.attribute_key ORDER BY tskv.entity_id, tskv.attribute_key";
	private static final String FROM_WHERE_IDKEY = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id, tskv.attribute_key ORDER BY tskv.entity_id, tskv.attribute_key";
	private static final String FROM_WHERE_IDKEYTS = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs GROUP BY tskv.entity_id, tskv.attribute_key ORDER BY tskv.entity_id, tskv.attribute_key";
	private static final String FROM_WHERE_IDTS = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.ts > :startTs AND tskv.ts <= :endTs GROUP BY tskv.entity_id, tskv.attribute_key ORDER BY tskv.entity_id, tskv.attribute_key";

	private static final String GET_VALUES_AGG = "SELECT tskv.entity_id AS entityId, 0 AS tsBucket, tskv.attribute_key as key, MAX(COALESCE(tskv.dbl_v, 0.0)) AS maxDoubleValue, MAX(COALESCE(tskv.long_v, 0.0)) AS maxLongValue, MIN(COALESCE(tskv.dbl_v, 0.0)) AS minDoubleValue, MIN(COALESCE(tskv.long_v, 0.0)) AS minLongValue, AVG(COALESCE(tskv.dbl_v, 0.0)) AS avgDoubleValue, AVG(COALESCE(tskv.long_v, 0.0)) AS avgLongValue, SUM(COALESCE(tskv.dbl_v, 0.0)) AS sumDoubleValue, SUM(COALESCE(tskv.long_v, 0.0)) AS sumLongValue, LAST(tskv.dbl_v, tskv.ts)  AS lastDoubleValue, LAST(tskv.long_v, tskv.ts) AS lastLongValue, FIRST(tskv.dbl_v, tskv.ts)  AS firstDoubleValue, FIRST(tskv.long_v, tskv.ts) AS firstLongValue ";
	private static final String GET_VALUES_DELTA_DOUBLE = "SELECT entityId, tsBucket, key, doubleValue, 0 AS longValue FROM (SELECT tskv.entity_id AS entityId, tskv.ts AS tsBucket, tskv.attribute_key AS key, COALESCE(tskv.dbl_v, 0.0) AS doubleValue, (tskv.dbl_v - LAG(tskv.dbl_v) OVER (ORDER BY tskv.ts)) AS delta ";
	private static final String GET_VALUES_DELTA_LONG = "SELECT entityId, tsBucket, key, 0 AS 0 AS doubleValue, longValue FROM (SELECT tskv.entity_id AS entityId, tskv.ts AS tsBucket, tskv.attribute_key AS key, COALESCE(tskv.long_v, 0.0) AS longValue, (tskv.long_v - LAG(tskv.long_v) OVER (ORDER BY tskv.ts)) AS delta ";
	
	private static final int WHERE_ID = 1;		// by ID
	private static final int WHERE_IDKEY = 2;	// by ID and KEY
	private static final int WHERE_IDKEYTS = 3;	// by ID, KEY and TIMESERIES
	private static final int WHERE_IDTS = 4;	// by ID and TIMESERIES
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// получение всех агрегатов для Unit
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId) {
	    List<TsKeyAllValue> resultList = getResultListAll(entityId, getQuery(GET_VALUES_AGG, WHERE_ID));
	    return resultList;
	}
	
	// получение всех агрегатов для Unit по ключу атрибута
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey) {
	    List<TsKeyAllValue> resultList = getResultListAll(entityId, attributeKey, getQuery(GET_VALUES_AGG, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение все агрегатов для Unit по ключу атрибута в заданном периоде
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey, Long startTs, Long endTs) {
	    List<TsKeyAllValue> resultList = getResultListAll(entityId, attributeKey, startTs, endTs, getQuery(GET_VALUES_AGG, WHERE_IDKEYTS));
	    return resultList;
	}

	// получение всех агрегатов для Unit в заданном периоде
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, Long startTs, Long endTs) {
	    List<TsKeyAllValue> resultList = getResultListAll(entityId, startTs, endTs, getQuery(GET_VALUES_AGG, WHERE_IDTS));
	    return resultList;
	}
	
	// получение дельт значений для Unit по ключу атрибута и типу данных
	@Transactional(readOnly=true)
	public List<TsKeyValue> findDelta(String entityId, String attributeKey, DataType type) {
	    String query = "";
	    switch(type) {
	    	case DOUBLE:
	    		query = getQuery(GET_VALUES_DELTA_DOUBLE, WHERE_IDKEY);
	    		break;
	    	case LONG:
	    		query = getQuery(GET_VALUES_DELTA_LONG, WHERE_IDKEY);
	    		break;
	    }
	    query = query.substring(0, query.length() - 88);
		List<TsKeyValue> resultList = getResultList(entityId, attributeKey, query + ") x WHERE  delta != 0");
	    return resultList;
	}
	
	// получение дельт значений для Unit по ключу атрибута и типу данных в заданном периоде
	@Transactional(readOnly=true)
	public List<TsKeyValue> findDelta(String entityId, String attributeKey, Long startTs, Long endTs, DataType type) {
	    String query = "";
	    switch(type) {
	    	case DOUBLE:
	    		query = getQuery(GET_VALUES_DELTA_DOUBLE, WHERE_IDKEYTS);
	    		break;
	    	case LONG:
	    		query = getQuery(GET_VALUES_DELTA_LONG, WHERE_IDKEYTS);
	    		break;
	    }
	    query = query.substring(0, query.length() - 88);
		List<TsKeyValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, query + ") x WHERE  delta != 0");
	    return resultList;
	}
	
	// формирование условия запроса
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
	
	// выполнение запроса для Unit
	private List getResultListAll(String entityId, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId) 
	      .getResultList(); 
	}
	
	// выполнение запроса для Unit по ключу атрибута
	private List getResultListAll(String entityId, String attributeKey, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .getResultList(); 
	}
	
	// выполнение запроса для Unit по ключу атрибута в заданном периоде
	private List getResultListAll(String entityId, String attributeKey, Long startTs, Long endTs, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .getResultList(); 
	}
	
	// выполнение запроса для Unit в заданном периоде
	private List getResultListAll(String entityId, Long startTs, Long endTs, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .getResultList(); 
	}
	
	// выполнение запроса для Unit по ключу атрибута
	private List getResultList(String entityId, String attributeKey, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .getResultList(); 
	}
	
	// выполнение запроса для Unit по ключу атрибута в заданном периоде
	private List getResultList(String entityId, String attributeKey, Long startTs, Long endTs, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .getResultList(); 
	}
	
}
