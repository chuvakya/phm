package org.zs.phm3.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private String login;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    @JsonIgnore
    private Long lastSeen;
    @JsonIgnore
    private Boolean isAccess;
    @JsonIgnore
    private String pictureExtention;

    @JsonIgnore
    @Column(length = 1000)
    private String password;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private Boolean isDeleted;

    @JsonIgnore
    @Column(length = 1000)
    private String organization;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "userEntity")
    private List<UserEntityUserRole> userEntityUserRoles = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public UserEntity(String login, String name, String firstName, String lastName, Long lastSeen, Boolean isAccess,
                      String pictureExtention, String password, String email, String organization, Boolean isDeleted) {
        this.login = login;
        this.isDeleted = isDeleted;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastSeen = lastSeen;
        this.isAccess = isAccess;
        this.pictureExtention = pictureExtention;
        this.password = password;
        this.email = email;
        this.organization = organization;
    }
    @JsonIgnore
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getPictureExtention() {
        return pictureExtention;
    }

    public void setPictureExtention(String pictureExtention) {
        this.pictureExtention = pictureExtention;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;
    }
    @JsonIgnore
    public Boolean getAccess() {
        return isAccess;
    }

    public void setAccess(Boolean access) {
        isAccess = access;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<UserEntityUserRole> getUserEntityUserRoles() {
        return userEntityUserRoles;
    }

    public void setUserEntityUserRoles(List<UserEntityUserRole> userEntityUserRoles) {
        this.userEntityUserRoles = userEntityUserRoles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
