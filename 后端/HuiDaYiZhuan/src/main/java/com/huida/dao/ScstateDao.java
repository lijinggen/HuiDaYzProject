package com.huida.dao;

import com.huida.bean.Scstate;
import com.huida.bean.ScstateComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScstateDao {
    @Insert("insert into scstate (openID,admireTimes,finalImageList,content,issueTime,isTop,nickName,avatarUrl)" +
            "values (#{openID},#{admireTimes},#{finalImageList},#{content},#{issueTime},#{isTop},#{nickName},#{avatarUrl})")
    public void issueScstate(Scstate scstate);

    @Select("select * from scstate order by scstateID desc")
    public List<Scstate> loading();

    @Insert("insert into scComment(answerOpenID,answerNickName,answerAvatarUrl,targetOpenID,targetNickName,targetAvatarUrl,scstateID,issueTime,content)" +
            "values(#{answerOpenID},#{answerNickName},#{answerAvatarUrl},#{targetOpenID},#{targetNickName},#{targetAvatarUrl},#{scstateID},#{issueTime},#{content})")
    public void issueComment(ScstateComment scstateComment);

    @Select("select * from scComment where scstateID=#{scstateID}")
    public List<ScstateComment> getComment(Integer scstateID);
}
