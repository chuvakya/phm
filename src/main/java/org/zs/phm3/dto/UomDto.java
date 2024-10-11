package org.zs.phm3.dto;


public class UomDto {

    private Integer id;
    private String text;

    public UomDto(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public UomDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
