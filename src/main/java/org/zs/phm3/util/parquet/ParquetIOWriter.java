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
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.hadoop.ParquetFileWriter.Mode;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.zs.phm.util.parquet.csv.PhmCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.zs.phm3.util.parquet.csv.PhmCSV;


public class ParquetIOWriter extends ParquetIOAbstract {

	private static final Logger logger = LoggerFactory.getLogger(ParquetIOWriter.class);
	
	// режимы сжатия файла
	public static final int COMPRESS_SNAPPY = CompressionCodecName.SNAPPY.ordinal();
	public static final int COMPRESS_GZIP = CompressionCodecName.GZIP.ordinal();
	public static final int COMPRESS_LZ4 = CompressionCodecName.LZ4.ordinal();
	public static final int COMPRESS_LZO = CompressionCodecName.LZO.ordinal();
	public static final int COMPRESS_UNCOMPRESSED = CompressionCodecName.UNCOMPRESSED.ordinal();
	
	// версия файла
	public static final int VERSION_PARQUET_1 = WriterVersion.PARQUET_1_0.ordinal();
	public static final int VERSION_PARQUET_2 = WriterVersion.PARQUET_2_0.ordinal();
	
	// режим записи файла
	public static final int MODE_CREATE = Mode.CREATE.ordinal();
	public static final int MODE_OWERWRITE = Mode.OVERWRITE.ordinal();
	
	// типы данных
	public static final int TYPE_STRING = Schema.Type.STRING.ordinal();
	public static final int TYPE_INT32 = Schema.Type.INT.ordinal();
	public static final int TYPE_LONG = Schema.Type.LONG.ordinal(); // INT64
	public static final int TYPE_DOUBLE = Schema.Type.DOUBLE.ordinal();
	public static final int TYPE_FLOAT = Schema.Type.FLOAT.ordinal();
	public static final int TYPE_BOOLEAN = Schema.Type.BOOLEAN.ordinal();
	
	public static final int TYPE_RECORD = Schema.Type.RECORD.ordinal();
	
	// параметры схемы по умолчанию
	protected static final String SCHEMA_NAME_DEFAULT = "PHM_SCHEMA";
	protected static final String SCHEMA_NAMESPACE_DEFAULT = "PHM";

	// тип кодека сжатия данных
	private int comperssCodec;
	// размер буфера для группы записей (по умолчанию 128 MB)
	private int rowGroupSize;
	// размер буфера для страницы (по умолчанию 1 MB)
	private int pageSize;
	// версия релиза
	private int version;
	// режим записи (новый или перезапись)
	private int writeMode;
	
	// имя пространства
	private String namespaceName;

	
	public ParquetIOWriter(String path) {
		this.path = new Path(path);
		this.conf = new Configuration();
		this.comperssCodec = COMPRESS_SNAPPY;
		this.rowGroupSize = ParquetWriter.DEFAULT_BLOCK_SIZE;
		this.pageSize = ParquetWriter.DEFAULT_PAGE_SIZE;
		this.version = VERSION_PARQUET_1;
		this.writeMode = MODE_CREATE;
	}

	public ParquetIOWriter() {
		super();
		this.conf = new Configuration();
		this.comperssCodec = COMPRESS_SNAPPY;
		this.rowGroupSize = ParquetWriter.DEFAULT_BLOCK_SIZE;
		this.pageSize = ParquetWriter.DEFAULT_PAGE_SIZE;
		this.version = VERSION_PARQUET_1;
		this.writeMode = MODE_CREATE;
	}	
	
	public int getComperssCodec() {
		return comperssCodec;
	}

	public void setComperssCodec(int comperssCodec) {
		this.comperssCodec = comperssCodec;
	}
	
	public int getRowGroupSize() {
		return rowGroupSize;
	}

	public void setRowGroupSize(int rowGroupSize) {
		this.rowGroupSize = rowGroupSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getWriteMode() {
		return writeMode;
	}

	public void setWriteMode(int writeMode) {
		this.writeMode = writeMode;
	}	
	
	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		if (namespaceName == null || namespaceName == "") {
			this.namespaceName = this.SCHEMA_NAMESPACE_DEFAULT;
		} else {
			this.namespaceName = namespaceName;
		}
	}

	public void setSchemaName(String schemaName) {
		if (schemaName == null || schemaName == "") {
			this.schemaName = this.SCHEMA_NAME_DEFAULT;
		} else {
			this.schemaName = schemaName;
		}
	}	
	

	
	//**********************************************
	// BEGIN READ VALUES
	//**********************************************
	
