package org.zs.phm3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

import org.zs.phm3.util.parquet.ParquetIOWriter;

public class ParquetIOWriterTest extends TestCase {

	
	public void test_CreateParquetDataSet() {
		ParquetIOWriter test = new ParquetIOWriter();
		String file = "C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate50.parquet";
		
		// путь к файлу
		test.setPath(file);

		// режим записи - перезаписывать существующий файл новым
		test.setWriteMode(test.MODE_OWERWRITE);
		
		ArrayList<String> keys = new ArrayList<>();
		
		// create schema for dataset
		keys.add("timestamp");
		keys.add("Tenv");
		keys.add("Trad");
		keys.add("U");
		keys.add("I");
		keys.add("remaining_uptime");
		keys.add("BIT_code");
    	
    	test.createSchema(keys, test.TYPE_DOUBLE);
    	
    	test.printSchemaFields();
    	System.out.println("Schema created");
    	System.out.println("\n\r");
    	
    	// generate data records
    	List<Map<String, Object>> list = new ArrayList<>();
    	
    	for (int i = 0; i < 1000; i++) {
	    	Map<String, Object> data = new LinkedHashMap<String,Object>();
	    	
	    	data.put(keys.get(0), i);
	    	data.put(keys.get(1), new Random().nextDouble());
	    	data.put(keys.get(2), new Random().nextDouble());
	    	data.put(keys.get(3), new Random().nextDouble() + new Random().nextInt(44));
	    	data.put(keys.get(4), new Random().nextDouble() + new Random().nextInt(80));
	    	data.put(keys.get(5), new Random().nextInt(100));
	    	data.put(keys.get(6), new Random().nextInt(5));
	    	
	    	list.add(data);
    	}
   	
    	//test.printRecordsList();
    	System.out.println("Dataset values loaded");
    	System.out.println("\n\r");
    	
    	// write data records
    	test.addValuesByKeysObject(list);
    	
    	// create file
    	test.writeData();
    	System.out.println("File created success!");
	}
	
	// create schema
    public void test_CreateSchema() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// add structure for schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);
    	
