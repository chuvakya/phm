package org.zs.phm3.repository.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@SuppressWarnings("unchecked")
public class ChunkAllRepository {

	private static final String FROM_WHERE_IDKEY = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";
	private static final String FROM_WHERE_IDKEYTS = "FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs GROUP BY tskv.entity_id, tskv.attribute_key, tsBucket ORDER BY tskv.entity_id, tskv.attribute_key, tsBucket";

	private static final String GET_VALUES = "SELECT tskv.entity_id AS entityId, time_bucket(:timeBucket, tskv.ts) AS tsBucket, tskv.attribute_key as key, MAX(COALESCE(tskv.dbl_v, 0.0)) AS maxDoubleValue, MAX(COALESCE(tskv.long_v, 0.0)) AS maxLongValue, MIN(COALESCE(tskv.dbl_v, 0.0)) AS minDoubleValue, MIN(COALESCE(tskv.long_v, 0.0)) AS minLongValue, AVG(COALESCE(tskv.dbl_v, 0.0)) AS avgDoubleValue, AVG(COALESCE(tskv.long_v, 0.0)) AS avgLongValue, SUM(COALESCE(tskv.dbl_v, 0.0)) AS sumDoubleValue, SUM(COALESCE(tskv.long_v, 0.0)) AS sumLongValue, LAST(tskv.dbl_v, tskv.ts)  AS lastDoubleValue, LAST(tskv.long_v, tskv.ts) AS lastLongValue, FIRST(tskv.dbl_v, tskv.ts)  AS firstDoubleValue, FIRST(tskv.long_v, tskv.ts) AS firstLongValue ";
	private static final String GET_VALUES_TIMESTAMP = "SELECT tskv.entity_id AS entityId, cast(extract('epoch' from time_bucket(cast(:timeBucket as interval), to_timestamp(tskv.ts))) as bigint) AS tsBucket, tskv.attribute_key as key, MAX(COALESCE(tskv.dbl_v, 0.0)) AS maxDoubleValue, MAX(COALESCE(tskv.long_v, 0.0)) AS maxLongValue, MIN(COALESCE(tskv.dbl_v, 0.0)) AS minDoubleValue, MIN(COALESCE(tskv.long_v, 0.0)) AS minLongValue, AVG(COALESCE(tskv.dbl_v, 0.0)) AS avgDoubleValue, AVG(COALESCE(tskv.long_v, 0.0)) AS avgLongValue, SUM(COALESCE(tskv.dbl_v, 0.0)) AS sumDoubleValue, SUM(COALESCE(tskv.long_v, 0.0)) AS sumLongValue, LAST(tskv.dbl_v, tskv.ts)  AS lastDoubleValue, LAST(tskv.long_v, tskv.ts) AS lastLongValue, FIRST(tskv.dbl_v, tskv.ts)  AS firstDoubleValue, FIRST(tskv.long_v, tskv.ts) AS firstLongValue ";

	private static final int WHERE_IDKEY = 1;	// by ID and KEY
	private static final int WHERE_IDKEYTS = 2;	// by ID, KEY and TIMESERIES
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// for timeseries BIGINT
	// получение всех значений для Unit по ключу и размеру чанка
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// timeBucket - размер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey, Long timeBucket) {
	    List<TsKeyAllValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_VALUES, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение всех значений для Unit по ключу и размеру чанка в заданном периоде
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// startTs - начало периода
	// endTs - конец периода
	// timeBucket - размер чанка в миллисекундах
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
	    List<TsKeyAllValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_VALUES, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// for TIMESERIES TIMESTAMP
	// получение всех значений для Unit по ключу и размеру чанка
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// timeBucket - размер чанка в виде строки (из объекта ChunkPeriod)
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey, String timeBucket) {
	    List<TsKeyAllValue> resultList = getResultList(entityId, attributeKey, timeBucket, getQuery(GET_VALUES_TIMESTAMP, WHERE_IDKEY));
	    return resultList;
	}
	
	// получение всех значений для Unit по ключу и размеру чанка в заданном периоде
	// entityId - идентификатор Unit
	// attributeKey - ключ атрибута
	// startTs - начало периода
	// endTs - конец периода
	// timeBucket - размер чанка в виде строки (из объекта ChunkPeriod)
	@Transactional(readOnly=true)
	public List<TsKeyAllValue> findAll(String entityId, String attributeKey, Long startTs, Long endTs, String timeBucket) {
	    List<TsKeyAllValue> resultList = getResultList(entityId, attributeKey, startTs, endTs, timeBucket, getQuery(GET_VALUES_TIMESTAMP, WHERE_IDKEYTS));
	    return resultList;
	}
	
	// формирование условия запроса
	private String getQuery(String typeQuery, int typeWhere) {
		if (typeWhere == WHERE_IDKEY) {
			return typeQuery + FROM_WHERE_IDKEY;
		} else if (typeWhere == WHERE_IDKEYTS) {
			return typeQuery + FROM_WHERE_IDKEYTS;
		}
		return " ";
	}
	
	// получение данных из запроса по Unit, ключу атрибута и размеру чанка
	private List getResultList(String entityId, String attributeKey, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
	
	// получение данных из запроса по Unit, ключу атрибута, периоду и размеру чанка
	private List getResultList(String entityId, String attributeKey, Long startTs, Long endTs, Long timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .setParameter("timeBucket", Long.valueOf(timeBucket))
	      .getResultList(); 
	}
	
	// получение данных из запроса по Unit, ключу атрибута и размеру чанка (из объекта ChunkPeriod)
	private List getResultList(String entityId, String attributeKey, String timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("timeBucket", timeBucket)
	      .getResultList(); 
	}
	
	// получение данных из запроса по Unit, ключу атрибута, периоду и размеру чанка (из объекта ChunkPeriod)
	private List getResultList(String entityId, String attributeKey, Long startTs, Long endTs, String timeBucket, String query) { 
	return this.entityManager.createNativeQuery(query, "tsAggAllMapped")
	      .setParameter("entityId", entityId)
	      .setParameter("attributeKey", attributeKey)
	      .setParameter("startTs", Long.valueOf(startTs))
	      .setParameter("endTs", Long.valueOf(endTs))
	      .setParameter("timeBucket", timeBucket)
	      .getResultList(); 
	}
	
}
