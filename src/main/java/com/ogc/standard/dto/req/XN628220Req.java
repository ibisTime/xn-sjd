package com.ogc.standard.dto.req;

import java.util.List;

public class XN628220Req {

    // 关键字导入列表
    private List<XN801010CReq> keywordList;

    // 所属公司(必填)
    private String companyCode;

    // 系统编号(必填)
    private String systemCode;

    public List<XN801010CReq> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<XN801010CReq> keywordList) {
        this.keywordList = keywordList;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
