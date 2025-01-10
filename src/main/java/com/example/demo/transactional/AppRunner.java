package com.example.demo.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Override
    public void run(String... args) throws Exception {

        userController.combineMethod();
//            User user = new User();
//            user.setName("jack");
//            user.setAge("18");
//            userService.insertUser(user);
//            log.info("insert user res: {}", user);

//            User user1 = new User();
//            user1.setName("marry");
//            user1.setAge("22");
//            userService.insertUser(user1);
//            log.info("insert user1 res: {}", user1);
    }
}
