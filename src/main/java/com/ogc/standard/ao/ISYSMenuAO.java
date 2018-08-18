package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.dto.req.XN630010Req;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午7:38:53 
 * @history:
 */
public interface ISYSMenuAO {

    String DEFAULT_ORDER_COLUMN = "order_no";

    /**
     * 添加菜单
     * @param data
     * @return 
     * @create: 2016年4月16日 下午7:39:51 Gejin
     * @history:
     */
    public String addSYSMenu(XN630010Req req);

    /**
     * 删除菜单
     * @param code
     * @return 
     * @create: 2016年4月16日 下午7:40:31 Gejin
     * @history:
     */
    public boolean dropSYSMenu(String code);

    /**
     * 修改菜单
     * @param data
     * @return 
     * @create: 2016年4月16日 下午7:41:17 Gejin
     * @history:
     */

    public boolean editSYSMenu(SYSMenu data);

    /**
     * 分页查询
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年4月16日 下午7:42:47 Gejin
     * @history:
     */
    public Paginable<SYSMenu> querySYSMenuPage(int start, int limit,
            SYSMenu condition);

    /**
     * 列表查询
     * @param condition
     * @return 
     * @create: 2016年4月16日 下午7:42:17 Gejin
     * @history:
     */

    public List<SYSMenu> querySYSMenuList(SYSMenu condition);

    /**
     * 获取详情
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年4月16日 下午7:42:47 Gejin
     * @history:
     */
    public SYSMenu getSYSMenu(String code);
}
