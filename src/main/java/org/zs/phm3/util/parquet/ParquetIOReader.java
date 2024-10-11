package org.zs.phm3.util.parquet;

/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.util.parquet.csv.PhmCSV;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.InputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.zs.phm.util.parquet.csv.PhmCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.zs.phm3.util.parquet.csv.PhmCSV;

public class ParquetIOReader extends ParquetIOAbstract {

	private static final Logger logger = LoggerFactory.getLogger(ParquetIOReader.class);

	
	public ParquetIOReader(String path) {
		this.path = new Path(path);
		this.conf = new Configuration();
	}

	public ParquetIOReader() {
		super();
		this.conf = new Configuration();
	}
	
	// читаем данные из файла
	public void readData() {
		recordsList = new ArrayList<>();
		this.readFromFile();
		
        if(recordsList == null)
        {
        	logger.warn("Dataset is null! ");
        }
        
        if (recordsList.isEmpty()) {
        	logger.warn("Dataset is empty!");
        }
        // получаем структуру схемы
        schema = recordsList.get(0).getSchema();
	}
	
	
	
	//**********************************************
	// BEGIN READ VALUES
	//**********************************************
	
	// возвращает наборы данных с ключами
	public List<Map<String, Object>> getValuesMap() {
		List<Map<String, Object>> keysValues =  new ArrayList<>();
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();		
		
		for (GenericData.Record record : this.recordsList) {
			Map<String, Object> values = new HashMap<>();
			for (Field field : schema.getFields()) {
				Object col = record.get(field.name());
				values.put(field.name(), col);
			}
			while(values.values().remove(null));
			keysValues.add(values);
		}
		return keysValues;
	}	
	

	//**********************************************
	// END READ VALUES
	//**********************************************
	

	
	//**********************************************
	// BEGIN FILE OPERATION
	//**********************************************	
	
	// конвертация в файл CSV (в новый файл)
	public boolean toCSV(String csvFile) {
		return writeCSV(csvFile, false);
	}
	
	// конвертация в файл CSV (добавление записей, если файл существует)
	public boolean toCSVAppend(String csvFile) {
		return writeCSV(csvFile, true);
	}	
	
	private boolean writeCSV(String csvFile, Boolean isAppend) {
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();
        List<String> keysList = new ArrayList<>();
        
        for (Field field: schema.getFields())
        {
            String keyName = field.name();
            keysList.add(keyName);
        }
        
        int columnNumber = keysList.size();
        
        List<List<String>> values = new ArrayList<>();
        for (int i = 0; i < this.recordsList.size(); i++) {
            GenericData.Record record = this.recordsList.get(i);
            List<String> commonRecord = new ArrayList<>();
            for (int j = 0; j < columnNumber; j++) {
                if (record.get(j) == null){
                    commonRecord.add("NULL");
                }else{
                    commonRecord.add(String.valueOf(record.get(j)));
                }
            }
            values.add(commonRecord);
        }
        
        // пишем в CSV файл
        PhmCSV writer = new PhmCSV(csvFile);
        writer.setIsAppend(isAppend);
        return writer.writeFrom(keysList, values);		
	}
	
	
	// чтение данных из файла
	private void readFromFile(){
        try{
            //conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName() );
            //conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName() );
            InputFile inputFile = HadoopInputFile.fromPath(this.path, this.conf);
            org.apache.parquet.hadoop.ParquetReader<GenericData.Record> reader =
                    AvroParquetReader.<GenericData.Record>builder(inputFile)
                            .disableCompatibility()
                            .withDataModel(GenericData.get())
                            .withConf(this.conf)
                            .build();
            GenericData.Record record;
            while((record = reader.read()) != null){
            	this.recordsList.add(record);
            }
        }catch(Exception e){
        	logger.warn("Failed to read the file! The exception throws is:  " + e.getMessage());
        }		
	}

	
	//**********************************************
	// END FILE OPERATION
	//**********************************************	
	
	
	@Override
	public String toString() {
		return "ParquetIOReader [path=" + path + ", conf=" + conf + "]";
	}	
	
	
}
