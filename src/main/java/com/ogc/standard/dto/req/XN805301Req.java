/**
 * @Title XN805301Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午2:58:04 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 发布消息
 * @author: dl 
 * @since: 2018年8月22日 下午2:58:04 
 * @history:
 */
public class XN805301Req {

    // 消息编号
    private String code;

    // 消息内容
    @NotBlank
    private String content;

    // 标题
    @NotBlank
    private String title;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    // 消息类型
    @NotBlank
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
