package com.huida.service;

import com.huida.bean.Scstate;
import com.huida.bean.ScstateComment;
import com.huida.dao.ScstateDao;
import com.huida.utils.FormatTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ScstateService")
public class ScstateService {
    @Autowired
    ScstateDao scstateDao;

    public static List<Scstate> scstateList=null;

    public void issueScstate(Scstate scstate) {
        scstateDao.issueScstate(scstate);
    }

    public List<Scstate> loading(int page) {
        scstateList = scstateDao.loading();
        page=page*10;
        if(page+10>scstateList.size()){
            for (int i = page; i < scstateList.size(); i++) {
                scstateList.get(i).setFormatTime(FormatTimeUtils.format(scstateList.get(i).getIssueTime()));
                scstateList.get(i).setScstateCommentList(scstateDao.getComment(scstateList.get(i).getScstateID()));
            }
            return scstateList.subList(page,scstateList.size());
        }
        for (int i = page; i < page+10; i++) {
            scstateList.get(i).setFormatTime(FormatTimeUtils.format(scstateList.get(i).getIssueTime()));
            scstateList.get(i).setScstateCommentList(scstateDao.getComment(scstateList.get(i).getScstateID()));
        }
        return scstateList.subList(page,page+10);
    }

    public void issueComment(ScstateComment scstateComment) {
        scstateDao.issueComment(scstateComment);
    }

}
