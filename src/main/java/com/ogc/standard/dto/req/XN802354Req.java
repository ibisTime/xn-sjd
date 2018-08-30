/**
 * @Title XN802354Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月30日 下午8:40:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * 打回取现订单（不支付）
 * @author: dl 
 * @since: 2018年8月30日 下午8:40:38 
 * @history:
 */
public class XN802354Req {

    // 取现订单编号集
    @NotEmpty
    private List<String> codeList;

    // 支付说明
    @NotBlank
    private String payNote;

    // 支付人
    @NotBlank
    private String payUser;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

}
