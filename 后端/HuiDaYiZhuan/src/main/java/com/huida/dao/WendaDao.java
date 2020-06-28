package com.huida.dao;

import com.huida.bean.Wenda;
import com.huida.bean.WendaComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WendaDao {
    @Insert("insert into wenda(openID,avatarUrl,nickName,finalImgUrl,watchTimes,question,detail,issueTime) " +
            "values(#{openID},#{avatarUrl},#{nickName},#{finalImgUrl},#{watchTimes},#{question},#{detail},#{issueTime})")
    public void issueWenda(Wenda wenda);

    @Select("SELECT * from wenda ORDER BY wendaID desc LIMIT #{page},10 ")
    public List<Wenda> loding(int page);

    @Insert("Insert into wdcomment(wendaID,commentContent,issueTime,admireTimes,openID,avatarUrl,nickName) " +
            "values (#{belongID},#{commentContent},#{issueTime},#{admireTimes},#{openID},#{avatarUrl},#{nickName})")
    public void issueComment(WendaComment wendaComment);

    @Select("Select * from wdcomment where wendaID=#{wendaID} order by commentid desc")
    public List<WendaComment> loadingComment(Integer wendaID);
}
