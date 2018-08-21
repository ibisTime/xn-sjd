package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Blacklist;

public interface IBlacklistAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    /**
     * 将某个用户拉入黑名单，一种类型只能拉黑一次
     * @param userId
     * @param type
     * @param updater
     * @param remark 
     * @create: 2018年8月20日 下午8:11:25 dl
     * @history:
     */
    public void addBlacklist(String userId, String type, String updater,
            String remark);

    /**
     * 解除拉黑记录（假删除为了查询历史）
     * @param id
     * @param updater
     * @param remark
     * @return 
     * @create: 2018年8月20日 下午8:11:37 dl
     * @history:
     */
    public int editBlacklist(Long id, String updater, String remark);

    public Paginable<Blacklist> queryBlacklistPage(int start, int limit,
            Blacklist condition);

    public List<Blacklist> queryBlacklistList(Blacklist condition);

    public Blacklist getBlacklist(Long id);

}
