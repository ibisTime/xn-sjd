/**
 * @Title SYSDictAOImpl.java 
 * @Package com.xnjr.moom.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:19:00 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISYSDictAO;
import com.ogc.standard.bo.ISYSDictBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSDict;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.enums.EDictType;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:19:00 
 * @history:
 */
@Service
public class SYSDictAOImpl implements ISYSDictAO {
    @Autowired
    ISYSDictBO sysDictBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    /** 
     * @see com.xnjr.base.ao.ISYSDictAO#addSYSDict(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Long addSYSDict(String type, String parentKey, String key,
            String value, String updater, String remark) {
        if (EDictType.SECOND.getCode().equals(type)) {
            if (StringUtils.isBlank(parentKey)) {
                throw new BizException("xn000000", "第二层字典数据，parentKey不能为空");
            }
            // 查看父节点是否存在
            SYSDict fDict = new SYSDict();
            fDict.setDkey(parentKey);
            fDict.setType(EDictType.FIRST.getCode());
            if (sysDictBO.getTotalCount(fDict) <= 0) {
                throw new BizException("xn000000", "parentKey不存在");
            }
            // 第二层数据字典 在当前父节点下key不能重复
            SYSDict condition = new SYSDict();
            condition.setDkey(key);
            condition.setParentKey(parentKey);
            condition.setType(EDictType.SECOND.getCode());
            if (sysDictBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "当前节点下，key不能为重复");
            }
        } else if (EDictType.FIRST.getCode().equals(type)) {
            // 第一层数据字典 key不能重复
            SYSDict condition = new SYSDict();
            condition.setDkey(key);
            condition.setType(EDictType.FIRST.getCode());
            if (sysDictBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "第一层key不能为重复");
            }
        } else {
            throw new BizException("xn000000", "type类型不在枚举类中 0-第一层 1-第二层");
        }
        SYSDict sysDict = new SYSDict();
        sysDict.setType(type);
        if (EDictType.SECOND.getCode().equals(type)) {
            sysDict.setParentKey(parentKey);
        }
        sysDict.setDkey(key);
        sysDict.setDvalue(value);
        sysDict.setUpdater(updater);
        sysDict.setRemark(remark);
        return sysDictBO.saveSYSDict(sysDict);
    }

    @Override
    public int dropSYSDict(Long id) {
        int count = 0;
        if (id != null) {
            SYSDict condition = new SYSDict();
            condition.setId(id);
            if (sysDictBO.getTotalCount(condition) <= 0) {
                throw new BizException("xn000000", "id记录不存在");
            }
            count = sysDictBO.removeSYSDict(id);
        }
        return count;
    }

    @Override
    public int editSYSDict(Long id, String value, String updater,
            String remark) {
        SYSDict data = new SYSDict();
        data.setId(id);
        data.setDvalue(value);
        data.setUpdater(updater);
        data.setRemark(remark);
        return sysDictBO.refreshSYSDict(data);
    }

    @Override
    public Paginable<SYSDict> querySYSDictPage(int start, int limit,
            SYSDict condition) {
        Paginable<SYSDict> page = sysDictBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (SYSDict sysDict : page.getList()) {
                initDict(sysDict);
            }
        }

        return page;
    }

    @Override
    public List<SYSDict> querySysDictList(SYSDict condition) {
        List<SYSDict> list = sysDictBO.querySYSDictList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (SYSDict sysDict : list) {
                initDict(sysDict);
            }
        }

        return list;
    }

    @Override
    public SYSDict getSYSDict(Long id) {
        SYSDict sysDict = null;
        if (id != null) {
            SYSDict condition = new SYSDict();
            condition.setId(id);
            if (sysDictBO.getTotalCount(condition) <= 0) {
                throw new BizException("xn000000", "id记录不存在");
            }
            sysDict = sysDictBO.getSYSDict(id);

            initDict(sysDict);
        }
        return sysDict;
    }

    private void initDict(SYSDict sysDict) {
        if (EUser.ADMIN.getCode().equals(sysDict.getUpdater())) {
            sysDict.setUpdaterName(EUser.ADMIN.getValue());
        }

        if (!EUser.ADMIN.getCode().equals(sysDict.getUpdater())) {
            SYSUser user = sysUserBO.getSYSUserUnCheck(sysDict.getUpdater());
            if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
                sysDict.setUpdaterName(EUser.ADMIN.getValue());
            } else {
                sysDict.setUpdaterName(user.getLoginName());
            }
        }

    }
}
