package org.zs.phm3.models.ts;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class TsKvLatestKey implements Serializable {

	private String entityId;
	  
	private String key;
	

	@ConstructorProperties({"entityId", "key"})
	public TsKvLatestKey(String entityId, String key) {
		this.entityId = entityId;
		this.key = key;
	}
	  
	public TsKvLatestKey() {}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TsKvLatestKey))
			return false;
		TsKvLatestKey other = (TsKvLatestKey) obj;
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
		return true;
	}
	
	protected boolean canEqual(Object other) { return other instanceof TsKvLatestKey; }

	@Override
	public String toString() {
		return "TsKvLatestCompositeKey [getEntityId()=" + getEntityId() + ", getKey()=" + getKey() + "]";
	}
	
}
