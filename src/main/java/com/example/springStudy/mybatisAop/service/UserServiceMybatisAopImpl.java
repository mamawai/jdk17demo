package com.example.springStudy.mybatisAop.service;

import com.example.springStudy.mybatisAop.entity.User;
import com.example.springStudy.persistence.dao.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMybatisAopImpl implements UserServiceMybatisAop {

    @Autowired
    StuMapper stuMapper;

    @Override
    public User getUserById(long id) {
        User user = stuMapper.selectUserById(id);
        System.out.println(user);
        return user;
    }
}
