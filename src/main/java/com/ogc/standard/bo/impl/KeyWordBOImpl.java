package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IKeyWordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IKeyWordDAO;
import com.ogc.standard.domain.KeyWord;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.exception.BizException;

@Component
public class KeyWordBOImpl extends PaginableBOImpl<KeyWord>
        implements IKeyWordBO {

    @Autowired
    private IKeyWordDAO keyWordDAO;

    @Override
    public boolean isKeyWordExist(Integer id) {
        KeyWord condition = new KeyWord();
        condition.setId(id);
        if (keyWordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isKeyWordExist(String word) {
        KeyWord condition = new KeyWord();
        condition.setWord(word);
        if (keyWordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveKeyWord(String word, String level, String reaction,
            String remark, String updater) {
        KeyWord data = new KeyWord();
        data.setWord(word);
        data.setLevel(level);
        data.setReaction(reaction);
        data.setRemark(remark);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        keyWordDAO.insert(data);
    }

    @Override
    public void removeKeyWord(Integer id) {
        KeyWord data = new KeyWord();
        data.setId(id);
        keyWordDAO.delete(data);
    }

    @Override
    public void refreshKeyWord(Integer id, String word, String level,
            String reaction, String remark, String updater) {
        KeyWord data = new KeyWord();
        data.setId(id);
        data.setWord(word);
        data.setLevel(level);
        data.setReaction(reaction);
        data.setRemark(remark);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        keyWordDAO.updateKeyWord(data);
    }

    @Override
    public KeyWord checkContent(String content) {
        KeyWord result = null;
        if (StringUtils.isNotBlank(content)) {
            List<KeyWord> allList = keyWordDAO.selectList(new KeyWord());
            for (KeyWord keyWord : allList) {
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
    public List<KeyWord> queryKeyWordList(KeyWord condition) {
        return keyWordDAO.selectList(condition);
    }

    @Override
    public KeyWord getKeyWord(Integer id) {
        KeyWord data = null;
        KeyWord condition = new KeyWord();
        condition.setId(id);
        data = keyWordDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "keyName记录不存在");
        }
        return data;
    }

}
