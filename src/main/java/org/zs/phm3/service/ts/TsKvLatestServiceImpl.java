package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.ArrayList;
import java.util.List;

import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.models.ts.TsKvLatest;
import org.zs.phm3.models.ts.TsKvLatestKey;
import org.zs.phm3.repository.ts.TsKvHyperRepository;
import org.zs.phm3.repository.ts.TsKvLatestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.service.BaseService;


@Service
public class TsKvLatestServiceImpl extends BaseService implements TsKvLatestService {
	@Autowired
	private final TsKvLatestRepository latestRepository;
	
	@Autowired
	private TsKvHyperRepository hyperRepository;
	 
	@Autowired
	public TsKvLatestServiceImpl(TsKvLatestRepository repository) {
		this.latestRepository = repository;
	}

	@Override
	public List<TsKvLatest> findAllByEntityId(String unitId) {
		return latestRepository.findAllByEntityId(unitId);
	}

	@Override
	public List<TsKvLatest> findAllByEntityIdAndKey(String unitId, String key) {
		return latestRepository.findAllByEntityIdAndKey(unitId, key);
	}

	@Override
	public void saveLatest(String unitId) {
		List<TsKvHyper> latestValues = new ArrayList<TsKvHyper>();
		
		latestValues = hyperRepository.findAllLatestValues(unitId);
		latestValues.forEach( e -> {
			TsKvLatest v = new TsKvLatest();
			v.setEntityId(unitId);
			v.setKey(e.getKey());
			v.setTs(e.getTs());
			v.setBooleanValue(e.getBooleanValue());
			v.setDoubleValue(e.getDoubleValue());
			v.setLongValue(e.getLongValue());
			v.setStrValue(e.getStrValue());
			latestRepository.save(v);
		});

	}
	
	@Override
	public void saveLatest(String unitId, String key) {
		TsKvLatest latest = new TsKvLatest();
		TsKvHyper latestValues = new TsKvHyper();
		
		latestValues = hyperRepository.findAllLatestValues(unitId, key);
		
		latest.setEntityId(unitId);
		latest.setKey(key);
		latest.setTs(latestValues.getTs());
		latest.setBooleanValue(latestValues.getBooleanValue());
		latest.setDoubleValue(latestValues.getDoubleValue());
		latest.setLongValue(latestValues.getLongValue());
		latest.setStrValue(latestValues.getStrValue());
		latestRepository.save(latest);
	}

	@Override
	public void saveLatest(TsKvHyper val) {
		TsKvLatest latest = new TsKvLatest();
		latest.setEntityId(val.getEntityId());
		latest.setKey(val.getKey());
		latest.setTs(val.getTs());
		latest.setBooleanValue(val.getBooleanValue());
		latest.setDoubleValue(val.getDoubleValue());
		latest.setLongValue(val.getLongValue());
		latest.setStrValue(val.getStrValue());
		latestRepository.save(latest);
	}

	@Override
	public void saveLatest(List<TsKvHyper> list) {
		list.forEach( e -> {
			TsKvLatest v = new TsKvLatest();
			v.setEntityId(e.getEntityId());
			v.setKey(e.getKey());
			v.setTs(e.getTs());
			v.setBooleanValue(e.getBooleanValue());
			v.setDoubleValue(e.getDoubleValue());
			v.setLongValue(e.getLongValue());
			v.setStrValue(e.getStrValue());
			latestRepository.save(v);
		});
		
	}

	@Override
	public void deleteById(String unitId, String key) {
		latestRepository.deleteById(new TsKvLatestKey(unitId, key));
	}

	@Override
	public void deleteByEntityId(String unitId) {
		latestRepository.deleteByEntityId(unitId);
	}

	@Override
	public void renameKey(String unitId, String keyOld, String keyNew) {
		latestRepository.renameKey(unitId, keyOld, keyNew);
	}

}
