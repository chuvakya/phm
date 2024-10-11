package org.zs.phm3.dto.ptl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zs.phm3.dto.Dto;

@JsonIgnoreProperties(value = {"new"})
public class DtoIdIntName{// implements Dto {
    Integer id;
    String name;
//    Boolean selected;

    public DtoIdIntName(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

/*    public DtoIdIntName(Integer id, String name, Boolean selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }*/
}
