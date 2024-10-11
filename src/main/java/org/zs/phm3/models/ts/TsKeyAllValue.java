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
	    name="tsAggAllMapped",
	    classes={
	      @ConstructorResult(
	        targetClass=TsKeyAllValue.class,
	        columns={
	          @ColumnResult(name="entityId", type=String.class),
	          @ColumnResult(name="tsBucket", type=Long.class),
	          @ColumnResult(name="key", type=String.class),
	          @ColumnResult(name="maxDoubleValue", type=Double.class),
	          @ColumnResult(name="maxLongValue", type=Long.class),
	          @ColumnResult(name="minDoubleValue", type=Double.class),
	          @ColumnResult(name="minLongValue", type=Long.class),   
	          @ColumnResult(name="avgDoubleValue", type=Double.class),
	          @ColumnResult(name="avgLongValue", type=Long.class),
	          @ColumnResult(name="sumDoubleValue", type=Double.class),
	          @ColumnResult(name="sumLongValue", type=Long.class),
	          @ColumnResult(name="lastDoubleValue", type=Double.class),
	          @ColumnResult(name="lastLongValue", type=Long.class),
	          @ColumnResult(name="firstDoubleValue", type=Double.class),
	          @ColumnResult(name="firstLongValue", type=Long.class) })})
public class TsKeyAllValue extends Object {

	@Id
	@Column(name = "entityId")
	private String entityId;
	
	@Column(name = "tsBucket")
	private Long ts;
	
	@Id
	@Column(name = "key")
	private String key;

	@Column(name = "maxDoubleValue")
	private Double maxDoubleValue;
	
	@Column(name = "maxLongValue")
	private Long maxLongValue;
	
	@Column(name = "minDoubleValue")
	private Double minDoubleValue;
	
	@Column(name = "minLongValue")
	private Long minLongValue;
	
	@Column(name = "avgDoubleValue")
	private Double avgDoubleValue;
	
	@Column(name = "avgLongValue")
	private Long avgLongValue;
	
	@Column(name = "sumDoubleValue")
	private Double sumDoubleValue;
	
	@Column(name = "sumLongValue")
	private Long sumLongValue;
	
	@Column(name = "lastDoubleValue")
	private Double lastDoubleValue;
	
	@Column(name = "lastLongValue")
	private Long lastLongValue;
	
	@Column(name = "firstDoubleValue")
	private Double firstDoubleValue;
	
	@Column(name = "firstLongValue")
	private Long firstLongValue;
	
	public TsKeyAllValue() {}

