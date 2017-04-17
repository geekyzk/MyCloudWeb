package com.em248.dao.Uaa;

import com.em248.config.cloudfoundry.AdminClient;
import org.cloudfoundry.uaa.users.CreateUserRequest;
import org.cloudfoundry.uaa.users.CreateUserResponse;
import org.cloudfoundry.uaa.users.Email;
import org.cloudfoundry.uaa.users.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tian on 2017/4/17.
 */

@Repository
public class UaaUserDao {
    AdminClient adminClient;

    @Autowired
    public UaaUserDao(AdminClient adminClient) {
        this.adminClient = adminClient;
    }


    public CreateUserResponse createUser(String password,
                           String familyName,
                           String givenName,
                           String email,
                           String username){
        CreateUserResponse user = adminClient.getUaaClient()
                .users()
                .create(CreateUserRequest
                        .builder()
                        .password(password)
                        .name(Name.builder()
                                .givenName(givenName)
                                .familyName(familyName)
                                .build())
                        .verified(true)
                        .email(Email.builder()
                                .value(email)
                                .primary(true)
                                .build())
                        .active(true)
                        .userName(username)
                        .build()).block();
        return user;
    }
}
