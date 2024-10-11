package org.zs.phm3.models.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import javax.persistence.*;


@Entity
@Table(name = "ts_kv_latest")
@IdClass(TsKvLatestKey.class)
public final class TsKvLatest {

	
	@Id
	@Column(name = "entity_id", length = 31)
	private String entityId;
	  
	@Id
	@Column(name = "attribute_key")
	private String key;
	  
	@Id
	@Column(name = "ts")
	private Long ts;
	 
	@Column(name = "bool_v")
	private Boolean booleanValue;
	  
	@Column(name = "str_v")
	private String strValue;
	 
	@Column(name = "long_v")
	private Long longValue;
	  
	@Column(name = "dbl_v")
	private Double doubleValue;

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

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booleanValue == null) ? 0 : booleanValue.hashCode());
		result = prime * result + ((doubleValue == null) ? 0 : doubleValue.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((longValue == null) ? 0 : longValue.hashCode());
		result = prime * result + ((strValue == null) ? 0 : strValue.hashCode());
		result = prime * result + (int) (ts ^ (ts >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TsKvLatest))
			return false;
		TsKvLatest other = (TsKvLatest) obj;
		if (booleanValue == null) {
			if (other.booleanValue != null)
				return false;
		} else if (!booleanValue.equals(other.booleanValue))
			return false;
		if (doubleValue == null) {
			if (other.doubleValue != null)
				return false;
		} else if (!doubleValue.equals(other.doubleValue))
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
		if (longValue == null) {
			if (other.longValue != null)
				return false;
		} else if (!longValue.equals(other.longValue))
			return false;
		if (strValue == null) {
			if (other.strValue != null)
				return false;
		} else if (!strValue.equals(other.strValue))
			return false;
		if (ts != other.ts)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TsKvLatestEntity [entityId=" + getEntityId() + ", key=" + getKey() + ", ts=" + getTs() + ", booleanValue="
				+ getBooleanValue() + ", strValue=" + getStrValue() + ", longValue=" + getLongValue() + ", doubleValue=" + getDoubleValue()
				+ "]";
	}	
	

}
