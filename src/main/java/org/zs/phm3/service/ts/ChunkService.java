package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;


public interface ChunkService {
	
	// timeseries-keys-values
	// MAX
	// получение максимальных ключ-значение
	public List<TsKeyValue> findMax(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findMax(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findMax(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findMax(String unitId, Long startTs, Long endTs, Long timeBucket);

	// MIN
	// получение минимальных ключ-значение
	public List<TsKeyValue> findMin(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findMin(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findMin(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findMin(String unitId, Long startTs, Long endTs, Long timeBucket);

	// AVG
	// получение средних ключ-значение
	public List<TsKeyValue> findAvg(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findAvg(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findAvg(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findAvg(String unitId, Long startTs, Long endTs, Long timeBucket);

	// SUM
	// получение сумм ключ-значение
	public List<TsKeyValue> findSum(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findSum(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findSum(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findSum(String unitId, Long startTs, Long endTs, Long timeBucket);

	// COUNT
	// получение количкства ключ-значение
	public List<TsKeyValue> findCount(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findCount(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findCount(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findCount(String unitId, Long startTs, Long endTs, Long timeBucket);

	// LAST
	// получение последних ключ-значение
	public List<TsKeyValue> findLast(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findLast(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findLast(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findLast(String unitId, Long startTs, Long endTs, Long timeBucket);	
	
	// FIRST
	// получение первых ключ-значение
	public List<TsKeyValue> findFirst(String unitId, Long timeBucket);
	
	public List<TsKeyValue> findFirst(String unitId, String attributeKey, Long timeBucket);
	
	public List<TsKeyValue> findFirst(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket);
	
	public List<TsKeyValue> findFirst(String unitId, Long startTs, Long endTs, Long timeBucket);	

	// keys-values
	// получение минимальных ключ-значение по типу
	public List getMinKeysValues(String unitId, Long timeBucket, DataType type);
	public List getMinKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);

	// получение максимальных ключ-значение по типу
	public List getMaxKeysValues(String unitId, Long timeBucket, DataType type);
	public List getMaxKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	// получение средних ключ-значение по типу
	public List getAvgKeysValues(String unitId, Long timeBucket, DataType type);
	public List getAvgKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	// получение сумм ключ-значение по типу
	public List getSumKeysValues(String unitId, Long timeBucket, DataType type);
	public List getSumKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	// получение количества ключ-значение по типу
	public List getCountKeysValues(String unitId, Long timeBucket, DataType type);
	public List getCountKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	// получение последних ключ-значение по типу
	public List getLastKeysValues(String unitId, Long timeBucket, DataType type);
	public List getLastKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);

	// получение первых ключ-значение по типу
	public List getFirstKeysValues(String unitId, Long timeBucket, DataType type);
	public List getFirstKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type);

	
	// values
	// получение списка значений
	public List getMaxValues(String unitId, String key, Long timeBucket, DataType type);
	public List getMaxValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getMinValues(String unitId, String key, Long timeBucket, DataType type);
	public List getMinValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getAvgValues(String unitId, String key, Long timeBucket, DataType type);
	public List getAvgValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getSumValues(String unitId, String key, Long timeBucket, DataType type);
	public List getSumValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getCountValues(String unitId, String key, Long timeBucket, DataType type);
	public List getCountValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getLastValues(String unitId, String key, Long timeBucket, DataType type);
	public List getLastValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);
	
	public List getFirstValues(String unitId, String key, Long timeBucket, DataType type);
	public List getFirstValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type);

}
