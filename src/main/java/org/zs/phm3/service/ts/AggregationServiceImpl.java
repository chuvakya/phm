package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.AggregationRepository;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.repository.ts.ext.LongValueEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregationServiceImpl implements AggregationService {

	@Autowired
	private AggregationRepository aggregationRepository;


	@Autowired
	public AggregationServiceImpl(AggregationRepository repository) {
		this.aggregationRepository = repository;
	}
	
	// AGG
	@Override
	public List<TsKeyAllValue> findAll(String unitId) {
		return this.aggregationRepository.findAll(unitId);
	}
	
	@Override
	public List<TsKeyAllValue> findAll(String unitId, Long startTs, Long endTs) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return this.aggregationRepository.findAll(unitId, start, end);
	}

	@Override
	public TsKeyAllValue findOne(String unitId, String attributeKey) {
		return this.aggregationRepository.findAll(unitId, attributeKey).get(0);
	}

	@Override
	public TsKeyAllValue findOne(String unitId, String attributeKey, Long startTs, Long endTs) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0);
	}
	
	// keys-values
	@Override
	public List getLastKeysValues(String unitId, DataType type) {
		List<Object> values = new ArrayList<Object>();
		this.aggregationRepository.findAll(unitId).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getLastDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLastLongValue()));
		});
		return values;
	}
	
	@Override
	public List getLastKeysValues(String unitId, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		List<Object> values = new ArrayList<Object>();
		this.aggregationRepository.findAll(unitId, start, end).forEach(e -> {
			values.add((type == DataType.DOUBLE) ?
					new DoubleValueEntry(e.getKey(), e.getLastDoubleValue()) :
					new LongValueEntry(e.getKey(), e.getLastLongValue()));
		});
		return values;
	}
	
	// values
	@Override
	public Object getMaxValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getMaxDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getMaxLongValue()));
		}
	}

	@Override
	public Object getMaxValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getMaxDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getMaxLongValue()));
		}
	}
	
	@Override
	public Object getMinValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getMinDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getMinLongValue()));
		}

	}

	@Override
	public Object getMinValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getMinDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getMinLongValue()));
		}

	}
	
	@Override
	public Object getAvgValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getAvgDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getAvgLongValue()));
		}

	}

	@Override
	public Object getAvgValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getAvgDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getAvgLongValue()));
		}

	}

	@Override
	public Object getSumValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getSumDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getSumLongValue()));
		}

	}

	@Override
	public Object getSumValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getSumDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getSumLongValue()));
		}

	}

	@Override
	public Object getLastValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getLastDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getLastLongValue()));
		}

	}

	@Override
	public Object getLastValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getLastDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getLastLongValue()));
		}

	}

	@Override
	public Object getFirstValue(String unitId, String attributeKey, DataType type) {
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getFirstDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey).get(0).getFirstLongValue()));
		}

	}

	@Override
	public Object getFirstValue(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		if (type == DataType.DOUBLE) {
			return (new Double(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getFirstDoubleValue()));
		} else {
			return (new Long(this.aggregationRepository.findAll(unitId, attributeKey, start, end).get(0).getFirstLongValue()));
		}

	}

	// DELTA
	@Override
	public List<TsKeyValue> findDeltaKeysValues(String unitId, String attributeKey, DataType type) {
		return this.aggregationRepository.findDelta(unitId, attributeKey, type);
	}
	
	@Override
	public List<TsKeyValue> findDeltaKeysValues(String unitId, String attributeKey, Long startTs, Long endTs, DataType type) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return this.aggregationRepository.findDelta(unitId, attributeKey, start, end, type);
	}


}
