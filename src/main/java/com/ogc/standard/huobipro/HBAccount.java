package com.ogc.standard.huobipro;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:21:54 
 * @history:
 */
public class HBAccount {

    private Long id;

    private String state;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
