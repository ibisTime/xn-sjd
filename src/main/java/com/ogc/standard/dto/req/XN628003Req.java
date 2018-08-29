package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class XN628003Req {
    // 关键字导入列表
    private List<XN628000Req> keywordList;

    @NotBlank
    private String updater;

    public List<XN628000Req> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<XN628000Req> keywordList) {
        this.keywordList = keywordList;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
