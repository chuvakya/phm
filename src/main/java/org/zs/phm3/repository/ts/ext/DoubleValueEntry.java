package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.Optional;

public class DoubleValueEntry {
	  
	 private final String key;
	 private final Double value;
	  
	 public DoubleValueEntry(String key, Double value) {
	    this.key = key;
	    this.value = value;
	 }
	  
	  //public DoubleValueEntry() {}
	  
	 public DataType getDataType() { return DataType.DOUBLE; }
	  
	 public Optional<Double> getDoubleValue() { return Optional.ofNullable(this.value); }
	  
	 public Object getValue() { return this.value; }
	  
	 public Optional<String> getKey() { return Optional.ofNullable(this.key); }

	 @Override
	 public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DoubleValueEntry))
			return false;
		DoubleValueEntry other = (DoubleValueEntry) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	  
	  public String toString() {
	    return "DoubleValueEntry{value=" + this.value + "} " + super.toString();
	  }
	  
	  public String getValueAsString() { return Double.toString(this.value.doubleValue()); }

}
