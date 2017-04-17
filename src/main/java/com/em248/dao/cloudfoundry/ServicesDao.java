package com.em248.dao.cloudfoundry;

import com.em248.config.cloudfoundry.UserClient;
import org.cloudfoundry.client.v2.services.ListServicesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
public class ServicesDao {


    UserClient userClient;

    @Autowired
    public ServicesDao(UserClient userClient) {
        this.userClient = userClient;
    }

    public void getServices(){
//        userClient.getCloudFoundryClient()
    }
}
