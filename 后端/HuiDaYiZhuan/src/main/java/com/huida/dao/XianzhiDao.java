package com.huida.dao;

import com.huida.bean.Comment;
import com.huida.bean.Xianzhi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XianzhiDao {
    @Insert("insert into xianzhi (formatTime,openID,title,introduce,contact,wechat,finalImgUrl,watchTimes,hownew,classify,location,avatarUrl,nickName,price,issueTime) " +
            "values(#{formatTime},#{openID},#{title},#{introduce},#{contact},#{wechat},#{finalImgUrl},#{watchTimes},#{hownew},#{classify},#{location},#{avatarUrl},#{nickName},#{price},#{issueTime})")
    public void insert(Xianzhi xianzhi);

    @Select("Select * from xianzhi order by xianzhiID desc")
    public List<Xianzhi> loading();

    @Update("Update xianzhi set watchTimes=watchTimes+1 where xianzhiID=#{xianzhiID}")
    public void setWatchTimes(Integer xianzhiID);

    @Insert("insert into xzcomment(xianzhiID,commentContent,issueTime) " +
            "values(#{belongID},#{commentContent},#{issueTime})")
    public void insertComment(Comment comment);

    @Select("select * from xzcomment where xianzhiID=#{xianzhiID} order by commentid desc")
    public List<Comment> getComment(Integer xianzhiID);
}
