package org.zs.phm3.repository.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.models.ts.TsKvHyperKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TsKvHyperRepository extends CrudRepository<TsKvHyper, TsKvHyperKey> {

	  // все данные С сортировкой по атрибутам
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId ORDER BY tskv.entityId, tskv.ts, tskv.key")
	  List<TsKvHyper> findAll(@Param("entityId") String paramString);

	  // данные за период > Start и <= End
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs")
	  List<TsKvHyper> findAllByTs(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2, @Param("startTs") Long paramLong1, @Param("endTs") Long paramLong2);

	  // данные за период >= Start и <= End
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey AND tskv.ts BETWEEN :startTs AND :endTs")
	  List<TsKvHyper> findAllByTsBetween(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2, @Param("startTs") Long paramLong1, @Param("endTs") Long paramLong2);
	  
	  // все данные БЕЗ сортировки по атрибутам
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId")
	  List<TsKvHyper> findAllByEntityId(@Param("entityId") String paramString);
	  
	  // все данные по ключу (UnitId, AttrubuteKey)
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey")
	  List<TsKvHyper> findAllByEntityIdAndKey(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);

	  // native SQL
	  // получить максимальное значение по UnitId и ключу атрибута
	  @Query(value = "SELECT max(tskv.dbl_v) FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey", nativeQuery = true)
	  public Double maxDouble(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // получить минимальное значение по UnitId и ключу атрибута
	  @Query(value = "SELECT min(tskv.dbl_v) FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey", nativeQuery = true)
	  public Double minDouble(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // получить максимальное значение по UnitId и ключу атрибута
	  @Query(value = "SELECT max(tskv.long_v) FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey", nativeQuery = true)
	  public Long maxLong(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // получить минимальное значение по UnitId и ключу атрибута
	  @Query(value = "SELECT min(tskv.long_v) FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey", nativeQuery = true)
	  public Long minLong(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // все доступные ключи атрибутов с сортировкой
	  @Query(value = "SELECT DISTINCT tskv.attribute_key as key FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId ORDER BY tskv.attribute_key", nativeQuery = true)
	  public List<String> getKeys(@Param("entityId") String paramString);
	  
	  // получить список значений по UnitId и ключу атрибута
	  @Query(value = "SELECT tskv.dbl_v as val FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId and tskv.attribute_key = :attributeKey ORDER BY tskv.ts", nativeQuery = true)
	  public List<Double> getValuesDouble(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  @Query(value = "SELECT tskv.long_v as val FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId and tskv.attribute_key = :attributeKey ORDER BY tskv.ts", nativeQuery = true)
	  public List<Long> getValuesLong(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  @Query(value = "SELECT tskv.str_v as val FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId and tskv.attribute_key = :attributeKey ORDER BY tskv.ts", nativeQuery = true)
	  public List<String> getValuesString(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  @Query(value = "SELECT tskv.bool_v as val FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId and tskv.attribute_key = :attributeKey ORDER BY tskv.ts", nativeQuery = true)
	  public List<Boolean> getValuesBoolean(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // latest values
	  // получить список последних значений по UnitId
	  @Query(value = "SELECT tskv.entity_id as entity_id, tskv.attribute_key as attribute_key, last(tskv.ts,tskv.ts) as ts, last(tskv.bool_v, tskv.ts) as bool_v, last(tskv.str_v, tskv.ts) as str_v, last(tskv.long_v, tskv.ts) as long_v, last(tskv.dbl_v, tskv.ts) as dbl_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId GROUP BY tskv.entity_id, tskv.attribute_key", nativeQuery = true)
	  public List<TsKvHyper> findAllLatestValues(@Param("entityId") String paramString);
	  
	  // получить последние значения (по типам) по UnitId и ключу атрибута
	  @Query(value = "SELECT tskv.entity_id as entity_id, tskv.attribute_key as attribute_key, last(tskv.ts,tskv.ts) as ts, last(tskv.bool_v, tskv.ts) as bool_v, last(tskv.str_v, tskv.ts) as str_v, last(tskv.long_v, tskv.ts) as long_v, last(tskv.dbl_v, tskv.ts) as dbl_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id, tskv.attribute_key", nativeQuery = true)
	  public TsKvHyper findAllLatestValues(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  // получить последнее значение по UnitId и ключу атрибута
	  @Query(value = "SELECT last(tskv.dbl_v, tskv.ts) as dbl_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id", nativeQuery = true)
	  public Double getLatestDoubleValue(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);

	  @Query(value = "SELECT last(tskv.long_v, tskv.ts) as long_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id", nativeQuery = true)
	  public Long getLatestLongValue(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);

	  @Query(value = "SELECT last(tskv.bool_v, tskv.ts) as bool_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id", nativeQuery = true)
	  public Boolean getLatestBoolValue(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  @Query(value = "SELECT last(tskv.str_v, tskv.ts) as str_v FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey GROUP BY tskv.entity_id", nativeQuery = true)
	  public String getLatestStringValue(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);

	  // technology operations
	  // удаление всех данных по UnitId
	  @Modifying
	  @Query(value = "DELETE FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId", nativeQuery = true)
	  public void delete(@Param("entityId") String paramString);

	  // удаление всех данных по UnitId и ключу атрибута
	  @Modifying
	  @Query(value = "DELETE FROM ts_kv_hyper tskv WHERE tskv.entity_id = :entityId AND tskv.attribute_key = :attributeKey", nativeQuery = true)
	  public void delete(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2);
	  
	  
	  // TimescaleDB special
	  // получение всех данных по UnitId и ключу атрибута в заданном периоде
	  @Query("SELECT tskv FROM TsKvHyper tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs")
	  List<TsKvHyper> findAllWithLimit(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2, @Param("startTs") Long paramLong1, @Param("endTs") Long paramLong2, Pageable paramPageable);

	  // удаление всех данных по UnitId и ключу атрибута в заданном периоде
	  @Modifying
	  @Query("DELETE FROM TsKvHyper tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey AND tskv.ts > :startTs AND tskv.ts <= :endTs")
	  void delete(@Param("entityId") String paramString1, @Param("attributeKey") String paramString2, @Param("startTs") Long paramLong1, @Param("endTs") Long paramLong2);

}
