package com.example.springStudy.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public void combineMethod() throws UserServiceImpl.CustomException {
        log.info("there is combineMethod");
        userService.insert(new User("jack", "18"));
    }
}
