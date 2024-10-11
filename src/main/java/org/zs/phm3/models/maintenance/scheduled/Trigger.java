package org.zs.phm3.models.maintenance.scheduled;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.zs.phm3.models.rule.ConditionType;
import org.zs.phm3.models.ts.TsKvAttribute;

import javax.persistence.*;

/**
 * Scheduled maintenance trigger entity
 * @author Pavel Chuvak
 */
@Entity
@Table(name = "maintenance_trigger")
public class Trigger {

    /** ID */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Trigger type */
    @Enumerated(EnumType.STRING)
    private TriggerType triggerType;

    /** Date type */
    @Enumerated(EnumType.STRING)
    private DateType dateType;

    /** Reapeat interval */
    private Integer repeatInterval;

    /** Start date */
    private Long startDate;

    /** End date */
    private Long endDate;

    /** Count reapeat */
    private Long countRepeat;

    /** Ts kv attribute */
    @JsonIgnore
    @ManyToOne
    private TsKvAttribute tsKvAttribute;

    /** Ts kv attribute ID */
    @Transient
    private Long tsKvAttributeId;

    /** Every number */
    private Double everyNumber;

    /** Every start number */
    private Double everyStartNumber;

    /** Every end number */
    private Double everyEndNumber;

    /** Condition type */
    @Enumerated(EnumType.STRING)
    private ConditionType conditionType;

    /** Value */
    private String value;

    /** Last repeat date */
    @JsonIgnore
    private Long lastRepeatDate;

    /** Current count repeat */
    @JsonIgnore
    private Long currentCountRepeat = 0L;

    /** Last scan */
    @JsonIgnore
    private Long lastScan = System.currentTimeMillis();

    /** Last scan every number */
    @JsonIgnore
    private Double lastScanEveryNumber;

    /** Maintenance rule */
    @JsonIgnore
    @ManyToOne
    private MaintenanceRule maintenanceRule;

    /**
     * Constructor
     */
    public Trigger() {
    }

    /**
     * Constructor
     * @param triggerType trigger type
     * @param dateType date type
     * @param repeatInterval repeat interval
     * @param startDate start date
     * @param endDate end date
     * @param countRepeat count repeat
     * @param tsKvAttribute ts kv attribute
     * @param everyNumber every number
     * @param everyStartNumber every start nubmer
     * @param everyEndNumber every end number
     * @param conditionType condition type
     * @param value value
     * @param maintenanceRule maintenance rule
     */
    public Trigger(TriggerType triggerType, DateType dateType, Integer repeatInterval, Long startDate,
                   Long endDate, Long countRepeat, TsKvAttribute tsKvAttribute, Double everyNumber, Double everyStartNumber,
                   Double everyEndNumber, ConditionType conditionType, String value, MaintenanceRule maintenanceRule) {
        this.triggerType = triggerType;
        this.dateType = dateType;
        this.repeatInterval = repeatInterval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countRepeat = countRepeat;
        this.tsKvAttribute = tsKvAttribute;
        this.everyNumber = everyNumber;
        this.everyStartNumber = everyStartNumber;
        this.everyEndNumber = everyEndNumber;
        this.conditionType = conditionType;
        this.value = value;
        this.maintenanceRule = maintenanceRule;
    }

    /**
     * Gets ts kv attribute ID
     * @return ts kv attribute ID
     */
    public Long getTsKvAttributeId() {
        return tsKvAttributeId;
    }

    /**
     * Sets ts kv attribute ID
     * @param tsKvAttributeId ts kv attribute ID
     */
    public void setTsKvAttributeId(Long tsKvAttributeId) {
        this.tsKvAttributeId = tsKvAttributeId;
    }

    /**
     * Gets last scan every number
     * @return last scan every number
     */
    public Double getLastScanEveryNumber() {
        return lastScanEveryNumber;
    }

    /**
     * Sets last scan every number
     * @param lastScanEveryNumber last scan every number
     */
    public void setLastScanEveryNumber(Double lastScanEveryNumber) {
        this.lastScanEveryNumber = lastScanEveryNumber;
    }

    /**
     * Gets last scan
     * @return last scan
     */
    public Long getLastScan() {
        return lastScan;
    }

