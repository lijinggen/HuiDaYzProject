package com.huida.service;

import com.huida.bean.Wenda;
import com.huida.bean.WendaComment;
import com.huida.dao.WendaDao;
import com.huida.utils.FormatTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service("WendaService")
public class WendaService {

    @Autowired
    WendaDao wendaDao;

    public void issueWenda(Wenda wenda){
        wendaDao.issueWenda(wenda);
    }
    public List<Wenda> loading(int page){
        List<Wenda> wendaList=wendaDao.loding(page*10);
        for(int i=0;i<wendaList.size();i++){
            wendaList.get(i).setFormatTime(FormatTimeUtils.format(wendaList.get(i).getIssueTime()));
            List<WendaComment >wendaCommentList=wendaDao.loadingComment(wendaList.get(i).getWendaID());
            for(int k=0;k<wendaCommentList.size();k++){
                wendaCommentList.get(k).setFormatTime(FormatTimeUtils.fomatTime(wendaCommentList.get(k).getIssueTime()));
            }
            wendaList.get(i).setWendaCommentList(wendaCommentList);
        }

        return wendaList;
    }

    public void issueComment(WendaComment wendaComment){
        wendaDao.issueComment(wendaComment);
    }

}
