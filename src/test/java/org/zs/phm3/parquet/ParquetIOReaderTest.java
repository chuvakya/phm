package org.zs.phm3.parquet;

import junit.framework.TestCase;
import org.apache.parquet.io.api.Binary;
import org.zs.phm3.util.parquet.ParquetIOReader;
//import org.zs.phm.util.parquet.ParquetIOReader;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

//import org.zs.phm3.util.parquet.ParquetIOReader;

public class ParquetIOReaderTest extends TestCase {

	// READ METHODS
	// **********************************************************************
	// получить все ключи из файла
    public void test_getKeys() {

    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");

    	test.readData();
    	
    	List<String> keys = test.getKeys();
    	
    	this.assertNotNull(keys);
    	
    	keys.forEach(e -> System.out.println(e));
    }
	
    // печать файла в консоль
    public void test_Print() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate5.parquet");
    	test.readData();
    	test.printDataSetList();
    }
    
    // проверка ключа по имени
    public void test_isKeyExists() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	test.readData();
    	
    	String keyName = "BIT_code";

    	if (test.isKeyExists(keyName)) {
    		System.out.printf("Key " + keyName + " is found!");
    	} else {
    		System.out.printf("Key " + keyName + " is NOT found!");
    	}
    }
    
    // получение типа ключа
    public void test_getKeyType() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	test.readData();
    	System.out.printf("Key type is: " + test.getKeyType("U"));
    }
    
    public void test_printSchemaFields() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	test.readData();
    	test.printSchemaFields();
    }
    
    public void test_getKeysTypes() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\Ds_par.parquet");
    	test.readData();
    	Map<String, String> keys = test.getKeysTypes();
    	keys.forEach((k, v) -> System.out.println(k + ": " + v));
    } 
    
    // структура схемы в формате JSON
    public void test_getSchema() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\Ds_par.parquet");
    	test.readData();
    	System.out.println(test.getSchemaToJSON());
    }
    
    // получение наборов данных по всем ключам
    public void test_getValues() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	
    	List<List<Double>> data = test.getValues();
    	//List<List<Integer>> data = test.getValues();
    	//List<List<Float>> data = test.getValues();
    	//List<List<String>> data = test.getValues();
    	
    	this.assertNotNull(data);
    	
    	data.forEach(e -> System.out.println(e));
    }
    
    // получение наборов данных по всем ключам
    public void test_getValuesMap() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate5.parquet");
    	test.readData();
    	
    	List<Map<String, Object>> maps = test.getValuesMap();
    	
    	this.assertNotNull(maps);
    	
    	maps.forEach(e -> System.out.println(e));
    }
    
 // получение наборов данных по всем ключам
    public void test_getValuesMapEx() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	
    	List<Map<String, Object>> maps = test.getValuesMapEx();
    	//List<Map<String, String>> maps = test.getValuesMapEx();
    	
    	this.assertNotNull(maps);
    	
    	maps.forEach(e -> System.out.println(e));
    }    
    
    // получение значений с конвертацией в тип STRING
    public void test_getValuesAsString() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	
    	List<List<String>> data = test.getValuesAsString();
    	
    	this.assertNotNull(data);
    	
    	data.forEach(e -> System.out.println(e));
    	
    	//data.forEach((list) -> {
		//	list.forEach((e)->System.out.println(e));
		//});  
    }
    
    // получение значений по имени ключа с конвертацией в тип STRING
    public void test_getValuesByKeyAsString() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	String keyName = "U";
    	
    	List<String> data = test.getValuesByKeyAsString(keyName);
    	
    	this.assertNotNull(data);
    	
    	data.forEach(e -> System.out.println(e));
    }
    
    // получение значений с конвертацией в тип DOUBLE
    public void test_getValuesAsDouble() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	
    	List<List<Double>> data = test.getValuesAsDouble();
    	
    	this.assertNotNull(data);
    	
    	data.forEach((list) -> {
			list.forEach((e)-> System.out.println(e.doubleValue()));
		});   
    }   
    
    // получение значений по имени ключа с конвертацией в тип DOUBLE
    public void test_getValuesByKeyAsDouble() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	String keyName = "U";
    	
    	List<Double> data = test.getValuesByKeyAsDouble(keyName);
    	
    	this.assertNotNull(data);
    	
    	data.forEach(e -> System.out.println(e));
    }
    
    // получение всех значений по ключу
    public void test_getValuesByKey() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\par3.parquet");
    	test.readData();
    	String keyName = "timestamp";
    	
    	List<?> data = test._getValuesByKey(keyName);
    	//List<List<String>> data = test.getValuesByKey(keyName);
    	//List<Map<String, Object>> data = test.getValuesMapByKey(keyName);

    	this.assertNotNull(data);

    	data.forEach(e -> System.out.println(e));
    }
    
    // конвертация TIMESTAMP в INT96
    public void test_TimestampToINT96() {
    	ParquetIOReader test = new ParquetIOReader();
    	String ts = "2019-03-14 15:09:26.535898";
    	Binary data = test.toINT96(ts);
    	
    	this.assertNotNull(data);
    	
    	System.out.println(data);
    }
    
    // конвертация INT96 в TIMESTAMP
    public void test_INT96ToTimestamp() {
    	ParquetIOReader test = new ParquetIOReader();
    	byte[] buf = {0, 28, -30, 16, -50, 39, 0, 0, -67, -125, 37, 0};
    	Binary data = Binary.fromReusedByteArray(buf);
    	Timestamp ts = test.toTimeStamp(data);
    	
    	System.out.println(ts);
    	
    }
    
    // конвертация INT96 в LONG
    public void test_INT96ToLong() {
    	ParquetIOReader test = new ParquetIOReader();
    	byte[] buf = {0, 28, -30, 16, -50, 39, 0, 0, -67, -125, 37, 0};
    	Binary data = Binary.fromReusedByteArray(buf);
    	Long ts = test.toLong(data);
    	
    	this.assertNotNull(ts);
    	
    	System.out.println(ts);
    	
    }  

    // конвертация LONG в TIMESTAMP
    public void test_LongToTimestamp() {
    	ParquetIOReader test = new ParquetIOReader();
    	Long data = 1552565366000L;
    	Timestamp ts = test.toTimeStamp(data);
    	
    	this.assertNotNull(ts);
    	
    	System.out.println(ts);
    	
    }    
    
    // запись в CSV файл
    public void test_WriteToCSV() {
    	ParquetIOReader test = new ParquetIOReader();
    	test.setPath("file:\\C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.parquet");
    	String csvFile = "C:\\Users\\Admin\\Documents\\PHM\\PHM\\test\\testCreate2.csv";
    	test.readData();
    	
    	assertTrue(test.toCSV(csvFile));
    }
    
    
    
	// END READ METHODS
	// **********************************************************************
    

    
    
}
