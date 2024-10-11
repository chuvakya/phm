package org.zs.phm3.util.parquet.csv;

/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class PhmCSV {

	private static final Logger logger = LoggerFactory.getLogger(PhmCSV.class);
	// символ разделитель значений
	public static final String DEFAULT_DELEMITER = ",";
	// символ обертка
	private static final char DEFAULT_QUOTE = '"';
	
	// путь к файлу
	private String path;
	
	// разделитель
	private String delimiter;
	
	// признак добавления данных в файл, если файл существует, иначе будет создан новый файл
	private Boolean isAppend;
	
	// список ключей
	private List<String> keys;
	
	// список значений
	private List<List<String>> values;
	
	// список ключ-значение
	private List<Map<String, String>> keysValues;
	
	// число строк
	private Long rowsCount;
	
	// число столбцов
	private Long colsCount;
	
	public PhmCSV() {
		super();
		this.isAppend = false;
		this.rowsCount = 0L;
		this.colsCount = 0L;
	}

	public PhmCSV(String path) {
		this.path = path;
		this.isAppend = false;
		this.rowsCount = 0L;
		this.colsCount = 0L;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void setPath(File file) {
		this.path = file.getPath();
	}		

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public Boolean getIsAppend() {
		return isAppend;
	}

	public void setIsAppend(Boolean isAppend) {
		this.isAppend = isAppend;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<String> keys) {
		this.keys = keys;
	}

	public List<List<String>> getValues() {
		return values;
	}

	public void setValues(List<List<String>> values) {
		this.values = values;
	}
	
	public List<Map<String, String>> getKeysValues() {
		return keysValues;
	}

	public void setKeysValues(ArrayList<Map<String, String>> keysValues) {
		this.keysValues = keysValues;
	}

	public Long getRowsCount() {
		if (keys != null && !keys.isEmpty()) {
			rowsCount = Long.valueOf(keys.size());
		} else if (keysValues != null && !keysValues.isEmpty()) {
			List<String> keys = new ArrayList(this.keysValues.get(0).keySet());
			rowsCount = Long.valueOf(keys.size());
		}
		return rowsCount;
	}

	public Long getColsCount() {
		if(values != null && !values.isEmpty()) {
			colsCount = Long.valueOf(values.size());
		} else if (keysValues != null && !keysValues.isEmpty()) {
			colsCount = Long.valueOf(keysValues.size());
		}
		return colsCount;
	}
	
	//*************************************************************
	// BEGIN WRITE OPERATION
	//*************************************************************
	
	// записать в файл
	public boolean writeFrom(List<String> keys, List<List<String>> values) {
		this.keys = keys;
		this.values = values;
		return write();
	}
	
	public boolean write(){
		
        String keySplit = getDelimiter();
        
        if(keySplit == null) {
        	keySplit = DEFAULT_DELEMITER;
        }	
		try{
            PrintWriter out = new PrintWriter(new FileWriter(path, this.isAppend));
            if (this.values.size() == 0) {
                out.write("");
                return true;
            }
            
            int colNumber = this.keys.size();
            
            // запись шапки
            if (!this.isAppend) {
	            List<String> keysList = this.keys;
	            
	            for (int i = 0; i < colNumber; i++) {
	                if (i == (colNumber - 1)){
	                    out.println(keysList.get(i));
	                }else{
	                    out.print(keysList.get(i));
	                    out.print(keySplit);
	                }
	            }
            }

            // запись значений
            for (List<String> val: this.values) {
                for (int i = 0; i < colNumber; i++) {
                    if (i == (colNumber - 1)) {
                        if (val.get(i) == null)
                            out.println("NULL");
                        else {
                                if (val.get(i).toString().contains(keySplit)) {
                                    out.println("\"" + val.get(i) + "\"");
                                }else{
                                    out.println(val.get(i));
                                }
                             }
                    }else{
                        if (val.get(i) == null){
                            out.print("NULL");
                        }
                        else{
                            if (val.get(i).toString().contains(keySplit)){
                                out.print("\"" + val.get(i) + "\"");
                            }else{
                                out.print(val.get(i));
                            }
                        }
                        out.print(keySplit);
                    }
                }
            }
            out.flush();
            out.close();
            return true;
        }catch(Exception e){
            logger.warn("Can't write file! " + e.getMessage());
            return false;
        }
    }
	
	// записать в файл из карты key-value
	public boolean writeFrom(List<Map<String, String>> keysValues) {
		this.keysValues = keysValues;
		return writeMap();
	}
	
	public boolean writeMap(){
		String keySplit = getDelimiter();
		Writer out = null;
		CSVPrinter writer = null;
		
		if(keySplit == null) {
        	keySplit = DEFAULT_DELEMITER;
        }	
		
		// получаем ключи
		List<String> keys = new ArrayList(this.keysValues.get(0).keySet());
		String header = String.join(keySplit, keys);
		
		try
		{
			out = new BufferedWriter(new FileWriter(path, this.isAppend));
			writer = new CSVPrinter(out, CSVFormat.DEFAULT.withQuote(null).withDelimiter(keySplit.charAt(0)));
			
			// записываем шапку
	        if(!this.isAppend && header != null){
	        	writer.printRecord(header);
	        } 
	        
	        // записываем значения
	        for(int i = 0; i < this.keysValues.size(); i++) {
	        	Map<String, String> val = this.keysValues.get(i);
	        	writer.printRecord(val.values());
	        }
	        //writer.flush();
	        writer.close();
	        out.close();
	        return true;    
		 } catch(Exception e) {
	            logger.warn("Can't write file! " + e.getMessage());
	            return false;
		 } finally {
	        try {
		          if (writer != null) { writer.close(); }
		          if (out != null) { out.close(); }
		        }
		        catch (IOException ex) {
		        	logger.warn("Can't close file! " + ex.getMessage());
		        	return false;
		        }
		}
	}
	
	//*************************************************************
	// END WRITE OPERATION
	//*************************************************************
	
	
	
	//*************************************************************
	// BEGIN READ OPERATION
	//*************************************************************
	
	// прочиать из файла в список
	public boolean read() {
        BufferedReader in = null;
        String line = "";
        String keySplit = getDelimiter();
        boolean isFirstLine = true;
        
        if(keySplit == null) {
        	keySplit = DEFAULT_DELEMITER;
        }

        try {

        	in = new BufferedReader(new FileReader(path));
        	this.values = new ArrayList<>();
        	
            while ((line = in.readLine()) != null) {
            	
            	if (!isFirstLine) {
            		// загружаем значения
            		List<String> list = new ArrayList<String>(Arrays.asList(line.split(keySplit)));//parseLine(line, DELEMITER_DEFAULT.charAt(0), DEFAULT_QUOTE);
            		this.values.add(list);
            		//}
            	} else {
                    // загружаем ключи
            		this.keys = new ArrayList<String>(Arrays.asList(line.split(keySplit)));
            		isFirstLine = false;
            	}
            }
            in.close();
    		return true;
        } catch (Exception e) {
            logger.warn("Can't read file! " + e.getMessage());
            return false;
        } finally {
            if (in != null) {
                try {
                	in.close();
                } catch (Exception e) {
                	logger.warn("Can't close file! " + e.getMessage());
                    return false;
                }
            }
        }
	}
	
	// прочитать из файла в карту key-value
	public boolean readMap() {
		Reader reader = null;
        String keySplit = getDelimiter();
        
        if(keySplit == null) {
        	keySplit = DEFAULT_DELEMITER;
        }		
        this.keysValues = new ArrayList<Map<String, String>>();

	    try {
	    	reader = new BufferedReader(new FileReader(path));
	        //CSVParser records = CSVFormat.DEFAULT.withHeader().parse(iCvs);
	        CSVParser records = CSVFormat.DEFAULT.withHeader().withDelimiter(keySplit.charAt(0)).parse(reader);
	        
	        for (CSVRecord record : records) {
	        	this.keysValues.add((Map<String, String>)record.toMap());
	        }
	        records.close();
	        reader.close();
	        return true;
	      }
	      catch (IOException ex) {
	    	  logger.warn("Can't read file! " + ex.getMessage());
	    	  return false;
	      }
	      finally {
	        try {
	          if (reader != null) { reader.close(); }
	        }
	        catch (IOException ex) {
	        	logger.warn("Can't close file! " + ex.getMessage());
	        	return false;
	        }
	      }
	}	
	
	//*************************************************************
	// END READ OPERATION
	//*************************************************************
	

	
	

	//*************************************************************
	// BEGIN COMMON METHODS
	//*************************************************************
	
	// создание заголовка
    public void createHeader() {
    	this.keys = new ArrayList<>();
    }
    
    // добавление столбца
    public void addColumn(String columnName) {
    	if (this.keys != null) {
    		this.keys.add(columnName);
    	}
    }
	
    // создание набора записей
    public void createRows() {
    	this.values = new ArrayList<List<String>>(); //new ArrayList<>();
    }
    
    // добавление значений
    public void addRow(List<String> row) {
    	if(this.values != null) {
    		// проверка числа значений в строке
    		if(this.keys != null && !this.keys.isEmpty()) {
    			this.values.add(row);
    		}
    	}
    }
    
    public void addRow(int keyNum, List<String> row) {
    	if(this.values != null) {
    		// проверка числа значений в строке
    		if(this.keys != null && !this.keys.isEmpty()) {
    			this.values.add(keyNum, row);
    		}
    	}
    }    
    
    // конвертация столбцов и значений в пары ключ-значение
    public void toMap() {
    	if (this.keys != null && !this.keys.isEmpty() && this.values != null && !this.values.isEmpty()) {
	    	this.keysValues = new ArrayList<Map<String, String>>();
   	
	    	for(int i = 0; i < this.values.size(); i++) {
	    		Map<String, String> keyValue = new LinkedHashMap<String, String>();
	    		for(int j = 0; j < this.keys.size(); j++) {
	    			keyValue.put(this.keys.get(j), this.values.get(i).get(j));
	    		}
	    		this.keysValues.add(keyValue);
	    	}		    	
    	}
    }    
    
	private static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        // если пустое значение
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }
        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }
        if (separators == ' ') {
            separators = DEFAULT_DELEMITER.charAt(0);
        }
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    continue;
                } else if (ch == '\n') {
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());

        return result;
    }
	
	//*************************************************************
	// END COMMON METHODS
	//*************************************************************
    
    

	
}
