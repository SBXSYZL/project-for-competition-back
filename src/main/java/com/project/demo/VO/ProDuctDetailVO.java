package com.project.demo.VO;

import com.project.demo.DO.CommentDO;
import com.project.demo.DO.ProductImageDO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/4 17:51
 */
public class ProDuctDetailVO {
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer likeCnt;

    private Integer commentCnt;

    private Integer saleCnt;

    private Integer shopId;

    private String shopName;

    private Integer typeId;

    private String desc;

    private Date postDate;

    private List<ProductImageDO> images;

    private Map comments;

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

    public Integer getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public Integer getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(Integer commentCnt) {
        this.commentCnt = commentCnt;
    }

    public Integer getSaleCnt() {
        return saleCnt;
    }

    public void setSaleCnt(Integer saleCnt) {
        this.saleCnt = saleCnt;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public List<ProductImageDO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDO> images) {
        this.images = images;
    }

    public Map getComments() {
        return comments;
    }

    public void setComments(Map comments) {
        this.comments = comments;
    }
}
