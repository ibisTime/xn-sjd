/**
 * @Title IJourAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:05:07 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.dto.res.XN629905Res;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:05:07 
 * @history:
 */
public interface IJourAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 流水对账(包括现在和历史流水)
    public void checkJour(String code, BigDecimal checkAmount, String checkUser,
            String checkNote, String systemCode);

    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code);

    public XN629905Res getTotalBenefitAmount(String userId, String accountType,
            String dateStart, String dateEnd);

}
