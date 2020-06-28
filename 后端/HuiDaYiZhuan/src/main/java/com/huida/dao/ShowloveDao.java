package com.huida.dao;

import com.huida.bean.Comment;
import com.huida.bean.Showlove;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowloveDao {
    @Insert("insert into showlove (nickName,openID,target,isShow,reason,avatarUrl,issueTime,finalImgUrl,formatTime)" +
            "values(#{nickName},#{openID},#{target},#{isShow},#{reason},#{avatarUrl},#{issueTime},#{finalImgUrl},#{formatTime})")
    public void insert(Showlove showlove);

    @Select("select * from showlove where isShow=1")
    public List<Showlove> loading();

    @Insert("insert into slcomment(showloveID,commentContent,issueTime) " +
            "values(#{belongID},#{commentContent},#{issueTime})")
    public void insertComment(Comment comment);

    @Select("select * from slcomment where showloveID=#{showloveID} order by commentid desc")
    public List<Comment> getComment(Integer showloveID);
}
