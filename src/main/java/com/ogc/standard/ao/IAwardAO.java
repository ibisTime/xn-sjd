/**
 * @Title IAwardAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午7:19:18 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Award;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午7:19:18 
 * @history:
 */

public interface IAwardAO {
    public void settle(Long id, String isSettle, String remark);

    public Paginable<Award> queryAwardPage(int start, int limit,
            Award condition);

    public List<Award> queryAwardList(Award condition);
}
