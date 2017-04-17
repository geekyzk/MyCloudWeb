package com.em248.dao.cloudfoundry;

import com.em248.config.cloudfoundry.UserClient;
import org.cloudfoundry.client.v2.users.CreateUserRequest;
import org.cloudfoundry.client.v2.users.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
public class UserDao {

    UserClient userClient;

    @Autowired
    public UserDao(UserClient userClient) {
        this.userClient = userClient;
    }


    public CreateUserResponse createUser(String uaaId){
        CreateUserResponse userResponse = userClient.getCloudFoundryClient()
                .users()
                .create(CreateUserRequest
                        .builder()
                        .uaaId(uaaId)
                        .build()).block();
        return userResponse;
    }
}
