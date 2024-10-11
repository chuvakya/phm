package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.Objects;
import java.util.Optional;

public class LongValueEntry  {
	  
	  private final String key;
	  private final Long value;
	  
	  public LongValueEntry(String key, Long value) {
	    this.key = key;
	    this.value = value;
	  }
	  
	  public DataType getDataType() { return DataType.LONG; }
	  
	  public Optional<Long> getLongValue() { return Optional.ofNullable(this.value); }
	  
	  public Optional<String> getKey() { return Optional.ofNullable(this.key); }
	  
	  public Object getValue() { return this.value; }
	  
	  public boolean equals(Object o) {
	    if (this == o)
	      return true; 
	    if (!(o instanceof LongValueEntry))
	      return false; 
	    if (!super.equals(o))
	      return false; 
	    LongValueEntry that = (LongValueEntry)o;
	    return Objects.equals(this.value, that.value);
	  }
	  
	  public int hashCode() { return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), this.value }); }
	  
	  public String toString() {
	    return "LongValueEntry{value=" + this.value + "} " + super.toString();
	  }
	  
	  public String getValueAsString() { return Long.toString(this.value.longValue()); }


}
