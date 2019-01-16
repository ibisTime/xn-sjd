package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/***
 * 添加搜索历史
 * @author: silver 
 * @since: Jan 15, 2019 4:09:52 PM 
 * @history:
 */
public class XN629650Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    @NotBlank
    private String userId;

    // 分类（1树/2商品）
    @NotBlank
    private String type;

    // 内容
    @NotBlank
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
