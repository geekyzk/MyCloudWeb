package com.em248.service;

import org.cloudfoundry.client.v2.users.CreateUserResponse;

/**
 * Created by tian on 2017/4/17.
 */
public interface UserService {

    CreateUserResponse createUser(String password,
                                  String familyName,
                                  String givenName,
                                  String email,
                                  String username);
}
