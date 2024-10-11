package org.zs.phm3.models.ml;

import org.zs.phm3.models.ts.TsKvAttribute;

import javax.persistence.*;

/**
 * This class is responsible for binding the attribute of a unit with a data schema.
 * @author Pavel Chuvak
 */
@Entity
public class DataSchemaTsKvAttribute {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** DataSchema entity */
    @ManyToOne
    private DataSchema dataSchema;

    /** Unit attribute */
    @ManyToOne
    private TsKvAttribute tsKvAttribute;

    /** Empty constructor */
    public DataSchemaTsKvAttribute() {
    }

    /**
     * Constructor
     * @param dataSchema DataSchema entity
     * @param tsKvAttribute TsKvAttribute entity
     */
    public DataSchemaTsKvAttribute(DataSchema dataSchema, TsKvAttribute tsKvAttribute) {
        this.dataSchema = dataSchema;
        this.tsKvAttribute = tsKvAttribute;
    }

    /**
     * Getting ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Setting ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getting DataSchema entity
     * @return DataSchema entity
     */
    public DataSchema getDataSchema() {
        return dataSchema;
    }

    /**
     * Setting DataSchema entity
     * @param dataSchema DataSchema entity
     */
    public void setDataSchema(DataSchema dataSchema) {
        this.dataSchema = dataSchema;
    }

    /**
     * Getting unit attribute
     * @return TsKvAttribute entity
     */
    public TsKvAttribute getTsKvAttribute() {
        return tsKvAttribute;
    }

    /**
     * Setting unit attribute
     * @param tsKvAttribute TsKvAttribute entity
     */
    public void setTsKvAttribute(TsKvAttribute tsKvAttribute) {
        this.tsKvAttribute = tsKvAttribute;
    }
}
