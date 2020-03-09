package com.project.demo.VO;

import com.project.demo.DO.ProductImageDO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/4 9:15
 */
public class ProductFirstVO {
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String shopName;

    private String desc;

    private String url;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
