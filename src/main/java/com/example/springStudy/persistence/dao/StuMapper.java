package com.example.springStudy.persistence.dao;

import com.example.springStudy.mybatisAop.entity.User;

public interface StuMapper {
    User selectUserById(Long id);
}
