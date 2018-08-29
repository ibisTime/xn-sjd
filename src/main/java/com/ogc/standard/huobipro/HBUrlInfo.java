package com.ogc.standard.huobipro;

import java.util.Map;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:23:21 
 * @history:
 */
public class HBUrlInfo {

    private String url;

    private Map<String, String> parames;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParames() {
        return parames;
    }

    public void setParames(Map<String, String> parames) {
        this.parames = parames;
    }

}
