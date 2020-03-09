package com.project.demo.VO;

import java.util.List;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/3 16:42
 */
public class ProductTypeVO {
    private String title;
    private Integer id;
    private List child;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List getChild() {
        return child;
    }

    public void setChild(List child) {
        this.child = child;
    }
}
