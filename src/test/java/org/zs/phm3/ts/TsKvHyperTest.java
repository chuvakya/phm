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
import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.service.ts.TsKvHyperService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsKvHyperTest {

	@Autowired
	private TsKvHyperService hyperService;
	
	private static String unitId = "1ea7578b1c58b00a8acb566a9639462";

	@Test
	public void test_FindAllByTs() {
		String key= "Tenv";
		Long strartTs = 1530000L;
		Long endTs = 1530007L;
		List<TsKvHyper> listValues = new ArrayList<TsKvHyper>();
		
		listValues = hyperService.findAllByTs(unitId, key, strartTs, endTs);
		
		assertTrue("error reading Hyper data timeseries", listValues.size()>0);
		
		listValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue() + " Boolean value: " + 
		e.getBooleanValue() + " String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_findByTsBetween() {
		String key= "U";
		Long strartTs = 1530001L;
		Long endTs = 1530009L;
		List<TsKvHyper> listValues = new ArrayList<TsKvHyper>();

		listValues = hyperService.findAllByTsBetween(unitId, key, strartTs, endTs);
		
		assertTrue("error reading Hyper data timeseries", listValues.size()>0);
		
		listValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue() + " Boolean value: " + 
		e.getBooleanValue() + " String value: " + e.getStrValue()));

	}
	
	@Test
	public void test_findAllByEntityId() {
		List<TsKvHyper> listValues = new ArrayList<TsKvHyper>();
		listValues = hyperService.findAllByEntityId(unitId);
		
		assertTrue("error reading Hyper data timeseries", listValues.size()>0);
		
		listValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue() + " Boolean value: " + 
		e.getBooleanValue() + " String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_findAllByEntityIdAndKey() {
		String key= "Tenv";
		List<TsKvHyper> listValues = new ArrayList<TsKvHyper>();
		
		listValues = hyperService.findAllByEntityIdAndKey(unitId, key);
		
		assertTrue("error reading Hyper data timeseries", listValues.size()>0);
		
		listValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + " Double value: " + e.getDoubleValue() + " Long value: " + e.getLongValue() + " Boolean value: " + 
		e.getBooleanValue() + " String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_MAX_Value() {
		String key= "Tenv";
		Double maxDoubleValue = 0.0;
		Long maxLongValue = 0L;
		
		maxDoubleValue = hyperService.maxDouble(unitId, key);
		System.out.println("Maximun value DOUBLE: " + maxDoubleValue);
		
		maxLongValue = hyperService.maxLong(unitId, key);
		System.out.println("Maximun value LONG: " + maxLongValue);
	}
	
	@Test
	public void test_MIN_Value() {
		String key= "Tenv";
		Double minDoubleValue = 0.0;
		Long minLongValue = 0L;
		
		minDoubleValue = hyperService.minDouble(unitId, key);
		System.out.println("Minimum value DOUBLE: " + minDoubleValue);
		
		minLongValue = hyperService.minLong(unitId, key);
		System.out.println("Minimum value LONG: " + minLongValue);
	}
	
	@Test 
	public void test_getKeys() {
		List<String> listKeys = new ArrayList<String>();
		
		listKeys = hyperService.getKeys(unitId);
		
		listKeys.forEach(e -> System.out.println(e));
	}
	
	@Test
	public void test_getValues() {
		String key= "Tenv";
		List<Double> listDoubleValues = new ArrayList<Double>();
		List<Long> listLongValues = new ArrayList<Long>();
		List<String> listStringValues = new ArrayList<String>();
		List<Boolean> listBooleanValues = new ArrayList<Boolean>();
		
		listDoubleValues = hyperService.getValuesDouble(unitId, key);
		System.out.println("Double values");
		listDoubleValues.forEach(e -> System.out.println(e));
		
		listLongValues = hyperService.getValuesLong(unitId, key);
		System.out.println("Long values");
		listLongValues.forEach(e -> System.out.println(e));
		
		listStringValues = hyperService.getValuesString(unitId, key);
		System.out.println("String values");
		listStringValues.forEach(e -> System.out.println(e));
		
		listBooleanValues = hyperService.getValuesBoolean(unitId, key);
		System.out.println("Boolean values");
		listBooleanValues.forEach(e -> System.out.println(e));
	}
	
	
	@Test
	public void test_save() {
		TsKvHyper val = new TsKvHyper();
		val.setEntityId("2");
		val.setKey("MyKey2");
		val.setTs(1530001L);
		val.setDoubleValue(55.00234);
		
		hyperService.save(val);

		List<TsKvHyper> dest = hyperService.findAllByEntityId("2");
		assertTrue("error save Hyper data timeseries", dest.size() > 0);
	}
	
	@Test
	public void test_saveAll() {
		List<TsKvHyper> list = new ArrayList<TsKvHyper>();
		TsKvHyper val = new TsKvHyper();
		val.setEntityId("3");
		val.setKey("MyKey3");
		val.setTs(1530000L);
		val.setDoubleValue(150.00234);
		list.add(val);
		
		val = new TsKvHyper();
		val.setEntityId("3");
		val.setKey("MyKey3");
		val.setTs(1530001L);
		val.setDoubleValue(151.00234);
		list.add(val);
		
		val = new TsKvHyper();
		val.setEntityId("3");
		val.setKey("MyKey3");
		val.setTs(1530002L);
		val.setDoubleValue(152.00234);
		list.add(val);
		
		hyperService.saveAll(list);
	}
	
	@Test
	public void test_findAllLatestValues() {
		List<TsKvHyper> list = new ArrayList<TsKvHyper>();
		
		list = hyperService.findAllLatestValues(unitId);
		list.forEach(e -> System.out.println(e));
	}
	
	@Test
	public void test_findAllLatestValuesByKey() {
		TsKvHyper vals = new TsKvHyper();
		String key = "Tenv";
		
		vals = hyperService.findAllLatestValues(unitId, key);
		System.out.println(vals);
	}
	
	@Test
	public void test_getLatestValue() {
		String key = "Tenv";
		
		Double dlValue =  hyperService.getLatestDoubleValue(unitId, key);
		System.out.println("Double value: " + dlValue);
		
		Long llValue =  hyperService.getLatestLongValue(unitId, key);
		System.out.println("Long value: " + llValue);
		
		String slValue =  hyperService.getLatestStringValue(unitId, key);
		System.out.println("String value: " + slValue);
		
		Boolean blValue =  hyperService.getLatestBoolValue(unitId, key);
		System.out.println("Boolean value: " + blValue);
	}
	
	@Test
	public void test_saveCSV() {
		boolean isSave = hyperService.saveToCSV(unitId);
		assertTrue("error save Hyper data timeseries", isSave);
	}
	
	@Test
	public void test_saveParquet() {
		boolean isSave = hyperService.saveToParquet(unitId);
		assertTrue("error save Hyper data timeseries", isSave);
	}
	
	

}
