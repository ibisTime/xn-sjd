package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IKeywordDAO;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.exception.BizException;

@Component
public class KeywordBOImpl extends PaginableBOImpl<Keyword>
        implements IKeywordBO {

    @Autowired
    private IKeywordDAO keyWordDAO;

    @Override
    public boolean isKeywordExist(Integer id) {
        Keyword condition = new Keyword();
        condition.setId(id);
        if (keyWordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isKeywordExist(String word) {
        Keyword condition = new Keyword();
        condition.setWord(word);
        if (keyWordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveKeyword(String word, String level, String reaction,
            String remark, String updater) {
        Keyword data = new Keyword();
        data.setWord(word);
        data.setLevel(level);
        data.setReaction(reaction);
        data.setRemark(remark);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        keyWordDAO.insert(data);
    }

    @Override
    public void removeKeyword(Integer id) {
        Keyword data = new Keyword();
        data.setId(id);
        keyWordDAO.delete(data);
    }

    @Override
    public void refreshKeyword(Integer id, String word, String level,
            String reaction, String remark, String updater) {
        Keyword data = new Keyword();
        data.setId(id);
        data.setWord(word);
        data.setLevel(level);
        data.setReaction(reaction);
        data.setRemark(remark);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        keyWordDAO.updateKeyword(data);
    }

    @Override
    public Keyword checkContent(String content) {
        Keyword result = null;
        if (StringUtils.isNotBlank(content)) {
            List<Keyword> allList = keyWordDAO.selectList(new Keyword());
            for (Keyword keyWord : allList) {
                if (content.indexOf(keyWord.getWord()) >= 0) {
                    result = keyWord;
                    if (keyWord.getReaction()
                        .equals(EKeyWordReaction.REFUSE.getCode())) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String replaceKeyword(String content, String word) {
        String replacement = "";
        for (int i = 0; i < word.length(); i++) {
            replacement += "*";
        }
        content = content.replaceAll(word, replacement);
        return content;
    }

    @Override
    public List<Keyword> queryKeywordList(Keyword condition) {
        return keyWordDAO.selectList(condition);
    }

    @Override
    public Keyword getKeyword(Integer id) {
        Keyword data = null;
        Keyword condition = new Keyword();
        condition.setId(id);
        data = keyWordDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "keyName记录不存在");
        }
        return data;
    }

}
