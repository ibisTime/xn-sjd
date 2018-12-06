package com.ogc.standard.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IUserExtBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IUserExtDAO;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.req.XN805072Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.exception.BizException;

@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt>
        implements IUserExtBO {
    @Autowired
    private IUserExtDAO userExtDAO;

    @Override
    public long getTotalCount(UserExt condition) {
        return userExtDAO.selectTotalCount(condition);
    }

    @Override
    public Paginable<UserExt> getPaginable(int start, UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Paginable<UserExt> getPaginable(int start, int pageSize,
            UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void refreshUserExt(UserExt data) {
        userExtDAO.updateUserExt(data);
    }

    @Override
    public String addUserExt(String userId) {
        UserExt data = new UserExt();
        data.setUserId(userId);
        userExtDAO.insert(data);
        return userId;

    }

    @Override
    public void personAuth(String userId, String idPic, String backIdPic,
            String introduce) {
        UserExt data = getUserExt(userId);
        UserExt userExt = new UserExt();
        userExt.setGender(data.getGender());
        userExt.setUserId(userId);
        userExt.setIdPic(idPic);
        userExt.setBackIdPic(backIdPic);
        userExt.setIntroduce(introduce);
        userExt.setAuthStatus(EBoolean.YES.getCode());
        userExt.setPersonAuthStatus(EBoolean.YES.getCode());
        userExtDAO.updateUserExt(userExt);
    }

    @Override
    public void companyAuth(XN805072Req req) {
        UserExt data = getUserExt(req.getUserId());
        UserExt userExt = new UserExt();
        userExt.setUserId(req.getUserId());
        userExt.setGender(data.getGender());
        userExt.setCompanyName(req.getCompanyName());
        userExt.setBussinessLicense(req.getBussinessLicense());

        userExt.setCompanyAddress(req.getCompanyAddress());
        userExt.setCompanyChargerName(req.getCompanyChargerName());
        userExt.setCompanyChargerMobile(req.getCompanyChargerMobile());
        userExt.setCompanyChargerIdNo(req.getCompanyChargerIdNo());
        userExt.setBussinessLicenseId(req.getBussinessLicenseId());

        userExt.setCompanyBank(req.getCompanyBank());
        userExt.setCompanyBankNumber(req.getCompanyBankNumber());
        userExt.setCompanyContactName(req.getCompanyContactName());
        userExt.setCompanyContactMobile(req.getCompanyContactMobile());
        userExt.setCompanyContactAddress(req.getCompanyContactAddress());

        userExt.setCompanyChargerIdPic(req.getCompanyChargerIdPic());
        userExt.setCompanyChargerBackIdPic(req.getCompanyChargerBackIdPic());
        userExt.setCompanyIntroduce(req.getCompanyIntroduce());

        userExt.setAuthStatus(EBoolean.YES.getCode());
        userExt.setCompanyAuthStatus(EBoolean.YES.getCode());
        userExtDAO.updateUserExt(userExt);
    }

    @Override
    public UserExt getUserExt(String userId) {
        UserExt condition = new UserExt();
        condition.setUserId(userId);
        return userExtDAO.getUserExt(condition);
    }

    @Override
    public void isEmailExit(String email) {
        if (StringUtils.isNotBlank(email)) {
            UserExt condition = new UserExt();
            condition.setEmail(email);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "邮箱已经存在");
            }
        }
    }

    @Override
    public void refreshEmail(UserExt data) {
        userExtDAO.bindEmail(data);

    }

}
