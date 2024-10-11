package org.zs.phm3.service.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.repository.ts.TsKvHyperRepository;
import org.zs.phm3.util.parquet.ParquetIOWriter;
import org.zs.phm3.util.parquet.csv.PhmCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.service.BaseService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class TsKvHyperServiceImpl extends BaseService implements TsKvHyperService {
	@Autowired
	private final TsKvHyperRepository repository;
	 
	@Autowired
	public TsKvHyperServiceImpl(TsKvHyperRepository repository) {
		this.repository = repository;
	}

	// get data
	@Override
	public List<TsKvHyper> findAll(String unitId) {
		return repository.findAll(unitId);
	}	
	
	@Override
	public List<TsKvHyper> findAllByTs(String unitId, String key, Long startTs, Long endTs) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return repository.findAllByTs(unitId, key, startTs, endTs);
	}

	@Override
	public List<TsKvHyper> findAllByTsBetween(String unitId, String key, Long startTs, Long endTs) {
		Long start = startTs;
		Long end = endTs;
		if(start == null || start < 0L) { start = 0L; }
		if(end == null || end < 0L || end <= start) { end = System.currentTimeMillis(); }
		return repository.findAllByTsBetween(unitId, key, startTs, endTs);
	}

	@Override
	public List<TsKvHyper> findAllByEntityId(String unitId) {
		return repository.findAllByEntityId(unitId);
	}

	@Override
	public List<TsKvHyper> findAllByEntityIdAndKey(String unitId, String key) {
		return repository.findAllByEntityIdAndKey(unitId, key);
	}

	@Override
	public Double maxDouble(String unitId, String key) {
		return repository.maxDouble(unitId, key);
	}

	@Override
	public Double minDouble(String unitId, String key) {
		return repository.minDouble(unitId, key);
	}

	@Override
	public Long maxLong(String unitId, String key) {
		return repository.maxLong(unitId, key);
	}

	@Override
	public Long minLong(String unitId, String key) {
		return repository.minLong(unitId, key);
	}


	@Override
	public List<String> getKeys(String unitId) {
		return repository.getKeys(unitId);
	}	
	
	@Override
	public List<Double> getValuesDouble(String unitId, String key) {
		return repository.getValuesDouble(unitId, key);
	}
	

	@Override
	public List<Long> getValuesLong(String unitId, String key) {
		return repository.getValuesLong(unitId, key);
	}

	@Override
	public List<String> getValuesString(String unitId, String key) {
		return repository.getValuesString(unitId, key);
	}

	@Override
	public List<Boolean> getValuesBoolean(String unitId, String key) {
		return repository.getValuesBoolean(unitId, key);
	}
	
	// save data
	@Override
	public void save(TsKvHyper val) {
		repository.save(val);
	}	
	
