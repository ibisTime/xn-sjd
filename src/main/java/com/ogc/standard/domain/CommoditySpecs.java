/**
 * @Title CommoditySpecs.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午4:42:53 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 商品规格
 * @author: taojian 
 * @since: 2018年11月2日 下午4:42:53 
 * @history:
 */
public class CommoditySpecs extends ABaseDO {

    private static final long serialVersionUID = 5036576645673751634L;

    // 编号
    private String code;

    // 编号
    private Long id;

    // 商品编号
    private String commodityCode;

    // 规格名称
    private String name;

    // 价格
    private BigDecimal price;

    // 库存
    private Long inventory;

    // 备注
    private String remark;

    /**********DB Properties*************/
    // 编号
    private List<Long> notInIdList;

    // 是否被使用
    private String used;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
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

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getNotInIdList() {
        return notInIdList;
    }

    public void setNotInIdList(List<Long> notInIdList) {
        this.notInIdList = notInIdList;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

}
