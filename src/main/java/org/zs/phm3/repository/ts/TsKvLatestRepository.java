package org.zs.phm3.repository.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKvLatest;
import org.zs.phm3.models.ts.TsKvLatestKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
@Transactional
public interface TsKvLatestRepository extends CrudRepository<TsKvLatest, TsKvLatestKey> {

	// получение последних данных по UnitId
	@Query("SELECT tskv FROM TsKvLatest tskv WHERE tskv.entityId = :entityId")
	public List<TsKvLatest> findAllByEntityId(@Param("entityId") String paramString);
	
	// получение последних данных по UnitId и ключу атрибута
	@Query("SELECT tskv FROM TsKvLatest tskv WHERE tskv.entityId = :entityId AND tskv.key = :attributeKey")
	public List<TsKvLatest> findAllByEntityIdAndKey(@Param("entityId")String paramString1, @Param("attributeKey") String paramString2);
	
	// удаление последних данных по UnitId
	@Modifying
	@Query(value = "DELETE FROM ts_kv_latest tskv WHERE tskv.entity_id = :entityId", nativeQuery = true)
	public void deleteByEntityId(@Param("entityId") String paramString);
	
	// переименование ключа атрибута по UnitId
	// paramString2 - существующее наименование ключа
	// paramString3 - новое наименование ключа
	@Modifying
	@Query(value = "UPDATE ts_kv_latest SET attribute_key = :attributeKeyNew where entity_id = :entityId and attribute_key =:attributeKeyOld", nativeQuery = true)
	public void renameKey(@Param("entityId") String paramString1, @Param("attributeKeyOld") String paramString2, @Param("attributeKeyNew") String paramString3);

}