//	@Override
//	public void save(List<TsKvHyper> vals) {
//		vals.forEach(e -> repository.save(e));
//	}
	
	@Override
	public void saveAll(List<TsKvHyper> vals) {
		repository.saveAll(vals);
	}
	
	@Override
	public boolean saveToCSV(String unitId) {
		return saveCSV(unitId);
	}

	@Override
	public boolean saveToParquet(String unitId) {
		return saveParquet(unitId);
	}
	
	private List<Map<String, String>> getToSave(String unitId) {
        List<TsKvHyper> tsReadedList = new ArrayList<>();
        Map<String, String> tsMap = new LinkedHashMap<>();
        List<Map<String, String>> tsListMapForExport = new ArrayList<>();
        tsReadedList = repository.findAll(unitId);
        long packet_ts = 0;

        for (TsKvHyper ts : tsReadedList) {
            if (packet_ts == 0) {
                packet_ts = ts.getTs();
                tsMap.put("timestamp", String.valueOf(packet_ts));
            }
            if (packet_ts == ts.getTs()) {
                tsMap.put(ts.getKey(), String.valueOf(ts.getDoubleValue()));
            } else {
                // close map data packet and add to list
                tsListMapForExport.add(tsMap);
                tsMap = new LinkedHashMap<>();
                // new map data packet
                packet_ts = ts.getTs();
                tsMap.put("timestamp", String.valueOf(packet_ts));
                tsMap.put(ts.getKey(), String.valueOf(ts.getDoubleValue()));
            }
        }
        if (tsMap.size() > 0) {
        	// save last record
        	tsListMapForExport.add(tsMap);
        }
        return tsListMapForExport;
	}
	
	private List<Map<String, Double>> getToSaveDouble(String unitId) {
        List<TsKvHyper> tsReadedList = new ArrayList<>();
        Map<String, Double> tsMap = new LinkedHashMap<>();
        List<Map<String, Double>> tsListMapForExport = new ArrayList<>();
        tsReadedList = repository.findAll(unitId);
        long packet_ts = 0;

        for (TsKvHyper ts : tsReadedList) {
            if (packet_ts == 0) {
                packet_ts = ts.getTs();
                tsMap.put("timestamp", Double.valueOf(packet_ts));
            }
            if (packet_ts == ts.getTs()) {
                tsMap.put(ts.getKey(), ts.getDoubleValue());
            } else {
                // close map data packet and add to list
                tsListMapForExport.add(tsMap);
                tsMap = new LinkedHashMap<>();
                // new map data packet
                packet_ts = ts.getTs();
                tsMap.put("timestamp", Double.valueOf(packet_ts));
                tsMap.put(ts.getKey(), ts.getDoubleValue());
            }
        }
        if (tsMap.size() > 0) {
        	// save last record
        	tsListMapForExport.add(tsMap);
        }
        return tsListMapForExport;
	}
	
	private boolean saveCSV(String unitId) {
        List<Map<String, String>> tsListMapForExport = getToSave(unitId);

        PhmCSV test = new PhmCSV();
        test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\save_01.csv");
        //test.setPath(mlServiceProperties.getExportFullpath());
        Boolean result=test.writeFrom(tsListMapForExport);

        return result;
	}
	
	private boolean saveParquet(String unitId) {
		List<String> keys = new ArrayList<>();
		List<String> keysSchema = new ArrayList<>();
		keys = repository.getKeys(unitId);
		keysSchema.add(0, "timestamp");
		keys.forEach(e -> keysSchema.add(e));
		List<Map<String, Double>> tsListMapForExport = getToSaveDouble(unitId);
		
		ParquetIOWriter test = new ParquetIOWriter();
		//test.setWriteMode(test.MODE_OWERWRITE);
		test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\save_01.parquet");
    	//test.setPath(mlServiceProperties.getExportFullpath());

		ArrayList<Integer> types = new ArrayList<>();
    	keysSchema.forEach(e -> types.add(test.TYPE_DOUBLE));
    	types.set(0, test.TYPE_LONG);
    	
    	test.createSchema(keysSchema, types);
		
		test.addValuesByKeysDouble(tsListMapForExport);
		
		test.writeData();
		
		return true;
	}
	
	// latest data
	@Override
	public List<TsKvHyper> findAllLatestValues(String unitId) {
		return repository.findAllLatestValues(unitId);
	}
	
	@Override
	public TsKvHyper findAllLatestValues(String unitId, String key) {
		return repository.findAllLatestValues(unitId, key);
	}

	@Override
	public Double getLatestDoubleValue(String unitId, String key) {
		return repository.getLatestDoubleValue(unitId, key);
	}

	@Override
	public Long getLatestLongValue(String unitId, String key) {
		return repository.getLatestLongValue(unitId, key);
	}

	@Override
	public Boolean getLatestBoolValue(String unitId, String key) {
		return repository.getLatestBoolValue(unitId, key);
	}

	@Override
	public String getLatestStringValue(String unitId, String key) {
		return repository.getLatestStringValue(unitId, key);
	}

	// technology operations
	@Override
	public void delete(String unitId) {
		repository.delete(unitId);
	}

	@Override
	public void delete(String unitId, String key) {
		repository.delete(unitId, key);		
	}
	
	
}
