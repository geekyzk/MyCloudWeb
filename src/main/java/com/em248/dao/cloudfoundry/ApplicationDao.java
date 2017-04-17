package com.em248.dao.cloudfoundry;

import com.em248.config.cloudfoundry.AdminClient;
import com.em248.config.cloudfoundry.UserClient;
import com.em248.entity.CloudFoundryPage;
import org.cloudfoundry.client.v2.OrderDirection;
import org.cloudfoundry.client.v2.applications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tian on 2017/4/17.
 */
@Repository
public class ApplicationDao {

    private UserClient userClient;
    private AdminClient adminClient;

    @Autowired
    public ApplicationDao(UserClient userClient,AdminClient adminClient) {
        this.userClient = userClient;
        this.adminClient = adminClient;
    }

    public List<ApplicationResource> getApplicationsBySpace(CloudFoundryPage page,String spaceGuid){
        ListApplicationsResponse listApplicationsResponse = userClient.getCloudFoundryClient()
                .applicationsV2()
                .list(ListApplicationsRequest
                        .builder()
                        .spaceId(spaceGuid)
                        .page(page.getPage())
                        .resultsPerPage(page.getResultsPerPage())
                        .orderDirection(page.getOrderDirection().equals("asc") ?OrderDirection.ASCENDING : OrderDirection.DESCENDING)
                        .build())
                .block();
        return listApplicationsResponse.getResources();
    }



    public CopyApplicationResponse copyApplicationResource(String targetAppId,String sourceAppId){
        CopyApplicationResponse copyApplicationResponse = adminClient.getCloudFoundryClient()
                .applicationsV2()
                .copy(CopyApplicationRequest
                        .builder()
                        .applicationId(targetAppId)
                        .sourceApplicationId(sourceAppId)
                        .build())
                .block();
        return copyApplicationResponse;
    }

}
