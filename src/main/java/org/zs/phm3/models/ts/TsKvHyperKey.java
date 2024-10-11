package org.zs.phm3.models.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class TsKvHyperKey implements Serializable {
	private String entityId;
	  
	private String key;
	
	private Long ts;

	@ConstructorProperties({"entityId", "key", "ts"})
	public TsKvHyperKey(String entityId, String key, Long ts) {
		this.entityId = entityId;
		this.key = key;
		this.ts = ts;
	}
	  
	public TsKvHyperKey() {}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + (int) (ts ^ (ts >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TsKvHyperKey))
			return false;
		TsKvHyperKey other = (TsKvHyperKey) obj;
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
		if (ts != other.ts)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TsKvHyperCompositeKey [getEntityId()=" + getEntityId() + ", getKey()=" + getKey() + ", getTs()=" + getTs()
				+ "]";
	}

}
