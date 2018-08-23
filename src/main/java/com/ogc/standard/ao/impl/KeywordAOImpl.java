package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.exception.BizException;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:52:40 
 * @history:
 */
@Service
public class KeywordAOImpl implements IKeywordAO {

    @Autowired
    private IKeywordBO keywordBO;

    @Override
    public void addKeyword(String word, String level, String reaction,
            String remark, String updater) {
        if (keywordBO.isKeywordExist(word)) {
            throw new BizException("xn0000", "关键字记录已存在，请更换关键字！");
        }

        keywordBO.saveKeyword(word, level, reaction, remark, updater);
    }

    @Override
    public void dropKeyword(Integer id) {
        keywordBO.removeKeyword(id);
    }

    @Override
    public void editKeyword(Integer id, String word, String level,
            String reaction, String remark, String updater) {
        if (keywordBO.isKeywordExist(word)) {
            throw new BizException("xn0000", "关键字记录已存在，请更换关键字！");
        }

        keywordBO.refreshKeyword(id, word, level, reaction, remark, updater);
    }

    @Override
    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition) {
        return keywordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Keyword> queryKeywordList(Keyword condition) {
        return keywordBO.queryKeywordList(condition);
    }

    @Override
    public Keyword getKeyword(Integer id) {
        return keywordBO.getKeyword(id);
    }
}
