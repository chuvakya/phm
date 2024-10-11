package org.zs.phm3.models.ts;

import org.zs.phm3.models.UomEntity;
import org.zs.phm3.models.ml.DataSchema;
import org.zs.phm3.models.ml.DataSchemaTsKvAttribute;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ml.Dataset;
import org.zs.phm3.models.rule.Condition;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit data attribute class
 * @author Pavel Chuvak
 */
@Entity
public class TsKvAttribute {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ID */
    @Transient
    private Long tsKvAttributeId;

    /** Attribute name */
    @JsonIgnore
    private String name;

    /** Unit ID */
    @JsonIgnore
    private String unitId;

    /** Attribute key */
    @JsonIgnore
    private String attributeKey;

    /** Device ID */
    @JsonIgnore
    private String deviceId;

    /** Is table */
    @JsonIgnore
    private Boolean isTable;

    /** Uom input */
    @JsonIgnore
    @ManyToOne
    private UomEntity uomInput;

    /** Uom output */
    @JsonIgnore
    @ManyToOne
    private UomEntity uomOutput;

    /** Uom output symbol */
    @JsonIgnore
    private String outputSymbol;

    /** Data type */
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private TsKvAttrDataTypes dataType;

    /** Attribute type */
    @JsonIgnore
    @ManyToOne
    private TsKvAttributeType tsKvAttributeType;

    /** Data schema and tsKvAttribute */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "tsKvAttribute", cascade = CascadeType.ALL)
    private List<DataSchemaTsKvAttribute> dataSchemaTsKvAttributes = new ArrayList<>();

    /** Conditions */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "tsKvAttribute")
    private List<Condition> conditions = new ArrayList<>();

    /** Data Schemas */
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "bitAttribute")
    private List<DataSchema> dataSchemas = new ArrayList<>();

    /**
     * Constructor
     */
    public TsKvAttribute() {}

    /**
     * Constructor
     * @param unitId unit ID
     * @param attributeKey attribute key
     * @param category category
     * @param name attribute name
     * @param deviceId device ID
     * @param uomInput oum input
     * @param uomOutput uom output
     * @param dataType data type
     * @param isTable is table
     * @param outputSymbol output symbol
     */
    public TsKvAttribute(String unitId, String attributeKey, TsKvAttributeType category, String name, String deviceId,
                         UomEntity uomInput, UomEntity uomOutput, TsKvAttrDataTypes dataType, Boolean isTable,
                         String outputSymbol) {
        this.unitId = unitId;
        this.outputSymbol = outputSymbol;
        this.attributeKey = attributeKey;
        this.name = name;
        this.deviceId = deviceId;
        this.uomInput = uomInput;
        this.uomOutput = uomOutput;
        this.dataType = dataType;
        this.isTable = isTable;
        this.tsKvAttributeType = category;
    }

    /**
     * Gets data schemas
     * @return data schemas
     */
    public List<DataSchema> getDataSchemas() {
        return dataSchemas;
    }

    /**
     * Sets data schemas
     * @param dataSchemas data schemas
     */
    public void setDataSchemas(List<DataSchema> dataSchemas) {
        this.dataSchemas = dataSchemas;
    }

    /**
     * Gets output symbol
     * @return output symbol
     */
    public String getOutputSymbol() {
        return outputSymbol;
    }

    /**
     * Sets output symbol
     * @param outputSymbol output symbol
     */
    public void setOutputSymbol(String outputSymbol) {
        this.outputSymbol = outputSymbol;
    }

    /**
     * Gets data schemas and tsKvAttribute
     * @return data schemas and tsKvAttribute
     */
    public List<DataSchemaTsKvAttribute> getDataSchemaTsKvAttributes() {
        return dataSchemaTsKvAttributes;
    }

    /**
     * Sets data schemas and tsKvAttribute
     * @param dataSchemaTsKvAttributes data schemas and tsKvAttribute
     */
    public void setDataSchemaTsKvAttributes(List<DataSchemaTsKvAttribute> dataSchemaTsKvAttributes) {
        this.dataSchemaTsKvAttributes = dataSchemaTsKvAttributes;
    }

    /**
     * Gets conditions
     * @return conditions
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Sets conditions
     * @param conditions conditions
     */
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
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
     * Gets unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Sets unit ID
     * @param unitId ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Gets attribute key
     * @return attribute key
     */
    public String getAttributeKey() {
        return attributeKey;
    }

    /**
     * Sets attribute key
     * @param attributeKey attribute key
     */
    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    /**
     * Gets attribute type
     * @return attribute type
     */
    public TsKvAttributeType getTsKvAttributeType() {
        return tsKvAttributeType;
    }

    /**
     * Sets attribute type
     * @param tsKvAttributeType attribute type
     */
    public void setTsKvAttributeType(TsKvAttributeType tsKvAttributeType) {
        this.tsKvAttributeType = tsKvAttributeType;
    }

    /**
     * Gets is table
     * @return is table
     */
    @JsonIgnore
    public Boolean getTable() {
        return isTable;
    }

    /**
     * Sets is table
     * @param table is table
     */
    public void setTable(Boolean table) {
        isTable = table;
    }

    /**
     * Gets data type
     * @return data type
     */
    public TsKvAttrDataTypes getDataType() {
        return dataType;
    }

    /**
     * Sets data type
     * @param dataType data type
     */
    public void setDataType(TsKvAttrDataTypes dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets device ID
     * @return device ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets device ID
     * @param deviceId device ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets attribute name
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets attribute name
     * @param name attribute name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets uom input
     * @return uom input
     */
    public UomEntity getUomInput() {
        return uomInput;
    }

    /**
     * Sets uom input
     * @param uomInput uom input
     */
    public void setUomInput(UomEntity uomInput) {
        this.uomInput = uomInput;
    }

    /**
     * Gets uom output
     * @return uom output
     */
    public UomEntity getUomOutput() {
        return uomOutput;
    }

    /**
     * Sets uom output
     * @param uomOutput uom output
     */
    public void setUomOutput(UomEntity uomOutput) {
        this.uomOutput = uomOutput;
    }
}
