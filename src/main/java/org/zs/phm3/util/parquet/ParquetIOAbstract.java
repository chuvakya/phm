package org.zs.phm3.util.parquet;

/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class ParquetIOAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(ParquetIOReader.class);
	private static final int JULIAN_DAY_OF_EPOCH = 2440588;
	
	// путь к файлу
	protected Path path;
	
	// конфигурация
	protected Configuration conf;	
	
	// схема
	protected Schema schema;
	
	// набор записей (датасет)
	protected List<GenericData.Record> recordsList;
	
	// имя схемы
	protected String schemaName;
	
	// common
	public String getPath() {
		return path.toString();
	}	
	
	public void setPath(File file) {
		this.path = new Path(file.getPath());
	}	
	
	public void setPath(String path) {
		this.path = new Path(path);
	}		
	
	// имя схемы
	public String getSchemaName() {
		return schema.getName(); 
	}
	
	// структура схемы в формате JSON
	public String getSchemaToJSON() {
		return schema.toString(true);
	}	
	
	
	
	//**********************************************
	// BEGIN SCHEMA OPERATION
	//**********************************************
	
	// возвращает для заданного ключа описание типа (LONG, DOUBLE и т.д.)
	public String getKeyType(String keyName) {
		Field key = this.schema.getField(keyName);
		if (key == null) {
			throw new IllegalArgumentException("Invalid Name key: " + keyName);
		}
		return key.schema().getType().toString();
	}
	
	// возвращает все ключи текущей схемы
	public List<String> getKeys(){
    	List<String> list = new ArrayList<>();
    	this.schema.getFields().forEach(e -> list.add(e.name()));
    	return list;
    }
	
	// возвращает имена ключей с типом
	public Map<String, String> getKeysTypes(){
		Map<String, String> keys = new LinkedHashMap<>();
		if(this.schema.isUnion()) {
			this.schema.getFields().forEach(e -> {				
					e.schema().getTypes().forEach(s -> keys.put(e.name(), s.getType().name()));
				});
		} else {
			this.schema.getFields().forEach(e -> keys.put(e.name(), e.schema().getType().name()));
		}
		return keys;
	}
	
	// проверка существования ключа в файле
	public boolean isKeyExists(String keyName) {
        for (Field field: this.schema.getFields())
        {
            if(field.name().equals(keyName)) {
            	return true;
            }
        }
        return false;
	}	
	
	// печать полей схемы
	public void printSchemaFields() {
		this.schema.getFields().forEach(e -> System.out.println(e));
	}
	
	//**********************************************
	// END SCHEMA OPERATION
	//**********************************************	
	
	
	
	//**********************************************
	// BEGIN READ VALUES
	//**********************************************
	
	// получение наборов данных по всем ключам в заданном формате
	public <T> List<List<T>> getValues(){
	    List<List<T>> values = new ArrayList<List<T>>();
	    
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();	    
        List<Field> fields = schema.getFields();
        
		for(int i = 0; i < fields.size(); i++) {
	    	List<T> val = new ArrayList<>();
			val = _getValuesByKey(fields.get(i).name());
			while (val.remove(null));
	
			values.add(val);
		}        
	    return values;
	}

	// возвращает наборы данных с ключами
	public <T> List<Map<String, T>> getValuesMapEx() {
		List<Map<String, T>> keysValues =  new ArrayList<>();
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();		
		
		for (GenericData.Record record : this.recordsList) {
			Map<String, T> values = new HashMap<>();
			for (Field field : schema.getFields()) {
				Object col = record.get(field.name());
				values.put(field.name(), (T)col);
			}
			while(values.values().remove(null));
			keysValues.add(values);
		}
		return keysValues;
	}	
	
	// получение значений по ключу в заданном формате
	public <T> List<T> _getValuesByKey(String keyName){
	    List<T> values = new ArrayList<T>();
	    for (GenericData.Record record : this.recordsList) {
	      values.add((T)record.get(keyName));
	    }
	    return values;		
	}	
	
	// возвращает набор данных только для заданного ключа
	public List<List<String>> getValuesByKey(String keyName) {
		List<List<String>> keysValues =  new ArrayList<>();
    	List<String> values = new ArrayList<>();
		values = _getValuesByKey(keyName);
		while (values.remove(null));

		keysValues.add(values);

		return keysValues;
	}
	
	// возвращает набор данных только для заданного ключа
	public List<Map<String, Object>> getValuesMapByKey(String keyName) {
		List<Map<String, Object>> keysValues =  new ArrayList<>();
		
		for (GenericData.Record record : this.recordsList) {
			Map<String, Object> values = new HashMap<>();
			for (Field field : this.schema.getFields()) {
				if (field.name() == keyName) {
					Object col = record.get(field.name());
					values.put(field.name(), col);
				}
			}
			while(values.values().remove(null));
			keysValues.add(values);
		}
		keysValues.removeIf(value -> value.isEmpty());
		
		return keysValues;
	}
	
	// получение значений по ключу в формате STRING
	public List<String> getValuesByKeyAsString(String keyName){
		List<String> data = new ArrayList<>();
        
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();
        
        schema.getFields().forEach( e -> { 
        	if(e.name() == keyName) { 
	        	for (int i = 0; i < this.recordsList.size(); i++) {  
	        		data.add(String.valueOf(this.recordsList.get(i).get(keyName) ) );
	        	}  
        	}  
        } );        
		return data;		
	}
	
	// получение значений по ключу в формате DOUBLE
	public List<Double> getValuesByKeyAsDouble(String keyName){
		List<Double> data = new ArrayList<>();
        
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();
        
        schema.getFields().forEach( e -> { 
        	if(e.name() == keyName) { 
	        	for (int i = 0; i < this.recordsList.size(); i++) {  
	        		
	            	if( this.recordsList.get(i).get(keyName) instanceof Long) {
	            		Long lv = (Long)this.recordsList.get(i).get(keyName);
            			data.add(lv.doubleValue());
	            	} else if ( this.recordsList.get(i).get(keyName) instanceof Integer) {
	            		Integer iv = (Integer)recordsList.get(i).get(keyName);
	            		data.add(iv.doubleValue());
	            	} else if ( this.recordsList.get(i).get(keyName) instanceof Boolean) {
	            		Boolean bv = (Boolean)this.recordsList.get(i).get(keyName);
	            		data.add(bv?1.0:0.0);
	            	} else if ( this.recordsList.get(i).get(keyName) instanceof Float) {
	            		Float fv = (Float)this.recordsList.get(i).get(keyName);
	            		data.add(fv.doubleValue());
	            	} else {
	            		data.add((Double)this.recordsList.get(i).get(keyName));
	            	}	        		
	        	}  
        	}  
        } );        
		return data;		
	}
	
	// получение значений в формате STRING
	public List<List<String>> getValuesAsString(){
		List<List<String>> data = new ArrayList<>();
        
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();
        
        int columnNumber = schema.getFields().size();
        
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
            data.add(commonRecord);
        }
		return data;
	}
	
	// получение значений в формате DOUBLE
	public List<List<Double>> getValuesAsDouble(){
		List<List<Double>> data = new ArrayList<>();
        
        GenericData.Record firstRecord = this.recordsList.get(0);
        Schema schema = firstRecord.getSchema();
        
        int columnNumber = schema.getFields().size();
        
        for (int i = 0; i < this.recordsList.size(); i++) {
            GenericData.Record record = this.recordsList.get(i);
            List<Double> commonRecord = new ArrayList<>();
            for (int j = 0; j < columnNumber; j++) {
                if (record.get(j) == null){
                	commonRecord.add(0.0); 
                }else{
                	if( record.get(j) instanceof Long) {
                		Long lv = (Long)record.get(j);
                		commonRecord.add(lv.doubleValue());
                	} else if ( record.get(j) instanceof Integer) {
                		Integer iv = (Integer)record.get(j);
                		commonRecord.add(iv.doubleValue());
                	} else if ( record.get(j) instanceof Boolean) {
                		Boolean bv = (Boolean)record.get(j);
                		commonRecord.add(bv?1.0:0.0);
                	} else if ( record.get(j) instanceof Float) {
                		Float fv = (Float)record.get(j);
                		commonRecord.add(fv.doubleValue());
                	} else {
                		commonRecord.add((Double)record.get(j));
                	}
                }
            }
            data.add(commonRecord);
        }
		return data;		
	}

	//**********************************************
	// END READ VALUES
	//**********************************************
	
	
	//**********************************************
	// BEGIN PRINT OPERATION
	//**********************************************
	
	// печать данных в консоль
	public void printDataSetList() {
		for(GenericData.Record item : this.recordsList) {
			for(int i = 0; i < item.getSchema().getFields().size(); i++) {
				System.out.println( item.get(i) );
			}
		}
	}	
	
