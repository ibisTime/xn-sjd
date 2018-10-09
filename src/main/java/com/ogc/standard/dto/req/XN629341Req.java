package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午1:09:19 
 * @history:
 */
public class XN629341Req {

    // 文章编号
    @NotBlank
    private String code;

    // 类型(0保存1提交)
    @NotBlank
    private String dealType;

    // 认养权编号
    private String adoptTreeCode;

    // 树的编号
    private String treeNo;

    // 权限（1公开、2私密、3仅好友可见）
    private String openLevel;

    // 标题
    private String title;

    // 内容
    private String content;

    // 照片
    private List<String> photoList;

    // 更新人
    @NotBlank
    private String updater;

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getTreeNo() {
        return treeNo;
    }

    public void setTreeNo(String treeNo) {
        this.treeNo = treeNo;
    }

    public String getOpenLevel() {
        return openLevel;
    }

    public void setOpenLevel(String openLevel) {
        this.openLevel = openLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

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

}
