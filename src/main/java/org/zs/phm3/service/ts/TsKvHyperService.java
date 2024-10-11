package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKvHyper;

public interface TsKvHyperService {
	
	// получение списка всех значений
	public List<TsKvHyper> findAll(String unitId);
	
	// получение списка значений за период
	public List<TsKvHyper> findAllByTs(String unitId, String key, Long startTs, Long endTs);
	
	public List<TsKvHyper> findAllByTsBetween(String unitId, String key, Long startTs, Long endTs);
	
	public List<TsKvHyper> findAllByEntityId(String unitId);
	
	// получение списка значений по UnitId и ключу
	public List<TsKvHyper> findAllByEntityIdAndKey(String unitId, String key);
	
	// получение максимального значения
	public Double maxDouble(String unitId, String key);
	// получение минимального значения
	public Double minDouble(String unitId, String key);
	
	public Long maxLong(String unitId, String key);
	
	public Long minLong(String unitId, String key);
	
	// получение наименований всех ключей атрибутов по UnitId
	public List<String> getKeys(String unitId);
	
	// получение списка значений по UnitId и ключу
	public List<Double> getValuesDouble(String unitId, String key);
	
	public List<Long> getValuesLong(String unitId, String key);
	
	public List<String> getValuesString(String unitId, String key);
	
	public List<Boolean> getValuesBoolean(String unitId, String key);
	
	// сохранение значений
	public void save(TsKvHyper val);
	
	public void saveAll(List<TsKvHyper> vals);
	
	// сохранение всех значений в CSV
	public boolean saveToCSV(String unitId);
	
	// сохранение всех значений в Parquet
	public boolean saveToParquet(String unitId);
	
	// latest values
	// получение последних значений
	public List<TsKvHyper> findAllLatestValues(String unitId);
	
	public TsKvHyper findAllLatestValues(String unitId, String key);
	
	public Double getLatestDoubleValue(String unitId, String key);
	
	public Long getLatestLongValue(String unitId, String key);
	
	public Boolean getLatestBoolValue(String unitId, String key);
	
	public String getLatestStringValue(String unitId, String key);

	// technology operations
	// удаление значений
	public void delete(String unitId);
	
	public void delete(String unitId, String key);

}
