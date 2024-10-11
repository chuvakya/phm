package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.Objects;
import java.util.Optional;

public class BooleanValueEntry {
	  
	private final String key;
	private final Boolean value;
	  
	  public BooleanValueEntry(String key, Boolean value) {
	    this.key = key;
	    this.value = value;
	  }
	  
	  public DataType getDataType() { return DataType.BOOLEAN; }
	  
	  public Optional<Boolean> getBooleanValue() { return Optional.ofNullable(this.value); }
	  
	  public Optional<String> getKey() { return Optional.ofNullable(this.key); }
	  
	  public boolean equals(Object o) {
	    if (this == o)
	      return true; 
	    if (!(o instanceof BooleanValueEntry))
	      return false; 
	    if (!super.equals(o))
	      return false; 
	    BooleanValueEntry that = (BooleanValueEntry)o;
	    return Objects.equals(this.value, that.value);
	  }
	  
	  public Object getValue() { return this.value; }
	  
	  public int hashCode() { return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), this.value }); }
	  
	  public String toString() {
	    return "BooleanValueEntry{value=" + this.value + "} " + super.toString();
	  }
	  
	  public String getValueAsString() { return Boolean.toString(this.value.booleanValue()); }


}
