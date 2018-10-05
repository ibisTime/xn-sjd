package com.ogc.standard.dto.res;

import com.ogc.standard.domain.User;

/**
 * 本周能量比拼
 * @author: silver 
 * @since: Oct 5, 2018 3:06:16 PM 
 * @history:
 */
public class XN629900Res {
    // 用户本周总量
    private long userWeekQuantity;

    // 好友本周总量
    private long toUserWeekQuantity;

    // 用户信息
    private User userInfo;

    // 好友用户信息
    private User toUserInfo;

    public long getUserWeekQuantity() {
        return userWeekQuantity;
    }

    public void setUserWeekQuantity(long userWeekQuantity) {
        this.userWeekQuantity = userWeekQuantity;
    }

    public long getToUserWeekQuantity() {
        return toUserWeekQuantity;
    }

    public void setToUserWeekQuantity(long toUserWeekQuantity) {
        this.toUserWeekQuantity = toUserWeekQuantity;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public User getToUserInfo() {
        return toUserInfo;
    }

    public void setToUserInfo(User toUserInfo) {
        this.toUserInfo = toUserInfo;
    }

}
