package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ChunkRepository;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.repository.ts.ext.LongValueEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Service
public class ChunkServiceImpl implements ChunkService {
	//private static final Logger log = LoggerFactory.getLogger(AggregationServiceImpl.class);
	
	@Autowired
	private ChunkRepository chunkRepository;
	
	@Autowired
	public ChunkServiceImpl(ChunkRepository repository) {
		this.chunkRepository = repository;
	}

	// timeseries-keys-values
	@Override
	public List<TsKeyValue> findMax(String unitId, Long timeBucket) {
		return chunkRepository.findMax(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findMax(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findMax(unitId, attributeKey, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findMax(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findMax(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findMin(String unitId, Long timeBucket) {
		return chunkRepository.findMax(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findMin(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findMax(unitId, attributeKey, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findMin(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findMin(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findAvg(String unitId, Long timeBucket) {
		return chunkRepository.findMax(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findAvg(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findMax(unitId, attributeKey, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findAvg(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findAvg(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findMax(unitId, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findSum(String unitId, Long timeBucket) {
		return chunkRepository.findSum(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findSum(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findSum(unitId, attributeKey, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findSum(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findSum(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findSum(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findSum(unitId, start, end, timeBucket);
	}

	@Override
	public List<TsKeyValue> findCount(String unitId, Long timeBucket) {
		return chunkRepository.findCount(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findCount(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findCount(unitId, attributeKey, timeBucket);
	}

	@Override
	public List<TsKeyValue> findCount(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findCount(unitId, attributeKey, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findCount(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findCount(unitId, start, end, timeBucket);
	}
	
	@Override
	public List<TsKeyValue> findLast(String unitId, Long timeBucket) {
		return chunkRepository.findLast(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findLast(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findLast(unitId, attributeKey, timeBucket);
	}

	@Override
	public List<TsKeyValue> findLast(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findLast(unitId, attributeKey, start, end, timeBucket);
	}

	@Override
	public List<TsKeyValue> findLast(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findLast(unitId, start, end, timeBucket);
	}

	@Override
	public List<TsKeyValue> findFirst(String unitId, Long timeBucket) {
		return chunkRepository.findFirst(unitId, timeBucket);
	}

	@Override
	public List<TsKeyValue> findFirst(String unitId, String attributeKey, Long timeBucket) {
		return chunkRepository.findFirst(unitId, attributeKey, timeBucket);
	}

	@Override
	public List<TsKeyValue> findFirst(String unitId, String attributeKey, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findFirst(unitId, attributeKey, start, end, timeBucket);
	}

	@Override
	public List<TsKeyValue> findFirst(String unitId, Long startTs, Long endTs, Long timeBucket) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return chunkRepository.findFirst(unitId, start, end, timeBucket);
	}

	// keys-values
	@Override
	public List getMaxKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findMax(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getMaxKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findMax(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getMinKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findMin(unitId, timeBucket).forEach(e -> {
			values.add( (type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getMinKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findMin(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getAvgKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findAvg(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getAvgKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findAvg(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getSumKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findSum(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getSumKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findSum(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getCountKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findCount(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getCountKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findCount(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getLastKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findLast(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getLastKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findLast(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getFirstKeysValues(String unitId, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findFirst(unitId, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getFirstKeysValues(String unitId, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findFirst(unitId, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLongValue()));
		});
		return values;
	}
	
	// values
	@Override
	public List getMaxValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findMax(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					    new Double(e.getDoubleValue()) :
						new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getMaxValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findMax(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getMinValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findMin(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getMinValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findMin(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getAvgValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findAvg(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getAvgValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findAvg(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getSumValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findSum(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getSumValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findSum(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getCountValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findCount(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getCountValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findCount(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getLastValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findLast(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getLastValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findLast(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}
	
	@Override
	public List getFirstValues(String unitId, String key, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.chunkRepository.findFirst(unitId, key, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}

	@Override
	public List getFirstValues(String unitId, String key, Long startTs, Long endTs, Long timeBucket, DataType type) {
		List<Object> values = new ArrayList<Object>();
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		this.chunkRepository.findFirst(unitId, key, start, end, timeBucket).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
				    new Double(e.getDoubleValue()) :
					new Long(e.getLongValue()));
		});
		return values;
	}



}
