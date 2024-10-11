package org.zs.phm3.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DataUtil;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.service.ts.AggregationService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TsKvAggregationServiceTest {
	private static String unitId = "1ea7578b1c58b00a8acb566a9639462";
	
	@Autowired
	private AggregationService aggregationService;
	
	@Test
	public void test_findAll() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		
		listValues = aggregationService.findAll(unitId);
		
		assertTrue("error reading Aggregation data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println("Attribute key: " + e.getKey() + 
												   " Double AVG value: " + e.getAvgDoubleValue() + 
												   " Double FIRST value: " + e.getFirstDoubleValue() +
												   " Double LAST value: " + e.getLastDoubleValue() +
												   " Double MAX value: " + e.getMaxDoubleValue() +
												   " Double MIN value: " + e.getMinDoubleValue() +
												   " Double SUM value: " + e.getSumDoubleValue())); 
		
	}
	
	@Test
	public void test_findAllByPeriod() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		Long startTs = 0L;
		Long endTs = null;
		listValues = aggregationService.findAll(unitId, startTs, endTs);
		
		assertTrue("error reading Aggregation data timeseries by period", listValues.size() > 0);

		listValues.forEach(e -> System.out.println("Attribute key: " + e.getKey() + 
												   " Double AVG value: " + e.getAvgDoubleValue() + 
												   " Double FIRST value: " + e.getFirstDoubleValue() +
												   " Double LAST value: " + e.getLastDoubleValue() +
												   " Double MAX value: " + e.getMaxDoubleValue() +
												   " Double MIN value: " + e.getMinDoubleValue() +
												   " Double SUM value: " + e.getSumDoubleValue())); 
		
	}
	
	@Test
	public void test_getLastKeysValues() {
		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, aggregationService.getLastKeysValues(unitId, DataType.DOUBLE));
		
		assertTrue("error reading MAX Aggregation data Key-Value", values.size() > 0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_getLastKeysValuesByPeriod() {
		Long startTs = 0L;
		Long endTs = null;
		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, aggregationService.getLastKeysValues(unitId, startTs, endTs, DataType.DOUBLE));
		
		assertTrue("error reading MAX Aggregation data Key-Value", values.size() > 0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_getMaxValue() {
		String key = "U";
		Double val = (Double)aggregationService.getMaxValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading MAX Aggregation data value", val != null);
		
		System.out.println("MAX value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getMaxValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getMaxValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading MAX Aggregation data value", val != null);
		
		System.out.println("MAX value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getMinValue() {
		String key = "U";
		Double val = (Double)aggregationService.getMinValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading MIN Aggregation data value", val != null);
		
		System.out.println("MIN value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getMinValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getMinValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading MIN Aggregation data value", val != null);
		
		System.out.println("MIN value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getAvgValue() {
		String key = "U";
		Double val = (Double)aggregationService.getAvgValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading AVG Aggregation data value", val != null);
		
		System.out.println("AVG value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getAvgValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getAvgValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading AVG Aggregation data value", val != null);
		
		System.out.println("AVG value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getSumValue() {
		String key = "U";
		Double val = (Double)aggregationService.getSumValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading SUM Aggregation data value", val != null);
		
		System.out.println("SUM value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getSumValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getSumValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading SUM Aggregation data value", val != null);
		
		System.out.println("SUM value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getLastValue() {
		String key = "U";
		Double val = (Double)aggregationService.getLastValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading LAST Aggregation data value", val != null);
		
		System.out.println("LAST value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getLastValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getLastValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading LAST Aggregation data value", val != null);
		
		System.out.println("LAST value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getFirstValue() {
		String key = "U";
		Double val = (Double)aggregationService.getFirstValue(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading FIRST Aggregation data value", val != null);
		
		System.out.println("FIRST value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_getFirstValueByPeriod() {
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		Double val = (Double)aggregationService.getFirstValue(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading FIRST Aggregation data value", val != null);
		
		System.out.println("FIRST value for attribute '" + key +"': " + val);
	}
	
	@Test
	public void test_findDeltaKeysValues() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		String key = "U";

		listValues = aggregationService.findDeltaKeysValues(unitId, key, DataType.DOUBLE);
		
		assertTrue("error reading DELTA Aggregation data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue())); 
	}
	
	@Test
	public void test_findDeltaKeysValuesByPeriod() {
		List<TsKeyValue> listValues = new ArrayList<TsKeyValue>();
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		listValues = aggregationService.findDeltaKeysValues(unitId, key, startTs, endTs, DataType.DOUBLE);
		
		assertTrue("error reading DELTA Aggregation data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue())); 
	}
	
	
}
