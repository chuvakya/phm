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
import org.zs.phm3.models.ts.TsKvLatest;
import org.zs.phm3.service.ts.TsKvHyperService;
import org.zs.phm3.service.ts.TsKvLatestService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsKvLatestTest {

	@Autowired
	private TsKvLatestService latestService;
	
	@Autowired
	private TsKvHyperService hyperService;
	
	
	@Test
	public void test_findAllByEntityId() {
		String unitId = "1";
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		latestValues = latestService.findAllByEntityId(unitId);
		
		assertTrue("error reading Latest data keys", latestValues.size()>0);
		
		latestValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + 
				" Double value: " + e.getDoubleValue() + 
				" Long value: " + e.getLongValue() + 
				" Boolean value: " + e.getBooleanValue() + 
				" String value: " + e.getStrValue()));
	}

	@Test
	public void test_findAllByEntityIdAndKey() {
		String unitId = "1";
		String key = "U";
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		latestValues = latestService.findAllByEntityIdAndKey(unitId, key);
		
		assertTrue("error reading Latest data key", latestValues.size()>0);
		
		latestValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + 
				" Double value: " + e.getDoubleValue() + 
				" Long value: " + e.getLongValue() + 
				" Boolean value: " + e.getBooleanValue() + 
				" String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_saveLatest() {
		String unitId = "1";
		
		latestService.saveLatest(unitId);
		
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		latestValues = latestService.findAllByEntityId(unitId);
		
		assertTrue("error saving Latest data", latestValues.size()>0);
		
		latestValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + 
				" Double value: " + e.getDoubleValue() + 
				" Long value: " + e.getLongValue() + 
				" Boolean value: " + e.getBooleanValue() + 
				" String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_saveLatestByKey() {
		String unitId = "3";
		String key = "MyKey3";
		
		latestService.saveLatest(unitId, key);
		
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		latestValues = latestService.findAllByEntityId(unitId);
		
		assertTrue("error saving Latest data", latestValues.size()>0);
		
		latestValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + 
				" Double value: " + e.getDoubleValue() + 
				" Long value: " + e.getLongValue() + 
				" Boolean value: " + e.getBooleanValue() + 
				" String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_saveLatestByHyper() {
		String unitId = "2";
		List<TsKvHyper> latestHyper = hyperService.findAllLatestValues(unitId);
		
		assertTrue("error finding Latest data", latestHyper.size()>0);
		
		latestService.saveLatest(latestHyper);
		
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		latestValues = latestService.findAllByEntityId(unitId);
		
		assertTrue("error saving Latest data", latestValues.size()>0);
		
		latestValues.forEach(e -> System.out.println("Unit ID: " + e.getEntityId() + " TS: " + e.getTs() + " Attribute key: " + e.getKey() + 
				" Double value: " + e.getDoubleValue() + 
				" Long value: " + e.getLongValue() + 
				" Boolean value: " + e.getBooleanValue() + 
				" String value: " + e.getStrValue()));
	}
	
	@Test
	public void test_deleteById() {
		String unitId = "2";
		String key = "MyKey2";
		List<TsKvLatest> latestValues = new ArrayList<TsKvLatest>();
		
		latestValues = latestService.findAllByEntityIdAndKey(unitId, key);
		assertTrue("error reading Latest data", latestValues.size()>0);
		System.out.println("Count rows before delete: " + latestValues.size());
		
		latestService.deleteById(unitId, key);
		
		latestValues = latestService.findAllByEntityIdAndKey(unitId, key);
		System.out.println("Count rows after delete: " + latestValues.size());
	}
	
	@Test
	public void test_deleteByEntityId() {
		String unitId = "3";
		
		List<TsKvLatest> latestValues = latestService.findAllByEntityId(unitId);
		assertTrue("error reading Latest data", latestValues.size()>0);
		
		System.out.println("Count rows before delete: " + latestValues.size());
		
		latestService.deleteByEntityId(unitId);
				
		latestValues = latestService.findAllByEntityId(unitId);
		assertTrue("error deleting Latest data", latestValues.size()==0);
		System.out.println("Count rows after delete: " + latestValues.size());
	}
	
	@Test
	public void test_keyNew() {
		String unitId = "2";
		String keyOld = "MyKey20";
		String keyNew = "MyKey2";
		
		latestService.renameKey(unitId, keyOld, keyNew);
		
		List<TsKvLatest> latestValues = latestService.findAllByEntityIdAndKey(unitId, keyNew);
		
		assertTrue("error finding new attribute key", latestValues.size()>0);
	}
	
	
	

}
