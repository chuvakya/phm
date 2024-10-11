package org.zs.phm3.models;

import org.zs.phm3.models.basic.IdEntity;

//@Entity
public class IdTextReturn extends IdEntity {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IdTextReturn() {
    }

    public IdTextReturn(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
