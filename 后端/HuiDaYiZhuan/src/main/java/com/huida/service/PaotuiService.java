package com.huida.service;

import com.huida.bean.Paotui;
import com.huida.dao.PaotuiDao;
import com.huida.utils.FormatTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PaotuiService")
public class PaotuiService {
    @Autowired
    PaotuiDao paotuiDao;

    public static List<Paotui> paotuiList=null;

    public void issuePaotui(Paotui paotui){
        paotuiDao.issuePaotui(paotui);
    }

    public List<Paotui> loading(Integer page){
        paotuiList=paotuiDao.loading();
        page=page*10;
        if(page+10>paotuiList.size()){
            for(int i=page;i<paotuiList.size();i++){
                paotuiList.get(i).setFormatTime(FormatTimeUtils.format(paotuiList.get(i).getIssueTime()));
            }
            return paotuiList.subList(page,paotuiList.size());
        }
        for(int i=page;i<page+10;i++){
            paotuiList.get(i).setFormatTime(FormatTimeUtils.format(paotuiList.get(i).getIssueTime()));
        }
        return paotuiList.subList(page,page+10);
    }

}
