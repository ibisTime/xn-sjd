package com.ogc.standard.ao;

public interface IAttentionAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 是否关注
     * @param userId
     * @param groupCode 
     * @create: 2018年8月21日 下午2:05:19 lei
     * @history:
     */
    public void isAttention(String userId, String groupCode);

    /**
     * 是否提醒
     * @param userId
     * @param groupCode 
     * @create: 2018年8月21日 下午2:04:56 lei
     * @history:
     */
    public void isRemind(String userId, String groupCode);

}
