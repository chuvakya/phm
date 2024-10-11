package org.zs.phm3.ftamodel;

import org.zs.phm3.models.user.UserEntity;
import org.zs.phm3.util.mapping.JsonStringType;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "t_fta_diagram")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class FtaDiagramEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long projectId;
    private String name;
    @ManyToOne
    private FtaDiagramEntity parent;
    @OneToMany(mappedBy = "parent")
    private List<FtaDiagramEntity> child;
    @Type(type = "json")
    @Column(columnDefinition = "varchar")
    private JsonNode body;
    private boolean isActive;

    private FtaDiagramStatus status;
    @OneToOne
    private UserEntity user;



    public FtaDiagramEntity() {
    }

    public FtaDiagramEntity(Long id, Long projectId, FtaDiagramEntity parent, List<FtaDiagramEntity> child, JsonNode body) {
        this.id = id;
        this.projectId = projectId;
        this.parent = parent;
        this.child = child;
        this.body = body;
        this.isActive = false;
        this.status = FtaDiagramStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setParent(FtaDiagramEntity parent) {
        this.parent = parent;
    }

    public List<FtaDiagramEntity> getChild() {
        return this.child;
    }

    public JsonNode getBody() {
        return this.body;
    }

    public void setBody(JsonNode body) {
        this.body = body;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public FtaDiagramStatus getStatus() {
        return status;
    }

    public void setStatus(FtaDiagramStatus status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
