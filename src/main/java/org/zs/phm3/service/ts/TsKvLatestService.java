package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.models.ts.TsKvLatest;

public interface TsKvLatestService {

	// получение последних значений
	List<TsKvLatest> findAllByEntityId(String unitId);
	
	List<TsKvLatest> findAllByEntityIdAndKey(String unitId, String key);
	
	// сохранение последних значений
	public void saveLatest(String unitId);
	
	public void saveLatest(String unitId, String key);
	
	public void saveLatest(TsKvHyper val);
	
	public void saveLatest(List<TsKvHyper> list);
	
	// удаление последних значений
	public void deleteById(String unitId, String key);
	
	public void deleteByEntityId(String unitId);
	
	// переименование ключа атрибута
	public void renameKey(String unitId, String keyOld, String keyNew);
}
