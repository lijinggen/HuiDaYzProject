package com.huida.dao;

import com.huida.bean.Paotui;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaotuiDao {
    @Select("select * from Paotui order by paotuiID desc")
    public List<Paotui> loading();

    @Insert("Insert into Paotui(openID,nickName,avatarUrl,issueTime,phone,wechat,price,qujianPlace,qujianTime,songdaPlace,songdaTime,imageUrl,classify)" +
            "values(#{openID},#{nickName},#{avatarUrl},#{issueTime},#{phone},#{wechat},#{price},#{qujianPlace},#{qujianTime},#{songdaPlace},#{songdaTime},#{imageUrl},#{classify})")
    public void issuePaotui(Paotui paotui);
}
