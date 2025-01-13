package com.example.springStudy.transactional;

import com.example.springStudy.persistence.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Lazy
    private UserService userService;

    // 自定义异常
    static class  CustomException extends Exception{}

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insertUser(User user) throws CustomException {
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("active: {}, transactionName: {}", active, transactionName);
        // save user
        userMapper.insert(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(User user) throws CustomException {
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("active: {}, transactionName: {}", active, transactionName);
        userMapper.insert(new User("bob", "23"));
        userService.insertUser(user);
//        throw new CustomException();
    }

    @Transactional
    public int updateUser(User user) {
        // update user
        return userMapper.updateById(user);
    }
}
