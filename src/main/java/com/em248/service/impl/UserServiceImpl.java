package com.em248.service.impl;

import com.em248.dao.Uaa.UaaUserDao;
import com.em248.dao.cloudfoundry.UserDao;
import com.em248.service.UserService;
import org.cloudfoundry.client.v2.users.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tian on 2017/4/17.
 */
@Service
public class UserServiceImpl implements UserService {

    UaaUserDao uaaUserDao;
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UaaUserDao uaaUserDao, UserDao userDao) {
        this.uaaUserDao = uaaUserDao;
        this.userDao = userDao;
    }

    @Override
    public CreateUserResponse createUser(String password, String familyName, String givenName, String email, String username) {
        org.cloudfoundry.uaa.users.CreateUserResponse uaaUserInfo = uaaUserDao.createUser(password, familyName, givenName, email, username);
        CreateUserResponse userResponse = userDao.createUser(uaaUserInfo.getId());
        return userResponse;
    }
}
