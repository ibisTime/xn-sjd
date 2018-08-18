/**
 * @Title SYSConfig.java 
 * @Package com.xnjr.moom.domain 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月16日 下午9:45:46 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
 *  系统配置
 * @author: dl 
 * @since: 2018年8月18日 下午2:46:18 
 * @history:
 */
public class SYSConfig extends ABaseDO {
    private static final long serialVersionUID = -6136818068168453506L;

    // ***********db properties***********
    // 编号（自增长）
    private Long id;

    // 类型
    private String type;

    // key值
    private String ckey;

    // value值
    private String cvalue;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db properties***********

    private String ckeyForQuery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCkeyForQuery() {
        return ckeyForQuery;
    }

    public void setCkeyForQuery(String ckeyForQuery) {
        this.ckeyForQuery = ckeyForQuery;
    }

}
