package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:35:33 
 * @history:
 */
public class XN629003Req extends BaseReq {

    private static final long serialVersionUID = 723605155250286738L;

    // 编号
    private String code;

    // 编号列表
    private List<String> codeList;

    // 更新人
    @NotBlank
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
