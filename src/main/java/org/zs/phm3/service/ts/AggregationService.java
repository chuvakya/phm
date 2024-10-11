package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;


public interface AggregationService {

	// AGG
	// получение списков агрегированных значений
	public List<TsKeyAllValue> findAll(String unitId);
	public List<TsKeyAllValue> findAll(String unitId, Long startTs, Long endTs);
	public TsKeyAllValue findOne(String unitId, String attributeKey);
	public TsKeyAllValue findOne(String unitId, String attributeKey, Long startTs, Long endTs);
	// получение последних значений
	public List getLastKeysValues(String unitId, DataType type);
	public List getLastKeysValues(String unitId, Long startTs, Long endTs, DataType type);
	// получение одиночных значений
	public Object getMaxValue(String unitId, String attributeKey, DataType type);
	public Object getMaxValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	public Object getMinValue(String unitId, String attributeKey, DataType type);
	public Object getMinValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	public Object getAvgValue(String unitId, String attributeKey, DataType type);
	public Object getAvgValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	public Object getSumValue(String unitId, String attributeKey, DataType type);
	public Object getSumValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	public Object getLastValue(String unitId, String attributeKey, DataType type);
	public Object getLastValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	public Object getFirstValue(String unitId, String attributeKey, DataType type);
	public Object getFirstValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);
	
	// DELTA
	// получение списка дельт значений
	public List<TsKeyValue> findDeltaKeysValues(String unitId, String attributeKey, DataType type);
	public List<TsKeyValue> findDeltaKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, DataType type);

}
