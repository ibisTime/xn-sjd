package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改产品分类
 * @author: silver 
 * @since: Jan 17, 2019 2:22:48 PM 
 * @history:
 */
public class XN629016Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    @NotBlank
    private String code;

    // 产品大类
    @NotBlank
    private String parentCategoryCode;

    // 产品小类
    @NotBlank
    private String categoryCode;

    // 更新人
    @NotBlank
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
