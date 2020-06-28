package com.huida.service;

import com.huida.bean.Renzheng;
import com.huida.dao.RenzhengDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RenzhengService")
public class RenzhengService {
    @Autowired
    RenzhengDao renzhengDao;

    public void insert(Renzheng renzheng){
        renzhengDao.insert(renzheng);
    }

    public Renzheng loading(String openID){
        return renzhengDao.loding(openID);
    }
}
