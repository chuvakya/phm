package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.Objects;
import java.util.Optional;

public class StringValueEntry {
	  
	  private final String key;
	  private final String value;
	  
	  public StringValueEntry(String key, String value) {
	    this.key = key;
	    this.value = value;
	  }
	  
	  public DataType getDataType() { return DataType.STRING; }
	  
	  public Optional<String> getStrValue() { return Optional.ofNullable(this.value); }
	  
	  public Optional<String> getKey() { return Optional.ofNullable(this.key); }
	  
	  public Object getValue() { return this.value; }
	  
	  public boolean equals(Object o) {
	    if (this == o)
	      return true; 
	    if (!(o instanceof StringValueEntry))
	      return false; 
	    if (!super.equals(o))
	      return false; 
	    StringValueEntry that = (StringValueEntry)o;
	    return Objects.equals(this.value, that.value);
	  }
	  
	  public int hashCode() { return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), this.value }); }
	  
	  public String toString() { return "StringValueEntry{value='" + this.value + '\'' + "} " + super.toString(); }
	  
	  public String getValueAsString() { return this.value; }


}
