package com.example.springStudy.transactional;

public interface UserService {
    void insertUser(User user) throws UserServiceImpl.CustomException;
    int updateUser(User user);
    void insert(User user) throws UserServiceImpl.CustomException;
}
