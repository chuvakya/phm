package org.zs.phm3.models.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(
	    name="tsAggMapped",
	    classes={
	      @ConstructorResult(
	        targetClass=TsKeyValue.class,
	        columns={
	          @ColumnResult(name="entityId", type=String.class),
	          @ColumnResult(name="tsBucket", type=Long.class),
	          @ColumnResult(name="key", type=String.class),
	          @ColumnResult(name="doubleValue", type=Double.class),
	          @ColumnResult(name="longValue", type=Long.class)})})
public class TsKeyValue extends Object {

	@Id
	@Column(name = "entityId")
	private String entityId;
	
	@Column(name = "tsBucket")
	private Long ts;
	
	@Column(name = "key")
	private String key;
	
	@Column(name = "doubleValue")
	private Double doubleValue;
	
	@Column(name = "longValue")
	private Long longValue;
	
	public TsKeyValue() {};
	
	public TsKeyValue(String entityId, Long ts, String key, Double doubleValue, Long longValue) {
		super();
		this.entityId = entityId;
		this.key = key;
		this.ts = ts;
		this.doubleValue = doubleValue;
		this.longValue = longValue;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doubleValue == null) ? 0 : doubleValue.hashCode());
		result = prime * result + ((longValue == null) ? 0 : longValue.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((ts == null) ? 0 : ts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TsKeyValue))
			return false;
		TsKeyValue other = (TsKeyValue) obj;
		if (doubleValue == null) {
			if (other.doubleValue != null)
				return false;
		} else if (!doubleValue.equals(other.doubleValue))
			return false;
		if (longValue == null) {
			if (other.longValue != null)
				return false;
		} else if (!longValue.equals(other.longValue))
			return false;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (ts == null) {
			if (other.ts != null)
				return false;
		} else if (!ts.equals(other.ts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TsKeyValue [entityId=" + entityId + ", ts=" + ts + ", key=" + key + ", doubleValue=" + doubleValue
				+ ", longValue=" + longValue + "]";
	}


	
}
