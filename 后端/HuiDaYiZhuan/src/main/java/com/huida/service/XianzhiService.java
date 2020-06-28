package com.huida.service;

import com.huida.bean.Comment;
import com.huida.bean.Xianzhi;
import com.huida.dao.XianzhiDao;
import com.huida.utils.FormatTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("XianzhiService")
public class XianzhiService {
    @Autowired
    private XianzhiDao xianzhiDao;
    public static List<Xianzhi> xianzhiList=null;
    public static List<Xianzhi> searchList=null;

    public void insert(Xianzhi xianzhi){
        System.out.println(xianzhi);
        xianzhiDao.insert(xianzhi);
    }

    public List<Xianzhi> loading(int page){
        xianzhiList=xianzhiDao.loading();
        page=page*10;
        if(page+10>xianzhiList.size()){
            return xianzhiList.subList(page,xianzhiList.size());
        }
        return xianzhiList.subList(page,page+10);
    }

    public List<Xianzhi> search(String target){
        if(searchList==null||searchList.size()>0){
            searchList=new ArrayList<>();
        }
        if(xianzhiList==null){
            return null;
        }
        for(int i=0;i<xianzhiList.size();i++){
            if(xianzhiList.get(i).getTitle().contains(target)){
                searchList.add(xianzhiList.get(i));
            }
        }
        return loadTempList(0);
    }

    public List<Xianzhi> loadTempList(Integer page){
        if(searchList.size()==0){
            return null;
        }
        page=page*10;
        if(page+10>searchList.size()){
            return searchList.subList(page,searchList.size());
        }
        return searchList.subList(page,page+10);
    }

    public void setWatchTimes(Integer xianzhiID){
        xianzhiDao.setWatchTimes(xianzhiID);
    }

    public void insertComment(Comment comment){
        xianzhiDao.insertComment(comment);
    }

    public List<Comment> getComment(Integer xianzhiID){
        List<Comment> commentList=xianzhiDao.getComment(xianzhiID);
        for(int i=0;i<commentList.size();i++){
            commentList.get(i).setFormatTime(FormatTimeUtils.format(commentList.get(i).getIssueTime()));
        }
        return commentList;
    }
}
