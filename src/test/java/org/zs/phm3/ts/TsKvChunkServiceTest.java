package org.zs.phm3.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DataUtil;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.service.ts.ChunkService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsKvChunkServiceTest {

	private static String unitId = "1ea7578b1c58b00a8acb566a9639462";
	
	@Autowired
	private ChunkService chunkService;
	
	// TIMESERIES KEYS-VALUES
	@Test
	public void test_findMax() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 50L;
		
		listValues = chunkService.findMax(unitId, timeBucket);
		
		assertTrue("error reading MAX Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findMaxByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 50L;
		String key = "U";
		
		listValues = chunkService.findMax(unitId, key, timeBucket);
		
		assertTrue("error reading MAX Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findMin() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 30L;
		
		listValues = chunkService.findMin(unitId, timeBucket);
		
		assertTrue("error reading MIN Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue()));
	}
	
	@Test
	public void test_findMinByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "I";
		
		listValues = chunkService.findMin(unitId, key, timeBucket);
		
		assertTrue("error reading MIN Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findAvg() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		
		listValues = chunkService.findAvg(unitId, timeBucket);
		
		assertTrue("error reading AVG Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findAvgByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "I";
		
		listValues = chunkService.findAvg(unitId, key, timeBucket);
		
		assertTrue("error reading AVG Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findSum() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		
		listValues = chunkService.findSum(unitId, timeBucket);
		
		assertTrue("error reading SUM Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findSumByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "I";
		
		listValues = chunkService.findSum(unitId, key, timeBucket);
		
		assertTrue("error reading SUM Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findCount() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		
		listValues = chunkService.findCount(unitId, timeBucket);
		
		assertTrue("error reading COUNT Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findCountByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "U";
		
		listValues = chunkService.findCount(unitId, key, timeBucket);
		
		assertTrue("error reading COUNT Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findLast() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		
		listValues = chunkService.findLast(unitId, timeBucket);
		
		assertTrue("error reading LAST Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findLastByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "U";
		
		listValues = chunkService.findLast(unitId, key, timeBucket);
		
		assertTrue("error reading LAST Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findFirst() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		
		listValues = chunkService.findFirst(unitId, timeBucket);
		
		assertTrue("error reading FIRST Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	@Test
	public void test_findFirstByKey() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		Long timeBucket = 25L;
		String key = "U";
		
		listValues = chunkService.findFirst(unitId, key, timeBucket);
		
		assertTrue("error reading FIRST Hyper data timeseries", listValues.size()>0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue())); 
	}
	
	// KEYS-VALUES
	@Test
	public void test_getMinKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getMinKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MIN Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getMinKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getMinKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MIN Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getMaxKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getMaxKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MAX Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getMaxKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getMaxKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MAX Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getAvgKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getAvgKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading AVG Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getAvgKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getAvgKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading AVG Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getSumKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getSumKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading SUM Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getSumKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getSumKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading SUM Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getCountKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getCountKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading COUNT Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getCountKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getCountKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading COUNT Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getLastKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getLastKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading LAST Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getLastKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getLastKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading LAST Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getFirstKeysValues() {
		Long timeBucket = 50L;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getFirstKeysValues(unitId, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading FIRST Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_getFirstKeysValuesByPeriod() {
		Long timeBucket = 50L;
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkService.getFirstKeysValues(unitId, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading FIRST Hyper data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	// VALUES
	@Test
	public void test_getMaxValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getMaxValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MAX Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getMaxValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getMaxValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MAX Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getMinValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getMinValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MIN Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getMinValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getMinValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading MIN Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getAvgValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getAvgValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading AVG Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getAvgValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getAvgValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading AVG Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getSumValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getSumValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading SUM Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getSumValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getSumValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading SUM Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getCountValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getCountValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading COUNT Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getCountValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getCountValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading COUNT Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}

	@Test
	public void test_getLastValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getLastValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading LAST Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getLastValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getLastValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading LAST Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getFirstValues() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getFirstValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading FIRST Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
	
	@Test
	public void test_getFirstValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		List<Double> values = DataUtil.toList(Double.class, chunkService.getFirstValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading FIRST Hyper data values", values.size()>0);
		
		values.forEach(e -> System.out.println(" Value: " + e.doubleValue()));
	}
}