	public TsKeyAllValue(String entityId, Long ts, String key, Double maxDoubleValue, Long maxLongValue,
			Double minDoubleValue, Long minLongValue, Double avgDoubleValue, Long avgLongValue, Double sumDoubleValue,
			Long sumLongValue, Double lastDoubleValue, Long lastLongValue, Double firstDoubleValue,
			Long firstLongValue) {
		super();
		this.entityId = entityId;
		this.ts = ts;
		this.key = key;
		this.maxDoubleValue = maxDoubleValue;
		this.maxLongValue = maxLongValue;
		this.minDoubleValue = minDoubleValue;
		this.minLongValue = minLongValue;
		this.avgDoubleValue = avgDoubleValue;
		this.avgLongValue = avgLongValue;
		this.sumDoubleValue = sumDoubleValue;
		this.sumLongValue = sumLongValue;
		this.lastDoubleValue = lastDoubleValue;
		this.lastLongValue = lastLongValue;
		this.firstDoubleValue = firstDoubleValue;
		this.firstLongValue = firstLongValue;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getMaxDoubleValue() {
		return maxDoubleValue;
	}

	public void setMaxDoubleValue(Double maxDoubleValue) {
		this.maxDoubleValue = maxDoubleValue;
	}

	public Long getMaxLongValue() {
		return maxLongValue;
	}

	public void setMaxLongValue(Long maxLongValue) {
		this.maxLongValue = maxLongValue;
	}

	public Double getMinDoubleValue() {
		return minDoubleValue;
	}

	public void setMinDoubleValue(Double minDoubleValue) {
		this.minDoubleValue = minDoubleValue;
	}

	public Long getMinLongValue() {
		return minLongValue;
	}

	public void setMinLongValue(Long minLongValue) {
		this.minLongValue = minLongValue;
	}

	public Double getAvgDoubleValue() {
		return avgDoubleValue;
	}

	public void setAvgDoubleValue(Double avgDoubleValue) {
		this.avgDoubleValue = avgDoubleValue;
	}

	public Long getAvgLongValue() {
		return avgLongValue;
	}

	public void setAvgLongValue(Long avgLongValue) {
		this.avgLongValue = avgLongValue;
	}

	public Double getSumDoubleValue() {
		return sumDoubleValue;
	}

	public void setSumDoubleValue(Double sumDoubleValue) {
		this.sumDoubleValue = sumDoubleValue;
	}

	public Long getSumLongValue() {
		return sumLongValue;
	}

	public void setSumLongValue(Long sumLongValue) {
		this.sumLongValue = sumLongValue;
	}

	public Double getLastDoubleValue() {
		return lastDoubleValue;
	}

	public void setLastDoubleValue(Double lastDoubleValue) {
		this.lastDoubleValue = lastDoubleValue;
	}

	public Long getLastLongValue() {
		return lastLongValue;
	}

	public void setLastLongValue(Long lastLongValue) {
		this.lastLongValue = lastLongValue;
	}

	public Double getFirstDoubleValue() {
		return firstDoubleValue;
	}

	public void setFirstDoubleValue(Double firstDoubleValue) {
		this.firstDoubleValue = firstDoubleValue;
	}

	public Long getFirstLongValue() {
		return firstLongValue;
	}

	public void setFirstLongValue(Long firstLongValue) {
		this.firstLongValue = firstLongValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avgDoubleValue == null) ? 0 : avgDoubleValue.hashCode());
		result = prime * result + ((avgLongValue == null) ? 0 : avgLongValue.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((firstDoubleValue == null) ? 0 : firstDoubleValue.hashCode());
		result = prime * result + ((firstLongValue == null) ? 0 : firstLongValue.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((lastDoubleValue == null) ? 0 : lastDoubleValue.hashCode());
		result = prime * result + ((lastLongValue == null) ? 0 : lastLongValue.hashCode());
		result = prime * result + ((maxDoubleValue == null) ? 0 : maxDoubleValue.hashCode());
		result = prime * result + ((maxLongValue == null) ? 0 : maxLongValue.hashCode());
		result = prime * result + ((minDoubleValue == null) ? 0 : minDoubleValue.hashCode());
		result = prime * result + ((minLongValue == null) ? 0 : minLongValue.hashCode());
		result = prime * result + ((sumDoubleValue == null) ? 0 : sumDoubleValue.hashCode());
		result = prime * result + ((sumLongValue == null) ? 0 : sumLongValue.hashCode());
		result = prime * result + ((ts == null) ? 0 : ts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TsKeyAllValue))
			return false;
		TsKeyAllValue other = (TsKeyAllValue) obj;
		if (avgDoubleValue == null) {
			if (other.avgDoubleValue != null)
				return false;
		} else if (!avgDoubleValue.equals(other.avgDoubleValue))
			return false;
		if (avgLongValue == null) {
			if (other.avgLongValue != null)
				return false;
		} else if (!avgLongValue.equals(other.avgLongValue))
			return false;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (firstDoubleValue == null) {
			if (other.firstDoubleValue != null)
				return false;
		} else if (!firstDoubleValue.equals(other.firstDoubleValue))
			return false;
		if (firstLongValue == null) {
			if (other.firstLongValue != null)
				return false;
		} else if (!firstLongValue.equals(other.firstLongValue))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (lastDoubleValue == null) {
			if (other.lastDoubleValue != null)
				return false;
		} else if (!lastDoubleValue.equals(other.lastDoubleValue))
			return false;
		if (lastLongValue == null) {
			if (other.lastLongValue != null)
				return false;
		} else if (!lastLongValue.equals(other.lastLongValue))
			return false;
		if (maxDoubleValue == null) {
			if (other.maxDoubleValue != null)
				return false;
		} else if (!maxDoubleValue.equals(other.maxDoubleValue))
			return false;
		if (maxLongValue == null) {
			if (other.maxLongValue != null)
				return false;
		} else if (!maxLongValue.equals(other.maxLongValue))
			return false;
		if (minDoubleValue == null) {
			if (other.minDoubleValue != null)
				return false;
		} else if (!minDoubleValue.equals(other.minDoubleValue))
			return false;
		if (minLongValue == null) {
			if (other.minLongValue != null)
				return false;
		} else if (!minLongValue.equals(other.minLongValue))
			return false;
		if (sumDoubleValue == null) {
			if (other.sumDoubleValue != null)
				return false;
		} else if (!sumDoubleValue.equals(other.sumDoubleValue))
			return false;
		if (sumLongValue == null) {
			if (other.sumLongValue != null)
				return false;
		} else if (!sumLongValue.equals(other.sumLongValue))
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
		return "TsKeyAllValue [entityId=" + entityId + ", ts=" + ts + ", key=" + key + ", maxDoubleValue="
				+ maxDoubleValue + ", maxLongValue=" + maxLongValue + ", minDoubleValue=" + minDoubleValue
				+ ", minLongValue=" + minLongValue + ", avgDoubleValue=" + avgDoubleValue + ", avgLongValue="
				+ avgLongValue + ", sumDoubleValue=" + sumDoubleValue + ", sumLongValue=" + sumLongValue
				+ ", lastDoubleValue=" + lastDoubleValue + ", lastLongValue=" + lastLongValue + ", firstDoubleValue="
				+ firstDoubleValue + ", firstLongValue=" + firstLongValue + "]";
	};
	
	
}
