package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AdoptOrderTreeAOImpl implements IAdoptOrderTreeAO {

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Autowired
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private IBizLogBO bizLogBO;

    public void doDailyAdoptOrderTree() {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);
        for (AdoptOrderTree adoptOrderTree : list) {
        }
    }

    @Override
    @Transactional
    public void giveTree(String code, String userId, String toMobile) {
        User user = userBO.getUser(userId);
        User toUser = userBO.getUserByMobile(toMobile);

        // 终止现有认养权，产生新的认养权，更改认养权持有人
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        if (EAdoptOrderTreeStatus.ADOPT.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "认养权不在认养中");
        }
        adoptOrderTreeBO.giveTree(data, user, toUser);
        // 新增赠送树记录
        giveTreeRecordBO.saveGiveTreeRecord(userId, toUser.getUserId(),
            data.getCode());
    }

    @Override
    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition) {
        Paginable<AdoptOrderTree> page = adoptOrderTreeBO.getPaginable(start,
            limit, condition);
        for (AdoptOrderTree adoptOrderTree : page.getList()) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return page;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition) {
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);
        for (AdoptOrderTree adoptOrderTree : list) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return list;
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        initAdoptOrderTree(data);
        return data;
    }

    private void initAdoptOrderTree(AdoptOrderTree data) {
        Tree tree = treeBO.getTreeByTreeNumber(data.getTreeNumber());
        data.setTree(tree);
        User user = userBO.getUser(data.getCurrentHolder());
        data.setUser(user);
    }

}
