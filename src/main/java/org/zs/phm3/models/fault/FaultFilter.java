package org.zs.phm3.models.fault;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for filter by fault
 * @author Pavel Chuvak
 */
public class FaultFilter {

    /** Event names */
    private List<String> eventNames = new ArrayList<>();

    /** Priorities */
    private List<Integer> priorities = new ArrayList<>();

    /** Severities */
    private List<Integer> severities = new ArrayList<>();

    /** Statuses */
    private List<Integer> statuses = new ArrayList<>();

    /** Start time from */
    private Long startTimeFrom;

    /** Start time to */
    private Long startTimeTo;

    /** End time from */
    private Long endTimeFrom;

    /** End time from */
    private Long endTimeTo;

    /** Sort by */
    private String sortBy;

    /** Sort type */
    private String sortType;

    /** Different time */
    private Long differentTime;

    /** Start time */
    private Long startTime;

    /** End time */
    private Long endTime;

    /** Offset */
    private Integer offset;

    /** Limit */
    private Integer limit;

    /** Search */
    private String search;

    /** Unit ID */
    private String unitId;

    /**
     * Gets search
     * @return search
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets search
     * @param search search
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * Gets start time from
     * @return start time from
     */
    public Long getStartTimeFrom() {
        return startTimeFrom;
    }

    /**
     * Sets start time from
     * @param startTimeFrom start time from
     */
    public void setStartTimeFrom(Long startTimeFrom) {
        this.startTimeFrom = startTimeFrom;
    }

    /**
     * Gets start time to
     * @return start time to
     */
    public Long getStartTimeTo() {
        return startTimeTo;
    }

    /**
     * Gets start time to
     * @param startTimeTo start time to
     */
    public void setStartTimeTo(Long startTimeTo) {
        this.startTimeTo = startTimeTo;
    }

    /**
     * Gets end time from
     * @return end time from
     */
    public Long getEndTimeFrom() {
        return endTimeFrom;
    }

    /**
     * Gets end time from
     * @param endTimeFrom end time from
     */
    public void setEndTimeFrom(Long endTimeFrom) {
        this.endTimeFrom = endTimeFrom;
    }

    /**
     * Gets end time to
     * @return end time to
     */
    public Long getEndTimeTo() {
        return endTimeTo;
    }

    /**
     * Sets end time to
     * @param endTimeTo end time to
     */
    public void setEndTimeTo(Long endTimeTo) {
        this.endTimeTo = endTimeTo;
    }

    /**
     * Gets sort by
     * @return sort by
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets sort by
     * @param sortBy sort by
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Gets sort type
     * @return sort type
     */
    public String getSortType() {
        return sortType;
    }

    /**
     * Sets sort type
     * @param sortType sort type
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    /**
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets event names
     * @return event names
     */
    public List<String> getEventNames() {
        return eventNames;
    }

    /**
     * Sets event names
     * @param eventNames
     */
    public void setEventNames(List<String> eventNames) {
        this.eventNames = eventNames;
    }

    /**
     * Gets priorities
     * @return priorities
     */
    public List<Integer> getPriorities() {
        return priorities;
    }

    /**
     * Sets priorities
     * @param priorities priorities
     */
    public void setPriorities(List<Integer> priorities) {
        this.priorities = priorities;
    }

    /**
     * Gets severities
     * @return severities
     */
    public List<Integer> getSeverities() {
        return severities;
    }

    /**
     * Sets severities
     * @param severities severities
     */
    public void setSeverities(List<Integer> severities) {
        this.severities = severities;
    }

    /**
     * Gets statuses
     * @return statuses
     */
    public List<Integer> getStatuses() {
        return statuses;
    }

    /**
     * Sets statuses
     * @param statuses statuses
     */
    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }

    /**
     * Gets statuses
     * @return statuses
     */
    public Long getDifferentTime() {
        return differentTime;
    }

    /**
     * Gets different time
     * @param differentTime different time
     */
    public void setDifferentTime(Long differentTime) {
        this.differentTime = differentTime;
    }

    /**
     * Gets start time
     * @return start time
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * Sets start time
     * @param startTime start time
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time
     * @return end time
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * Sets end time
     * @param endTime end time
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets offset
     * @return offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Sets offset
     * @param offset offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Gets limit
     * @return limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets limit
     * @param limit limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
