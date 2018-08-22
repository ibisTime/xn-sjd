package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IKeyWordAO;
import com.ogc.standard.bo.IKeyWordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.KeyWord;
import com.ogc.standard.exception.BizException;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:52:40 
 * @history:
 */
@Service
public class KeyWordAOImpl implements IKeyWordAO {

    @Autowired
    private IKeyWordBO keyWordBO;

    @Override
    public void addKeyWord(String word, String level, String reaction,
            String remark, String updater) {
        if (keyWordBO.isKeyWordExist(word)) {
            throw new BizException("xn0000", "关键字记录已存在，请更换关键字！");
        }

        keyWordBO.saveKeyWord(word, level, reaction, remark, updater);
    }

    @Override
    public void dropKeyWord(Integer id) {
        if (!keyWordBO.isKeyWordExist(id)) {
            throw new BizException("xn0000", "关键字记录编号不存在！");
        }
        keyWordBO.removeKeyWord(id);
    }

    @Override
    public void editKeyWord(Integer id, String word, String level,
            String reaction, String remark, String updater) {
        if (!keyWordBO.isKeyWordExist(id)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        if (keyWordBO.isKeyWordExist(word)) {
            throw new BizException("xn0000", "关键字记录已存在，请更换关键字！");
        }

        keyWordBO.refreshKeyWord(id, word, level, reaction, remark, updater);
    }

    @Override
    public Paginable<KeyWord> queryKeyWordPage(int start, int limit,
            KeyWord condition) {
        return keyWordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<KeyWord> queryKeyWordList(KeyWord condition) {
        return keyWordBO.queryKeyWordList(condition);
    }

    @Override
    public KeyWord getKeyWord(Integer id) {
        return keyWordBO.getKeyWord(id);
    }
}
