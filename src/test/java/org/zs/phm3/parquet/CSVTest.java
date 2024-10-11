package org.zs.phm3.parquet;

import junit.framework.TestCase;
import org.zs.phm3.util.parquet.csv.PhmCSV;
//import org.zs.phm.util.parquet.csv.PhmCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.zs.phm3.util.parquet.csv.PhmCSV;

public class CSVTest extends TestCase {

	public void test_ReadFile() {
		PhmCSV test = new PhmCSV();
//		test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\td2.csv");
//		test.setPath("C:\\java_work_technologies\\for_phm\\src\\main\\resources\\td2.csv");
		test.setPath("D:\\Java_CETC\\phm4\\src\\test\\resources\\td2.csv");
		long start = System.currentTimeMillis()/ 1000;
		
		// читаем данные из файла
		this.assertTrue(test.read()); 
		
		long finish = System.currentTimeMillis()/ 1000;
		
		System.out.println("Time read (sec): " + (finish - start));
		
		// получаем ключи
		List<String> keys = test.getKeys();
		
		this.assertNotNull(keys);
		
		keys.forEach(e -> System.out.println(e));
		
		// получаем значения ключей
		List<List<String>> values = test.getValues();
		
		this.assertNotNull(values);
		
		values.forEach(e -> System.out.println(e));
		
		// конвертируем ключи и значения в пары ключ-значение
		test.toMap();
		List<Map<String, String>> keysValues = test.getKeysValues();
		keysValues.forEach(e -> System.out.println(e));
	}
	
	public void test_ReadFileMap() {
		PhmCSV test = new PhmCSV();
//		test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\input.csv");
//		test.setPath("C:\\java_work_technologies\\for_phm\\src\\main\\resources\\td2.csv");
		test.setPath("D:\\Java_CETC\\phm4\\src\\test\\resources\\td2.csv");

		long start = System.currentTimeMillis()/ 1000;
		this.assertTrue(test.readMap()); 
		long finish = System.currentTimeMillis()/ 1000;
		System.out.println("Time read (sec): " + (finish - start));
		
		List<Map<String, String>> keysValues = test.getKeysValues();
		
		keysValues.forEach(e -> System.out.println(e));
	}
	
	public void test_writeFileMap() {
		PhmCSV test = new PhmCSV();
//		test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\input.csv");
		test.setPath("D:\\Java_CETC\\phm4\\src\\test\\resources\\input.csv");
		this.assertTrue(test.readMap());
		
		// out file
//		test.setPath("C:\\Users\\Admin\\Documents\\PHM\\PHM\\dataset\\output.csv");
		test.setPath("D:\\Java_CETC\\phm4\\src\\test\\resources\\output.csv");
		test.setIsAppend(false);
		long start = System.currentTimeMillis()/ 1000;
		this.assertTrue(test.writeMap());
		long finish = System.currentTimeMillis()/ 1000;
		System.out.println("Time write (sec): " + (finish - start));
	}
	
	// создание датасета для записи в файл
	public void test_DatasetCreate() {
		PhmCSV test = new PhmCSV();
		
		// создаем ключи
		List<String> keys = new ArrayList<>();
		test.createHeader();
		test.addColumn("Param1");
		test.addColumn("Param2");
		test.addColumn("Param3");
		
		// формируем значения ключей
		test.createRows();
		List<String> val = new ArrayList<>();
		// row 1
		val.add("120.00"); // Param 1 Value1
		val.add("100.00"); // Param 2 Value1
		val.add("500.01"); // Param 3 Value1
		test.addRow(val);
		
		val = new ArrayList<>();
		
		// row 2
		val.add("220.00"); // Param 1 Value2
		val.add("200.00"); // Param 2 Value2
		val.add("500.02"); // Param 3 Value2
		test.addRow(val);
		
		val =  new ArrayList<>();
		
		// row 3
		val.add("320.00"); // Param 1 Value3
		val.add("300.00"); // Param 2 Value3
		val.add("500.03"); // Param 3 Value3
		test.addRow(val);
		
		val =  new ArrayList<>();
		
		// row 4
		val.add("420.00"); // Param 1 Value4
		val.add("400.00"); // Param 2 Value4
		val.add("500.04"); // Param 3 Value4
		test.addRow(val);
		
		// получаем ключи
		keys = test.getKeys();
		
		this.assertNotNull(keys);
		
		System.out.println("Keys:");
		keys.forEach(e -> System.out.println(e));
		System.out.println("\n\r");
		
		// получаем значения ключей
		List<List<String>> values = test.getValues();
		
		this.assertNotNull(values);
		
		System.out.println("Values:");
		values.forEach(e -> System.out.println(e));
		System.out.println("\n\r");
		
		// конвертируем ключи и значения в пары ключ-значение
		test.toMap();
		List<Map<String, String>> keysValues = test.getKeysValues();
		System.out.println("Map key-value:");
		keysValues.forEach(e -> System.out.println(e));
	}
}
