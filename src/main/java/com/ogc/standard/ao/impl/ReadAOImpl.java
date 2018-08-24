/**
 * @Title ReadAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午9:15:00 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IReadAO;
import com.ogc.standard.bo.IReadBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Read;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午9:15:00 
 * @history:
 */
@Service
public class ReadAOImpl implements IReadAO {

    @Autowired
    private IReadBO readBO;

    @Override
    public void editStatusRead(long id) {
        readBO.refreshStatusRead(id);
    }

    @Override
    public void editStatusDelete(long id) {
        readBO.refreshStatusDelete(id);

    }

    @Override
    public Paginable<Read> queryReadPage(int start, int limit, Read condition) {
        return readBO.getPaginable(start, limit, condition);
    }

    @Override
    public Read getRead(long id) {
        return readBO.getRead(id);
    }

}
