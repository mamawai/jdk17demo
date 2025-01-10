package com.example.demo.springAop;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService{
    public List<User> findUserList() {
        System.out.println("find user list");
        return Collections.singletonList(new User("mmy", 18));
    }

    /**
     * add user
     */
    public void addUser() {
        // do something
        System.out.println("add user");
    }
}