    /**
     * Sets last scan
     * @param lastScan last scan
     */
    public void setLastScan(Long lastScan) {
        this.lastScan = lastScan;
    }

    /**
     * Gets current count repeat
     * @return current count repeat
     */
    public Long getCurrentCountRepeat() {
        return currentCountRepeat;
    }

    /**
     * Sets current count repeat
     * @param currentCountRepeat current count repeat
     */
    public void setCurrentCountRepeat(Long currentCountRepeat) {
        this.currentCountRepeat = currentCountRepeat;
    }

    /**
     * Gets last repeat date
     * @return last repeat date
     */
    public Long getLastRepeatDate() {
        return lastRepeatDate;
    }

    /**
     * Sets last repeat date
     * @param lastRepeatDate last repeat date
     */
    public void setLastRepeatDate(Long lastRepeatDate) {
        this.lastRepeatDate = lastRepeatDate;
    }

    /**
     * Gets ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets trigger type
     * @return trigger type
     */
    public TriggerType getTriggerType() {
        return triggerType;
    }

    /**
     * Sets trigger type
     * @param triggerType trigger type
     */
    public void setTriggerType(TriggerType triggerType) {
        this.triggerType = triggerType;
    }

    /**
     * Gets date type
     * @return date type
     */
    public DateType getDateType() {
        return dateType;
    }

    /**
     * Sets date type
     * @param dateType date type
     */
    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

    /**
     * Gets repeat interval
     * @return repeat interval
     */
    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    /**
     * Sets repeat interval
     * @param repeatInterval repeat interval
     */
    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    /**
     * Gets start date
     * @return start date
     */
    public Long getStartDate() {
        return startDate;
    }

    /**
     * Sets start date
     * @param startDate start date
     */
    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date
     * @return end date
     */
    public Long getEndDate() {
        return endDate;
    }

    /**
     * Sets end date
     * @param endDate end date
     */
    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets count repeat
     * @return count repeat
     */
    public Long getCountRepeat() {
        return countRepeat;
    }

    /**
     * Sets count repeat
     * @param countRepeat count repeat
     */
    public void setCountRepeat(Long countRepeat) {
        this.countRepeat = countRepeat;
    }

    /**
     * Gets ts kv attribute
     * @return ts kv attribute
     */
    public TsKvAttribute getTsKvAttribute() {
        return tsKvAttribute;
    }

    /**
     * Sets ts kv attribute
     * @param tsKvAttribute ts kv attribute
     */
    public void setTsKvAttribute(TsKvAttribute tsKvAttribute) {
        this.tsKvAttribute = tsKvAttribute;
    }

    /**
     * Gets every number
     * @return every number
     */
    public Double getEveryNumber() {
        return everyNumber;
    }

    /**
     * Sets every number
     * @param everyNumber every number
     */
    public void setEveryNumber(Double everyNumber) {
        this.everyNumber = everyNumber;
    }

    /**
     * Gets every start number
     * @return every start number
     */
    public Double getEveryStartNumber() {
        return everyStartNumber;
    }

    /**
     * Sets every start number
     * @param everyStartNumber every start number
     */
    public void setEveryStartNumber(Double everyStartNumber) {
        this.everyStartNumber = everyStartNumber;
    }

    /**
     * Gets every end number
     * @return every end number
     */
    public Double getEveryEndNumber() {
        return everyEndNumber;
    }

    /**
     * Sets every end number
     * @param everyEndNumber every end number
     */
    public void setEveryEndNumber(Double everyEndNumber) {
        this.everyEndNumber = everyEndNumber;
    }

    /**
     * Gets condition type
     * @return condition type
     */
    public ConditionType getConditionType() {
        return conditionType;
    }

    /**
     * Sets condition type
     * @param conditionType condition type
     */
    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    /**
     * Gets value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets maintenance rule
     * @return maintenance rule
     */
    public MaintenanceRule getMaintenanceRule() {
        return maintenanceRule;
    }

    /**
     * Sets maintenance rule
     * @param maintenanceRule maintenance rule
     */
    public void setMaintenanceRule(MaintenanceRule maintenanceRule) {
        this.maintenanceRule = maintenanceRule;
    }
}
