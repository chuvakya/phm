package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.repository.ts.ext.DataType;


public interface ChunkAllService {
	// ALL
	// получение списка ключ-значение
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);

	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, String timeBucket);
	
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long startTs, Long endTs, String timeBucket);

	// LAST
	// получение последних ключ-значение
	public List findLastKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findLastKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	// FIRST
	// получение первых ключ-значение
	public List findFirstKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findFirstKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	// MAX
	// получение максимальных ключ-значение
	public List findMaxKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findMaxKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	// MIN
	// получение минимальных ключ-значение
	public List findMinKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findMinKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	// SUM
	// получение сумм ключ-значение
	public List findSumKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findSumKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	// AVG
	// получение средних ключ-значение
	public List findAvgKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type);
	public List findAvgKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type);

	
}
