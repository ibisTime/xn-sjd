package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN629760Req {

    @NotBlank
    private String word;

    @NotBlank
    private String reaction;

    @NotBlank
    private String updater;

    private String remark;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
