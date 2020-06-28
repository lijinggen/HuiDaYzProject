package com.huida.service;

import com.huida.bean.Comment;
import com.huida.bean.Showlove;
import com.huida.dao.ShowloveDao;
import com.huida.utils.FormatTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ShowloveService")
public class ShowloveService {

    @Autowired
    private ShowloveDao showloveDao;

    public static List<Showlove> showlovesList;

    public void insert(Showlove showlove) {
        showloveDao.insert(showlove);
    }

    public List<Showlove> loading() {
        showlovesList = showloveDao.loading();
        return showlovesList;
    }

    public void insertComment(Comment comment) {
        showloveDao.insertComment(comment);
    }

    public List<Comment> getComment(Integer showloveID) {
        List<Comment> commentList=showloveDao.getComment(showloveID);
        for(int i=0;i<commentList.size();i++){
            commentList.get(i).setFormatTime(FormatTimeUtils.format(commentList.get(i).getIssueTime()));
        }
        return commentList;
    }

}
