package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.repository.ts.ChunkAllRepository;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.repository.ts.ext.LongValueEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkAllServiceImpl implements ChunkAllService {

	@Autowired
	private ChunkAllRepository chunkAllRepository;
	
	@Autowired
	public ChunkAllServiceImpl(ChunkAllRepository repository) {
		this.chunkAllRepository = repository;
	}

	@Override
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long timeBucket) {
		return chunkAllRepository.findAll(unitId, attributeKey, timeBucket);
	}

	@Override
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, String timeBucket) {
		return chunkAllRepository.findAll(unitId, attributeKey, timeBucket);
	}

	@Override
	public List<TsKeyAllValue> findAll(String unitId, String attributeKey, Long startTs, Long endTs, String timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket);
	}

	// LAST
	@Override
	public List findLastKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getLastDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLastLongValue()));
		});
		return values;
	}

	@Override
	public List findLastKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getLastDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLastLongValue()));
		});
		return values;
	}

	// FIRST
	public List findFirstKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getFirstDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getFirstLongValue()));
		});
		return values;
	}

	@Override
	public List findFirstKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getFirstDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getFirstLongValue()));
		});
		return values;
	}

	// MAX
	public List findMaxKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getMaxDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getMaxLongValue()));
		});
		return values;
	}

	@Override
	public List findMaxKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getMaxDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getMaxLongValue()));
		});
		return values;
	}

	// MIN
	public List findMinKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getMinDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getMinLongValue()));
		});
		return values;
	}

	@Override
	public List findMinKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getMinDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getMinLongValue()));
		});
		return values;
	}

	// SUM
	public List findSumKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getSumDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getSumLongValue()));
		});
		return values;
	}

	@Override
	public List findSumKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getSumDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getSumLongValue()));
		});
		return values;
	}

	// AVG
	public List findAvgKeysValues(String unitId, String attributeKey, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkAllRepository.findAll(unitId, attributeKey, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getAvgDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getAvgLongValue()));
		});
		return values;
	}

	@Override
	public List findAvgKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkAllRepository.findAll(unitId, attributeKey, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getAvgDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getAvgLongValue()));
		});
		return values;
	}
	

}
