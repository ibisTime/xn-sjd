package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.dao.IBizLogDAO;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EBizLogType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;

@Component
public class BizLogBOImpl extends PaginableBOImpl<BizLog> implements IBizLogBO {

    @Autowired
    private IBizLogDAO bizLogDAO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IUserBO userBO;

    public long leaveMessage(String adoptTreeCode, String note, String userId) {
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(adoptTreeCode);

        BizLog data = new BizLog();
        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptOrderTree.getCurrentHolder());
        data.setType(EBizLogType.LEAVE_MESSAGE.getCode());
        data.setNote(note);
        data.setUserId(userId);

        data.setCreateDatetime(new Date());
        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long gatherCarbonBubble(String adoptTreeCode, String adoptUserId,
            BigDecimal quantity, String userId, String type) {
        BizLog data = new BizLog();

        data.setAdoptUserId(adoptUserId);
        data.setAdoptTreeCode(adoptTreeCode);
        data.setType(type);
        data.setQuantity(quantity);
        data.setUserId(userId);

        data.setCreateDatetime(new Date());
        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long gatherNoCarbonBubble(String adoptTreeCode, String adoptUserId,
            String userId) {
        BizLog data = new BizLog();

        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setType(EBizLogType.GATHER_NO.getCode());
        data.setQuantity(BigDecimal.ZERO);
        data.setUserId(userId);

        data.setCreateDatetime(new Date());
        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long useShelter(String toolName, String adoptTreeCode,
            String adoptUserId, String userId) {
        BizLog data = new BizLog();

        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setType(EBizLogType.USE_SHELTER.getCode());
        data.setQuantity(BigDecimal.ZERO);
        data.setUserId(userId);
        data.setCreateDatetime(new Date());

        String note = null;
        if (userId.equals(adoptUserId)) {
            note = "我使用了" + toolName;
        } else {
            User user = userBO.getUserUnCheck(userId);
            String userName = null;
            if (null != user) {
                userName = user.getMobile();
                if (null != user.getNickname()) {
                    userName = user.getNickname();
                }
            }
            note = userName + "使用了" + toolName;
        }
        data.setNote(note);

        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long useGetall(String adoptTreeCode, String adoptUserId,
            String userId, BigDecimal quantity) {
        BizLog data = new BizLog();

        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setType(EBizLogType.GET_ALL.getCode());
        data.setQuantity(quantity);
        data.setUserId(userId);
        data.setCreateDatetime(new Date());

        String note = null;
        if (userId.equals(adoptUserId)) {
            note = "我使用了一键收取";
        } else {
            User user = userBO.getUserUnCheck(userId);
            String userName = null;
            if (null != user) {
                userName = user.getMobile();
                if (null != user.getNickname()) {
                    userName = user.getNickname();
                }
            }
            note = userName + "使用了一键收取";
        }
        data.setNote(note);

        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long sendBarrage(String adoptTreeCode, String adoptUserId,
            String userId, String note) {
        BizLog data = new BizLog();

        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setType(EBizLogType.BARRAGE.getCode());
        data.setUserId(userId);
        data.setNote(note);
        data.setCreateDatetime(new Date());

        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public long createCarbonBubble(String adoptTreeCode, String adoptUserId,
            BigDecimal quantity) {
        BizLog data = new BizLog();
        String quantityString = quantity.divide(new BigDecimal(1000))
            .toString();
        String note = "产生" + quantityString + "个碳泡泡";

        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setType(EBizLogType.CREATE_CB.getCode());
        data.setNote(note);
        data.setQuantity(quantity);
        data.setCreateDatetime(new Date());
        data.setUserId(ESysUser.SYS_USER.getCode());

        bizLogDAO.insert(data);

        return bizLogDAO.selectMaxId();
    }

    @Override
    public BigDecimal getWeekQuantitySum(String adoptUserId, String userId) {
        BizLog condition = new BizLog();
        condition.setAdoptUserId(adoptUserId);
        condition.setUserId(userId);
        condition.setCreateDatetimeStart(DateUtil.getBeginDayOfWeek());
        condition.setCreateDatetimeEnd(DateUtil.getEndDayOfWeek());

        List<String> typeList = new ArrayList<String>();
        typeList.add(EBizLogType.GATHER.getCode());
        condition.setTypeList(typeList);

        return bizLogDAO.selectQuantitySum(condition);
    }

    @Override
    public List<BizLog> queryBizLogList(BizLog condition) {
        return bizLogDAO.selectList(condition);
    }

    @Override
    public BizLog getBizLog(int id) {
        BizLog data = null;
        if (id != 0) {
            BizLog condition = new BizLog();
            condition.setId(id);
            data = bizLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "日志编号不存在");
            }
        }
        return data;
    }

}