	// возвращает наборы данных по всем ключам 
	public List<List<String>> getValues(){
		List<List<String>> keysValues =  new ArrayList<>();
		List<Field> fields = this.schema.getFields();
		
		for(int i = 0; i < fields.size(); i++) {
	    	List<String> values = new ArrayList<>();
			values = _getValuesByKey(fields.get(i).name());
			while (values.remove(null));

			keysValues.add(values);
		}
		return keysValues;
	}
	
	// возвращает наборы данных по всем ключам
	public List<Map<String, Object>> getValuesMap() {
		List<Map<String, Object>> keysValues =  new ArrayList<>();
		
		for (GenericData.Record record : this.recordsList) {
			Map<String, Object> values = new HashMap<>();
			for (Field field : this.schema.getFields()) {
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
	// BEGIN ADD VALUES
	//**********************************************
	
    // добавляет один ключ с набором значений для заданного формата
	public void addValues(Map<String, ?> data){
        GenericData.Record record = new GenericData.Record(this.schema);
        
        for(Map.Entry<String, ?> item : data.entrySet()){
        	record.put(item.getKey(), item.getValue());
        }
        this.recordsList.add(record);
    } 	    
    
    // добавляет много ключей с наборами значений для заданного формата
	public void addValues(List<Map<String, ?>> data){
        GenericData.Record record = null;
        
        for(int i = 0; i < data.size(); i++) {
        	record = new GenericData.Record(this.schema);

	        for(Map.Entry<String, ?> item : data.get(i).entrySet()){
	        	record.put(item.getKey(), item.getValue());
	        }
	        this.recordsList.add(record);
        }	
	}	
	
	// добавляет один ключ с набором значений для заданного формата
	public void addValuesByKey(String keyName, List<?> values){
		GenericData.Record record = new GenericData.Record(this.schema);
        record.put(keyName, values);
        
        this.recordsList.add(record);
    }		
	
	// добавляет один ключ с набором значений в формате DOUBLE
	public void addValuesByKeyDouble(String keyName, List<Double> values){
		GenericData.Record record = new GenericData.Record(this.schema);
        record.put(keyName, values);
        
        this.recordsList.add(record);
    }	
	
	// добавляет один ключ с набором значений в формате STRING
	public void addValuesByKeyString(String keyName, List<String> values){
		GenericData.Record record = new GenericData.Record(this.schema);
        record.put(keyName, values);
        
        this.recordsList.add(record);
    }	

	// добавляет один ключ с набором значений в формате OBJECT
	public void addValuesByKeyObject(String keyName, List<Object> values){
		GenericData.Record record = new GenericData.Record(this.schema);
        record.put(keyName, values);
        
        this.recordsList.add(record);
    }	

	// один ключ с набором значений в формате DOUBLE
	public void addValuesByKeysDouble(Map<String, Double> data){
        GenericData.Record record = new GenericData.Record(this.schema);
        
        for(Map.Entry<String, Double> item : data.entrySet()){
        	record.put(item.getKey(), item.getValue());
        }
        this.recordsList.add(record);
    }
	
    // добавляет один ключ с набором значений в формате STRING
	public void addValuesByKeysString(Map<String, String> data){
        GenericData.Record record = new GenericData.Record(this.schema);
        
        for(Map.Entry<String, String> item : data.entrySet()){
        	record.put(item.getKey(), item.getValue());
        }
        this.recordsList.add(record);
    } 
	
    // добавляет один ключ с набором значений в формате OBJECT
	public void addValuesByKeysObject(Map<String, Object> data){
        GenericData.Record record = new GenericData.Record(this.schema);
        
        for(Map.Entry<String, Object> item : data.entrySet()){
        	record.put(item.getKey(), item.getValue());
        }
        this.recordsList.add(record);
    } 	
    
	// добавляет много ключей с наборами значений в формате STRING
    public void addValuesByKeysString(List<Map<String, String>> data){
        GenericData.Record record = null;
        
        for(int i = 0; i < data.size(); i++) {
        	record = new GenericData.Record(this.schema);
        	
	        for(Map.Entry<String, String> item : data.get(i).entrySet()){
	        	record.put(item.getKey(), item.getValue());
	        }
	        this.recordsList.add(record);
        }
    }
    
    // добавляет много ключей с наборами значений в формате DOUBLE
    public void addValuesByKeysDouble(List<Map<String, Double>> data){
        GenericData.Record record = null;
        
        for(int i = 0; i < data.size(); i++) {
        	record = new GenericData.Record(this.schema);
        	
	        for(Map.Entry<String, Double> item : data.get(i).entrySet()){
	        	record.put(item.getKey(), item.getValue());
	        }
	        this.recordsList.add(record);
        }
    } 
    
    // добавляет много ключей с наборами значений в формате OBJECT
    public void addValuesByKeysObject(List<Map<String, Object>> data){
        GenericData.Record record = null;
        
        for(int i = 0; i < data.size(); i++) {
        	record = new GenericData.Record(this.schema);
        	
	        for(Map.Entry<String, Object> item : data.get(i).entrySet()){
	        	record.put(item.getKey(), item.getValue());
	        }
	        this.recordsList.add(record);
        }
    }   

	//**********************************************
	// END ADD VALUES
	//**********************************************    
    
    
    
	//**********************************************
	// BEGIN SCHEMA OPERATION
	//**********************************************
    
    // создание новой схемы для хранения ВСЕХ значений в заданном формате 
	public void createSchema(List<String> keys, Integer type) {
    	String schemaName = (this.schemaName == null) ? this.SCHEMA_NAME_DEFAULT : this.schemaName;
    	String doc = "Data of " + schemaName;
    	String namespaceName = (this.namespaceName == null) ? this.SCHEMA_NAMESPACE_DEFAULT : this.namespaceName;
    	Schema columnSchema;
    	Field field;
    	ArrayList<Field> schemaFields;
    	
    	schemaFields = new ArrayList<>();
    	
    	for(String tcol : keys) {
    		columnSchema = Schema.create(Schema.Type.values()[type]);
    		field = new Field(tcol, columnSchema, doc, (Object)null);
    		schemaFields.add(field);
    	}

    	this.schema = Schema.createRecord(schemaName, doc, namespaceName, false);
    	this.schema.setFields(schemaFields);
    	
    	// инициализация набора значений
    	this.recordsList = new ArrayList<>();
    }
	
	// создание новой схемы для хранения значений в заданных форматах (должно быть соответствие порядка ключей и их типов)
	public void createSchema(List<String> keys, ArrayList<Integer> types) {
    	String schemaName = (this.schemaName == null) ? this.SCHEMA_NAME_DEFAULT : this.schemaName;
    	String doc = "Data of " + schemaName;
    	String namespaceName = (this.namespaceName == null) ? this.SCHEMA_NAMESPACE_DEFAULT : this.namespaceName;
    	Schema columnSchema;
    	Field field;
    	ArrayList<Field> schemaFields;
    	
    	schemaFields = new ArrayList<>();
    	
    	// проверка соответствия размеров массивов ключей и типов
    	if (keys.size() != types.size()) {
    		logger.warn("Input and type mismatch!");
    		throw new IllegalArgumentException();
    	}
    	
    	for(int i = 0; i < keys.size(); i++) {
    		columnSchema = Schema.create(Schema.Type.values()[types.get(i)]);
    		field = new Field(keys.get(i), columnSchema, doc, (Object)null);
    		schemaFields.add(field);
    	}
    	this.schema = Schema.createRecord(schemaName, doc, namespaceName, false);
    	this.schema.setFields(schemaFields);	
    	
    	// инициализация набора значений
    	this.recordsList = new ArrayList<>();
	}
	
	// тоже самое плюс задаются описания для каждого ключа
	public void createSchema(List<String> keys, List<Integer> types, ArrayList<String> keysDescription) {
    	String schemaName = (this.schemaName == null) ? this.SCHEMA_NAME_DEFAULT : this.schemaName;
    	String doc = "Data of " + schemaName;
    	String namespaceName = (this.namespaceName == null) ? this.SCHEMA_NAMESPACE_DEFAULT : this.namespaceName;
    	Schema columnSchema;
    	Field field;
    	ArrayList<Field> schemaFields;
    	
    	schemaFields = new ArrayList<>();
    	
    	// проверка соответствия размеров массивов ключей и типов
    	if (keys.size() != types.size()) {
    		logger.warn("Input and type mismatch!");
    		throw new IllegalArgumentException();
    	}
    	
    	for(int i = 0; i < keys.size(); i++) {
    		columnSchema = Schema.create(Schema.Type.values()[types.get(i)]);
    		field = new Field(keys.get(i), columnSchema, keysDescription.get(i), (Object)null);
    		schemaFields.add(field);
    	}
    	this.schema = Schema.createRecord(schemaName, doc, namespaceName, false);
    	this.schema.setFields(schemaFields);	
    	
    	// инициализация набора значений
    	this.recordsList = new ArrayList<>();
	}	
	
	// создание новой схемы из структуры JSON
	public void createSchema(String json) {
		schema = new Schema.Parser().parse(json);
    	// инициализация набора значений
    	recordsList = new ArrayList<>();
	}
	
	// добавляет ключ заданного типа в текущую схему
	public void addKeySchema(String keyName, Integer type) {
		if (this.schema == null) {return;}
		if (this.schema.getFields().size() > 0) {

			Schema newColumnSchema;
			newColumnSchema = Schema.create(Schema.Type.values()[type]);
			
			List<Field> oldSchema = this.schema.getFields().stream()
		            .map(field -> new Field(field.name(), field.schema(), field.doc(), field.defaultVal()))
		            .collect(Collectors.toList());
			
			// добавляем новый ключ в схему
			oldSchema.add(new Field(keyName, newColumnSchema));
			
			Schema newSchema = Schema.createRecord(this.schema.getName(), this.schema.getDoc(), this.schema.getNamespace(), false, oldSchema);	
			this.schema = newSchema;
			
			// очищаем набор значений
			this.recordsList.clear();
		}
	}
	
	// удаляет ключ из текущей схемы
	public void delKeySchema(String keyName) {
		if (this.schema == null) {return;}
		if (this.schema.getFields().size() > 0) {
			
			List<Field> oldSchema = this.schema.getFields().stream()
		            .map(field -> new Field(field.name(), field.schema(), field.doc(), field.defaultVal()))
		            .collect(Collectors.toList());
			
			// удаляем ключ из схемы
			oldSchema.removeIf(name -> name.name() == keyName);

			Schema newSchema = Schema.createRecord(this.schema.getName(), this.schema.getDoc(), this.schema.getNamespace(), false, oldSchema);	
			this.schema = newSchema;
			
			// очищаем набор значений
			this.recordsList.clear();
		}
	}


	//**********************************************
	// END SCHEMA OPERATION
	//**********************************************
	
    
    
    
	//**********************************************
	// BEGIN FILE OPERATION
	//**********************************************
    
    // записывает схемы и наборы данных в файл (PARQUET)
    public void writeData() {
    	writeToFile(this.recordsList, this.schema);
    }
	
    public void fromCSV(String csvPath) {
    	PhmCSV reader = new PhmCSV(csvPath);
    	if(reader.read()) {
    		// получаем ключи
    		List<String> keys = reader.getKeys();
    		// создаем новую схему
    		createSchema(keys, TYPE_DOUBLE);
    		// загружаем значения
    		List<List<String>> values = reader.getValues();
    		
    		GenericData.Record record = new GenericData.Record(this.schema);
    		
    		for (int i = 0; i < keys.size(); i++) {
    			List<String> vals = new ArrayList<>();
    			
    			for(int j = 0; j< values.size(); j++) {
    				vals.add(values.get(j).get(i) );
    			}
                record.put(keys.get(i), vals);
    		}
    		this.recordsList.add(record);
    	}
    }
    
    
    // сохраняем в PARQUET файл
    private void writeToFile(List<GenericData.Record> recordList, Schema schema) {
        ParquetWriter<GenericData.Record> writer = null;

        try {
            writer = AvroParquetWriter.
                <GenericData.Record>builder(this.path)
                .withRowGroupSize(this.rowGroupSize)
                .withPageSize(this.pageSize)
                .withSchema(schema)
                .withConf(this.conf)
                .withCompressionCodec(CompressionCodecName.values()[this.comperssCodec])
                .withValidation(false)
                .withDictionaryEncoding(false)
                .withWriterVersion(WriterVersion.values()[this.version])
                .withWriteMode(Mode.values()[this.writeMode])
                .build();
            // writing records
            for (GenericData.Record record : recordList) {
                writer.write(record);
            }
            
        }catch(IOException e) {
        	logger.warn("Can't save to file: " + e.getMessage());
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                	logger.warn("Can't close file: " + e.getMessage());
                }
            }
        }     	
    }

    
	//**********************************************
	// END FILE OPERATION
	//**********************************************
    
    
	@Override
	public String toString() {
		return "ParquetIOWriter [schema=" + schema + ", comperssCodec=" + comperssCodec + ", rowGroupSize="
				+ rowGroupSize + ", pageSize=" + pageSize + ", version=" + version + ", writeMode=" + writeMode
				+ ", namespaceName=" + namespaceName + ", schemaName=" + schemaName + "]";
	}

}
