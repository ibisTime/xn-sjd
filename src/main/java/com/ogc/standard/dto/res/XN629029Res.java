/**
 * @Title XN625000Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午5:02:42 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

/**
 * 查询产品品种
 * @author: silver 
 * @since: Jan 15, 2019 3:15:50 PM 
 * @history:
 */
public class XN629029Res {

    // 品种
    private String variety;

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public XN629029Res(String variety) {
        super();
        this.variety = variety;
    }

}
