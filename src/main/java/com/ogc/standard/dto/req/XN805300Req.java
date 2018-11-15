/**
 * @Title XN805300Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午2:54:06 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 保存消息（草稿）
 * @author: dl 
 * @since: 2018年8月22日 下午2:54:06 
 * @history:
 */
public class XN805300Req {

    // 标题
    @NotBlank
    private String title;

    // 消息内容
    @NotBlank
    private String content;

    // 发布时间
    @NotBlank
    private String publishDatetime;

    // 消息类型
    @NotBlank
    private String type;

    // 对象类型
    @NotBlank
    private String object;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

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

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(String publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

}
