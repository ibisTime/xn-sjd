package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下架产品
 * @author: silver 
 * @since: 2018年9月27日 上午9:51:25 
 * @history:
 */
public class XN629015Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    private String code;

    // 编号列表
    private List<String> codeList;

    // 更新人
    @NotBlank
    private String updater;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
