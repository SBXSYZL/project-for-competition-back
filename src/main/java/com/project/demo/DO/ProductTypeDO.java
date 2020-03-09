package com.project.demo.DO;

public class ProductTypeDO {
    private Integer id;

    private Integer typeGroupId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeGroupId() {
        return typeGroupId;
    }

    public void setTypeGroupId(Integer typeGroupId) {
        this.typeGroupId = typeGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}