package com.em248.dao.cloudfoundry;

import com.em248.config.cloudfoundry.UserClient;
import org.cloudfoundry.client.v2.buildpacks.BuildpackResource;
import org.cloudfoundry.client.v2.buildpacks.ListBuildpacksRequest;
import org.cloudfoundry.client.v2.buildpacks.ListBuildpacksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
public class BuildpackDao {


    UserClient userClient;


    @Autowired
    public BuildpackDao(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<BuildpackResource> getBuildpacks(){
        ListBuildpacksResponse listBuildpacksResponse = userClient.getCloudFoundryClient()
                .buildpacks()
                .list(ListBuildpacksRequest
                        .builder()
                        .build())
                .block();
        return listBuildpacksResponse.getResources();
    }
}
