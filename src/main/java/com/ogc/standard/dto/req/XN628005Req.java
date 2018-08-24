package com.ogc.standard.dto.req;

/**
 * 分页查询关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:11:15 
 * @history:
 */
public class XN628005Req extends APageReq {
    private static final long serialVersionUID = -8635157418061288998L;

    // 关键字
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
