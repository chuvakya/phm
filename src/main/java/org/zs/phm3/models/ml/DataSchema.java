package org.zs.phm3.models.ml;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.zs.phm3.models.ptl.BIT;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the data schema on the basis of which the dataset and data sets are created to send the
 * trained ml model to the service.
 * @author Pavel Chuvak
 */
@Entity
public class DataSchema {

    /** ID field */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Data schema name field */
    private String name;

    /** Project ID field */
    private Integer projectId;

    /** Unit ID field */
    private String unitId;

    /** The person who made the last change */
    @ManyToOne
    private UserEntity modifiedBy;

    /** Data schema creation time */
    private Long createdTime;

    /** Unit attribute responsible for the bit code column */
    @ManyToOne
    private TsKvAttribute bitAttribute;

    /** A collection of datasets created according to this data schema */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "dataSchema", cascade = CascadeType.ALL)
    private List<Dataset> datasets = new ArrayList<>();

    /** A collection of ml models created according to this data schema */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "dataSchema", cascade = CascadeType.ALL)
    private List<MlModel> mlModels = new ArrayList<>();

    /** A set of links with unit attributes */
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @OneToMany(mappedBy = "dataSchema", cascade = CascadeType.ALL)
    private List<DataSchemaTsKvAttribute> dataSchemaTsKvAttributes = new ArrayList<>();

    /** BIT error code */
    @ManyToOne
    private BIT bitErrorCode;

    /**
     * Empty constructor
     */
    public DataSchema() {}

    /**
     * DataSchema class constructor
     * @param name data schema name
     * @param unitId unit ID
     * @param projectId project ID
     * @param modifiedBy the person who made the last change
     * @param createdTime create time
     * @param bitAttribute BIT unit attribute
     * @param errorCode BIT error code
     */
    public DataSchema(String name, String unitId, Integer projectId, UserEntity modifiedBy, Long createdTime,
                      TsKvAttribute bitAttribute, BIT errorCode) {
        this.name = name;
        this.bitErrorCode = errorCode;
        this.unitId = unitId;
        this.projectId = projectId;
        this.modifiedBy = modifiedBy;
        this.createdTime = createdTime;
        this.bitAttribute = bitAttribute;
    }

    /**
     * Getting a bit of code
     * @return BIT error code
     */
    public BIT getBitErrorCode() {
        return bitErrorCode;
    }

    /**
     * Setting BIT error code
     * @param bitErrorCode setting BIT error code
     */
    public void setBitErrorCode(BIT bitErrorCode) {
        this.bitErrorCode = bitErrorCode;
    }

    /**
     * Getting Unit attribute BIT column
     * @return Unit attribute BIT column
     */
    public TsKvAttribute getBitAttribute() {
        return bitAttribute;
    }

    /**
     * Setting Unit BIT attribute column
     * @param bitAttribute Unit BIT attribute
     */
    public void setBitAttribute(TsKvAttribute bitAttribute) {
        this.bitAttribute = bitAttribute;
    }

    /**
     * Getting a set of links with unit attributes
     * @return DataSchemaTsKvAttribute entities
     */
    public List<DataSchemaTsKvAttribute> getDataSchemaTsKvAttributes() {
        return dataSchemaTsKvAttributes;
    }

    /**
     * Setting a set of links with unit attributes
     * @param dataSchemaTsKvAttributes DataSchemaTsKvAttribute entities
     */
    public void setDataSchemaTsKvAttributes(List<DataSchemaTsKvAttribute> dataSchemaTsKvAttributes) {
        this.dataSchemaTsKvAttributes = dataSchemaTsKvAttributes;
    }

    /**
     * Getting UserEntity by last modified Data Schema
     * @return UserEntity class by last modified Data Schema
     */
    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Setting UserEntity by last modified Data Schema
     * @param modifiedBy UserEntity by last modified Data Schema
     */
    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Getting create time Data Schema
     * @return crete time
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * Setting create time DataSchema
     * @param createdTime create time
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Getting project ID
     * @return project ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Setting project ID
     * @param projectId project ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * Getting all Dataset entity
     * @return dataset entities
     */
    public List<Dataset> getDatasets() {
        return datasets;
    }

    /**
     * Setting dataset entities
     * @param datasets list dataset entities
     */
    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    /**
     * Getting ID
     * @return data schema ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Setting data schema ID
     * @param id data schema ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getting data schema name
     * @return data schema name
     */
    public String getName() {
        return name;
    }

    /**
     * Setting data schema name
     * @param name data schema name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getting Unit ID
     * @return unit ID
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * Setting Unit ID
     * @param unitId Unit ID
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * Getting ml models entities
     * @return ml model entities
     */
    public List<MlModel> getMlModels() {
        return mlModels;
    }

    /**
     * Setting ml models entities
     * @param mlModels ml model entities
     */
    public void setMlModels(List<MlModel> mlModels) {
        this.mlModels = mlModels;
    }
}