    	test.printSchemaFields();
    }
    
    // create schema from JSON
    public void test_createSchemaFromJSON() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	String json = "{\r\n" + 
    			"  \"type\" : \"record\",\r\n" + 
    			"  \"name\" : \"schema\",\r\n" + 
    			"  \"fields\" : [ {\r\n" + 
    			"    \"name\" : \"timestamp\",\r\n" + 
    			"    \"type\" : [ \"null\", \"long\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"Tenv\",\r\n" + 
    			"    \"type\" : [ \"null\", \"double\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"Trad\",\r\n" + 
    			"    \"type\" : [ \"null\", \"double\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"U\",\r\n" + 
    			"    \"type\" : [ \"null\", \"long\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"I\",\r\n" + 
    			"    \"type\" : [ \"null\", \"double\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"remaining_uptime\",\r\n" + 
    			"    \"type\" : [ \"null\", \"double\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  }, {\r\n" + 
    			"    \"name\" : \"BIT_code\",\r\n" + 
    			"    \"type\" : [ \"null\", \"long\" ],\r\n" + 
    			"    \"default\" : null\r\n" + 
    			"  } ]\r\n" + 
    			"}";
    	test.createSchema(json);
    	test.printSchemaFields();
    }
    
    public void test_addKeySchema() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// add structure for schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);
    	
    	System.out.println("Old schema:");
    	test.printSchemaFields();
    	
    	// add new key
    	test.addKeySchema("New_Param", test.TYPE_DOUBLE);
    	
    	System.out.println("\n\r");
    	System.out.println("New schema:");
    	test.printSchemaFields(); 
    	
    	System.out.println("\n\r");
    	
    	// remove key
    	test.delKeySchema("U");
    	test.printSchemaFields(); 
    }
    
    public void test_createSchemaByTypes() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// add structure for schema
    	columns.add("timestamp");		//1
    	columns.add("Tenv");			//2
    	columns.add("Trad");			//3
    	columns.add("U");				//4
    	columns.add("I");				//5
    	columns.add("remaining_uptime");//6
    	columns.add("BIT_code"); 		//7
    	
    	// add types for schema
    	ArrayList<Integer> types = new ArrayList<>();
    	
    	types.add(test.TYPE_INT32);		//1
    	types.add(test.TYPE_DOUBLE);	//2
    	types.add(test.TYPE_DOUBLE);	//3
    	types.add(test.TYPE_DOUBLE);	//4
    	types.add(test.TYPE_DOUBLE);	//5
    	types.add(test.TYPE_LONG);		//6
    	types.add(test.TYPE_INT32);		//7
    	
    	test.createSchema(columns, types);
    	
    	System.out.println(test.getSchemaToJSON());
    	
    	String type = test.getKeyType("remaining_uptime");
    	System.out.println("Type: " + type);
    }
    
    public void test_createRecordsFromString() {
   	
    	ParquetIOWriter test = new ParquetIOWriter();
    	//test.setPath("file:\\C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns,test.TYPE_DOUBLE);   
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));    	
    	
    	// generate data
    	Map<String, String> data = new LinkedHashMap<String,String>();
    	
    	data.put(columns.get(0), "100");
    	data.put(columns.get(1), "24.01");
    	data.put(columns.get(2), "234.007");
    	data.put(columns.get(3), "5.83");
    	data.put(columns.get(4), "12.224");
    	data.put(columns.get(5), "0.551");
    	data.put(columns.get(6), "1.0");
    	
    	// add data to SCHEMA
    	test.addValuesByKeysString(data);
    	
    	test.printDataSetList();
    	
    }
    
    public void test_createRecordsFromDouble() {
    	
    	ParquetIOWriter test = new ParquetIOWriter();
    	//test.setPath("file:\\C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);   
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));    	
    	
    	// generate data
    	Map<String, Double> data = new LinkedHashMap<String,Double>();
    	
    	data.put(columns.get(0), 100.00);
    	data.put(columns.get(1), 24.0);
    	data.put(columns.get(2), 234.007);
    	data.put(columns.get(3), 5.83);
    	data.put(columns.get(4), 12.224);
    	data.put(columns.get(5), 0.551);
    	data.put(columns.get(6), 1.0);
    	
    	// add data to SCHEMA
    	test.addValuesByKeysDouble(data);
    	
    	test.printDataSetList();
  	
    }
    
    public void test_createRecordsFromStringList() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);   
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));    	
    	
    	// generate data
    	List<Map<String, Double>> list = new ArrayList<>();
    	
    	for (int i = 0; i < 1000; i++) {
	    	Map<String, Double> data = new LinkedHashMap<String,Double>();
	    	
	    	data.put(columns.get(0), 100.00);
	    	data.put(columns.get(1), 24.0);
	    	data.put(columns.get(2), 234.007);
	    	data.put(columns.get(3), 5.83);
	    	data.put(columns.get(4), 12.224);
	    	data.put(columns.get(5), 0.551);
	    	data.put(columns.get(6), 1.0);
	    	
	    	list.add(data);
    	}
    	test.addValuesByKeysDouble(list);
    	
    	test.printDataSetList();
    }
    
    public void test_createRecordsFromObject() {
    	
    	ParquetIOWriter test = new ParquetIOWriter();
    	//test.setPath("file:\\C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);   
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));    	
    	
    	// generate data
    	Map<String, Object> data = new LinkedHashMap<String,Object>();
    	
    	data.put(columns.get(0), 100.00);
    	data.put(columns.get(1), 24.0);
    	data.put(columns.get(2), 234.007);
    	data.put(columns.get(3), 5.83);
    	data.put(columns.get(4), 12.224);
    	data.put(columns.get(5), 0.551);
    	data.put(columns.get(6), 1.0);
    	
    	// add data to SCHEMA
    	test.addValuesByKeysObject(data); 
    	
    	test.printDataSetList();
    }    
	
    
    public void test_addValuesFromDouble() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE); 
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));       	
    	
    	List<Double> values = new ArrayList<>();
    	
    	for (int i = 0; i < 1000; i++) {
    		values.add(12.005);
    	}
    	test.addValuesByKeyDouble("timestamp", values);
    	
    	test.printDataSetList();
    }
    
    public void test_addValues() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);   
    	
    	// get all keys
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));    	
    	
    	// generate data
    	List<Map<String, ?>> list = new ArrayList<>();
    	
    	for (int i = 0; i < 1000; i++) {
	    	Map<String, Double> data = new LinkedHashMap<String,Double>();
	    	
	    	data.put(columns.get(0), 100.00);
	    	data.put(columns.get(1), 24.0);
	    	data.put(columns.get(2), 234.007);
	    	data.put(columns.get(3), 5.83);
	    	data.put(columns.get(4), 12.224);
	    	data.put(columns.get(5), 0.551);
	    	data.put(columns.get(6), 1.0);
	    	
	    	list.add(data);
    	}
    	//test.addValuesFromDouble(list);
    	test.addValues(list);
    	
    	test.printDataSetList();    	
    }    
    
    public void test_getRecordsList() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// create new schema
    	columns.add("timestamp");
    	columns.add("Tenv");
    	columns.add("Trad");
    	columns.add("U");
    	columns.add("I");
    	columns.add("remaining_uptime");
    	columns.add("BIT_code");
    	
    	test.createSchema(columns, test.TYPE_DOUBLE);   
    	// generate values for keys
    	List<Object> values1 = new ArrayList<>();
    	List<Object> values2 = new ArrayList<>();
    	List<Object> values3 = new ArrayList<>();
    	List<Object> values4 = new ArrayList<>();
    	List<Object> values5 = new ArrayList<>();
    	List<Object> values6 = new ArrayList<>();
    	List<Object> values7 = new ArrayList<>();
    	
    	for (int i = 0; i < 1000; i++) {
    		values1.add(i);
    		values2.add(new Random().nextDouble());
    		values3.add(new Random().nextDouble());
    		values4.add(new Random().nextDouble() + new Random().nextInt(44));
    		values5.add(new Random().nextDouble() + new Random().nextInt(80));
    		values6.add(new Random().nextInt(100));
    		values7.add(new Random().nextInt(5));
    	}
    	test.addValuesByKeyObject("timestamp", values1);
    	test.addValuesByKeyObject("Tenv", values2);
    	test.addValuesByKeyObject("Trad", values3);
    	test.addValuesByKeyObject("U", values4);
    	test.addValuesByKeyObject("I", values5);
    	test.addValuesByKeyObject("remaining_uptime", values6);
    	test.addValuesByKeyObject("BIT_code", values7);
    	
    	List<List<String>> list = test.getValues();
    	list.forEach(e -> System.out.println(e));
    	
    	System.out.println("\n\r");
    	
    	List<Map<String, Object>> maps = test.getValuesMap();
    	maps.forEach(e -> System.out.println(e));
    	
    	System.out.println("\n\r");
    	
    	List<List<String>> listKey = test.getValuesByKey("Tenv");
    	listKey.forEach(e -> System.out.println(e));
    	
    	System.out.println("\n\r");
    	
    	List<Map<String, Object>> mapsKey = test.getValuesMapByKey("Tenv");
    	mapsKey.forEach(e -> System.out.println(e));
    }
    
    // from CSV file
    public void test_FromCSV() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	String csvPath = "C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\td2.csv";
    	
    	// get data from CSV
    	test.fromCSV(csvPath);
    	
    	// print schema
    	test.printSchemaFields();
    	
    	// print values
    	test.printDataSetList();
    }
    
    // get schema keys and type
    public void test_getKeysTypes() {
    	ParquetIOWriter test = new ParquetIOWriter();
    	
    	ArrayList<String> columns = new ArrayList<>();
    	
    	// add structure for schema
    	columns.add("timestamp");		//1
    	columns.add("Tenv");			//2
    	columns.add("Trad");			//3
    	columns.add("U");				//4
    	columns.add("I");				//5
    	columns.add("remaining_uptime");//6
    	columns.add("BIT_code"); 		//7
    	
    	// add types for schema
    	ArrayList<Integer> types = new ArrayList<>();
    	
    	types.add(test.TYPE_INT32);		//1
    	types.add(test.TYPE_DOUBLE);	//2
    	types.add(test.TYPE_DOUBLE);	//3
    	types.add(test.TYPE_DOUBLE);	//4
    	types.add(test.TYPE_DOUBLE);	//5
    	types.add(test.TYPE_LONG);		//6
    	types.add(test.TYPE_INT32);		//7
    	
    	test.createSchema(columns, types);
    	
    	// get schema keys and type
    	Map<String, String> keys;
    	keys = test.getKeysTypes();
    	keys.forEach((k, v) -> System.out.println(k + ": " + v));
    }
	
}
