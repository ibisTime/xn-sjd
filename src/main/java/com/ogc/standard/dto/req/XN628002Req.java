package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:02:12 
 * @history:
 */
public class XN628002Req {
    // 编号
    @NotBlank
    private String id;

    // 词语
    @NotBlank
    private String word;

    // 作用等级
    @NotBlank
    private String level;

    // 反应(1 直接拦截/2 替换**/3 审核)
    @NotBlank
    private String reaction;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
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
}
