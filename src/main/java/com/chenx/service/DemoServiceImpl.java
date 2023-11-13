package com.chenx.service;

import com.chenx.annotations.MasterDatasource;
import com.chenx.annotations.SlaveDatasource;
import com.chenx.dao.DemoDao;
import com.chenx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl {

    @Autowired
    private DemoDao demoDao;

    @MasterDatasource
    public void insert(User user){
        demoDao.insertUser(user);
    }

    @SlaveDatasource
    public List<User> select(){
        return demoDao.selectUser();
    }
}
