package com.ogc.standard.dto.res;

/**
 * 是否存在未读消息
 * @author: silver 
 * @since: Nov 23, 2018 10:38:32 AM 
 * @history:
 */
public class XN629788Res {
    // 是否存在未读
    private String existsUnread;

    public String getExistsUnread() {
        return existsUnread;
    }

    public void setExistsUnread(String existsUnread) {
        this.existsUnread = existsUnread;
    }

    public XN629788Res(String existsUnread) {
        super();
        this.existsUnread = existsUnread;
    }

}