//	private static void printGroupKey(Group g) {
//	    int fieldCount = g.getType().getFieldCount();
//	    for (int field = 0; field < fieldCount; field++) {
//	      int valueCount = g.getFieldRepetitionCount(field);
//
//	      Type fieldType = g.getType().getType(field);
//	      String fieldName = fieldType.getName();
//	 
//	      for (int index = 0; index < valueCount; index++) {
//	        if (fieldType.isPrimitive()) {
//	          System.out.println(fieldName + " " + g.getValueToString(field, index));
//	        }
//	      }
//	    }
//	    System.out.println("");
//	}	
//	
//	// печать данных из файла в консоль
//	protected void print() {
//	    
//	    try {
//	      ParquetMetadata readFooter = ParquetFileReader.readFooter(conf, path, ParquetMetadataConverter.NO_FILTER);
//	      MessageType schema = readFooter.getFileMetaData().getSchema();
//	      ParquetFileReader r = new ParquetFileReader(conf, path, readFooter);
//	      
//	      PageReadStore pages = null;
//	      
//	      try {
//	        while (null != (pages = r.readNextRowGroup())) {
//	          final long rows = pages.getRowCount();
//	          System.out.println("Number of rows: " + rows);
//	 
//	          final MessageColumnIO columnIO = new ColumnIOFactory().getColumnIO(schema);
//	          final RecordReader recordReader = columnIO.getRecordReader(pages, new GroupRecordConverter(schema));
//	          for (int i = 0; i < rows; i++) {
//	            final Group g = (Group)recordReader.read();
//	            printGroupKey(g);
//	          }
//	        }
//	      } finally {
//	        r.close();
//	      }
//	    } catch (IOException e) {
//	      System.out.println("Error reading parquet file.");
//	      e.printStackTrace();
//	    }		
//	}	
	
	
	//**********************************************
	// BEGIN OTHERS OPERATION
	//**********************************************	
	// конвертация TIMESTAMP в INT96
	public Binary toINT96(String timestamp) {
		Binary result = Binary.EMPTY;
		
		final long NANOS_PER_HOUR = TimeUnit.HOURS.toNanos(1);
		final long NANOS_PER_MINUTE = TimeUnit.MINUTES.toNanos(1);
		final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);

		// Parse date
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		
		try {
			cal.setTime(parser.parse(timestamp));
		} catch (ParseException e) {
			logger.warn("Parse timestamp value error.");
			e.printStackTrace();
		}

		// Calculate Julian days and nanoseconds in the day
		LocalDate dt = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
		int julianDays = (int) JulianFields.JULIAN_DAY.getFrom(dt);
		long nanos = (cal.get(Calendar.HOUR_OF_DAY) * NANOS_PER_HOUR)
		           + (cal.get(Calendar.MINUTE) * NANOS_PER_MINUTE)
		           + (cal.get(Calendar.SECOND) * NANOS_PER_SECOND);

		// Write INT96 timestamp
		byte[] timestampBuffer = new byte[12];
		ByteBuffer buf = ByteBuffer.wrap(timestampBuffer);
		buf.order(ByteOrder.LITTLE_ENDIAN).putLong(nanos).putInt(julianDays);

		// This is the properly encoded INT96 timestamp
		result = Binary.fromReusedByteArray(timestampBuffer);		
		
		return result;
	}
	
	// конвертация INT96 в LONG
	public long toLong(Binary val) {
		  byte[] readBuffer = val.getBytes();
		  if ( readBuffer.length != 12 ) {
			logger.warn("Invalid length INT96");
		    throw new RuntimeException( "Invalid byte array length for INT96" );
		  }

		  long timeOfDayNanos =
		      ( ( (long) readBuffer[7] << 56 ) + 
		    	( (long) ( readBuffer[6] & 255 ) << 48 )
		      + ( (long) ( readBuffer[5] & 255 ) << 40 ) + ( (long) ( readBuffer[4] & 255 ) << 32 )
		          + ( (long) ( readBuffer[3] & 255 ) << 24 ) + ( ( readBuffer[2] & 255 ) << 16 )
		          + ( ( readBuffer[1] & 255 ) << 8 ) + ( ( readBuffer[0] & 255 ) << 0 ) );

		  int julianDay =
		      ( (int) ( readBuffer[11] & 255 ) << 24 ) + ( ( readBuffer[10] & 255 ) << 16 )
		          + ( ( readBuffer[9] & 255 ) << 8 ) + ( ( readBuffer[8] & 255 ) << 0 );

		  return ( julianDay - JULIAN_DAY_OF_EPOCH) * 24L * 60L * 60L * 1000L + timeOfDayNanos / 1000000;		
	}
	
	// конвертация INT96 в TIMESTAMP
	public Timestamp toTimeStamp(Binary val) {
		return new Timestamp(toLong(val));
	}
	
	// конвертация LONG в TIMESTAMP
	public Timestamp toTimeStamp(Long val) {
		return new Timestamp(val);
	}
	
	
	//**********************************************
	// END OTHERS OPERATION
	//**********************************************	
	
	
	
	// abstract methods
	public abstract List<Map<String, Object>> getValuesMap();

}
