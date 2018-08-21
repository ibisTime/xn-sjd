package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Blacklist;
import com.ogc.standard.domain.User;

public interface IBlacklistBO extends IPaginableBO<Blacklist> {

    public Long saveBlacklist(User user, String type, String updater,
            String remark);

    public int refreshBlacklist(Long id, String updater, String remark);

    public List<Blacklist> queryBlacklistList(Blacklist condition);

    public Blacklist getBlacklist(Long id);

    public void isAddBlacklist(String userId, String type);

}
