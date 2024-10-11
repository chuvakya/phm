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
import org.zs.phm3.repository.ts.ext.ChunkPeriod;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DataUtil;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.service.ts.ChunkAllService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsKvChunkAllServiceTest {

	private static String unitId = "1ea7578b1c58b00a8acb566a9639462";
	
	@Autowired
	private ChunkAllService chunkAllService;
	
	@Test
	public void test_FindAll() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		Long timeBucket = 50L;
		String key = "U";
		
		listValues = chunkAllService.findAll(unitId, key, timeBucket);
		
		assertTrue("error reading Hyper data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + 
													" Attribute key: " + e.getKey() + 
													" Double AVG value: " + e.getAvgDoubleValue() + 
													" Double FIRST value: " + e.getFirstDoubleValue() +
													" Double LAST value: " + e.getLastDoubleValue() +
													" Double MAX value: " + e.getMaxDoubleValue() +
													" Double MIN value: " + e.getMinDoubleValue() +
													" Double SUM value: " + e.getSumDoubleValue())); 

	}
	
	@Test
	public void test_FindAllInterval() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		ChunkPeriod interval = new ChunkPeriod(5, ChunkPeriod.PERIOD.SECOND);
		String timeBucket = interval.getIntervalStr();
		String key = "U";
		
		listValues = chunkAllService.findAll(unitId, key, timeBucket);
		
		assertTrue("error reading Hyper data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + 
													" Attribute key: " + e.getKey() + 
													" Double AVG value: " + e.getAvgDoubleValue() + 
													" Double FIRST value: " + e.getFirstDoubleValue() +
													" Double LAST value: " + e.getLastDoubleValue() +
													" Double MAX value: " + e.getMaxDoubleValue() +
													" Double MIN value: " + e.getMinDoubleValue() +
													" Double SUM value: " + e.getSumDoubleValue())); 

	}
	
	@Test
	public void test_FindAllByPeriod() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;
		
		listValues = chunkAllService.findAll(unitId, key, startTs, endTs, timeBucket);
		
		assertTrue("error reading Hyper data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + 
													" Attribute key: " + e.getKey() + 
													" Double AVG value: " + e.getAvgDoubleValue() + 
													" Double FIRST value: " + e.getFirstDoubleValue() +
													" Double LAST value: " + e.getLastDoubleValue() +
													" Double MAX value: " + e.getMaxDoubleValue() +
													" Double MIN value: " + e.getMinDoubleValue() +
													" Double SUM value: " + e.getSumDoubleValue())); 

	}
	
	@Test
	public void test_FindAllByPeriodInterval() {
		List<TsKeyAllValue> listValues = new ArrayList<TsKeyAllValue>();
		ChunkPeriod interval = new ChunkPeriod(20, ChunkPeriod.PERIOD.SECOND);
		String timeBucket = interval.getIntervalStr();
		Long startTs = 0L;
		Long endTs = null;
		String key = "U";
		
		listValues = chunkAllService.findAll(unitId, key, startTs, endTs, timeBucket);
		
		assertTrue("error reading Hyper data timeseries", listValues.size() > 0);

		listValues.forEach(e -> System.out.println(" TS: " + e.getTs() + 
													" Attribute key: " + e.getKey() + 
													" Double AVG value: " + e.getAvgDoubleValue() + 
													" Double FIRST value: " + e.getFirstDoubleValue() +
													" Double LAST value: " + e.getLastDoubleValue() +
													" Double MAX value: " + e.getMaxDoubleValue() +
													" Double MIN value: " + e.getMinDoubleValue() +
													" Double SUM value: " + e.getSumDoubleValue())); 

	}
	
	@Test
	public void test_findLastKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findLastKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper LAST data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findLastKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findLastKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper LAST data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_findFirstKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findFirstKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper FIRST data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findFirstKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findFirstKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper FIRST data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_findMaxKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findMaxKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper MAX data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findMaxKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findMaxKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper MAX data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_findMinKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findMinKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper MIN data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findMinKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findMinKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper MIN data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
	@Test
	public void test_findSumKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findSumKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper SUM data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findSumKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findSumKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper SUM data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}

	@Test
	public void test_findAvgKeysValues() {
		Long timeBucket = 50L;
		String key = "U";

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findAvgKeysValues(unitId, key, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper AVG data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));

	}
	
	@Test
	public void test_findAvgKeysValuesByPeriod() {
		Long timeBucket = 50L;
		String key = "U";
		Long startTs = 0L;
		Long endTs = null;

		List<DoubleValueEntry> values = DataUtil.toList(DoubleValueEntry.class, chunkAllService.findAvgKeysValues(unitId, key, startTs, endTs, timeBucket, DataType.DOUBLE));
		
		assertTrue("error reading Hyper AVG data Key-Value", values.size()>0);
		
		values.forEach(e -> System.out.println(" Key: " + e.getKey() + " Value: " + e.getValueAsString()));
	}
	
}
