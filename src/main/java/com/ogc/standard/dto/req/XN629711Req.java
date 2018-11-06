/**
 * @Title XN629710Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:08:41 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午11:08:41 
 * @history:
 */
public class XN629711Req {

    // 购物车编号列表
    @NotBlank
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
